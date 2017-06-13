package com.kakatoto.imagesearch.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class ScrapBean extends RealmObject {
    @PrimaryKey
    long mKey;
    String thumbnail;
    String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
