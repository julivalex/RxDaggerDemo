package com.kotlin.note.rxdaggerdemo.dependencies;

import com.kotlin.note.rxdaggerdemo.service.FlowerService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @AppScope
    @Provides
    FlowerService provideFlowerService(Retrofit retrofit) {
        return retrofit.create(FlowerService.class);
    }
}
