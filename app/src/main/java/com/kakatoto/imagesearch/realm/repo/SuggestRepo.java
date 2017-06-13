package com.kakatoto.imagesearch.realm.repo;


import com.kakatoto.imagesearch.model.Suggest;
import com.kakatoto.imagesearch.realm.repo.impl.ISuggestRepo;

import io.realm.Realm;
import io.realm.RealmResults;


public class SuggestRepo implements ISuggestRepo {
    private Realm realm;

    public SuggestRepo(Realm realm) {
        this.realm = realm;
    }


    @Override
    public void addSuggestObject(Suggest suggest, ISuggestRepo.OnSaveSuggestCallBack callBack) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(suggest);
        realm.commitTransaction();

        if (callBack != null)
            callBack.onSuccess();
    }

    @Override
    public void getAllSuggest(OnGetSearchSuggestCallBack callBack) {
        RealmResults<Suggest> results = realm.where(Suggest.class).findAll();
        if (callBack != null)
            callBack.onSuccess(results);
    }
}
