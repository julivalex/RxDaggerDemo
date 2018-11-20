package com.kotlin.note.rxdaggerdemo.service;

import com.kotlin.note.rxdaggerdemo.model.FlowerResponse;

import java.util.List;

import io.reactivex.Observable;

public interface FlowerViewInterface {

    void onCompleted();

    void onError(String message);

    void onFlowers(List<FlowerResponse> flowerResponses);

    Observable<List<FlowerResponse>> getFlowers();
}
