package com.kakatoto.imagesearch.presenter.fragment;

import com.kakatoto.imagesearch.presenter.fragment.impl.IImageScrapContract;
import com.kakatoto.imagesearch.presenter.impl.IMainContract;
import com.kakatoto.imagesearch.ui.MainActivity;

/**
 * Created by darong on 2017. 6. 12..
 */

public class ImageScrapPresenter implements IImageScrapContract.Presenter {
    private IImageScrapContract.View view;

    @Override
    public void attatch(IImageScrapContract.View view) {
        this.view = view;
    }

    @Override
    public void deatch() {
        this.view = null;
    }

}
