package com.project.receiverdomain.validator.common;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.math.BigInteger;

@Component
public class NumberValidator implements ConstraintValidator<ValidateNumber, String> {

    @Override
    public void initialize(ValidateNumber validateNumber) {

    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        if (number.matches("[0-9]*\\.?[0-9]*")) {
            return true;
        }
        return false;
    }

}
