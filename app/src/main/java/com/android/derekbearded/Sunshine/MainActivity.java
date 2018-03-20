package com.android.derekbearded.Sunshine;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final int WEATHER_ID = 1;

    @BindView(R.id.progressBar) ContentLoadingProgressBar progressBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private MyViewAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(WEATHER_ID, null, new LoaderManager.LoaderCallbacks<List<ForecastDay>>() {
            @NonNull
            @Override
            public Loader<List<ForecastDay>> onCreateLoader(int id, @Nullable Bundle args) {
                if (id != WEATHER_ID){
                    return null;
                }
                return new ForecastLoader(MainActivity.this, getString(R.string.api_key), 21742);
            }

            @Override
            public void onLoadFinished(@NonNull Loader<List<ForecastDay>> loader, List<ForecastDay> data) {
                adapter.setForecastDays(data);
                progressBar.hide();
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoaderReset(@NonNull Loader<List<ForecastDay>> loader) {
                progressBar.show();
                recyclerView.setVisibility(View.GONE);
                adapter.clear();
            }
        }).forceLoad();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyViewAdapter(recyclerView);
        recyclerView.setAdapter(adapter);
    }
}