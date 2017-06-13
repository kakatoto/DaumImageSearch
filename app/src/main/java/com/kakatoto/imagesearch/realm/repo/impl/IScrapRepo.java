package com.kakatoto.imagesearch.realm.repo.impl;

import com.kakatoto.imagesearch.model.ScrapBean;

import io.realm.RealmResults;

public interface IScrapRepo {

    interface OnScrapCallBack {
        void onSuccess();
        void onError(String message);
    }

    interface OnGetAllScrapCallBack {
        void onSuccess(RealmResults<ScrapBean> scrap);
        void onError(String message);
    }


    interface OnDeleteScrapCallBack {
        void onSuccess();
        void onError(String message);
    }


    void addScrapObject(ScrapBean scrap, OnScrapCallBack callBack);

    void getAllScrapObject(OnGetAllScrapCallBack callBack);

    void deleteScrapByKey(Long key, OnDeleteScrapCallBack callBack);

}
