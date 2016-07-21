package com.huge.app.moneychange.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alice on 7/20/16.
 */
public class LatestCuerrencyResponse{

    @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("rates")
    @Expose
    private Rates rates;

    /**
     *
     * @return
     * The base
     */
    public String getBase() {
        return base;
    }

    /**
     *
     * @param base
     * The base
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The rates
     */
    public Rates getRates() {
        return rates;
    }

    /**
     *
     * @param rates
     * The rates
     */
    public void setRates(Rates rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "LatestCuerrencyResponse{" +
                "base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }

    public  class Rates {

        public static final String BRL = "BRL";
        public static final String GBP = "GBP";
        public static final String JPY = "JPY";
        public static final String EUR = "EUR";

        public static final String BRL_NAME = "Brazil Reais";
        public static final String GBP_NAME  = "UK Pounds";
        public static final String JPY_NAME = "Japan Yen";
        public static final String EUR_NAME = "EU Euro";


        @SerializedName("BRL")
        @Expose
        private double bRL;
        @SerializedName("GBP")
        @Expose
        private double gBP;
        @SerializedName("JPY")
        @Expose
        private double jPY;
        @SerializedName("EUR")
        @Expose
        private double eUR;

        public double getbRL() {
            return bRL;
        }

        public void setbRL(double bRL) {
            this.bRL = bRL;
        }

        public double getgBP() {
            return gBP;
        }

        public void setgBP(double gBP) {
            this.gBP = gBP;
        }

        public double getjPY() {
            return jPY;
        }

        public void setjPY(double jPY) {
            this.jPY = jPY;
        }

        public double geteUR() {
            return eUR;
        }

        public void seteUR(double eUR) {
            this.eUR = eUR;
        }

        @Override
        public String toString() {
            return "Rates{" +
                    "bRL=" + bRL +
                    ", gBP=" + gBP +
                    ", jPY=" + jPY +
                    ", eUR=" + eUR +
                    '}';
        }
    }
}
