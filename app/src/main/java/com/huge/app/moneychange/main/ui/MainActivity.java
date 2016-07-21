package com.huge.app.moneychange.main.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.huge.app.moneychange.App;
import com.huge.app.moneychange.R;
import com.huge.app.moneychange.api.LatestCuerrencyResponse;
import com.huge.app.moneychange.main.MainPresenter;
import com.huge.app.moneychange.main.di.MainComponent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.inputAmount)
    EditText inputAmount;
    @BindView(R.id.btn_change)
    Button btnChange;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.chart)
    BarChart chart;

    private MainPresenter presenter;
    private MainComponent component;
    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupInjection();
        presenter.onCreate();
    }

    private void setupInjection() {
        app = (App) getApplication();
        component = app.getMainComponent(this);
        presenter = component.getPresenter();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    //    ===========================================MainView=========================================
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void showUIElements() {
        inputAmount.setEnabled(true);
        btnChange.setEnabled(true);
    }

    @Override
    public void hideUIElements() {
        inputAmount.setEnabled(false);
        btnChange.setEnabled(false);
    }

    @Override
    public void displayChange(LatestCuerrencyResponse.Rates change) {
        if (change != null) {
            int nDollars = Integer.parseInt(inputAmount.getText().toString());
            ArrayList<BarEntry> entries = getEntries(change, nDollars);
            BarDataSet dataSet = new BarDataSet(entries, "# of Dollars");
            ArrayList<String> labels = getLabels();
            BarData data = new BarData(labels, dataSet);
            chart.setData(data);

            chart.setDescription("Currency Change ($US)");
            chart.animateXY(2000, 2000);
            chart.setVisibility(View.VISIBLE);
            chart.invalidate();
        }
    }


    @Override
    public void onGetCurrencyError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    private ArrayList<BarEntry> getEntries(LatestCuerrencyResponse.Rates change, int nDollars) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry( (float) change.getbRL() * nDollars ,0));
        entries.add(new BarEntry( (float) change.getgBP()* nDollars,1));
        entries.add(new BarEntry( (float) change.getjPY()* nDollars,2));
        entries.add(new BarEntry( (float) change.geteUR()* nDollars,3));
        return entries;
    }


    private ArrayList<String> getLabels() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add(LatestCuerrencyResponse.Rates.BRL_NAME);
        xAxis.add(LatestCuerrencyResponse.Rates.GBP_NAME);
        xAxis.add(LatestCuerrencyResponse.Rates.JPY_NAME);
        xAxis.add(LatestCuerrencyResponse.Rates.EUR_NAME);
        return xAxis;
    }

    //    ============================================================================================
    @OnClick(R.id.btn_change)
    public void getChange(){
        Log.i("main", "click");

        String strAmount = inputAmount.getText().toString();
        if (strAmount.isEmpty()){
            inputAmount.setError(getString( R.string.main_error_empty_amount));
        }else {
            showProgress();
            hideUIElements();
            presenter.getChange(Integer.parseInt(strAmount));
        }
    }
}
