package com.kakatoto.imagesearch.presenter.fragment;

import com.kakatoto.imagesearch.ApplicationClass;
import com.kakatoto.imagesearch.adapter.ScrapListRecyclerAdapter;
import com.kakatoto.imagesearch.model.ScrapBean;
import com.kakatoto.imagesearch.presenter.fragment.impl.IScrapListContract;
import com.kakatoto.imagesearch.realm.repo.ScrapRepo;
import com.kakatoto.imagesearch.realm.repo.impl.IScrapRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.RealmResults;


public class ScrapListPresenter implements IScrapListContract.Presenter {
    private final static String TAG = ImageListPresenter.class.getSimpleName();
    private IScrapListContract.View view;
    private ScrapListRecyclerAdapter adapter;

    @Inject
    ScrapRepo scrapRepo;


    private ArrayList<ScrapBean> itemList = new ArrayList<>();

    public ScrapListPresenter() {
        ApplicationClass.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void attatch(IScrapListContract.View view) {
        this.view = view;
        this.view.setRecycler();
        this.reqeustScrapList();
    }

    @Override
    public void deatch() {
        this.view = null;
    }

    @Override
    public void setAdapter(ScrapListRecyclerAdapter adapter) {
        this.adapter = adapter;
        this.adapter.setItemList(itemList);
        this.adapter.setOnItemSelectListener(new ScrapListRecyclerAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(int pos) {
                view.showDeleteScrap(pos);
            }
        });
    }

    @Override
    public void reqeustScrapList() {
        itemList.clear();
        scrapRepo.getAllScrapObject(new IScrapRepo.OnGetAllScrapCallBack() {
            @Override
            public void onSuccess(RealmResults<ScrapBean> memos) {
                for (ScrapBean item : memos) {
                    itemList.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    @Override
    public void onDeleteScrap(final int pos) {
        scrapRepo.deleteScrapByKey(itemList.get(pos).getmKey(), new IScrapRepo.OnDeleteScrapCallBack() {
            @Override
            public void onSuccess() {
                itemList.remove(pos);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
