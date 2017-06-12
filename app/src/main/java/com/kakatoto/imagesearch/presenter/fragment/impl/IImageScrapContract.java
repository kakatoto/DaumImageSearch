package com.kakatoto.imagesearch.presenter.fragment.impl;

import com.kakatoto.imagesearch.ui.MainActivity;

/**
 * Created by darong on 2017. 6. 12..
 */

public interface IImageScrapContract {
    interface View{

    }

    interface Presenter{
        void attatch(View view);
        void deatch();
    }
}
