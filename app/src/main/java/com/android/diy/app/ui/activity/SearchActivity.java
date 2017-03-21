package com.android.diy.app.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.android.diy.app.R;
import com.android.diy.app.base.BaseActivity;
import com.android.diy.app.databinding.ActivitySearchBinding;

/**
 * Created by cheng on 2017/3/21.
 * 搜索
 */
public class SearchActivity extends BaseActivity<ActivitySearchBinding> {

    public static final String KEY_WORD = "keyWord";
    private String keyWord = "";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        keyWord = this.getIntent().getExtras().getString(KEY_WORD);
        mDataBinding.appToolbar.toolbar.setTitle(keyWord);
        initActionBar(mDataBinding.appToolbar.toolbar);
        mDataBinding.setResult(keyWord);
    }
}
