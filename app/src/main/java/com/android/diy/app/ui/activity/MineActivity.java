package com.android.diy.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.android.diy.app.R;
import com.android.diy.app.base.BaseActivity;
import com.android.diy.app.bean.UserDetailBean;
import com.android.diy.app.contract.UserContract;
import com.android.diy.app.databinding.ActivityMineBinding;
import com.android.diy.app.presenter.UserPresenter;
import com.android.diy.app.utils.TimeUtils;

/**
 * Created by cheng on 2017/3/6.
 * 我的
 */
public class MineActivity extends BaseActivity<ActivityMineBinding> implements UserContract.View {

    public static final String LOGIN_NAME = "loginName";
    private String loginName = "";

    private UserPresenter mPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        loginName = this.getIntent().getStringExtra(LOGIN_NAME);
        initActionBar(mDataBinding.appToolbar.toolbar);

        mPresenter = new UserPresenter();
        mPresenter.getUser(loginName);
        mPresenter.attachView(this);
    }

    @Override
    public void getUser(UserDetailBean userDetailBean) {
        mDataBinding.setUser(userDetailBean);
        mDataBinding.setTime(TimeUtils.formatTime(userDetailBean.getCreated_at()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
