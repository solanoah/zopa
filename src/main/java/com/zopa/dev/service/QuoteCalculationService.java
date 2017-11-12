package com.zopa.dev.service;

import com.zopa.dev.contracts.CalculationService;
import com.zopa.dev.model.Loan;
import com.zopa.dev.model.Offer;

import java.util.List;

import static com.zopa.dev.constants.QuoteConstant.TERM_MONTHS;

public class QuoteCalculationService implements CalculationService {

    private final Loan loan;
    private final List<Offer> offers;

    /**
     * @param loan
     * @param offers
     */
    public QuoteCalculationService(Loan loan, List<Offer> offers){

        this.loan = loan;
        this.offers = offers;
    }

    /**
     * @return Returns average rate
     */
    @Override
    public double getAverageRate() {
        double averageRate = this.offers.stream().mapToDouble(t -> t.getRate()).average().getAsDouble();

        // set to 1 decimal places
        return Math.round(averageRate * 1000.0)/1000.0;
    }

    /**
     * @return Returns monthly payment
     */
    @Override
    public double getMonthlyPayment() {

        this.offers.forEach(offer -> {
            // monthly offer rate
            double offerMonthlyRate = offer.getRate()/12;
            // Compound interest calculation
            double offerMonthlyPayment = (offer.getNeededAmount() * offerMonthlyRate) / (1 - Math.pow(1 + offerMonthlyRate, -TERM_MONTHS));
            // update offer with monthly repayment
            offer.setMonthlyPayment(offerMonthlyPayment);
        });

        // sum monthly payment for all offers to get total
        double totalMonthly = this.offers.stream().mapToDouble(t -> t.getMonthlyPayment()).sum();

        // set to 2 decimal precision
        return Math.round(totalMonthly * 100.00)/100.00;
    }

    /**
     * @return Returns Total payment
     */
    @Override
    public double getTotalPayment() {
        return this.getMonthlyPayment() * TERM_MONTHS;
    }
}
