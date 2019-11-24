package com.sobri.app.model.validation;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstField;
    private String secondField;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstField = constraintAnnotation.first();
        this.secondField = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, this.firstField);
            final Object secondObj = BeanUtils.getProperty(value, this.secondField);
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
