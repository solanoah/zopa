package com.zopa.dev.service;

import com.zopa.dev.Exceptions.InvalidRequestAmountException;
import com.zopa.dev.contracts.ValidationService;
import com.zopa.dev.model.Loan;

import java.math.BigDecimal;

import static com.zopa.dev.constants.QuoteConstant.INCREMENT_AMOUNT;
import static com.zopa.dev.constants.QuoteConstant.LOWER_RANGE;
import static com.zopa.dev.constants.QuoteConstant.UPPER_RANGE;

public class LoanValidationService implements ValidationService {

    /**
     * @param requestedAmountArg
     * @return
     * @throws InvalidRequestAmountException
     */
    @Override
    public boolean isNumeric(String requestedAmountArg) {
        return requestedAmountArg != null && requestedAmountArg.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * @param loan detail
     * @return
     * @throws InvalidRequestAmountException
     */
    @Override
    public boolean Validate(Loan loan) throws InvalidRequestAmountException {

        if (loan.getRequestedAmount().compareTo(LOWER_RANGE) < 0 || loan.getRequestedAmount().compareTo(UPPER_RANGE) > 0) {
            throw new InvalidRequestAmountException("The requested amount is out of range 1000 and 15000");
        }

        BigDecimal remainder = loan.getRequestedAmount().remainder(INCREMENT_AMOUNT);

        if (remainder.intValueExact() > 0)
            throw new InvalidRequestAmountException("The requested amount is not incremental of 100");

        return true;
    }
}
