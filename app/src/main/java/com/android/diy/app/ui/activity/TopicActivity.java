package com.android.diy.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.android.diy.app.R;
import com.android.diy.app.base.BaseActivity;
import com.android.diy.app.bean.TopicDetailBean;
import com.android.diy.app.bean.TopicReplyBean;
import com.android.diy.app.contract.TopicContract;
import com.android.diy.app.databinding.ActivityTopicBinding;
import com.android.diy.app.presenter.TopicPresenter;
import com.android.diy.app.ui.adapter.TopicReplyAdapter;
import com.android.diy.app.ui.widget.MarkdownView;
import com.android.diy.app.utils.TimeUtils;

import java.util.List;

/**
 * Created by cheng on 2017/3/10.
 * 话题
 */
public class TopicActivity extends BaseActivity<ActivityTopicBinding> implements TopicContract.View {

    public static final String TOPIC_ID = "topic_id";
    private int id = 0;
    private TopicContract.Presenter mPresenter;
    private TopicReplyAdapter mAdapter;
    private View mHeaderView;

    private TextView tvTitle, tvName, tvTime, tvReply;
    private MarkdownView webView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_topic;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initActionBar(mDataBinding.appToolbar.toolbar);
        id = this.getIntent().getExtras().getInt(TOPIC_ID);
        mPresenter = new TopicPresenter();
        initHeaderView();
        mPresenter.getTopic(id);
        mPresenter.getReply(id, null, null);
        mPresenter.attachView(this);
    }

    private void initHeaderView() {
        mHeaderView = getLayoutInflater().inflate(R.layout.list_topic_detail_item, null);
        tvTitle = (TextView) mHeaderView.findViewById(R.id.text_title);
        tvName = (TextView) mHeaderView.findViewById(R.id.text_name);
        tvTime = (TextView) mHeaderView.findViewById(R.id.text_time);
        tvReply = (TextView) mHeaderView.findViewById(R.id.text_reply);
        webView = (MarkdownView) mHeaderView.findViewById(R.id.webView);
        mDataBinding.listTopic.addHeaderView(mHeaderView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void getTopic(TopicDetailBean topicDetailBean) {
        tvTitle.setText(topicDetailBean.getTitle());
        tvName.setText(topicDetailBean.getUser().getLogin());
        tvTime.setText(topicDetailBean.getRepliedAt() != null
                ? TimeUtils.getInterval(topicDetailBean.getUpdatedAt())
                : TimeUtils.getInterval(topicDetailBean.getCreatedAt()));
        tvReply.setText("共收到" + topicDetailBean.getRepliesCount() + "条回复");
        webView.setOpenUrlInBrowser(true);
        webView.setMarkDownText(topicDetailBean.getBody());
    }

    @Override
    public void getReply(List<TopicReplyBean> topicReplyList) {
        mAdapter = new TopicReplyAdapter();
        mAdapter.addAll(topicReplyList);
        mDataBinding.listTopic.setAdapter(mAdapter);
    }
}
