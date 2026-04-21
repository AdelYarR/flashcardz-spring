package io.github.adelyarr.semester.model;

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
public class LoginForm {

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = PASSWORD_MIN, max = PASSWORD_MAX, message = "Пароль должен быть от 7 до 32 символов")
    private String password;
}