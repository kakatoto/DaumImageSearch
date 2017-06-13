package com.kakatoto.imagesearch.di.components;

import android.app.Activity;


import com.kakatoto.imagesearch.di.modules.AppModule;
import com.kakatoto.imagesearch.presenter.MainPresenter;
import com.kakatoto.imagesearch.presenter.fragment.ImageListPresenter;
import com.kakatoto.imagesearch.presenter.fragment.ScrapListPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(ImageListPresenter presenter);
    void inject(ScrapListPresenter presenter);
    void inject(MainPresenter presenter);

}
