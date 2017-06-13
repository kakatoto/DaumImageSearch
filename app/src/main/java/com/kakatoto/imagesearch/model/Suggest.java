package com.kakatoto.imagesearch.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by darong on 2017. 5. 23..
 */

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
