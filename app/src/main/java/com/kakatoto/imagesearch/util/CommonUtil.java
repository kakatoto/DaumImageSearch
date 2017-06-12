package com.kakatoto.imagesearch.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;


import java.util.List;
import java.util.Map;

/**
 * Created by ohyowan on 16. 5. 22..
 */
public class CommonUtil {
    private static final String TAG = CommonUtil.class.getSimpleName();


    public static boolean isNull(Object s) {
        if (s == null) {
            return true;
        }
        if ((s instanceof String) && (((String) s).trim().length() == 0)) {
            return true;
        }
        if (s instanceof Map) {
            return ((Map<?, ?>) s).isEmpty();
        }
        if (s instanceof List) {
            return ((List<?>) s).isEmpty();
        }
        if (s instanceof Object[]) {
            return (((Object[]) s).length == 0);
        }
        return false;
    }


    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "not found version name");
        }
        return versionName;
    }

    /**
     * Dp to Px
     */
    public static int convertDpToPx(Context ctx, float dp) {
        float d = ctx.getResources().getDisplayMetrics().density;
        return (int) (d * dp);
    }


}
