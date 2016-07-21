package com.huge.app.moneychange.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alice on 7/20/16.
 * http://api.fixer.io/latest?base=USD&symbols=GBP,EUR,JPY,BRL
 */
public class CurrencyClient {

    private Retrofit retrofit;
    private final static String BASE_URL ="http://api.fixer.io/";

    public CurrencyClient() {
        this.retrofit =  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;
    }

    public  CurrencyService getCurrencyService(){
        return this.retrofit.create(CurrencyService.class);
    }
}
