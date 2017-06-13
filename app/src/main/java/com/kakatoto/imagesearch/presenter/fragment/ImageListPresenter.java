package com.kakatoto.imagesearch.presenter.fragment;

import android.util.Log;

import com.cunoraz.tagview.Tag;
import com.kakatoto.imagesearch.ApplicationClass;
import com.kakatoto.imagesearch.R;
import com.kakatoto.imagesearch.adapter.ImageListRecyclerAdapter;
import com.kakatoto.imagesearch.model.ImageSearchResult;
import com.kakatoto.imagesearch.model.Suggest;
import com.kakatoto.imagesearch.presenter.fragment.impl.IImageListContract;
import com.kakatoto.imagesearch.presenter.impl.IMainContract;
import com.kakatoto.imagesearch.realm.repo.SuggestRepo;
import com.kakatoto.imagesearch.realm.repo.impl.ISuggestRepo;
import com.kakatoto.imagesearch.ui.MainActivity;
import com.kakatoto.imagesearch.util.CommonUtil;
import com.kakatoto.imagesearch.util.IConstant;
import com.kakatoto.imagesearch.util.retrofit.RestfulInterface;
import com.kakatoto.imagesearch.model.ImageSearchResult.ChannelBean;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by hwoh on 2017. 6. 12..
 */

public class ImageListPresenter implements IImageListContract.Presenter {
    private final static String TAG = ImageListPresenter.class.getSimpleName();
    private IImageListContract.View view;
    private ImageListRecyclerAdapter adapter;

    private int page;
    private String query;



    @Inject
    RestfulInterface retroInterface;


    private ArrayList<ChannelBean.ItemBean> itemList = new ArrayList<>();

    public ImageListPresenter() {
        this.page = 1;
        this.query = "";
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
                Log.d(TAG, "onItemSelect: " + pos);
            }
        });

        getList(true);
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
        final retrofit2.Call<ImageSearchResult> call = retroInterface.requestImageList(IConstant.API_KEY, q, IConstant.LIMIT, p, IConstant.TYPE);
        call.enqueue(new Callback<ImageSearchResult>() {
            @Override
            public void onResponse(Call<ImageSearchResult> call, Response<ImageSearchResult> response) {
                if (CommonUtil.isNull(view))
                    return;
                view.hideLoding();
                ImageSearchResult results = response.body();
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
            public void onFailure(Call<ImageSearchResult> call, Throwable t) {
                if (CommonUtil.isNull(view))
                    return;
                view.hideLoding();
                Log.d(TAG, "onFailure: " + t.toString());
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

}
