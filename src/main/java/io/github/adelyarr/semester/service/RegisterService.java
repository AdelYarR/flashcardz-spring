package io.github.adelyarr.semester.service;

import io.github.adelyarr.semester.entity.User;
import io.github.adelyarr.semester.exception.EmailTakenException;
import io.github.adelyarr.semester.model.RegistrationForm;
import io.github.adelyarr.semester.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long register(RegistrationForm form) {
        String hashedPassword = passwordEncoder.encode(form.getPassword());

        try {
            User user = new User(form.getEmail(), hashedPassword);
            User savedUser = userRepository.save(user);
            Long userId = savedUser.getId();

            log.info("Успешная регистрация пользователя id = {}", userId);
            return userId;
        } catch (DataIntegrityViolationException e) {
            throw new EmailTakenException("Пользователь с таким email уже зарегистрирован");
        }
    }
}
