package com.kakatoto.imagesearch.realm.repo.impl;

import com.kakatoto.imagesearch.model.ImageScrap;

import io.realm.RealmResults;

/**
 * Created by ohyowan on 2017. 5. 20..
 */

public interface IImageScrapRepo {

    interface OnSaveScrapCallBack {
        void onSuccess();
        void onError(String message);
    }

    interface OnGetAllScrapCallBack {
        void onSuccess(RealmResults<ImageScrap> memos);
        void onError(String message);
    }


    interface OnDeleteScrapCallBack {
        void onSuccess();
        void onError(String message);
    }


    void addScrapObject(ImageScrap image, OnSaveScrapCallBack callBack);

    void getAllScrapObject(OnGetAllScrapCallBack callBack);

    void deleteMemoByKey(Long key, OnDeleteScrapCallBack callBack);

}
