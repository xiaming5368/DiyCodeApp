package com.android.diy.app.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.android.diy.app.BR;

/**
 * Created by cheng on 2017/3/8.
 */
public class TopicModel extends BaseObservable {

    private String nodeName;
    public ObservableField<String> topicTitle = new ObservableField<>();
    public ObservableField<String> topicContent = new ObservableField<>();

    @Bindable
    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
        notifyPropertyChanged(BR.nodeName);
    }

}
