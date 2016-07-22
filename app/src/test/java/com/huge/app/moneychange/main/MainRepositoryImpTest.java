package com.huge.app.moneychange.main;

import com.huge.app.moneychange.BaseTest;
import com.huge.app.moneychange.api.CurrencyService;
import com.huge.app.moneychange.api.LatestCuerrencyResponse;
import com.huge.app.moneychange.libs.EventBus;
import com.huge.app.moneychange.main.event.MainEvent;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by alice on 7/21/16.
 */
public class MainRepositoryImpTest extends BaseTest {
    @Mock
    private EventBus eventBus;
    @Mock
    private CurrencyService service;
    @Mock
    private MainEvent event;
    @Mock
    private LatestCuerrencyResponse.Rates rates;

    private ArgumentCaptor<MainEvent> mainEventArgumentCaptor;
    private MainRepositoryImp repository;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new MainRepositoryImp(eventBus, service);
        mainEventArgumentCaptor = ArgumentCaptor.forClass(MainEvent.class);
    }


    @Test
    public void testGetChangeCalled_APIServiceSuccessCall_EventPost() throws Exception {

        when(service.search(CurrencyService.BASE, CurrencyService.SYMBOLS)).thenReturn(buldCall(true, null));
        int nDollar = 5;
        repository.getChange(nDollar);
        verify(service).search(CurrencyService.BASE, CurrencyService.SYMBOLS);
        verify(eventBus).post(mainEventArgumentCaptor.capture());
        MainEvent event = mainEventArgumentCaptor.getValue();
//
        assertEquals(MainEvent.GET, event.getType());
        assertNull(event.getError());
        assertNotNull(event.getRates());
        assertNotNull(event.getnDollars());
        assertEquals(rates, event.getRates());

    }

    @Test
    public void testGetChangeCalled_APIServiceFailureCall_EventPost() throws Exception {
        String error = "error";
        when(service.search(CurrencyService.BASE, CurrencyService.SYMBOLS)).thenReturn(buldCall(false, error));
        int nDollar = 5;
        repository.getChange(nDollar);
        verify(service).search(CurrencyService.BASE, CurrencyService.SYMBOLS);
        verify(eventBus).post(mainEventArgumentCaptor.capture());
        MainEvent event = mainEventArgumentCaptor.getValue();
//
        assertEquals(MainEvent.GET, event.getType());
        assertNotNull(event.getError());
        assertNull(event.getRates());
        assertNotNull(event.getnDollars());
        assertEquals(error, event.getError());


    }

    /**
     * Ficty call
     *
     * @param success
     * @param errorMsg
     * @return
     */
    private Call<LatestCuerrencyResponse> buldCall(final boolean success, final String errorMsg) {
        Call<LatestCuerrencyResponse> respose = new Call<LatestCuerrencyResponse>() {
            @Override
            public Response<LatestCuerrencyResponse> execute() throws IOException {
                Response<LatestCuerrencyResponse> result = null;
                if (success) {
                    LatestCuerrencyResponse response = new LatestCuerrencyResponse();
                    response.setRates(rates);
                    response.setBase(CurrencyService.BASE);
                    response.setDate("2015-05-03");
                    result = Response.success(response);

                } else {
                    result = Response.error(null, null);
                }
                return result;
            }

            @Override
            public void enqueue(Callback<LatestCuerrencyResponse> callback) {
                if (success) {
                    try {
                        callback.onResponse(this, execute());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callback.onFailure(this, new Throwable(errorMsg));
                }
            }

            @Override
            public boolean isExecuted() {
                return true;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<LatestCuerrencyResponse> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };

        return respose;
    }


}
