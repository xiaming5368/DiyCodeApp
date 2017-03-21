package com.android.diy.app.presenter;

import com.android.diy.app.bean.SiteBean;
import com.android.diy.app.contract.SitesListContract;
import com.android.diy.app.http.HttpClient;
import com.android.diy.app.http.HttpSubscriber;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cheng on 2017/3/1.
 */
public class SitesListPresenter implements SitesListContract.Presenter {

    private SitesListContract.View mView;

    @Override
    public void getSiteList() {
        HttpClient.getHttpService().getSites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<List<SiteBean>>() {
                    @Override
                    public void onFailure(Throwable e) {
                        mView.stopRefreshing();
                    }

                    @Override
                    public void onSuccess(List<SiteBean> siteBeen) {
                        mView.stopRefreshing();
                        mView.getSiteListData(siteBeen);
                    }
                });
    }

    @Override
    public void attachView(SitesListContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
