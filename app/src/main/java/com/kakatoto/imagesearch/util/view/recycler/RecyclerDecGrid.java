package com.kakatoto.imagesearch.util.view.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/*
* 2단또는 단수가 높아지는 그리드 뷰의 경우 xml에서 아이템에 패딩을 설정해줄경우 좌측에 있는 아이템과 우측아이템의
* 패딩이 동일하게 적용되어 중간의 패딩이 좌측 아이템 우측 아이템 합쳐진 패딩이 나타나게 된다
* 그럴경우 xml에서 패딩을 조절 하지 말고
* 코드에서 만약 우측 아이템일경우 padding-left를 빼주게 되면 여백이 골고루 들어가게 된다
* */
public class RecyclerDecGrid extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    //단수,패딩값,
    public RecyclerDecGrid(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing; // item bottom
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }
}
