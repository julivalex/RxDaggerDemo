package com.kotlin.note.rxdaggerdemo.dependencies;

import com.kotlin.note.rxdaggerdemo.ui.MainActivity;
import dagger.Component;

@AppScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {
    void inject(MainActivity activity);
}
