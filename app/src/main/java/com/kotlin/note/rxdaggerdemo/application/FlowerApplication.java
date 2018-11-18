package com.kotlin.note.rxdaggerdemo.application;

import android.app.Application;

import com.kotlin.note.rxdaggerdemo.dependencies.ApiComponent;
import com.kotlin.note.rxdaggerdemo.dependencies.DaggerApiComponent;
import com.kotlin.note.rxdaggerdemo.dependencies.DaggerNetworkComponent;
import com.kotlin.note.rxdaggerdemo.dependencies.NetworkComponent;
import com.kotlin.note.rxdaggerdemo.dependencies.NetworkModule;
import com.kotlin.note.rxdaggerdemo.model.Constant;

public class FlowerApplication extends Application {

    private ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        apiComponent = DaggerApiComponent
                .builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }

    public NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent
                .builder()
                .networkModule(new NetworkModule(Constant.BASE_URL))
                .build();
    }
}
