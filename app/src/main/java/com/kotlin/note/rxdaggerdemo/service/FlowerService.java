package com.kotlin.note.rxdaggerdemo.service;

import com.kotlin.note.rxdaggerdemo.model.FlowerResponse;
import java.util.List;
import retrofit2.http.GET;
import rx.Observable;

public interface FlowerService {
    @GET("/feeds/flowers.json")
    Observable<List<FlowerResponse>> getFlowers();
}
