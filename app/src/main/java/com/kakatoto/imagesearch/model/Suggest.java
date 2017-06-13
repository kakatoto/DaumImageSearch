package com.kakatoto.imagesearch.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Suggest extends RealmObject {
    @PrimaryKey
    String suggest;

    public Suggest() {
    }

    public Suggest(String suggest) {
        this.suggest = suggest;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}
