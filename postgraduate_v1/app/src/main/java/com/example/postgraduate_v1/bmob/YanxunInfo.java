package com.example.postgraduate_v1.bmob;

import cn.bmob.v3.BmobObject;

public class YanxunInfo extends BmobObject {
    private String yanxunImage;
    private String yanxunName;
    private String yanxunUrl;

    public String getYanxunImage() {
        return yanxunImage;
    }

    public void setYanxunImage(String yanxunImage) {
        this.yanxunImage = yanxunImage;
    }

    public String getYanxunName() {
        return yanxunName;
    }

    public void setYanxunName(String yanxunName) {
        this.yanxunName = yanxunName;
    }

    public String getYanxunUrl() {
        return yanxunUrl;
    }

    public void setYanxunUrl(String yanxunUrl) {
        this.yanxunUrl = yanxunUrl;
    }
}
