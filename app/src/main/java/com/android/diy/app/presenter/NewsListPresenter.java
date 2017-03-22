package com.android.diy.app.presenter;

import com.android.diy.app.bean.TopicBean;
import com.android.diy.app.contract.NewsListContract;
import com.android.diy.app.http.HttpClient;
import com.android.diy.app.http.HttpSubscriber;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cheng on 2017/2/27.
 */
public class NewsListPresenter implements NewsListContract.Presenter {

    private NewsListContract.View mView;

    @Override
    public void refreshData(int offset) {
        getNewsList(offset);
    }

    @Override
    public void loadMoreData(int offset) {
        getNewsList(offset);
    }

    public void getNewsList(final int offset) {
        HttpClient.getHttpService().getNews(null, offset, null)
                .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new HttpSubscriber<List<TopicBean>>() {
            @Override
            public void onFailure(Throwable e) {
                mView.stopRefreshing();
                mView.stopLoadMore();
            }

            @Override
            public void onSuccess(List<TopicBean> newsBeen) {
                if(offset == 0) {
                    mView.stopRefreshing();
                } else {
                    mView.stopLoadMore();
                }
                mView.getNewsListData(newsBeen);
            }
        });
    }

    @Override
    public void attachView(NewsListContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
