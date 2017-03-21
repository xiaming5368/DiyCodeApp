package com.android.diy.app.presenter;

import com.android.diy.app.bean.TopicBean;
import com.android.diy.app.contract.MyTopicContract;
import com.android.diy.app.http.HttpClient;
import com.android.diy.app.http.HttpSubscriber;
import com.kennyc.view.MultiStateView;

import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cheng on 2017/3/20.
 */
public class MyTopicPresenter implements MyTopicContract.Presenter {

    private MyTopicContract.View mView;

    @Override
    public void attachView(MyTopicContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getUserCreateTopics(String loginName, final Integer offset, final MultiStateView multiStateView) {
        HttpClient.getHttpService().getUserCreateTopics(loginName, offset, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<TopicBean>>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.stopRefreshing();
                        mView.stopLoadMore();
                        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
                    }

                    @Override
                    public void onSuccess(List<TopicBean> topicList) {
                        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                        if (offset == 1) {
                            mView.stopRefreshing();
                        } else {
                            mView.stopLoadMore();
                        }
                        if (topicList != null && topicList.size() > 0) {
                            mView.getUserCreateTopics(topicList);
                        } else {
                            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
                        }
                    }
                });
    }

    @Override
    public void getUserFavoriteTopics(String loginName, final Integer offset, final MultiStateView multiStateView) {
        HttpClient.getHttpService().getUserFavoriteTopics(loginName, offset, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<TopicBean>>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.stopRefreshing();
                        mView.stopLoadMore();
                        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
                    }

                    @Override
                    public void onSuccess(List<TopicBean> topicList) {
                        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                        if (offset == 1) {
                            mView.stopRefreshing();
                        } else {
                            mView.stopLoadMore();
                        }
                        if (topicList != null && topicList.size() > 0) {
                            mView.getUserFavoriteTopics(topicList);
                        } else {
                            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
                        }
                    }
                });
    }
}
