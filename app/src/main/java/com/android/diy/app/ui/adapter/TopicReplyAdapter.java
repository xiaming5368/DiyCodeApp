package com.android.diy.app.ui.adapter;

import android.databinding.DataBindingUtil;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.diy.app.R;
import com.android.diy.app.bean.TopicReplyBean;
import com.android.diy.app.databinding.ListTopicDetailReplyItemBinding;

import java.util.Locale;

/**
 * Created by cheng on 2017/3/13.
 */
public class TopicReplyAdapter extends BindingListAdapter<TopicReplyBean, DataBindingListViewHolder> {

    @Override
    public DataBindingListViewHolder onCreateViewHolder(ViewGroup parent, int typeIndex) {
        ListTopicDetailReplyItemBinding replyItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_topic_detail_reply_item, parent, false);
        return new DataBindingListViewHolder<>(replyItemBinding);
    }

    @Override
    public void onBindViewHolder(DataBindingListViewHolder holder, int position, TopicReplyBean item) {
        ((ListTopicDetailReplyItemBinding)holder.mDataBinding).setTopicReplyBean(item);
        ((ListTopicDetailReplyItemBinding)holder.mDataBinding).setBodyHtml(Html.fromHtml(item.getBodyHtml()).toString());
        ((ListTopicDetailReplyItemBinding)holder.mDataBinding).setFloor(String.format(Locale.CHINESE, "%d楼", position + 1));
    }
}
