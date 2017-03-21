package com.android.diy.app.presenter;

import com.android.diy.app.bean.UserDetailBean;
import com.android.diy.app.contract.UserContract;
import com.android.diy.app.http.HttpClient;
import com.android.diy.app.http.HttpSubscriber;
import com.android.diy.app.utils.Constant;
import com.jiongbull.jlog.JLog;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cheng on 2017/3/6.
 */
public class UserPresenter implements UserContract.Presenter {

    private UserContract.View mView;

    @Override
    public void getUser(String loginName) {
        JLog.e("token: " + Constant.VALUE_TOKEN_PREFIX + Constant.VALUE_TOKEN);
        HttpClient.getHttpService().getUser(Constant.VALUE_TOKEN_PREFIX + Constant.VALUE_TOKEN,
                loginName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<UserDetailBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                    }

                    @Override
                    public void onSuccess(UserDetailBean userDetailBean) {
                        mView.getUser(userDetailBean);
                    }
                });
    }

    @Override
    public void attachView(UserContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
