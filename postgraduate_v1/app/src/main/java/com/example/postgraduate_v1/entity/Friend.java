package com.example.postgraduate_v1.entity;

import java.io.Serializable;

public class Friend implements Serializable {
    private String userId;
    private String name;
    private String portraitUri;

    public Friend(String s, String s2, String s1) {
        userId = s;
        name = s2;
        portraitUri = s1;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }
}
