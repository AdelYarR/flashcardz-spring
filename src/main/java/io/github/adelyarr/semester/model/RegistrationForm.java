package io.github.adelyarr.semester.model;

import io.github.adelyarr.semester.validator.EqualsPasswords;
import io.github.adelyarr.semester.validator.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static io.github.adelyarr.semester.common.ValidationLimits.PASSWORD_MAX;
import static io.github.adelyarr.semester.common.ValidationLimits.PASSWORD_MIN;

@NoArgsConstructor
@Getter
@Setter
@EqualsPasswords(password = "password", passwordConfirm = "passwordConfirm")
public class RegistrationForm {

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = PASSWORD_MIN, max = PASSWORD_MAX, message = "Пароль должен быть от 7 до 32 символов")
    @StrongPassword
    private String password;

    @NotBlank(message = "Подтверждение пароля обязательно")
    @Size(min = PASSWORD_MIN, max = PASSWORD_MAX, message = "Подтверждения пароля должно быть от 7 до 32 символов")
    @StrongPassword
    private String passwordConfirm;
}