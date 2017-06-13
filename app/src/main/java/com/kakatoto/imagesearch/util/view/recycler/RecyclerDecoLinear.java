package com.kakatoto.imagesearch.util.view.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kakatoto.imagesearch.ApplicationClass;
import com.kakatoto.imagesearch.util.CommonUtil;


/**
 * Created by USER on 2016-10-13.
 */

public class RecyclerDecoLinear extends RecyclerView.ItemDecoration {

    private int left, right;
    private int top, bottom;

    public RecyclerDecoLinear() {
        this.top = CommonUtil.convertDpToPx(ApplicationClass.getInstance(), 0);
        this.bottom = CommonUtil.convertDpToPx(ApplicationClass.getInstance(), 0);
        this.left = CommonUtil.convertDpToPx(ApplicationClass.getInstance(), 0);
        this.right = CommonUtil.convertDpToPx(ApplicationClass.getInstance(), 5);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = top;
        outRect.left = left; // item bottom
        outRect.right = right; // item bottom
        outRect.bottom = bottom; // item bottom

    }
}
