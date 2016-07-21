package com.huge.app.moneychange.main;

import com.huge.app.moneychange.BaseTest;
import com.huge.app.moneychange.libs.EventBus;
import com.huge.app.moneychange.main.ui.MainView;

import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.verify;

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
    private  int mDollar;


    private  MainPresenterImp presenter;

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

    @Test void testGetChange() throws Exception{
        presenter.getChange(mDollar);

        assertNotNull(presenter.getView());
        verify(view).showProgress();
        verify(view).hideUIElements();
        verify(interactor).getChange(mDollar);


    }
}
