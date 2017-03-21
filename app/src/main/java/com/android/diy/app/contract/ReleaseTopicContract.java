package com.android.diy.app.contract;

import com.android.diy.app.base.BasePresenter;
import com.android.diy.app.base.BaseView;
import com.android.diy.app.bean.NodeBean;
import com.android.diy.app.bean.TopicDetailBean;

import java.util.ArrayList;

/**
 * Created by cheng on 2017/3/7.
 */
public interface ReleaseTopicContract {

    interface View extends BaseView {

        void getNode(ArrayList<NodeBean> nodeList);

        void showNode();

        void createTopic(TopicDetailBean topicDetailBean);

    }

    interface Presenter extends BasePresenter<View> {

        void getNode();

        void showNode();

        void createTopic(String title, String body, int nodeId);

    }

}
