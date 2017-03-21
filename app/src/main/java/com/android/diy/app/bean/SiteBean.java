package com.android.diy.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by cheng on 2017/3/1.
 */
public class SiteBean implements Parcelable {

    private String name;
    private String id;
    private List<SiteSubBean> sites;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SiteSubBean> getSites() {
        return sites;
    }

    public void setSites(List<SiteSubBean> sites) {
        this.sites = sites;
    }

    @Override
    public String toString() {
        return "SiteBean{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", sites=" + sites +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeTypedList(this.sites);
    }

    public SiteBean() {
    }

    protected SiteBean(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.sites = in.createTypedArrayList(SiteSubBean.CREATOR);
    }

    public static final Parcelable.Creator<SiteBean> CREATOR = new Parcelable.Creator<SiteBean>() {
        @Override
        public SiteBean createFromParcel(Parcel source) {
            return new SiteBean(source);
        }

        @Override
        public SiteBean[] newArray(int size) {
            return new SiteBean[size];
        }
    };
}
