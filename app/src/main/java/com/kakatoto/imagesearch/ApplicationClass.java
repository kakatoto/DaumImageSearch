package com.kakatoto.imagesearch;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.kakatoto.imagesearch.di.components.AppComponent;
import com.kakatoto.imagesearch.di.components.DaggerAppComponent;
import com.kakatoto.imagesearch.di.modules.AppModule;

/**
 * Created by darong on 2017. 6. 12..
 */

public class ApplicationClass extends Application {

    protected AppComponent appComponent;
    private static volatile ApplicationClass instance = null;

    public static ApplicationClass getInstance() {
        if (instance == null)
            throw new IllegalStateException("this application does not inherit ApplicationClass");
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Stetho.initializeWithDefaults(this);
        initAppComponent();
    }

    private void initAppComponent() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
