package com.android.diy.app.presenter;

import com.android.diy.app.bean.TopicBean;
import com.android.diy.app.contract.TopicListContract;
import com.android.diy.app.http.HttpClient;
import com.android.diy.app.http.HttpSubscriber;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cheng on 2017/2/21.
 */
public class TopicListPresenter implements TopicListContract.Presenter {

    private TopicListContract.View mView;

    @Override
    public void attachView(TopicListContract.View view) {
        this.mView = view;
    }

    @Override
    public void refreshData(int offset) {
        getTopicList(offset);
    }

    @Override
    public void loadMoreData(int offset) {
        getTopicList(offset);
    }

    public void getTopicList(final int offset) {
        HttpClient.getHttpService().getTopics(null, null, offset, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<TopicBean>>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.stopRefreshing();
                        mView.stopLoadMore();
                    }

                    @Override
                    public void onSuccess(List<TopicBean> topicBeen) {
                        if(offset == 0) {
                            mView.stopRefreshing();
                        } else {
                            mView.stopLoadMore();
                        }
                        mView.getTopicListData(topicBeen);
                    }
                });
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

}
