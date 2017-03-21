package com.android.diy.app.base;

/**
 * Created by cheng on 2017/2/17.
 */
public interface BasePresenter<V extends BaseView> {

    // 绑定View
    void attachView(V view);

    // 解除绑定View
    void detachView();

}
