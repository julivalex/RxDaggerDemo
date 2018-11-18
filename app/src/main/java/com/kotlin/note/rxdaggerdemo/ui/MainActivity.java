package com.kotlin.note.rxdaggerdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kotlin.note.rxdaggerdemo.R;
import com.kotlin.note.rxdaggerdemo.application.FlowerApplication;
import com.kotlin.note.rxdaggerdemo.service.FlowerService;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Inject
    FlowerService flowerService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolveDependency();

        ButterKnife.bind(MainActivity.this);
    }

    public void resolveDependency() {
        ((FlowerApplication)getApplication())
                .getApiComponent()
                .inject(this);
    }
}
