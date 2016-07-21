package com.huge.app.moneychange.api;

import com.huge.app.moneychange.BaseTest;
import com.huge.app.moneychange.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;

/**
 * Created by alice on 7/21/16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class CurrencyServiceTest extends BaseTest {

    private CurrencyService service;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        CurrencyClient client = new CurrencyClient();
        service  =  client.getCurrencyService();
    }

    @Test
    public void doSearch_getCurrencyFromBackend() throws IOException {

        //call the service
        Call<LatestCuerrencyResponse> call = service.search(CurrencyService.BASE, CurrencyService.SYMBOLS);
        Response<LatestCuerrencyResponse> callResponse = call.execute();

        assertTrue(callResponse.isSuccess());
        assertNotNull(callResponse.body());
    }

    @Test
    public void doSearch_requestCurrencyFromBackendWhithNotValidBaseCurrency() throws IOException {

        //call the service
        Call<LatestCuerrencyResponse> call = service.search("PESOS", CurrencyService.SYMBOLS);
        Response<LatestCuerrencyResponse> callResponse = call.execute();
        assertEquals("Unprocessable Entity",callResponse.message());
        assertFalse(callResponse.isSuccess());
    }

    @Test
    public void doSearch_getCurrencyFromBackendWithEmptySymbols() throws IOException {

        //call the service
        Call<LatestCuerrencyResponse> call = service.search(CurrencyService.BASE, "");
        Response<LatestCuerrencyResponse> callResponse = call.execute();
        assertTrue(callResponse.isSuccess());
        assertEquals( "OK", callResponse.message());
        assertEquals("Rates{bRL=0.0, gBP=0.0, jPY=0.0, eUR=0.0}" , callResponse.body().getRates().toString() );
    }

    @Test
    public void doSearch_getCurrencyFromBackendWithNoneSymbols() throws IOException {

        //call the service
        Call<LatestCuerrencyResponse> call = service.search(CurrencyService.BASE, null);
        Response<LatestCuerrencyResponse> callResponse = call.execute();
        assertTrue(callResponse.isSuccess());
        assertEquals( "OK", callResponse.message());
        assertNotSame("Rates{bRL=0.0, gBP=0.0, jPY=0.0, eUR=0.0}" , callResponse.body().getRates().toString() );
    }

}
