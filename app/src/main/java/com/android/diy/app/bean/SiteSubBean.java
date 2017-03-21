package com.android.diy.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cheng on 2017/3/1.
 */
public class SiteSubBean implements Parcelable {

    private String name;
    private String url;
    private String avatar_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "SiteSubBean{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeString(this.avatar_url);
    }

    public SiteSubBean() {
    }

    protected SiteSubBean(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
        this.avatar_url = in.readString();
    }

    public static final Parcelable.Creator<SiteSubBean> CREATOR = new Parcelable.Creator<SiteSubBean>() {
        @Override
        public SiteSubBean createFromParcel(Parcel source) {
            return new SiteSubBean(source);
        }

        @Override
        public SiteSubBean[] newArray(int size) {
            return new SiteSubBean[size];
        }
    };
}
