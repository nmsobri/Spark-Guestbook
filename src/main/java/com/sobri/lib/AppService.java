package com.sobri.lib;

import spark.Request;
import spark.Response;

import java.util.Set;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ConstraintViolation;

public class AppService {
    private Set<ConstraintViolation<Object>> errors;
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public boolean validate(Object subject) {
        this.errors = this.validator.validate(subject);
        return this.errors.isEmpty();
    }

    public String getMessage() {
        StringBuilder message = new StringBuilder();

        for (ConstraintViolation<Object> violation : this.errors) {
            message.append("<span class='error-msg'>").append(violation.getMessage()).append("</span>");
        }

        return message.toString();
    }
}