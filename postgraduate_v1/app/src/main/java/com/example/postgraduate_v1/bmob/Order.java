package com.example.postgraduate_v1.bmob;

import cn.bmob.v3.BmobObject;

public class Order extends BmobObject {
    private String publisherId;
    private String buyerId;
    private String pictureBookUrl;
    private String bookName;
    private String buyerName;
    private String buyerTele;
    private String buyerAddress;
    private String totalMoney;



    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getPictureBookUrl() {
        return pictureBookUrl;
    }

    public void setPictureBookUrl(String pictureBookUrl) {
        this.pictureBookUrl = pictureBookUrl;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerTele() {
        return buyerTele;
    }

    public void setBuyerTele(String buyerTele) {
        this.buyerTele = buyerTele;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
}
