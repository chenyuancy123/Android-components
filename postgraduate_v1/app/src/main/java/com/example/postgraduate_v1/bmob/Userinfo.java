package com.example.postgraduate_v1.bmob;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

public class Userinfo extends BmobObject {
   // private String objectId;  //id
    private String username; //昵称
    private String telephonenumber; //电话号码
    private String password;//密码
    private String userInfoPicture;  //头像
    private String idiograph;  //个人签名
    private String userInfoGrade;//年级
    private String userInfoDegree;//学历
    private String userInfoSchool;//学校
    private String userInfoMajor;//专业
    private String xuebi01;   //学币01
    private String xuebi02;   //学币02
    private String token;   //token

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //    @Override
//    public String getObjectId() {
//        return objectId;
//    }
//
//    @Override
//    public void setObjectId(String objectId) {
//        this.objectId = objectId;
//    }


    public String getXuebi01() {
        return xuebi01;
    }

    public void setXuebi01(String xuebi01) {
        this.xuebi01 = xuebi01;
    }

    public String getXuebi02() {
        return xuebi02;
    }

    public void setXuebi02(String xuebi02) {
        this.xuebi02 = xuebi02;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(String telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserInfoPicture() {
        return userInfoPicture;
    }

    public void setUserInfoPicture(String userInfoPicture) {
        this.userInfoPicture = userInfoPicture;
    }

    public String getIdiograph() {
        return idiograph;
    }

    public void setIdiograph(String idiograph) {
        this.idiograph = idiograph;
    }

    public String getUserInfoGrade() {
        return userInfoGrade;
    }

    public void setUserInfoGrade(String userInfoGrade) {
        this.userInfoGrade = userInfoGrade;
    }

    public String getUserInfoDegree() {
        return userInfoDegree;
    }

    public void setUserInfoDegree(String userInfoDegree) {
        this.userInfoDegree = userInfoDegree;
    }

    public String getUserInfoSchool() {
        return userInfoSchool;
    }

    public void setUserInfoSchool(String userInfoSchool) {
        this.userInfoSchool = userInfoSchool;
    }

    public String getUserInfoMajor() {
        return userInfoMajor;
    }

    public void setUserInfoMajor(String userInfoMajor) {
        this.userInfoMajor = userInfoMajor;
    }
}
