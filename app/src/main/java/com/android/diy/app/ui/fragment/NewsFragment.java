package com.android.diy.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.diy.app.R;
import com.android.diy.app.base.BaseFragment;
import com.android.diy.app.bean.TopicBean;
import com.android.diy.app.contract.NewsListContract;
import com.android.diy.app.databinding.FragmentNewsBinding;
import com.android.diy.app.databinding.FragmentTopicBinding;
import com.android.diy.app.presenter.NewsListPresenter;
import com.android.diy.app.ui.activity.WebViewActivity;
import com.android.diy.app.ui.adapter.TopicListAdapter;
import com.android.diy.app.ui.widget.DividerListItemDecoration;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;

import java.util.List;

/**
 * Created by cheng on 2017/1/19.
 */

public class NewsFragment extends BaseFragment<FragmentTopicBinding> implements NewsListContract.View{

    private NewsListContract.Presenter mPresenter;
    private TopicListAdapter mAdapter;
    private int offset = 1;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mPresenter = new NewsListPresenter();
        mAdapter = new TopicListAdapter();
        mDataBinding.swipeTarget.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        mDataBinding.swipeTarget.setLayoutManager(layoutManager);
        mDataBinding.swipeTarget.addItemDecoration(new DividerListItemDecoration(getContext()));

        // 下拉刷新
        mDataBinding.swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.clear(false);
                offset = 1;
                mPresenter.refreshData(offset);
            }
        });
        // 上拉更多
        mDataBinding.swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMoreData(offset);
            }
        });
        mAdapter.setOnItemClickListener(new TopicListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id, String url) {
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL, url);
                startActivity(intent);
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
        mPresenter.refreshData(offset);
    }

    @Override
    public void stopRefreshing() {
        mDataBinding.swipeToLoadLayout.setRefreshing(false);
    }

    @Override
    public void stopLoadMore() {
        mDataBinding.swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void getNewsListData(List<TopicBean> newsList) {
        mAdapter.addTopicList(newsList);
        offset++;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
