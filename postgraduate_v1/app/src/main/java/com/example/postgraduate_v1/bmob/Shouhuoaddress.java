package com.example.postgraduate_v1.bmob;

import cn.bmob.v3.BmobObject;

public class Shouhuoaddress extends BmobObject {
    private String buyerId;
    private String reaName;
    private String realTelephone;
    private String realAddress;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getReaName() {
        return reaName;
    }

    public void setReaName(String reaName) {
        this.reaName = reaName;
    }

    public String getRealTelephone() {
        return realTelephone;
    }

    public void setRealTelephone(String realTelephone) {
        this.realTelephone = realTelephone;
    }

    public String getRealAddress() {
        return realAddress;
    }

    public void setRealAddress(String realAddress) {
        this.realAddress = realAddress;
    }
}
