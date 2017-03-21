package com.android.diy.app.ui.widget.binding;

import android.databinding.BindingAdapter;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

/**
 * Created by cheng on 2017/2/21.
 */
public class SwipeToLoadLayoutBinding {

    @BindingAdapter("refreshing")
    public static void setRefreshing(SwipeToLoadLayout v, boolean refreshing) {
        if(refreshing != v.isRefreshing()) {
            v.setRefreshing(refreshing);
        }
    }

    @BindingAdapter("loadingMore")
    public static void setLoadMore(SwipeToLoadLayout v, boolean loadingMore) {
        if(loadingMore != v.isLoadingMore()) {
            v.setLoadingMore(loadingMore);
        }
    }

}
