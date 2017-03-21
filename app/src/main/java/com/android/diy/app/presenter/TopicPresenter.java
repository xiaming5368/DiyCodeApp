package com.android.diy.app.presenter;

import com.android.diy.app.bean.TopicDetailBean;
import com.android.diy.app.bean.TopicReplyBean;
import com.android.diy.app.contract.TopicContract;
import com.android.diy.app.http.HttpClient;
import com.android.diy.app.http.HttpSubscriber;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cheng on 2017/3/10.
 */
public class TopicPresenter implements TopicContract.Presenter {

    private TopicContract.View mView;

    @Override
    public void getTopic(int id) {
        HttpClient.getHttpService().getTopic(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<TopicDetailBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                    }

                    @Override
                    public void onSuccess(TopicDetailBean topicDetailBean) {
                        mView.getTopic(topicDetailBean);
                    }
                });
    }

    @Override
    public void getReply(int id, Integer offset, Integer limit) {
        HttpClient.getHttpService().getReply(id, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<TopicReplyBean>>() {
                    @Override
                    public void onFailure(Throwable e) {

                    }

                    @Override
                    public void onSuccess(List<TopicReplyBean> topicReplyList) {
                        mView.getReply(topicReplyList);
                    }
                });
    }

    @Override
    public void attachView(TopicContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
