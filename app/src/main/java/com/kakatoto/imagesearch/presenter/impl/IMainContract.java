package com.kakatoto.imagesearch.presenter.impl;

import com.kakatoto.imagesearch.ui.MainActivity;

/**
 * Created by darong on 2017. 6. 12..
 */

public interface IMainContract {
    interface View{
        void setToolBar();
        void setViewPager();
        void setTabLayout();

    }

    interface Presenter{
        void attatch(View view);
        void deatch();
    }
}
