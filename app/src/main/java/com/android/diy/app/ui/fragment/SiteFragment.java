package com.android.diy.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.diy.app.R;
import com.android.diy.app.base.BaseFragment;
import com.android.diy.app.bean.SiteBean;
import com.android.diy.app.bean.SiteSubBean;
import com.android.diy.app.contract.SitesListContract;
import com.android.diy.app.databinding.FragmentNewsBinding;
import com.android.diy.app.databinding.FragmentTopicBinding;
import com.android.diy.app.presenter.SitesListPresenter;
import com.android.diy.app.ui.adapter.SitesListAdapter;
import com.android.diy.app.ui.widget.SpacesItemDecoration;
import com.aspsine.swipetoloadlayout.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 2017/1/19.
 */

public class SiteFragment extends BaseFragment<FragmentTopicBinding> implements SitesListContract.View {

    private SitesListContract.Presenter mPresenter;
    private SitesListAdapter mAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mPresenter = new SitesListPresenter();
        mAdapter = new SitesListAdapter();
        mDataBinding.swipeTarget.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mDataBinding.swipeTarget.setLayoutManager(layoutManager);
        SpacesItemDecoration decoration=new SpacesItemDecoration(15);
        mDataBinding.swipeTarget.addItemDecoration(decoration);

        mDataBinding.swipeToLoadLayout.setLoadMoreEnabled(false);

        // 下拉刷新
        mDataBinding.swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.clear(false);
                mPresenter.getSiteList();
            }
        });
        mPresenter.attachView(this);
    }

    @Override
    protected void loadData() {
        mDataBinding.swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                mDataBinding.swipeToLoadLayout.setRefreshing(true);
            }
        });
        mPresenter.getSiteList();
    }

    @Override
    public void stopRefreshing() {
        mDataBinding.swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void getSiteListData(List<SiteBean> siteList) {
        List<SiteBean> parentList = siteList;
        List<SiteSubBean> siteSubList = new ArrayList<>();
        for(SiteBean siteBean : parentList) {
            siteSubList.addAll(siteBean.getSites());
        }
        mAdapter.addSiteList(siteSubList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
