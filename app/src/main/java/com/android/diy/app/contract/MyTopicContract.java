package com.android.diy.app.contract;

import com.android.diy.app.base.BasePresenter;
import com.android.diy.app.base.BaseView;
import com.android.diy.app.bean.TopicBean;
import com.kennyc.view.MultiStateView;

import java.util.List;

/**
 * Created by cheng on 2017/3/20.
 */
public interface MyTopicContract {

    interface View extends BaseView {

        void stopRefreshing();

        void stopLoadMore();

        void getUserCreateTopics(List<TopicBean> topicList);

        void getUserFavoriteTopics(List<TopicBean> topicList);

    }

    interface Presenter extends BasePresenter<View> {

        void getUserCreateTopics(String loginName, Integer offset, MultiStateView multiStateView);

        void getUserFavoriteTopics(String loginName, Integer offset, MultiStateView multiStateView);

    }

}
