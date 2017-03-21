package com.android.diy.app.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cheng on 2017/2/21.
 */
public abstract class BaseFragment<DB extends ViewDataBinding> extends Fragment {

    // 当前Fragment 是否可见
    protected boolean isVisible = false;
    // 是否加载过数据
    protected boolean isLoadData = false;

    // 标识view 是否初始化完成
    protected boolean isViewInit = false;

    protected DB mDataBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false);
        initView(savedInstanceState);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.isViewInit = true;
        if (getUserVisibleHint()) {
            prepareLoadData(false);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisible = isVisibleToUser;
        prepareLoadData(false);
    }

    public void prepareLoadData(boolean forceLoad) {
        if (isViewInit && isVisible && (!isLoadData || forceLoad)) {
            loadData();
            isLoadData = true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    protected abstract void loadData();

}
