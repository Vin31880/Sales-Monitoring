package com.project.receiverrest.controller;


import com.project.receiverdomain.entity.DailySalesEntry;
import com.project.receiverdomain.exceptions.NoSalesEntryFoundException;
import com.project.receiverdomain.exceptions.SalesEntryAlreadyExistsException;
import com.project.receiverdomain.model.SalesEntryRequest;
import com.project.receiverdomain.service.DailySalesEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@RequestMapping(value = SalesEntryController.BASE_URL)
@CrossOrigin
public class SalesEntryController {

    private static Logger logger = LoggerFactory.getLogger(SalesEntryController.class);

    public static final String BASE_URL = "/api/v1";
    private static final String PUSH_SALES_URL = "/sales";

    private DailySalesEntryService dailySalesEntryService;


    public SalesEntryController(@Autowired DailySalesEntryService dailySalesEntryService
    ) {
        this.dailySalesEntryService = dailySalesEntryService;
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping(value = PUSH_SALES_URL)
    public ResponseEntity<?> pushSalesEntry(@RequestBody SalesEntryRequest salesEntryRequest, BindingResult bindingResult) throws SalesEntryAlreadyExistsException {

        if (salesEntryRequest == null  || bindingResult.hasErrors()) {
            logger.warn("Invalid salesEntry request object");
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dailySalesEntryService.pushSalesEntry(salesEntryRequest));
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping(value = PUSH_SALES_URL)
    public List<DailySalesEntry> getAllSalesEntry() throws NoSalesEntryFoundException {
        return dailySalesEntryService.getAllSalesEntry();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
