package com.android.diy.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cheng on 2017/2/17.
 */
public class TopicBean implements Parcelable {

    private int id;
    private String title;
    private String created_at;
    private String updated_at;
    private String replied_at;
    private int replies_count;
    private String node_name;
    private int node_id;
    private int last_reply_user_id;
    private String last_reply_user_login;
    private String address;
    private UserBean user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getReplied_at() {
        return replied_at;
    }

    public void setReplied_at(String replied_at) {
        this.replied_at = replied_at;
    }

    public int getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public int getLast_reply_user_id() {
        return last_reply_user_id;
    }

    public void setLast_reply_user_id(int last_reply_user_id) {
        this.last_reply_user_id = last_reply_user_id;
    }

    public String getLast_reply_user_login() {
        return last_reply_user_login;
    }

    public void setLast_reply_user_login(String last_reply_user_login) {
        this.last_reply_user_login = last_reply_user_login;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TopicBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", replied_at='" + replied_at + '\'' +
                ", replies_count=" + replies_count +
                ", node_name='" + node_name + '\'' +
                ", node_id=" + node_id +
                ", last_reply_user_id=" + last_reply_user_id +
                ", last_reply_user_login='" + last_reply_user_login + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.created_at);
        dest.writeString(this.updated_at);
        dest.writeString(this.replied_at);
        dest.writeInt(this.replies_count);
        dest.writeString(this.node_name);
        dest.writeInt(this.node_id);
        dest.writeInt(this.last_reply_user_id);
        dest.writeString(this.last_reply_user_login);
        dest.writeString(this.address);
        dest.writeParcelable(this.user, flags);
    }

    public TopicBean() {
    }

    protected TopicBean(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.created_at = in.readString();
        this.updated_at = in.readString();
        this.replied_at = in.readString();
        this.replies_count = in.readInt();
        this.node_name = in.readString();
        this.node_id = in.readInt();
        this.last_reply_user_id = in.readInt();
        this.last_reply_user_login = in.readString();
        this.address = in.readString();
        this.user = in.readParcelable(UserBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TopicBean> CREATOR = new Parcelable.Creator<TopicBean>() {
        @Override
        public TopicBean createFromParcel(Parcel source) {
            return new TopicBean(source);
        }

        @Override
        public TopicBean[] newArray(int size) {
            return new TopicBean[size];
        }
    };
}
