package com.morse.gank.beans;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：Morse on 2015/11/9 11:41
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class Bean {
    /**
     * who : Andrew Liu
     * publishedAt : 2015-10-26T03:52:58.742Z
     * desc : iOS 开发者旅途中的指南针 - LLDB 调试技术
     * type : iOS
     * url : http://swiftcafe.io/2015/09/05/lldb-debug/?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io
     * used : true
     * objectId : 562b04f300b0d1db627f4a6a
     * createdAt : 2015-10-24T04:11:31.720Z
     * updatedAt : 2015-10-26T03:52:59.544Z
     */

    @SerializedName("who")
    private String who;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("desc")
    private String desc;
    @SerializedName("type")
    private String type;
    @SerializedName("url")
    private String url;
    @SerializedName("used")
    private boolean used;
    @SerializedName("objectId")
    private String objectId;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;

    public void setWho(String who) {
        this.who = who;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getWho() {
        return who;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean getUsed() {
        return used;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "who='" + who + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", desc='" + desc + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", objectId='" + objectId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
