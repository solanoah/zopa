package com.zopa.dev.service;

import com.zopa.dev.Exceptions.InvalidRequestAmountException;
import com.zopa.dev.contracts.ValidationService;
import com.zopa.dev.model.Loan;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class LoanValidationServiceTest {

    ValidationService validationService = new LoanValidationService();

    @Test(expected = InvalidRequestAmountException.class)
    public void validate_requested_amount_out_of_range_below() throws InvalidRequestAmountException {
        Loan loan = new Loan(BigDecimal.valueOf(990));
        validationService.Validate(loan);
    }

    @Test(expected = InvalidRequestAmountException.class)
    public void validate_requested_amount_out_of_range_above() throws InvalidRequestAmountException {
        Loan loan = new Loan(BigDecimal.valueOf(1501));
        validationService.Validate(loan);
    }

    @Test(expected = InvalidRequestAmountException.class)
    public void validate_requested_amount_not_incremental() throws InvalidRequestAmountException {
        Loan loan = new Loan(BigDecimal.valueOf(1050));
        validationService.Validate(loan);
    }

    @Test
    public void validate_requested_amount_isValid() throws InvalidRequestAmountException {
        Loan loan = new Loan(BigDecimal.valueOf(1200));
        boolean valid = validationService.Validate(loan);
        assertTrue("Requested amount is valid", valid);
    }
}