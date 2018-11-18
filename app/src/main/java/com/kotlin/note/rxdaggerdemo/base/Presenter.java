package com.kotlin.note.rxdaggerdemo.base;

public interface Presenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
}
