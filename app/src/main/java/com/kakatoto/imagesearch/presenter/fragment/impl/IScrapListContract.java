package com.kakatoto.imagesearch.presenter.fragment.impl;

import com.kakatoto.imagesearch.adapter.ImageListRecyclerAdapter;
import com.kakatoto.imagesearch.adapter.ScrapListRecyclerAdapter;
import com.kakatoto.imagesearch.ui.MainActivity;



public interface IScrapListContract {
    interface View{
        void setRecycler();

        void showDeleteScrap(int pos);
    }

    interface Presenter{
        void attatch(View view);
        void deatch();
        void setAdapter(ScrapListRecyclerAdapter adapter);
        void reqeustScrapList();
        void onDeleteScrap(int pos);
    }

}
