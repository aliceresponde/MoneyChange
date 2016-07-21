package com.huge.app.moneychange.main.event;

import com.huge.app.moneychange.api.LatestCuerrencyResponse;

/**
 * Created by alice on 7/20/16.
 */
public class MainEvent {

    private int type;
    private String error;
    private LatestCuerrencyResponse.Rates rates;

    public final static int GET = 0;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LatestCuerrencyResponse.Rates getRates() {
        return rates;
    }

    public void setRates(LatestCuerrencyResponse.Rates rates) {
        this.rates = rates;
    }
}
