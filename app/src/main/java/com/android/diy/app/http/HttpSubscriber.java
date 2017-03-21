package com.android.diy.app.http;

import rx.Subscriber;

/**
 * Created by cheng on 2017/3/21.
 */
public abstract class HttpSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onFailure(Throwable e);
    public abstract void onSuccess(T t);
}
