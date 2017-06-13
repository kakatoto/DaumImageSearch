package com.kakatoto.imagesearch.presenter.fragment.impl;

import com.cunoraz.tagview.Tag;
import com.kakatoto.imagesearch.adapter.ImageListRecyclerAdapter;
import com.kakatoto.imagesearch.ui.MainActivity;

import java.util.List;

/**
 * Created by hwoh on 2017. 6. 12..
 */

public interface IImageListContract {
    interface View {
        void addSearchTag(String query);

        void setTagLayout();

        void setRecycler();

        List<Tag> getTagList();

        void showLoding();

        void hideLoding();
    }

    interface Presenter {
        void attatch(View view);

        void deatch();

        void setAdapter(ImageListRecyclerAdapter adapter);

        void getList(boolean isRefresh);

        void reqeustImageList(int page, String query);

        String setQuery();

        int countCheck(String total);

        void onRefesh();

        void onLoadMore();

    }
}
