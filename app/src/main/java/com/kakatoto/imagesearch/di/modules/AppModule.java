package com.kakatoto.imagesearch.di.modules;

import android.app.Application;

import com.kakatoto.imagesearch.util.retrofit.RestfulAdapter;
import com.kakatoto.imagesearch.util.retrofit.RestfulInterface;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lee on 2016. 8. 16..
 */
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    public RestfulInterface provideRetrofitInterface() {
        return RestfulAdapter.getInstance();
    }

}
