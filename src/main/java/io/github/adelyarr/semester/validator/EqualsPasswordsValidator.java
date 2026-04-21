package io.github.adelyarr.semester.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class EqualsPasswordsValidator implements ConstraintValidator<EqualsPasswords, Object> {

    private String passwordField;
    private String passwordConfirmField;

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object password = new BeanWrapperImpl(o).getPropertyValue(passwordField);
        Object passwordConfirm = new BeanWrapperImpl(o).getPropertyValue(passwordConfirmField);

        return password != null && password.equals(passwordConfirm);
    }

    @Override
    public void initialize(EqualsPasswords constraintAnnotation) {
        passwordField = constraintAnnotation.password();
        passwordConfirmField = constraintAnnotation.passwordConfirm();
    }
}
