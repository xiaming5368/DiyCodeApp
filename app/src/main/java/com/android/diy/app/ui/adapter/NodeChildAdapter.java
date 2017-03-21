package com.android.diy.app.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.diy.app.R;
import com.android.diy.app.databinding.ListNodeItemBinding;

/**
 * Created by cheng on 2017/3/7.
 */
public class NodeChildAdapter extends BindingListAdapter<String, DataBindingListViewHolder> {

    private int selectItem = -1;
    private Context mContext;

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
        notifyDataSetChanged();
    }

    public NodeChildAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void clear() {
        selectItem = -1;
        super.clear();
    }

    @Override
    public DataBindingListViewHolder onCreateViewHolder(ViewGroup parent, int typeIndex) {
        ListNodeItemBinding nodeItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_node_item, parent, false);
        return new DataBindingListViewHolder<>(nodeItemBinding);
    }

    @Override
    public void onBindViewHolder(DataBindingListViewHolder holder, int position, String item) {
        ((ListNodeItemBinding) holder.mDataBinding).setSelectName(item);
        if (selectItem != -1 && selectItem == position) {
            ((ListNodeItemBinding) holder.mDataBinding).relateLay.setBackgroundColor(ContextCompat.getColor(mContext,
                    R.color.background_white));
        } else {
            ((ListNodeItemBinding) holder.mDataBinding).relateLay.setBackgroundColor(ContextCompat.getColor(mContext,
                    R.color.background_color));
        }
    }

}
