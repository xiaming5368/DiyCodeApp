package com.android.diy.app.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.diy.app.R;
import com.android.diy.app.bean.SiteSubBean;
import com.android.diy.app.databinding.ListSiteItemBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 2017/3/1.
 */
public class SitesListAdapter extends RecyclerView.Adapter<SitesListAdapter.SitesViewHolder> {

    private List<SiteSubBean> mSiteList = new ArrayList<>();

    public void addSiteList(List<SiteSubBean> siteList) {
        mSiteList.addAll(siteList);
        notifyDataSetChanged();
    }

    @Override
    public SitesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListSiteItemBinding siteItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_site_item, parent, false);
        return new SitesViewHolder(siteItemBinding);
    }

    @Override
    public void onBindViewHolder(SitesViewHolder holder, int position) {
        SiteSubBean siteSubBean = mSiteList.get(position);
        holder.mDataBinding.setSiteBean(siteSubBean);
    }

    @Override
    public int getItemCount() {
        return mSiteList.size();
    }

    public void clear(boolean notify) {
        mSiteList.clear();
        if (notify) {
            notifyDataSetChanged();
        }
    }

    class SitesViewHolder extends DataBindingRecyclerViewHolder<ListSiteItemBinding> {

        SitesViewHolder(ListSiteItemBinding dataBinding) {
            super(dataBinding);
        }
    }

}
