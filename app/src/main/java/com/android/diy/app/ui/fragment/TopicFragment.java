package com.android.diy.app.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.diy.app.R;
import com.android.diy.app.base.BaseFragment;
import com.android.diy.app.bean.TopicBean;
import com.android.diy.app.bean.TopicDetailBean;
import com.android.diy.app.contract.TopicListContract;
import com.android.diy.app.databinding.FragmentTopicBinding;
import com.android.diy.app.presenter.TopicListPresenter;
import com.android.diy.app.ui.activity.TopicActivity;
import com.android.diy.app.ui.adapter.TopicListAdapter;
import com.android.diy.app.ui.widget.DividerListItemDecoration;
import com.android.diy.app.utils.RxBus;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.jiongbull.jlog.JLog;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by cheng on 2017/1/19.
 */
public class TopicFragment extends BaseFragment<FragmentTopicBinding> implements TopicListContract.View{

    private TopicListContract.Presenter mPresenter;
    private TopicListAdapter mAdapter;
    private int offset = 0;
    private Subscription rxSubscription;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mPresenter = new TopicListPresenter();
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
                offset = 0;
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
                Intent intent = new Intent(getContext(), TopicActivity.class);
                intent.putExtra(TopicActivity.TOPIC_ID, id);
                startActivity(intent);
            }
        });
        rxSubscription = RxBus.getInstance().toObserverable(TopicDetailBean.class)
                .subscribe(new Action1<TopicDetailBean>() {
                    @Override
                    public void call(TopicDetailBean topicDetailBean) {
                        mAdapter.clear(false);
                        offset = 0;
                        mPresenter.refreshData(offset);
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
    public void getTopicListData(List<TopicBean> topicList) {
        mAdapter.addTopicList(topicList);
        offset = mAdapter.getItemCount();
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        if (!rxSubscription.isUnsubscribed()){
            rxSubscription.unsubscribe();
        }
        super.onDestroy();
    }

}
