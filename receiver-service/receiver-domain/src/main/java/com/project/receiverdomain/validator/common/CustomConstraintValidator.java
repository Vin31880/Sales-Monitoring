package com.project.receiverdomain.validator.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public abstract class CustomConstraintValidator<A extends Annotation, T> implements ConstraintValidator<A, T>
{
    @Autowired
    protected NotNullOrEmptyValidator notNullOrEmptyValidator;

    @Autowired
    protected UUIDValidator uuidValidator;

    @Autowired
    protected DateValidator dateValidator;

    @Autowired
    protected NumberValidator numberValidator;

    private ConstraintValidatorContext constraintValidatorContext;

    protected abstract boolean validate (T validationObject, ConstraintValidatorContext constraintValidatorContext);

    @Override
    public boolean isValid (T validationObject, ConstraintValidatorContext constraintValidatorContext)
    {
        this.constraintValidatorContext = constraintValidatorContext;
        constraintValidatorContext.disableDefaultConstraintViolation();

        return validate(validationObject, constraintValidatorContext);
    }

    protected void addMessage (String message, String fieldName)
    {
        Assert.state(constraintValidatorContext != null, "constraintValidatorContext must not be null");
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode(fieldName).addConstraintViolation();
    }

    protected void addMessage (ConstraintValidatorContext constraintValidatorContext, String message, String fieldName)
    {
        Assert.state(constraintValidatorContext != null, "constraintValidatorContext must not be null");
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode(fieldName).addConstraintViolation();
    }
}
