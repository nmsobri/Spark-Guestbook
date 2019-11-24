package com.sobri.app.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberValidator implements ConstraintValidator<Number, String> {
    @Override
    public void initialize(Number constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            return true;
        }
        return value.matches("\\d+");
    }
}
