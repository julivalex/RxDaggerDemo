package com.kotlin.note.rxdaggerdemo.service;

import com.kotlin.note.rxdaggerdemo.model.FlowerResponse;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface FlowerService {
    @GET("/feeds/flowers.json")
    Observable<List<FlowerResponse>> getFlowers();
}
