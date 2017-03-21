package com.android.diy.app.presenter;

import com.android.diy.app.bean.TokenBean;
import com.android.diy.app.bean.UserDetailBean;
import com.android.diy.app.contract.LoginContract;
import com.android.diy.app.http.HttpClient;
import com.android.diy.app.http.HttpSubscriber;
import com.android.diy.app.utils.Constant;
import com.android.diy.app.viewModel.LoginModel;
import com.jiongbull.jlog.JLog;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cheng on 2017/2/17.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    @Override
    public void attachView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }


    @Override
    public void getToken(LoginModel loginModel) {
        JLog.e("用户名: " + loginModel.userName.get());
        JLog.e("密码: " + loginModel.userPwd.get());
        HttpClient.getHttpService().getToken(Constant.VALUE_CLIENT_ID, Constant.VALUE_CLIENT_SECRET,
                Constant.VALUE_GRANT_TYPE, "xiaming5368@163.com", "diy5368g")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<TokenBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                    }

                    @Override
                    public void onSuccess(TokenBean tokenBean) {
                        mView.getToken(tokenBean);
                    }
                });
    }

    @Override
    public void getMe() {
        HttpClient.getHttpService().getMe(Constant.VALUE_TOKEN_PREFIX + Constant.VALUE_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpSubscriber<UserDetailBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                    }

                    @Override
                    public void onSuccess(UserDetailBean userDetailBean) {
                        mView.getMe(userDetailBean);
                    }
                });
    }

}
