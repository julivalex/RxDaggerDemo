package com.kotlin.note.rxdaggerdemo.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter implements Presenter {

    private CompositeDisposable compositeDisposable;

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        configureSubscription();
    }

    private CompositeDisposable configureSubscription() {

        if(compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    @Override
    public void onDestroy() {
        unSubscribeAll();
    }

    private void unSubscribeAll() {
        if(compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
