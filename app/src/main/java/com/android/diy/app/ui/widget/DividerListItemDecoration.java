package com.android.diy.app.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.diy.app.R;

/**
 * Created by cheng on 2017/2/22.
 */
public class DividerListItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable divider;

    public DividerListItemDecoration(Context context) {
        divider = context.getResources().getDrawable(R.drawable.app_item_divider_vertical);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.set(0, 0, 0, divider.getIntrinsicHeight());
    }

}
