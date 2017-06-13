package com.kakatoto.imagesearch.realm.repo;


import com.kakatoto.imagesearch.realm.repo.impl.IScrapRepo;
import com.kakatoto.imagesearch.realm.table.RealmTable;
import com.kakatoto.imagesearch.util.CommonUtil;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;



public class ScrapRepo implements IScrapRepo {
    private final String TAG = ScrapRepo.class.getSimpleName();
    private Realm realm;

    public ScrapRepo(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void addScrapObject(com.kakatoto.imagesearch.model.ScrapBean scrap, OnScrapCallBack callBack) {
        realm.beginTransaction();
        Number currentIdNum = realm.where(com.kakatoto.imagesearch.model.ScrapBean.class).max(RealmTable.ScrapBean.MKEY);
        if (scrap.getmKey() == 0) {
            int key = CommonUtil.isNull(currentIdNum) ? 1 : currentIdNum.intValue() + 1;
            scrap.setmKey(key);
            realm.insert(scrap);
        } else {
            realm.copyToRealmOrUpdate(scrap);
        }
        realm.commitTransaction();

        if (callBack != null)
            callBack.onSuccess();
    }


    @Override
    public void getAllScrapObject(OnGetAllScrapCallBack callBack) {
        RealmResults<com.kakatoto.imagesearch.model.ScrapBean> results = realm.where(com.kakatoto.imagesearch.model.ScrapBean.class).findAllSorted(RealmTable.ScrapBean.MKEY, Sort.DESCENDING);
        if (callBack != null)
            callBack.onSuccess(results);
    }

    @Override
    public void deleteScrapByKey(Long key, OnDeleteScrapCallBack callBack) {
        realm.beginTransaction();
        RealmResults<com.kakatoto.imagesearch.model.ScrapBean> result = realm.where(com.kakatoto.imagesearch.model.ScrapBean.class).equalTo(RealmTable.ScrapBean.MKEY, key).findAll();
        result.deleteAllFromRealm();
        realm.commitTransaction();
        if (callBack != null)
            callBack.onSuccess();
    }

}
