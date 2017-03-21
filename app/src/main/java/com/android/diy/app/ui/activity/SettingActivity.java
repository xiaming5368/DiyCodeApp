package com.android.diy.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.android.diy.app.R;
import com.android.diy.app.base.BaseActivity;
import com.android.diy.app.databinding.ActivitySettingBinding;
import com.android.diy.app.utils.PrefUtil;
import com.jiongbull.jlog.JLog;

/**
 * Created by cheng on 2017/3/6.
 * 设置
 */
public class SettingActivity extends BaseActivity<ActivitySettingBinding> {

    public static final int REQUEST_CODE = 112;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initActionBar(mDataBinding.appToolbar.toolbar);
        String loginName = PrefUtil.getMe(this).getLogin();
        mDataBinding.btnSignUp.setVisibility(TextUtils.isEmpty(loginName) ? View.GONE : View.VISIBLE);
        mDataBinding.setSignUpAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefUtil.clearMe(SettingActivity.this);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
