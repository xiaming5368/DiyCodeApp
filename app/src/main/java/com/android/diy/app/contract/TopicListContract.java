package com.android.diy.app.contract;


import com.android.diy.app.base.BasePresenter;
import com.android.diy.app.base.BaseView;
import com.android.diy.app.bean.TopicBean;

import java.util.List;

/**
 * Created by cheng on 2017/2/21.
 */
public interface TopicListContract {

    interface View extends BaseView {

        void stopRefreshing();

        void stopLoadMore();

        void getTopicListData(List<TopicBean> topicList);

    }

    interface Presenter extends BasePresenter<View> {

        void refreshData(int offset);

        void loadMoreData(int offset);
    }

}
