package com.android.diy.app.contract;

import com.android.diy.app.base.BasePresenter;
import com.android.diy.app.base.BaseView;
import com.android.diy.app.bean.UserDetailBean;

/**
 * Created by cheng on 2017/3/6.
 */
public interface UserContract {

    interface View extends BaseView {

        void getUser(UserDetailBean userDetailBean);

    }

    interface Presenter extends BasePresenter<View> {

        void getUser(String loginName);

    }

}
