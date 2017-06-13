package com.kakatoto.imagesearch.presenter.impl;

import com.kakatoto.imagesearch.ui.MainActivity;

/**
 * Created by hwoh on 2017. 6. 12..
 */

public interface IMainContract {
    interface View{
        void setToolBar();
        void setViewPager();
        void setTabLayout();
        void setSearchView();
        void setSuggest(String[] suggest);

    }

    interface Presenter{
        void attatch(View view);
        void deatch();
        void addSuggest(String query);
        void getSuggest();
    }
}
