package io.github.adelyarr.semester.service;

import io.github.adelyarr.semester.dto.OAuth2UserInfo;
import io.github.adelyarr.semester.entity.AuthType;
import io.github.adelyarr.semester.entity.User;
import io.github.adelyarr.semester.repository.UserRepository;
import io.github.adelyarr.semester.security.OAuth2UserAuthentication;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OAuth2UserService {

    private final UserRepository userRepository;

    public OAuth2UserAuthentication loadUser(OAuth2UserInfo userInfo) {
        userRepository.findByOauth2Id(userInfo.getSub())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(userInfo.getEmail());
                    newUser.setAuthType(AuthType.GOOGLE);
                    newUser.setOauth2Id(userInfo.getSub());

                    User savedUser = userRepository.saveAndFlush(newUser);
                    log.info("User сохранён в БД с id = {}", savedUser.getId());
                    return savedUser;
                });

        return new OAuth2UserAuthentication(userInfo);
    }
}
