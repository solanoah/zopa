package com.zopa.dev.contracts;

import com.zopa.dev.Exceptions.InsufficientOfferException;
import com.zopa.dev.model.Loan;
import com.zopa.dev.model.Offer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface OfferService {

    /**
     * @return List of offers
     */
    default List<Offer> getLoanOffers(Loan loan) throws InsufficientOfferException, IOException {
        return new ArrayList<>();
    }

    default List<Offer> getAvailableOffers() throws InsufficientOfferException, IOException {
        return new ArrayList<>();
    }
}
