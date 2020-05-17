package com.example.postgraduate_v1.entity;

import java.io.Serializable;

public class Dynamic implements Serializable {
    private String posterTopPicture; //动态人的头像
    private String posterName;  //动态人的姓名
    private String posterSchool; //动态人的学校
    private String posterMajor;  //动态人的专业
    private String postTime;  //动态人的发动态的时间
    private String postContext;  //动态人的发的内容

    public String getPosterMajor() {
        return posterMajor;
    }

    public void setPosterMajor(String posterMajor) {
        this.posterMajor = posterMajor;
    }

    public String getPosterSchool() {
        return posterSchool;
    }

    public void setPosterSchool(String posterSchool) {
        this.posterSchool = posterSchool;
    }

    public String getPosterTopPicture() {
        return posterTopPicture;
    }

    public void setPosterTopPicture(String posterTopPicture) {
        this.posterTopPicture = posterTopPicture;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostContext() {
        return postContext;
    }

    public void setPostContext(String postContext) {
        this.postContext = postContext;
    }
}
