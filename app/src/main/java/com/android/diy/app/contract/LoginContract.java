package com.android.diy.app.contract;

import com.android.diy.app.base.BasePresenter;
import com.android.diy.app.base.BaseView;
import com.android.diy.app.bean.TokenBean;
import com.android.diy.app.bean.UserDetailBean;
import com.android.diy.app.viewModel.LoginModel;

/**
 * Created by cheng on 2017/2/17.
 */
public interface LoginContract {

    interface View extends BaseView {

        void getToken(TokenBean tokenBean);

        void getMe(UserDetailBean userDetailBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getToken(LoginModel loginModel);

        void getMe();
    }
}
