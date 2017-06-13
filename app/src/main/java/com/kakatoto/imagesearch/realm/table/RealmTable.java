package com.kakatoto.imagesearch.realm.table;

/**
 * Created by ohyowan on 2017. 5. 21..
 */

public interface RealmTable {
    interface Meme {
        String MKEY = "mKey";
        String MEMO = "memo";
        String THUMBNAIL = "thumbnail";
        String URILIST = "uriList";
        String TAGLIST = "tagList";
        String REGDATE = "regDate";
        String UPDATE = "upDate";
    }

    interface Tags {
        String TAG = "tag";
    }

    interface Uris {
        String URI = "uri";
    }
}
