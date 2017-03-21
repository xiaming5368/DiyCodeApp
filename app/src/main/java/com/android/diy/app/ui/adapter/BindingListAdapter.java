package com.android.diy.app.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by cheng on 2017/3/8.
 */
public abstract class BindingListAdapter<T, VH extends BindingListAdapter.ViewHolder> extends BaseAdapter {

    private ArrayList<T> mData = new ArrayList<>();

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 添加数据
     * @param o
     */
    public void add(T o) {
        mData.add(o);
    }

    /**
     * 添加数据集合
     * @param c
     */
    public void addAll(Collection<T> c) {
        mData.addAll(c);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH holder;
        if (convertView == null) {
            holder = onCreateViewHolder(parent, getItemViewType(position));
            convertView = holder.itemView;
            convertView.setTag(holder);
        } else {
            holder = (VH) convertView.getTag();
        }
        T item;
        try {
            item = mData.get(position);
        } catch (IndexOutOfBoundsException e) {
            item = null;
        }
        this.onBindViewHolder(holder, position, item);
        return convertView;
    }

    public abstract VH onCreateViewHolder(ViewGroup parent, int typeIndex);

    public abstract void onBindViewHolder(VH holder, int position, T item);

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mData;
    }

    public static class ViewHolder {
        public View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
        }
    }

}
