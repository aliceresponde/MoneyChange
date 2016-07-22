package com.huge.app.moneychange.main;

import com.huge.app.moneychange.BaseTest;
import com.huge.app.moneychange.api.LatestCuerrencyResponse;
import com.huge.app.moneychange.libs.EventBus;
import com.huge.app.moneychange.main.event.MainEvent;
import com.huge.app.moneychange.main.ui.MainView;

import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by alice on 7/21/16.
 */
public class MainPresenterImpTest extends BaseTest {
    @Mock
    private EventBus eventBus;
    @Mock
    private MainView view;
    @Mock
    private MainInteractor interactor;
    @Mock
    private MainEvent event;


    private LatestCuerrencyResponse.Rates rates;
    private  MainPresenterImp presenter;
    int nDollar;

    /**
     * Initialize presenter
     * @throws Exception
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
        presenter = new MainPresenterImp(view, eventBus, interactor);
    }

    @Test
    public void testOncreate_subcribeToEventBus() throws Exception{
        presenter.onCreate();
        verify(eventBus).register(presenter);
    }

    @Test
    public void testOnDestroy_UnsubcribeToEventBus() throws Exception{
        presenter.onDestroy();
        verify(eventBus).unregister(presenter);
        assertNull(presenter.getView());
    }

    @Test
    public void testShowLoading_executeGetChange() throws Exception{
        presenter.getChange(nDollar);

        assertNotNull(presenter.getView());
        verify(view).showProgress();
        verify(view).hideUIElements();
        verify(interactor).getChange(nDollar);
    }

    @Test
    public void testOnEventMain_hasError()throws Exception{
        String error = "error";
        when(event.getError()).thenReturn(error);
        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        verify(view).hideProgress();
        verify(view).showUIElements();
        verify(view).onGetCurrencyError(error);
    }

    @Test
    public void testOnEventMain_callDisplayChangeFor()throws Exception{
        when(event.getType()).thenReturn(MainEvent.GET);
        presenter.onEventMainThread(event);
        assertNotNull(presenter.getView());

        when(event.getRates()).thenReturn(rates);
    }

    @Test
    public  void  testGetView_returnsView()throws Exception {
        assertEquals(view, presenter.getView());
    }


}
