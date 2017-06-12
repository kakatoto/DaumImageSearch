package com.kakatoto.imagesearch.di.components;

import android.app.Activity;


import com.kakatoto.imagesearch.di.modules.AppModule;
import com.kakatoto.imagesearch.presenter.fragment.ImageListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lee on 2016. 8. 16..
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(ImageListPresenter presenter);


}
