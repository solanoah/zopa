package com.zopa.dev;

import com.zopa.dev.Exceptions.InsufficientOfferException;
import com.zopa.dev.Exceptions.InvalidRequestAmountException;
import com.zopa.dev.contracts.CalculationService;
import com.zopa.dev.contracts.OfferService;
import com.zopa.dev.contracts.ValidationService;
import com.zopa.dev.model.Loan;
import com.zopa.dev.model.Offer;
import com.zopa.dev.service.CsvOfferService;
import com.zopa.dev.service.LoanValidationService;
import com.zopa.dev.service.QuoteCalculationService;

import java.io.IOException;
import java.util.List;

public class AppEntry {

    // Ideally the following services will be injected
    private static OfferService s_offerService;
    private static ValidationService s_validationService = new LoanValidationService();
    private static CalculationService s_calculationService;

    /**
     * @param args supplied file path and loan amount
     * @throws IOException and InvalidRequestAmountException
     */
    public static void main(String[] args) throws IOException, InvalidRequestAmountException {

        if (args.length < 2 || !s_validationService.isNumeric(args[1])) {
            System.err.println("Incomplete/invalid parameters supplied");
            System.exit(1);
        }

        try {
            Loan loan = new Loan(args[1]);

            // First call is to validate
            s_validationService.Validate(loan);

            // Create and initialize the offer service
            s_offerService = new CsvOfferService(args[0]);

            // Offers should only be obtained if request is valid
            List<Offer> offers = s_offerService.getLoanOffers(loan);

            // Create and initialize the calculation service
            s_calculationService = new QuoteCalculationService(loan, offers);

            // print the result
            System.out.println("Request Amount: £" + String.format("%.0f", loan.getRequestedAmount()));
            System.out.println("Rate: " + String.format("%.1f", s_calculationService.getAverageRate() * 100) + "%");
            System.out.println("Monthly repayment £" + String.format("%.2f", s_calculationService.getMonthlyPayment()));
            System.out.println("Total repayment: £" + String.format("%.2f", s_calculationService.getTotalPayment()));

        } catch (InvalidRequestAmountException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientOfferException e) {
            System.out.println(e.getMessage());
        }

        System.exit(0);
    }
}
