package com.kakatoto.imagesearch;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.kakatoto.imagesearch.di.components.AppComponent;
import com.kakatoto.imagesearch.di.components.DaggerAppComponent;
import com.kakatoto.imagesearch.di.modules.AppModule;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;

/**
 * Created by hwoh on 2017. 6. 12..
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

        initRealmConfiguration();
        initAppComponent();
    }

    private void initAppComponent() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    private void initRealmConfiguration() {
        Realm.init(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
