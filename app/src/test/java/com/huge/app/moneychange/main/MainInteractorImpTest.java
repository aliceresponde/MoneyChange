package com.huge.app.moneychange.main;

import com.huge.app.moneychange.BaseTest;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

/**
 * Created by alice on 7/21/16.
 */
public class MainInteractorImpTest extends BaseTest {

    @Mock
    private MainRepository repository;
    private MainInteractorImp interactor;



    @Override
    public void setUp() throws Exception {
        super.setUp();
        interactor = new MainInteractorImp(repository);
    }

    @Test
    public void testExcecute_callRespository() throws Exception {
        int mDolar = 0;
        interactor.getChange(mDolar);
        verify(repository).getChange(mDolar);
    }
}
