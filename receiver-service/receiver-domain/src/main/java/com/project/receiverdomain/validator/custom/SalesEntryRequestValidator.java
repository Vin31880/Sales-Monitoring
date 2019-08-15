package com.project.receiverdomain.validator.custom;

import com.project.receiverdomain.model.SalesEntryRequest;
import com.project.receiverdomain.validator.common.CustomConstraintValidator;
import com.project.receiverdomain.validator.common.ValidationMessage;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;

@Component
public class SalesEntryRequestValidator extends CustomConstraintValidator<ValidateSalesEntryRequest, SalesEntryRequest> {


    @Override
    protected boolean validate(SalesEntryRequest salesEntryRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (!notNullOrEmptyValidator.isValid(salesEntryRequest.getRetailerName(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_REQUIRED).addPropertyNode("retailer").addConstraintViolation();
            isValid = false;
        }

        if (!notNullOrEmptyValidator.isValid(salesEntryRequest.getBrand(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_REQUIRED).addPropertyNode("brand").addConstraintViolation();
            isValid = false;
        }


        if (!notNullOrEmptyValidator.isValid(salesEntryRequest.getRetailerLocation(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_REQUIRED).addPropertyNode("retailerLocation").addConstraintViolation();
            isValid = false;
        }

        if (!notNullOrEmptyValidator.isValid(salesEntryRequest.getCategory(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_REQUIRED).addPropertyNode("category").addConstraintViolation();
            isValid = false;
        }


        if (!dateValidator.isValid(salesEntryRequest.getShiftDate().toString(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_REQUIRED).addPropertyNode("shiftDate").addConstraintViolation();
            isValid = false;
        }


        if (!notNullOrEmptyValidator.isValid(salesEntryRequest.getShiftSalesValue(), constraintValidatorContext) ||
                !numberValidator.isValid(salesEntryRequest.getShiftSalesValue().toString(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_NUMBER).addPropertyNode("shiftSalesValue").addConstraintViolation();
            isValid = false;
        }

        if (!notNullOrEmptyValidator.isValid(salesEntryRequest.getRetailerId(), constraintValidatorContext) ||
                !numberValidator.isValid(salesEntryRequest.getRetailerId().toString(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_NUMBER).addPropertyNode("retailerId").addConstraintViolation();
            isValid = false;
        }

        if (!notNullOrEmptyValidator.isValid(salesEntryRequest.getDailySalesSummaryId(), constraintValidatorContext) ||
                !numberValidator.isValid(salesEntryRequest.getDailySalesSummaryId().toString(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_NUMBER).addPropertyNode("dailySalesSummaryId").addConstraintViolation();
            isValid = false;
        }

        if (!notNullOrEmptyValidator.isValid(salesEntryRequest.getRetailerLocation(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_REQUIRED).addPropertyNode("retailerLocation").addConstraintViolation();
            isValid = false;
        }

        if (!notNullOrEmptyValidator.isValid(salesEntryRequest.getReceiptProviderCode(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_REQUIRED).addPropertyNode("receiptProviderCode").addConstraintViolation();
            isValid = false;
        }

        if (!notNullOrEmptyValidator.isValid(salesEntryRequest.getMallCode(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_REQUIRED).addPropertyNode("mallCode").addConstraintViolation();
            isValid = false;
        }


        return isValid;
    }

    @Override
    public void initialize(ValidateSalesEntryRequest constraintAnnotation) {

    }
}
