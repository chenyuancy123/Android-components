package com.example.postgraduate_v1.entity;

import java.io.Serializable;

public class ChatUser implements Serializable {
    private int chatUserId;
    private String chatUserName;
    private String chatUserGrade;
    private String chatUserDegree;
    private String chatUserSchool;
    private String chatUserMajor;
    private String chatUserTopPicture;

    public int getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(int chatUserId) {
        this.chatUserId = chatUserId;
    }

    public String getChatUserName() {
        return chatUserName;
    }

    public void setChatUserName(String chatUserName) {
        this.chatUserName = chatUserName;
    }

    public String getChatUserGrade() {
        return chatUserGrade;
    }

    public void setChatUserGrade(String chatUserGrade) {
        this.chatUserGrade = chatUserGrade;
    }

    public String getChatUserDegree() {
        return chatUserDegree;
    }

    public void setChatUserDegree(String chatUserDegree) {
        this.chatUserDegree = chatUserDegree;
    }

    public String getChatUserSchool() {
        return chatUserSchool;
    }

    public void setChatUserSchool(String chatUserSchool) {
        this.chatUserSchool = chatUserSchool;
    }

    public String getChatUserMajor() {
        return chatUserMajor;
    }

    public void setChatUserMajor(String chatUserMajor) {
        this.chatUserMajor = chatUserMajor;
    }

    public String getChatUserTopPicture() {
        return chatUserTopPicture;
    }

    public void setChatUserTopPicture(String chatUserTopPicture) {
        this.chatUserTopPicture = chatUserTopPicture;
    }
}
