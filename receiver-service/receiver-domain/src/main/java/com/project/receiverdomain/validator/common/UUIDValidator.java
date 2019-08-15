package com.project.receiverdomain.validator.common;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


@Component
public class UUIDValidator implements ConstraintValidator<ValidateUUID, String> {

    public UUIDValidator(){

    }
    @Override
    public void initialize(ValidateUUID validateUuid) {

    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isEmpty(input))
            return true;

        boolean result = Pattern.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}", input);

        return result;
    }
}
