package com.android.diy.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.android.diy.app.R;
import com.android.diy.app.base.BaseActivity;
import com.android.diy.app.bean.NodeBean;
import com.android.diy.app.bean.TopicDetailBean;
import com.android.diy.app.contract.ReleaseTopicContract;
import com.android.diy.app.databinding.ActivityReleaseTopicBinding;
import com.android.diy.app.presenter.ReleaseTopicPresenter;
import com.android.diy.app.viewModel.TopicModel;

import java.util.ArrayList;

/**
 * Created by cheng on 2017/3/7.
 * 发布新话题
 */
public class ReleaseTopicActivity extends BaseActivity<ActivityReleaseTopicBinding>
        implements ReleaseTopicContract.View {

    public static final int REQUEST_CODE = 111;
    public static final String GROUP_NAME = "group_name";
    public static final String CHILD_NAME = "child_name";
    public static final String NODE_ID = "nodeId";
    private ReleaseTopicPresenter mPresenter;
    private ArrayList<NodeBean> mNodeList;
    private TopicModel topicModel;
    private int nodeId = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_release_topic;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initActionBar(mDataBinding.appToolbar.toolbar);
        mPresenter = new ReleaseTopicPresenter();
        topicModel = new TopicModel();
        topicModel.setNodeName(getString(R.string.release_topic_text_node));
        mPresenter.getNode();

        mDataBinding.setTopicModel(topicModel);
        mDataBinding.setSelectNode(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.showNode();
            }
        });
        mDataBinding.fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (topicModel.getNodeName().equals(getString(R.string.release_topic_text_node))) {
                    showShortToast(getString(R.string.release_topic_text_node_error));
                    return;
                }
                if(TextUtils.isEmpty(topicModel.topicTitle.get())
                        || TextUtils.isEmpty(topicModel.topicContent.get())) {
                    showShortToast(getString(R.string.release_topic_text_error));
                    return;
                }
                // 创建话题的功能已实现，但是没有测试。因为创建新话题后，无法删除。
                mPresenter.createTopic(topicModel.topicTitle.get(),
                        topicModel.topicContent.get(), nodeId);
            }
        });
        mPresenter.attachView(this);
    }

    @Override
    public void getNode(ArrayList<NodeBean> nodeList) {
        mNodeList = nodeList;
    }

    @Override
    public void showNode() {
        Intent intent = new Intent(ReleaseTopicActivity.this, SelectNodeActivity.class);
        intent.putParcelableArrayListExtra(SelectNodeActivity.NODE_LIST, mNodeList);
        startActivityForResult(intent, SelectNodeActivity.REQUEST_NODE);
    }

    @Override
    public void createTopic(TopicDetailBean topicDetailBean) {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.TOPIC_BEAN, topicDetailBean);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SelectNodeActivity.REQUEST_NODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            nodeId = bundle.getInt(NODE_ID);
            topicModel.setNodeName(bundle.getString(GROUP_NAME) + ", " + bundle.getString(CHILD_NAME));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
