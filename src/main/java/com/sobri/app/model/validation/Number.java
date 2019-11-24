package com.sobri.app.model.validation;

import javax.validation.*;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = NumberValidator.class)
@Documented
public @interface Number {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Number[] value();
    }
}
