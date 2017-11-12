package com.zopa.dev.model;

public class Loan {

    private final double requestedAmount;

    /**
     * @param requestedAmount
     */
    public Loan(double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Loan(String requestedAmount) {
        this.requestedAmount = Double.parseDouble(requestedAmount);
    }

    /**
     * @return Requested Amount
     */
    public double getRequestedAmount() {
        return requestedAmount;
    }
}
