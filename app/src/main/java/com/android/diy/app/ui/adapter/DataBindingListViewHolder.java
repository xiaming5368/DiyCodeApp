package com.android.diy.app.ui.adapter;

import android.databinding.ViewDataBinding;

/**
 * Created by cheng on 2017/3/8.
 */
public class DataBindingListViewHolder<DB extends ViewDataBinding> extends BindingListAdapter.ViewHolder {

    DB mDataBinding;

    DataBindingListViewHolder(DB dataBinding) {
        super(dataBinding.getRoot());
        this.mDataBinding = dataBinding;
    }

}
