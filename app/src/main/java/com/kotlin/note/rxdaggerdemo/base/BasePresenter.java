package com.kotlin.note.rxdaggerdemo.base;

import com.kotlin.note.rxdaggerdemo.model.FlowerResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter extends DisposableObserver<List<FlowerResponse>>
        implements Presenter {

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

    protected void unSubscribeAll() {
        if(compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    protected <F> void subscribe(Observable<F> observable, DisposableObserver<F> observer) {
        Disposable disposable = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribeWith(observer);

        configureSubscription().add(disposable);
    }
}
