package com.kakatoto.imagesearch.presenter.fragment;

import android.content.Context;

import com.kakatoto.imagesearch.ApplicationClass;
import com.kakatoto.imagesearch.adapter.ImageListRecyclerAdapter;
import com.kakatoto.imagesearch.model.ScrapBean;
import com.kakatoto.imagesearch.model.SearchResult;
import com.kakatoto.imagesearch.presenter.fragment.impl.IImageListContract;
import com.kakatoto.imagesearch.realm.repo.ScrapRepo;
import com.kakatoto.imagesearch.realm.repo.impl.IScrapRepo;
import com.kakatoto.imagesearch.util.CommonUtil;
import com.kakatoto.imagesearch.util.IConstant;
import com.kakatoto.imagesearch.util.retrofit.RestfulInterface;
import com.kakatoto.imagesearch.model.SearchResult.ChannelBean;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ImageListPresenter implements IImageListContract.Presenter{
    private IImageListContract.View view;
    private ImageListRecyclerAdapter adapter;

    private int page;
    private String query;
    private IImageListContract.ImageScrapListener scrapListener;

    @Inject
    RestfulInterface retroInterface;
    @Inject
    ScrapRepo scrapRepo;

    private ArrayList<ChannelBean.ItemBean> itemList = new ArrayList<>();

    public ImageListPresenter(Context context) {
        this.page = 1;
        this.query = "";
        this.scrapListener = (IImageListContract.ImageScrapListener) context;
        ApplicationClass.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void attatch(IImageListContract.View view) {
        this.view = view;
        this.view.setTagLayout();
        this.view.setRecycler();
    }

    @Override
    public void deatch() {
        this.view = null;
    }

    public void setQuery(String query) {
        this.query = query;
        this.view.addSearchTag(query);
    }

    @Override
    public void setAdapter(ImageListRecyclerAdapter adapter) {
        this.adapter = adapter;
        this.adapter.setItemList(itemList);
        this.adapter.setOnItemSelectListener(new ImageListRecyclerAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(int pos) {
                view.showScrapAlert(pos);
            }
        });

        this.getList(true);
    }

    @Override
    public void onRefesh() {
        getList(true);
    }

    @Override
    public void onLoadMore() {
        if (itemList.size() >= IConstant.LIMIT) {
            getList(false);
        }
    }

    @Override
    public void getList(boolean isRefresh) {
        if (isRefresh) {
            this.page = 1;
            this.itemList.clear();
            this.adapter.notifyDataSetChanged();
        }

        this.query = setQuery();
        if (!CommonUtil.isNull(query))
            this.reqeustImageList(page, query);
    }

    @Override
    public void reqeustImageList(int p, String q) {
        this.view.showLoding();
        final retrofit2.Call<SearchResult> call = retroInterface.requestImageList(IConstant.API_KEY, q, IConstant.LIMIT, p, IConstant.TYPE);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (CommonUtil.isNull(view))
                    return;
                view.hideLoding();
                SearchResult results = response.body();
                if (!CommonUtil.isNull(results)) {
                    ChannelBean bean = results.getChannel();
                    for (ChannelBean.ItemBean item : bean.getItem()) {
                        itemList.add(item);
                    }
                    adapter.notifyDataSetChanged();
                    page = countCheck(bean.getTotalCount());
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                if (CommonUtil.isNull(view))
                    return;
                view.hideLoding();
            }
        });
    }


    @Override
    public int countCheck(String strTotal) {
        if (CommonUtil.isNull(strTotal))
            return 1;

        int total = Integer.parseInt(strTotal);
        page = page < total ? page + 1 : page;

        return page;
    }

    @Override
    public String setQuery() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < view.getTagList().size(); i++) {
            sb.append(view.getTagList().get(i).text);
            if (i != view.getTagList().size() - 1)
                sb.append("+");
        }
        return sb.toString();
    }

    @Override
    public void onScrapImage(int pos) {
        ChannelBean.ItemBean rawData = itemList.get(pos);
        ScrapBean scrapBean = new ScrapBean();
        scrapBean.setThumbnail(rawData.getThumbnail());

        scrapRepo.addScrapObject(scrapBean, new IScrapRepo.OnScrapCallBack() {
            @Override
            public void onSuccess() {
                if (CommonUtil.isNull(view))
                    return;
                scrapListener.onImageScrap();
            }

            @Override
            public void onError(String message) {
                if (CommonUtil.isNull(view))
                    return;
            }
        });
    }
}
