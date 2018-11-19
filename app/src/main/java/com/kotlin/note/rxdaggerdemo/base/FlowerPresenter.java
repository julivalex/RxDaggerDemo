package com.kotlin.note.rxdaggerdemo.base;

import com.kotlin.note.rxdaggerdemo.model.FlowerResponse;
import com.kotlin.note.rxdaggerdemo.service.FlowerViewInterface;

import java.util.List;

import rx.Observer;

public class FlowerPresenter extends BasePresenter implements Observer<List<FlowerResponse>> {

    private FlowerViewInterface viewInterface;

    public FlowerPresenter(FlowerViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    @Override
    public void onCompleted() {
        viewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        viewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(List<FlowerResponse> flowerResponses) {
        viewInterface.onFlowers(flowerResponses);
    }

    public void fetchFlowers() {
        unSubscribeAll();
        subscribe(viewInterface.getFlowers(), FlowerPresenter.this);
    }
}
