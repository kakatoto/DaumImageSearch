package com.kakatoto.imagesearch.presenter;

import com.kakatoto.imagesearch.presenter.impl.IMainContract;
import com.kakatoto.imagesearch.ui.MainActivity;

/**
 * Created by darong on 2017. 6. 12..
 */

public class MainPresenter implements IMainContract.Presenter {
    private IMainContract.View view;

    @Override
    public void attatch(IMainContract.View view) {
        this.view = view;
        this.view.setToolBar();
        this.view.setViewPager();
        this.view.setTabLayout();
    }

    @Override
    public void deatch() {
        this.view = null;
    }

}
