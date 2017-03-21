package com.android.diy.app.presenter;

import com.android.diy.app.bean.NodeBean;
import com.android.diy.app.bean.TopicDetailBean;
import com.android.diy.app.contract.ReleaseTopicContract;
import com.android.diy.app.http.HttpClient;
import com.android.diy.app.http.HttpSubscriber;
import com.android.diy.app.utils.Constant;
import com.jiongbull.jlog.JLog;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cheng on 2017/3/7.
 */
public class ReleaseTopicPresenter implements ReleaseTopicContract.Presenter {

    private ReleaseTopicContract.View mView;

    /**
     * 获取节点
     */
    @Override
    public void getNode() {
        HttpClient.getHttpService().getNode().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<ArrayList<NodeBean>>() {
                    @Override
                    public void onFailure(Throwable e) {

                    }

                    @Override
                    public void onSuccess(ArrayList<NodeBean> nodeList) {
                        mView.getNode(nodeList);
                    }
                });
    }

    @Override
    public void showNode() {
        mView.showNode();
    }

    /**
     * 创建话题
     * @param title
     * @param body
     * @param nodeId
     */
    @Override
    public void createTopic(String title, String body, int nodeId) {
        HttpClient.getHttpService().createTopic(Constant.VALUE_TOKEN_PREFIX + Constant.VALUE_TOKEN,
                title, body, nodeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<TopicDetailBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                    }

                    @Override
                    public void onSuccess(TopicDetailBean topicDetailBean) {
                        mView.createTopic(topicDetailBean);
                    }
                });
    }

    @Override
    public void attachView(ReleaseTopicContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
