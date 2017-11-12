package com.zopa.dev.model;


public class Offer implements Comparable<Offer> {

    private final String lender;
    private final double rate;
    private final double availableAmount;
    private double neededAmount;
    private double monthlyPayment;

    /**
     * @param lender
     * @param rate
     * @param availableAmount
     */
    public Offer(String lender, double rate, double availableAmount) {
        this.lender = lender;
        this.rate = rate;
        this.availableAmount = availableAmount;
    }

    public Offer() {

        lender = null;
        rate = 0;
        availableAmount = 0;
    }

    @Override
    public String toString() {
        return this.getLender() + ' ' + this.getRate() + ' ' + this.getAvailableAmount();
    }

    /**
     * @return Lender
     */
    public String getLender() {
        return lender;
    }

    /**
     * @return Loan rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @return Available amount
     */
    public double getAvailableAmount() {
        return availableAmount;
    }

    @Override
    public int compareTo(Offer o) {
        if (this.getRate() == o.getRate())
            return 0;
        else
            return this.getRate() > o.getRate() ? 1 : -1;
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Offer objOffer = (Offer) obj;

        return this.getRate() == objOffer.getRate() &&
                this.getAvailableAmount() == objOffer.getAvailableAmount() &&
                this.getLender() == objOffer.getLender();
    }

    /**
     * @param neededAmount
     */
    public void setNeededAmount(double neededAmount) {
        this.neededAmount = neededAmount;
    }

    /**
     * @return the needed amount
     */
    public double getNeededAmount() {
        return neededAmount;
    }

    /**
     * @param monthlyPayment set the monthly payment
     */
    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    /**
     * @return returns the monthly payment
     */
    public double getMonthlyPayment() {
        return monthlyPayment;
    }
}
