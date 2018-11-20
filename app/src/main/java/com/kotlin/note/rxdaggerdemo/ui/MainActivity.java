package com.kotlin.note.rxdaggerdemo.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.kotlin.note.rxdaggerdemo.R;
import com.kotlin.note.rxdaggerdemo.application.FlowerApplication;
import com.kotlin.note.rxdaggerdemo.base.FlowerPresenter;
import com.kotlin.note.rxdaggerdemo.model.FlowerAdapter;
import com.kotlin.note.rxdaggerdemo.model.FlowerResponse;
import com.kotlin.note.rxdaggerdemo.service.FlowerService;
import com.kotlin.note.rxdaggerdemo.service.FlowerViewInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity
        implements FlowerViewInterface, FlowerAdapter.FlowerClickListener {

    @Inject
    FlowerService flowerService;

    private RecyclerView recyclerView;
    private FlowerPresenter presenter;
    private ProgressDialog progressDialog;
    private FlowerAdapter flowerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolveDependency();

        recyclerView = findViewById(R.id.recyclerView);

        configView();
        presenter = new FlowerPresenter(this);
        presenter.onCreate();
    }

    private void configView() {
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        flowerAdapter = new FlowerAdapter(this, getLayoutInflater());
        recyclerView.setAdapter(flowerAdapter);
    }

    public void resolveDependency() {
        ((FlowerApplication) getApplication())
                .getApiComponent()
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
        presenter.fetchFlowers();
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Downloading list");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
    }

    @Override
    public void onCompleted() {
        progressDialog.dismiss();
    }

    @Override
    public void onError(String message) {
        progressDialog.dismiss();
        Log.e("Error_log", message);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFlowers(List<FlowerResponse> flowerResponses) {
        flowerAdapter.addFlowers(flowerResponses);
    }

    @Override
    public Observable<List<FlowerResponse>> getFlowers() {
        return flowerService.getFlowers();
    }

    @Override
    public void onClick(int position, String name) {
        Toast.makeText(getApplicationContext(), "You clicked on " + name, Toast.LENGTH_LONG)
                .show();
    }
}
