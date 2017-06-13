package com.kakatoto.imagesearch.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by darong on 2017. 6. 13..
 */

public class ImageScrap extends RealmObject {
    @PrimaryKey
    long mKey;
    String thumbnail;

    public long getmKey() {
        return mKey;
    }

    public void setmKey(long mKey) {
        this.mKey = mKey;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
