package com.kakatoto.imagesearch.realm.repo.impl;


import com.kakatoto.imagesearch.model.Suggest;

import io.realm.RealmResults;


public interface ISuggestRepo {
    interface OnSaveSuggestCallBack{
        void onSuccess();
        void onError(String message);
    }

    interface OnGetSearchSuggestCallBack{
        void onSuccess(RealmResults<Suggest> suggests);
        void onError(String message);
    }

    void addSuggestObject(Suggest suggest, OnSaveSuggestCallBack callBack);

    void getAllSuggest(OnGetSearchSuggestCallBack callBack);

}
