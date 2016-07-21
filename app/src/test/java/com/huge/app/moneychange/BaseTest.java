package com.huge.app.moneychange;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by alice on 7/19/16.
 *  Clase abstracta, con un metodo @before para inicializar los mocks, de ella heredaran todos los test
 *  Requiero permisos de internet, asi que debo
 */
public abstract class BaseTest {
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        //ver si hay internet
        if (RuntimeEnvironment.application != null){
            ShadowApplication shadowApplication = Shadows.shadowOf(RuntimeEnvironment.application);
            shadowApplication.grantPermissions("android.grantPermissions.INTERNET");
        }
    }
}
