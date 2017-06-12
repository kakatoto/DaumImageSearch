package com.kakatoto.imagesearch.util;

import android.support.compat.BuildConfig;

/**
 * Created by darong on 2017. 6. 12..
 */

public class IConstant {
    public static final boolean PROD = !BuildConfig.DEBUG;

    public static final String SERVER_HOST = PROD ? "apis.daum.net" : "apis.daum.net";
    public static final String SERVER_URL = "https://" + SERVER_HOST;
}
