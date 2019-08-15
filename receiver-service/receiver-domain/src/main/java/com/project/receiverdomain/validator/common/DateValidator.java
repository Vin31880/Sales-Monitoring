package com.project.receiverdomain.validator.common;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@Component
public class DateValidator implements ConstraintValidator<ValidateDate, String> {

    public DateValidator() {

    }

    @Override
    public void initialize(ValidateDate validateDate) {

    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.isEmpty(input)) {
            try {
                LocalDate.parse(input);
            } catch (Exception ex) {
                return false;
            }
        }

        return true;
    }

    public boolean isGreaterThan(LocalDate input, LocalDate dateToCompare) {
        return input.compareTo(dateToCompare) > 0;
    }

    public boolean isGreaterThanOrEqual(LocalDate input, LocalDate dateToCompare) {
        return input.compareTo(dateToCompare) >= 0;
    }

    public boolean isBeforeThan(LocalDate input, LocalDate dateToCompare) {
        return input.compareTo(dateToCompare) < 0;
    }

    public boolean isBeforeThanOrEqual(LocalDate input, LocalDate dateToCompare) {
        return input.compareTo(dateToCompare) <= 0;
    }

    public boolean isEqual(LocalDate input, LocalDate dateToCompare) {
        return input.compareTo(dateToCompare) == 0;
    }
}
