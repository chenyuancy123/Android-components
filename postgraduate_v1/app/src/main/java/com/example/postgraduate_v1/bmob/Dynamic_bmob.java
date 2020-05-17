package com.example.postgraduate_v1.bmob;

import cn.bmob.v3.BmobObject;

public class Dynamic_bmob extends BmobObject {

    private String postId;
    private String posterTopPicture;
    private String posterName;
    private String posterSchool;
    private String posterMajor;
    private String postContext;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
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

    public String getPosterSchool() {
        return posterSchool;
    }

    public void setPosterSchool(String posterSchool) {
        this.posterSchool = posterSchool;
    }

    public String getPosterMajor() {
        return posterMajor;
    }

    public void setPosterMajor(String posterMajor) {
        this.posterMajor = posterMajor;
    }

    public String getPostContext() {
        return postContext;
    }

    public void setPostContext(String postContext) {
        this.postContext = postContext;
    }
}
