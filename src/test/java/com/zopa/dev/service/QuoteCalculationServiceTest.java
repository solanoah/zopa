package com.zopa.dev.service;

import com.zopa.dev.contracts.CalculationService;
import com.zopa.dev.contracts.OfferService;
import com.zopa.dev.model.Loan;
import com.zopa.dev.model.Offer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.Assert.*;

public class QuoteCalculationServiceTest {

    private CalculationService calculationService;

    @Before
    public void setUp() throws Exception {
        String filepath = getClass().getClassLoader().getResource("MarketData.csv").getPath();
        OfferService offerService = new CsvOfferService(filepath);
        Loan loanRequest = new Loan(new BigDecimal("1000.00"));
        List<Offer> offers = offerService.getLoanOffers(loanRequest);

        calculationService = new QuoteCalculationService(loanRequest, offers);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAverageRate() throws Exception {
        // Assert
        assertEquals("The rate should be 0.07", 0.07, calculationService.getAverageRate(), 0);
    }

    @Test
    public void getMonthlyPayment() throws Exception {
        // Assert
        assertEquals("The monthly payment should be 30.88", BigDecimal.valueOf(31).setScale(2, RoundingMode.CEILING), calculationService.getMonthlyPayment());
    }

    @Test
    public void getTotalPayment() throws Exception {
        // Assert
        assertEquals("The total payment should be 1111.68", BigDecimal.valueOf(1116).setScale(2, RoundingMode.CEILING), calculationService.getTotalPayment());
    }
}