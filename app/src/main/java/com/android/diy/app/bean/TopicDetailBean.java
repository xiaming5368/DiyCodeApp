package com.android.diy.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cheng on 2017/3/7.
 */
public class TopicDetailBean implements Parcelable {

    @SerializedName("id") private int id;
    @SerializedName("title") private String title;
    @SerializedName("created_at") private String createdAt;
    @SerializedName("updated_at") private String updatedAt;
    @SerializedName("replied_at") private String repliedAt;
    @SerializedName("replies_count") private int repliesCount;
    @SerializedName("node_name") private String nodeName;
    @SerializedName("node_id") private int nodeId;
    @SerializedName("last_reply_user_id") private int lastReplyUserId;
    @SerializedName("last_reply_user_login") private String lastReplyUserLogin;
    @SerializedName("user") private UserBean user;
    @SerializedName("deleted") private boolean deleted;
    @SerializedName("excellent") private boolean excellent;
    @SerializedName("body") private String body;
    @SerializedName("body_html") private String bodyHtml;
    @SerializedName("hits") private int hits;
    @SerializedName("likes_count") private int likesCount;
    @SerializedName("suggested_at") private String suggestedAt;

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

    public String getRepliedAt() {
        return repliedAt;
    }

    public void setRepliedAt(String repliedAt) {
        this.repliedAt = repliedAt;
    }

    public int getRepliesCount() {
        return repliesCount;
    }

    public void setRepliesCount(int repliesCount) {
        this.repliesCount = repliesCount;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getLastReplyUserId() {
        return lastReplyUserId;
    }

    public void setLastReplyUserId(int lastReplyUserId) {
        this.lastReplyUserId = lastReplyUserId;
    }

    public String getLastReplyUserLogin() {
        return lastReplyUserLogin;
    }

    public void setLastReplyUserLogin(String lastReplyUserLogin) {
        this.lastReplyUserLogin = lastReplyUserLogin;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isExcellent() {
        return excellent;
    }

    public void setExcellent(boolean excellent) {
        this.excellent = excellent;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public String getSuggestedAt() {
        return suggestedAt;
    }

    public void setSuggestedAt(String suggestedAt) {
        this.suggestedAt = suggestedAt;
    }

    @Override
    public String toString() {
        return "TopicDetailBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", repliedAt='" + repliedAt + '\'' +
                ", repliesCount=" + repliesCount +
                ", nodeName='" + nodeName + '\'' +
                ", nodeId=" + nodeId +
                ", lastReplyUserId=" + lastReplyUserId +
                ", lastReplyUserLogin='" + lastReplyUserLogin + '\'' +
                ", user=" + user +
                ", deleted=" + deleted +
                ", excellent=" + excellent +
                ", body='" + body + '\'' +
                ", bodyHtml='" + bodyHtml + '\'' +
                ", hits=" + hits +
                ", likesCount=" + likesCount +
                ", suggestedAt='" + suggestedAt + '\'' +
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
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeString(this.repliedAt);
        dest.writeInt(this.repliesCount);
        dest.writeString(this.nodeName);
        dest.writeInt(this.nodeId);
        dest.writeInt(this.lastReplyUserId);
        dest.writeString(this.lastReplyUserLogin);
        dest.writeParcelable(this.user, flags);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
        dest.writeByte(this.excellent ? (byte) 1 : (byte) 0);
        dest.writeString(this.body);
        dest.writeString(this.bodyHtml);
        dest.writeInt(this.hits);
        dest.writeInt(this.likesCount);
        dest.writeString(this.suggestedAt);
    }

    public TopicDetailBean() {
    }

    protected TopicDetailBean(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.repliedAt = in.readString();
        this.repliesCount = in.readInt();
        this.nodeName = in.readString();
        this.nodeId = in.readInt();
        this.lastReplyUserId = in.readInt();
        this.lastReplyUserLogin = in.readString();
        this.user = in.readParcelable(UserBean.class.getClassLoader());
        this.deleted = in.readByte() != 0;
        this.excellent = in.readByte() != 0;
        this.body = in.readString();
        this.bodyHtml = in.readString();
        this.hits = in.readInt();
        this.likesCount = in.readInt();
        this.suggestedAt = in.readString();
    }

    public static final Parcelable.Creator<TopicDetailBean> CREATOR = new Parcelable.Creator<TopicDetailBean>() {
        @Override
        public TopicDetailBean createFromParcel(Parcel source) {
            return new TopicDetailBean(source);
        }

        @Override
        public TopicDetailBean[] newArray(int size) {
            return new TopicDetailBean[size];
        }
    };
}
