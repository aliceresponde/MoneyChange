package com.huge.app.moneychange.main.ui;

import com.huge.app.moneychange.api.LatestCuerrencyResponse;

/**
 * Created by alice on 7/20/16.
 */
public interface MainView {
    void showProgress();
    void hideProgress();

    void showUIElements();
    void hideUIElements();

    void displayChange(LatestCuerrencyResponse.Rates change);
    void onGetCurrencyError(String error);
}
