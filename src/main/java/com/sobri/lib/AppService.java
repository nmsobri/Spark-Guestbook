package com.sobri.lib;

import java.util.Set;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ConstraintViolation;

public class AppService {
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public Pair<Boolean, String> validate(Object subject) {
        Set<ConstraintViolation<Object>> errors = this.validator.validate(subject);
        return new Pair<>(errors.isEmpty(), this.errorString(errors));
    }

    private String errorString(Set<ConstraintViolation<Object>> errors) {
        StringBuilder message = new StringBuilder();

        for (ConstraintViolation<Object> violation : errors) {
            message.append("<span class='error-msg'>").append(violation.getMessage()).append("</span>");
        }

        return message.toString();
    }
}