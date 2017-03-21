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
import com.android.diy.app.databinding.ActivitySignUpBinding;

/**
 * Created by cheng on 2017/3/21.
 * 注册
 */
public class SignUpActivity extends BaseActivity<ActivitySignUpBinding> {

    private boolean isHidden = true;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initActionBar(mDataBinding.appToolbar.toolbar);
        mDataBinding.setSignUpAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showShortToast(getString(R.string.sign_up_hint_text));
            }
        });

        mDataBinding.setHiddenPassAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hidePass();
            }
        });
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
}
