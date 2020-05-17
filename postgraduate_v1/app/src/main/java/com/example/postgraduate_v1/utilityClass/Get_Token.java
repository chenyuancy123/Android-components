package com.example.postgraduate_v1.utilityClass;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Get_Token {
    public static String getRongCloud_Token(String userId,String username,String portraitUri){
        final String[] token1 = new String[1];
        String result_token = "";

        String url = "https://api-cn.ronghub.com/user/getToken.json";
        String App_Key = "bmdehs6pba7xs"; //开发者平台分配的 App Key。
        String App_Secret = "0iFhkceLeGa";
        String Timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
        String Nonce = String.valueOf(Math.floor(Math.random() * 1000000));//随机数，无长度限制。
        String Signature = sha1(App_Secret + Nonce + Timestamp);//数据签名。
        Log.i("Get_Token","Signature:"+Signature);

        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("userId", userId);
        builder.add("name", username);
        builder.add("portraitUri", portraitUri);
        FormBody formBody = builder.build();
//        RequestBody body = new FormBody.Builder()
//                .add("userId", userId)
//                .add("name", username)
//                .add("portraitUri", portraitUri)
//                .build();
        Request request = new Request.Builder()
                .addHeader("App-Key",App_Key)
                .addHeader("Timestamp",Timestamp)
                .addHeader("Nonce",Nonce)
                .addHeader("Signature",Signature)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .url(url)
                .post(formBody)
//                .post(body)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                System.out.println(e.getMessage());
                Log.i("Get_Token",e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("Get_Token",response.code()+"   response.code");
                if(response.code() >= 200 && response.code() < 300) {
//                    System.out.println(response.body().string());
//                    Log.i("Get_Token",response.body().string());
                    JSONObject object = JSON.parseObject(response.body().string());
                    String token = String.valueOf(object.get("token"));
//                    result_token = token;
                    Log.i("Get_Token","token1  "+token);
                    token1[0] = token;
                    Log.i("Get_Token","token2  "+token1[0]);
//                    Integer.valueOf();
//                    int a;
//                    Integer a1 = new Integer(5);

                }
            }
        });

        return token1[0];
    }

    //SHA1加密//http://www.rongcloud.cn/docs/server.html#通用_API_接口签名规则
    private static String sha1(String data){
        StringBuffer buf = new StringBuffer();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(data.getBytes());
            byte[] bits = md.digest();
            for(int i = 0 ; i < bits.length;i++){
                int a = bits[i];
                if(a<0) a+=256;
                if(a<16) buf.append("0");
                buf.append(Integer.toHexString(a));
            }
        }catch(Exception e){

        }
        return buf.toString();
    }



}
