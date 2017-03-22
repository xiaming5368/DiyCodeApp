package com.android.diy.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import com.android.diy.app.R;
import com.android.diy.app.base.BaseActivity;
import com.android.diy.app.bean.TopicBean;
import com.android.diy.app.contract.MyTopicContract;
import com.android.diy.app.databinding.ActivityMyTopicBinding;
import com.android.diy.app.presenter.MyTopicPresenter;
import com.android.diy.app.ui.adapter.TopicListAdapter;
import com.android.diy.app.ui.widget.DividerListItemDecoration;
import com.android.diy.app.utils.PrefUtil;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 2017/3/6.
 * 我的帖子/我的收藏
 */
public class MyTopicActivity extends BaseActivity<ActivityMyTopicBinding>
        implements MyTopicContract.View {

    public static final String TYPE = "type";
    public static final int TYPE_CREATE = 1;
    public static final int TYPE_FAVORITE = 2;
    private MyTopicContract.Presenter mPresenter;
    private int offset, type = 0;
    private String loginName = "";
    private TopicListAdapter mAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_topic;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        type = this.getIntent().getExtras().getInt(TYPE);
        mDataBinding.appToolbar.toolbar.setTitle(type == TYPE_CREATE
                ? getString(R.string.app_activity_my_topic)
                : getString(R.string.app_activity_my_star));
        initActionBar(mDataBinding.appToolbar.toolbar);
        mPresenter = new MyTopicPresenter();
        mAdapter = new TopicListAdapter();
        mDataBinding.swipeTarget.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mDataBinding.swipeTarget.setLayoutManager(layoutManager);
        mDataBinding.swipeTarget.addItemDecoration(new DividerListItemDecoration(this));
        // 下拉刷新
        mDataBinding.swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.clear(false);
                offset = 0;
                postTopics();
            }
        });
        // 上拉更多
        mDataBinding.swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                postTopics();
            }
        });
        mAdapter.setOnItemClickListener(new TopicListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id, String url) {
                Intent intent = new Intent(MyTopicActivity.this, TopicActivity.class);
                intent.putExtra(TopicActivity.TOPIC_ID, id);
                startActivity(intent);
            }
        });
        mPresenter.attachView(this);
    }

    @Override
    protected void loadDate() {
        super.loadDate();
        loginName = PrefUtil.getMe(this).getLogin();
        mDataBinding.swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                mDataBinding.swipeToLoadLayout.setRefreshing(true);
            }
        });
        postTopics();
    }

    private void postTopics() {
        if (type == TYPE_CREATE) {
            mPresenter.getUserCreateTopics(loginName, offset,
                    mDataBinding.multiStateView);
        } else {
            mPresenter.getUserFavoriteTopics(loginName, offset,
                    mDataBinding.multiStateView);
        }
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
    public void getUserCreateTopics(List<TopicBean> topicList) {
        mAdapter.addTopicList(topicList);
        offset = mAdapter.getItemCount();
    }

    @Override
    public void getUserFavoriteTopics(List<TopicBean> topicList) {
        mAdapter.addTopicList(topicList);
        offset = mAdapter.getItemCount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
