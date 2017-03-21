package com.android.diy.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cheng on 2017/3/7.
 */
public class NodeBean implements Parcelable {

    @SerializedName("id") private int id;
    @SerializedName("name") private String name;
    @SerializedName("topics_count") private int topicsCount;
    @SerializedName("summary") private String summary;
    @SerializedName("section_id") private int sectionId;
    @SerializedName("sort") private int sort;
    @SerializedName("section_name") private String sectionName;
    @SerializedName("updated_at") private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopicsCount() {
        return topicsCount;
    }

    public void setTopicsCount(int topicsCount) {
        this.topicsCount = topicsCount;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "NodeBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", topicsCount=" + topicsCount +
                ", summary='" + summary + '\'' +
                ", sectionId=" + sectionId +
                ", sort=" + sort +
                ", sectionName='" + sectionName + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.topicsCount);
        dest.writeString(this.summary);
        dest.writeInt(this.sectionId);
        dest.writeInt(this.sort);
        dest.writeString(this.sectionName);
        dest.writeString(this.updatedAt);
    }

    public NodeBean() {
    }

    protected NodeBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.topicsCount = in.readInt();
        this.summary = in.readString();
        this.sectionId = in.readInt();
        this.sort = in.readInt();
        this.sectionName = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<NodeBean> CREATOR = new Parcelable.Creator<NodeBean>() {
        @Override
        public NodeBean createFromParcel(Parcel source) {
            return new NodeBean(source);
        }

        @Override
        public NodeBean[] newArray(int size) {
            return new NodeBean[size];
        }
    };
}
