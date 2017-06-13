package com.kakatoto.imagesearch.util;

import android.support.compat.BuildConfig;

/**
 * Created by hwoh on 2017. 6. 12..
 */

public class IConstant {
    public static final boolean PROD = !BuildConfig.DEBUG;

    public static final String SERVER_HOST = PROD ? "apis.daum.net" : "apis.daum.net";
    public static final String SERVER_URL = "https://" + SERVER_HOST;


    public final static String API_KEY = "96305f98bd114e37667099a6f80b85f88b52b224";
    public final static String TYPE = "JSON";
    public final static int LIMIT = 20;
}
