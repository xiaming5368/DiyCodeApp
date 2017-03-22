package com.android.diy.app.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.diy.app.R;
import com.android.diy.app.bean.TopicBean;
import com.android.diy.app.databinding.ListTopicItemBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 2017/2/21.
 */
public class TopicListAdapter extends RecyclerView.Adapter<TopicListAdapter.TopicViewHolder>  {

    private List<TopicBean> mTopicList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener ClickListener) {
        this.mOnItemClickListener = ClickListener;
    }

    public void addTopicList(List<TopicBean> topicList) {
        mTopicList = topicList;
        notifyDataSetChanged();
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListTopicItemBinding topicItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_topic_item, parent,false);
        return new TopicViewHolder(topicItemBinding);
    }

    @Override
    public void onBindViewHolder(final TopicViewHolder holder, int position) {
        TopicBean topicBean = mTopicList.get(position);
        holder.mDataBinding.setTopicBean(topicBean);
    }

    @Override
    public int getItemCount() {
        return mTopicList.size();
    }

    class TopicViewHolder extends DataBindingRecyclerViewHolder<ListTopicItemBinding> implements View.OnClickListener {

        TopicViewHolder(ListTopicItemBinding dataBinding) {
            super(dataBinding);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(mTopicList.get(getAdapterPosition()).getId(),
                        mTopicList.get(getAdapterPosition()).getAddress());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int id, String url);
    }

}
