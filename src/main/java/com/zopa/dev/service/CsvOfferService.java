package com.zopa.dev.service;

import com.zopa.dev.Exceptions.InsufficientOfferException;
import com.zopa.dev.contracts.OfferService;
import com.zopa.dev.model.Loan;
import com.zopa.dev.model.Offer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CsvOfferService implements OfferService {

    private final ArrayList<Offer> offers = new ArrayList<>();
    private final String filepath;

    /**
     * @param filepath
     */
    public CsvOfferService(String filepath) {
        this.filepath = filepath;
    }

    /**
     * @return List of offers
     */
    @Override
    public List<Offer> getLoanOffers(Loan loan) throws IOException, InsufficientOfferException {

        List<Offer> offerList = getAvailableOffers();

        // Calculate available sum
        BigDecimal sumAvailableAmount = offers.stream().map(t -> t.getAvailableAmount()).reduce(BigDecimal.valueOf(0), (a, b) -> a.add(b));

        if (sumAvailableAmount.compareTo(loan.getRequestedAmount()) < 0)
            throw new InsufficientOfferException();
        
        // get all applicable offers to fulfill the requested amount
        BigDecimal limit = new BigDecimal(0);
        ArrayList<Offer> applicableOffers = new ArrayList<>();
        for (Offer offer : offerList) {
            limit = limit.add(offer.getAvailableAmount());

            if (limit.compareTo(loan.getRequestedAmount()) >= 0) {
                offer.setNeededAmount(offer.getAvailableAmount().subtract(limit.subtract(loan.getRequestedAmount())));
                applicableOffers.add(offer);
                break;
            } else {
                offer.setNeededAmount(offer.getAvailableAmount());
                applicableOffers.add(offer);
            }
        }

        return applicableOffers;
    }

    public List<Offer> getAvailableOffers() throws IOException, InsufficientOfferException {

        // process excel file
        processFile();

        // Sorting ensures that we use offer with lowest rate first
        return offers.stream().sorted().collect(Collectors.toList());
    }

    /**
     * @throws IOException
     */
    private void processFile() throws IOException {
        BufferedReader reader = null;

        try {
            File inputFile = new File(this.filepath);
            reader = new BufferedReader(new FileReader(inputFile));

            for (String line; (line = reader.readLine()) != null; ) {
                line = line.trim();
                try {
                    processInputLine(line);
                } catch (IllegalArgumentException e) {}
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    /**
     * @param line lender offer
     * @throws IllegalArgumentException
     */
    private void processInputLine(String line) throws IllegalArgumentException {

        if (line.length() == 0) {
            return;
        }

        String[] splitted = line.split(",");
        if (splitted.length == 3) {
            String lender = splitted[0];
            double rate = Double.parseDouble(splitted[1]);
            BigDecimal amount = new BigDecimal(splitted[2]);

            this.offers.add(new Offer(lender, rate, amount));
        }
    }
}
