package com.sobri.app.model.validation;

import javax.validation.*;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /*First field*/
    String first();

    /*Second field*/
    String second();

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }
}
