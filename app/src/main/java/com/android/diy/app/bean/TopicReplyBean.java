package com.android.diy.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cheng on 2017/3/13.
 */
public class TopicReplyBean implements Parcelable {

    @SerializedName("id") private int id;
    @SerializedName("body_html") private String bodyHtml;
    @SerializedName("created_at") private String createdAt;
    @SerializedName("updated_at") private String updatedAt;
    @SerializedName("deleted") private boolean deleted;
    @SerializedName("topic_id") private int topicId;
    @SerializedName("user") private UserBean user;
    @SerializedName("likes_count") private int likesCount;

    @Override
    public String toString() {
        return "TopicReplyBean{" +
                "id=" + id +
                ", bodyHtml='" + bodyHtml + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", deleted=" + deleted +
                ", topicId=" + topicId +
                ", user=" + user +
                ", likesCount=" + likesCount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.bodyHtml);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeInt(this.topicId);
        dest.writeParcelable(this.user, flags);
        dest.writeInt(this.likesCount);
    }

    public TopicReplyBean() {
    }

    protected TopicReplyBean(Parcel in) {
        this.id = in.readInt();
        this.bodyHtml = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.deleted = in.readByte() != 0;
        this.topicId = in.readInt();
        this.user = in.readParcelable(UserBean.class.getClassLoader());
        this.likesCount = in.readInt();
    }

    public static final Parcelable.Creator<TopicReplyBean> CREATOR = new Parcelable.Creator<TopicReplyBean>() {
        @Override
        public TopicReplyBean createFromParcel(Parcel source) {
            return new TopicReplyBean(source);
        }

        @Override
        public TopicReplyBean[] newArray(int size) {
            return new TopicReplyBean[size];
        }
    };
}
