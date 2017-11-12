package com.zopa.dev.contracts;

public interface CalculationService {
    /**
     * @return Returns average rate
     */
    double getAverageRate();

    /**
     * @return Returns monthly payment
     */
    double getMonthlyPayment();

    /**
     * @return Returns Total payment
     */
    double getTotalPayment();
}
