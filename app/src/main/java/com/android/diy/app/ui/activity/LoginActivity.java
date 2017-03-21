package com.android.diy.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import com.android.diy.app.R;
import com.android.diy.app.base.BaseActivity;
import com.android.diy.app.bean.TokenBean;
import com.android.diy.app.bean.UserDetailBean;
import com.android.diy.app.contract.LoginContract;
import com.android.diy.app.databinding.ActivityLoginBinding;
import com.android.diy.app.presenter.LoginPresenter;
import com.android.diy.app.utils.PrefUtil;
import com.android.diy.app.viewModel.LoginModel;

/**
 * Created by cheng on 2017/1/19.
 * 登录
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements LoginContract.View {

    public static final int REQUEST_CODE = 110;
    private LoginContract.Presenter mPresenter;
    private LoginModel mLoginModel = new LoginModel();
    private boolean isHidden = true;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mPresenter = new LoginPresenter();
        mDataBinding.setLogin(mLoginModel);
        initActionBar(mDataBinding.appToolbar.toolbar);
        mDataBinding.setLoginAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getToken(mLoginModel);
            }
        });

        mDataBinding.setHiddenPassAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidePass();
            }
        });
        mPresenter.attachView(this);
    }

    @Override
    public void getToken(TokenBean tokenBean) {
        if(tokenBean != null) {
            PrefUtil.saveToken(this, tokenBean);
            mPresenter.getMe();
        } else {
            showShortToast(getString(R.string.token_error_text));
        }
    }

    @Override
    public void getMe(UserDetailBean userDetailBean) {
        if(userDetailBean != null) {
            PrefUtil.saveMe(this, userDetailBean);
            showShortToast(getString(R.string.login_success_text));
            setResult(RESULT_OK);
        } else {
            showShortToast(getString(R.string.login_error_text));
            setResult(RESULT_OK);
        }
        finish();
    }

    private void hidePass() {
        if (isHidden) {
            //设置EditText文本为可见的
            mDataBinding.imageEye.setImageResource(R.mipmap.ic_edittext_eye_blue);
            mDataBinding.editPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            //设置EditText文本为隐藏的
            mDataBinding.imageEye.setImageResource(R.mipmap.ic_edittext_eye);
            mDataBinding.editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        isHidden = !isHidden;
        mDataBinding.editPass.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = mDataBinding.editPass.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

}
