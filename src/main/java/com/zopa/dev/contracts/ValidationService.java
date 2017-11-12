package com.zopa.dev.contracts;

import com.zopa.dev.Exceptions.InvalidRequestAmountException;
import com.zopa.dev.model.Loan;

public interface ValidationService {

    /**
     * @param requestedAmountArg
     * @return
     */
    default boolean isNumeric(String requestedAmountArg) {
        return false;
    }

    /**
     * @param loan detail
     * @return sucess/fail
     * @throws InvalidRequestAmountException
     */
    default boolean Validate(Loan loan) throws InvalidRequestAmountException {
        return false;
    }
}
