package com.zopa.dev.Exceptions;

public class InsufficientOfferException extends RuntimeException {

    private static final long serialVersionUID = -5836180738846127205L;

    /**
     * @return
     */
    @Override
    public String getMessage() {
        return "It is not possible to provide a quote at this time - Insufficient offers from lenders";
    }
}