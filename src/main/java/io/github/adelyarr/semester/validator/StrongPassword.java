package io.github.adelyarr.semester.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface StrongPassword {

    String message() default "Пароль должен содержать строчные, заглавные буквы и хотя бы один специальный символ" +
            " (!, @, #, ...)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
