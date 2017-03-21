package com.android.diy.app.ui.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by cheng on 2017/2/21.
 */
class DataBindingRecyclerViewHolder<DB extends ViewDataBinding> extends RecyclerView.ViewHolder {

    DB mDataBinding;

    DataBindingRecyclerViewHolder(DB dataBinding) {
        super(dataBinding.getRoot());
        mDataBinding = dataBinding;
    }
}
