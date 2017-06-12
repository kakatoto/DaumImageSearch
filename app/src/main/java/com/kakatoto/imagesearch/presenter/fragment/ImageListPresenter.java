package com.kakatoto.imagesearch.presenter.fragment;

import com.kakatoto.imagesearch.ApplicationClass;
import com.kakatoto.imagesearch.adapter.ImageListRecyclerAdapter;
import com.kakatoto.imagesearch.model.ImageSearchResult;
import com.kakatoto.imagesearch.presenter.fragment.impl.IImageListContract;
import com.kakatoto.imagesearch.presenter.impl.IMainContract;
import com.kakatoto.imagesearch.ui.MainActivity;
import com.kakatoto.imagesearch.util.CommonUtil;
import com.kakatoto.imagesearch.util.retrofit.RestfulInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by darong on 2017. 6. 12..
 */

public class ImageListPresenter implements IImageListContract.Presenter {
    private IImageListContract.View view;
    private ImageListRecyclerAdapter adapter;

    @Inject
    RestfulInterface retroInterface;


    public ImageListPresenter() {
        ApplicationClass.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void attatch(IImageListContract.View view) {
        this.view = view;
        this.reqeustImageList("");
    }

    @Override
    public void deatch() {
        this.view = null;
    }

    @Override
    public void setAdapter(ImageListRecyclerAdapter adapter) {
        this.adapter = adapter;
        this.adapter.setOnItemSelectListener(new ImageListRecyclerAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(int pos) {

            }
        });
    }

    @Override
    public void reqeustImageList(String query) {
        final retrofit2.Call<ImageSearchResult> call = retroInterface.requestImageList("c2532961eeba8dbe760547021b5b944d", "카카오", 1, 1);
        call.enqueue(new Callback<ImageSearchResult>() {
            @Override
            public void onResponse(Call<ImageSearchResult> call, Response<ImageSearchResult> response) {
                if (CommonUtil.isNull(view))
                    return;
                ImageSearchResult results = response.body();


            }

            @Override
            public void onFailure(Call<ImageSearchResult> call, Throwable t) {
                if (CommonUtil.isNull(view))
                    return;
            }
        });
    }
}
