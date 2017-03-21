package com.android.diy.app.contract;

import com.android.diy.app.base.BasePresenter;
import com.android.diy.app.base.BaseView;
import com.android.diy.app.bean.SiteBean;

import java.util.List;

/**
 * Created by cheng on 2017/3/1.
 */
public interface SitesListContract {

    interface View extends BaseView {

        void stopRefreshing();

        void getSiteListData(List<SiteBean> siteList);

    }

    interface Presenter extends BasePresenter<SitesListContract.View> {

        void getSiteList();

    }

}
