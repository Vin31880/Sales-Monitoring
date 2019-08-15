package com.project.receiverdomain.validator.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SalesEntryRequestValidator.class)
@Documented
public @interface ValidateSalesEntryRequest {

    String message() default "Invalid sales entry request.";


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
