package com.kakatoto.imagesearch.di.modules;

import android.app.Application;

import com.kakatoto.imagesearch.realm.repo.SuggestRepo;
import com.kakatoto.imagesearch.util.retrofit.RestfulAdapter;
import com.kakatoto.imagesearch.util.retrofit.RestfulInterface;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;


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


    @Provides
    public SuggestRepo provideSuggestRepo() {
        return new SuggestRepo(Realm.getDefaultInstance());
    }
}
