package com.kakatoto.imagesearch.realm.repo;


import com.kakatoto.imagesearch.model.ImageScrap;
import com.kakatoto.imagesearch.realm.repo.impl.IImageScrapRepo;
import com.kakatoto.imagesearch.realm.table.RealmTable;
import com.kakatoto.imagesearch.util.CommonUtil;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by USER on 2017-03-23.
 */

public class ImageScrapRepo implements IImageScrapRepo {
    private final String TAG = ImageScrapRepo.class.getSimpleName();
    private Realm realm;

    public ImageScrapRepo(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void addScrapObject(ImageScrap image, OnSaveScrapCallBack callBack) {
        realm.beginTransaction();
        Number currentIdNum = realm.where(ImageScrap.class).max("mKey");
        if (image.getmKey() == 0) {
            int key = CommonUtil.isNull(currentIdNum) ? 1 : currentIdNum.intValue() + 1;
            image.setmKey(key);
            realm.insert(image);
        } else {
            realm.copyToRealmOrUpdate(image);
        }
        realm.commitTransaction();

        if (callBack != null)
            callBack.onSuccess();
    }


    @Override
    public void getAllScrapObject(OnGetAllScrapCallBack callBack) {
        RealmResults<ImageScrap> results = realm.where(ImageScrap.class).findAllSorted(RealmTable.Meme.REGDATE, Sort.DESCENDING);
        if (callBack != null)
            callBack.onSuccess(results);
    }

    @Override
    public void deleteMemoByKey(Long key, OnDeleteScrapCallBack callBack) {
        realm.beginTransaction();
        RealmResults<ImageScrap> result = realm.where(ImageScrap.class).equalTo(RealmTable.Meme.MKEY, key).findAll();
        result.deleteAllFromRealm();
        realm.commitTransaction();
        if (callBack != null)
            callBack.onSuccess();
    }

}
