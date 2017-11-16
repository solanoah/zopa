package com.zopa.dev.contracts;

import java.math.BigDecimal;

public interface CalculationService {
    /**
     * @return Returns average rate
     */
    double getAverageRate();

    /**
     * @return Returns monthly payment
     */
    BigDecimal getMonthlyPayment();

    /**
     * @return Returns Total payment
     */
    BigDecimal getTotalPayment();
}
