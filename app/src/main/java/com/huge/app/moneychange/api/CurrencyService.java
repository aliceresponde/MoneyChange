package com.huge.app.moneychange.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alice on 7/20/16.
 */
public interface CurrencyService {
    public final static String BASE ="USD";
    public final static String SYMBOLS ="GBP,EUR,JPY,BRL";

    @GET("latest")
    Call<LatestCuerrencyResponse> search(@Query("base") String base,
                        @Query("symbols") String symbols);

}
