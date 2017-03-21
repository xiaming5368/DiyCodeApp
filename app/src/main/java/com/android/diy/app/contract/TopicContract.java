package com.android.diy.app.contract;

import com.android.diy.app.base.BasePresenter;
import com.android.diy.app.base.BaseView;
import com.android.diy.app.bean.TopicDetailBean;
import com.android.diy.app.bean.TopicReplyBean;

import java.util.List;

/**
 * Created by cheng on 2017/3/10.
 */
public interface TopicContract {

    interface View extends BaseView {

        void getTopic(TopicDetailBean topicDetailBean);

        void getReply(List<TopicReplyBean> topicReplyList);

    }

    interface Presenter extends BasePresenter<View> {

        void getTopic(int id);

        void getReply(int id, Integer offset, Integer limit);

    }

}
