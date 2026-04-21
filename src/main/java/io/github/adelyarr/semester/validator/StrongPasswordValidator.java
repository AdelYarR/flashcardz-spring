package io.github.adelyarr.semester.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    private static final Set<Character> specialChars = new HashSet<>(List.of(
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '?', '.', ',', ':', ';',
            '/', '|', '[', ']', '{', '}'));

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean hasUpper = s.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = s.chars().anyMatch(Character::isLowerCase);
        boolean hasSpecial = s.chars().anyMatch(c -> specialChars.contains((char) c));

        return !s.isBlank() && hasUpper && hasLower && hasSpecial;
    }
}
