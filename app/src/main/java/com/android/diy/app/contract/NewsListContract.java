package com.android.diy.app.contract;

import com.android.diy.app.base.BasePresenter;
import com.android.diy.app.base.BaseView;
import com.android.diy.app.bean.TopicBean;

import java.util.List;

/**
 * Created by cheng on 2017/2/27.
 */
public interface NewsListContract {

    interface View extends BaseView {

        void stopRefreshing();

        void stopLoadMore();

        void getNewsListData(List<TopicBean> newsList);

    }

    interface Presenter extends BasePresenter<NewsListContract.View> {

        void refreshData(int offset);

        void loadMoreData(int offset);
    }

}
