package com.kakatoto.imagesearch.util.view.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class RecyclerDecoHorizontal extends RecyclerView.ItemDecoration {
    private final int divHeight;

    public RecyclerDecoHorizontal(int divHeight) {
        this.divHeight = divHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view); // item position
        if (position == 0) {
            //outRect.left = divHeight * 2;
        }
        if (position == state.getItemCount() -1) {
            //outRect.right = divHeight * 2;
            return;
        }
        outRect.right = divHeight;
    }
}