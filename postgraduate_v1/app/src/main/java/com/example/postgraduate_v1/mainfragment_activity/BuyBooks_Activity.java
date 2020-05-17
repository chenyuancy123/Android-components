package com.example.postgraduate_v1.mainfragment_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.bmob.Order;
import com.example.postgraduate_v1.bmob.Userinfo;
import com.example.postgraduate_v1.utilityClass.ApplicationController;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class BuyBooks_Activity extends AppCompatActivity {
    private Button back_commodity_detail_activity,bu_order_book_purchase;

    private TextView order_username,order_telephone,order_address,order_name,order_price;
    private NetworkImageView order_picture;

    private String shengxuMoney01;

    //存放该用户的所有的信息
    private SharedPreferences uSharedPreferences;
    private SharedPreferences.Editor uEditor;
    private String objectId;

    //用户的收货地址
    private SharedPreferences address_SharedPreferences;
    private SharedPreferences.Editor address_Editor;

    //存放该用户的学币信息
    private SharedPreferences xuebi_SharedPreferences;
    private SharedPreferences.Editor xuebi_Editor;
    private String xuebi01,xuebi02;

    private ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(BuyBooks_Activity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_buy_books_);

        if(imageLoader==null){
            imageLoader= ApplicationController.getInstance().getImageLoader();
        }

        //存放该用户的所有的信息
        uSharedPreferences = getSharedPreferences("rem_allUserInfo",Context.MODE_PRIVATE);
        uEditor = uSharedPreferences.edit();
        objectId = uSharedPreferences.getString("objectId","");

        //存放该用户的所有的信息
        xuebi_SharedPreferences = getSharedPreferences("rem_UserXuebi", Context.MODE_PRIVATE);
        xuebi_Editor = xuebi_SharedPreferences.edit();
        xuebi01 = xuebi_SharedPreferences.getString("xuebi01","");
        xuebi02 = xuebi_SharedPreferences.getString("xuebi02","");

        //存放该用户的用户的收货地址
        address_SharedPreferences = getSharedPreferences("rem_UserAddress", Context.MODE_PRIVATE);
        address_Editor = address_SharedPreferences.edit();

        final String realname,telephone,address;
        realname = address_SharedPreferences.getString("realname","");
        telephone = address_SharedPreferences.getString("telephone","");
        address = address_SharedPreferences.getString("address","");

        //初始化组件
        init();

        final String publisherId=getIntent().getStringExtra("publisherId");
        final String commodityPicture=getIntent().getStringExtra("commodityPicture");
        final String commodityName=getIntent().getStringExtra("commodityName");
        final String commodityPrice=getIntent().getStringExtra("commodityPrice");
        final String commodityDescribe=getIntent().getStringExtra("commodityDescribe");

//        private TextView order_username,order_telephone,order_address,order_name,order_price;
        order_username.setText(realname);
        order_telephone.setText(telephone);
        order_address.setText(address);

        order_picture.setImageUrl(commodityPicture,imageLoader);
        order_name.setText(commodityName);
        order_price.setText(commodityPrice);
//        book_Describe.setText(commodityDescribe);

        back_commodity_detail_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(BuyBooks_Activity.this, Commodity_xiangxi_Activity.class);
                intent.putExtra("publisherId",publisherId);
                intent.putExtra("commodityPicture",commodityPicture);
                intent.putExtra("commodityName",commodityName);
                intent.putExtra("commodityPrice",commodityPrice);
                intent.putExtra("commodityDescribe",commodityDescribe);
                startActivity(intent);
                finish();
            }
        });

        bu_order_book_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer bookPrice = Integer.valueOf(commodityPrice);
                Integer xuebi01_02 = Integer.valueOf(xuebi01);
                Log.i("BuyBooks_Activity",bookPrice+"");
                Log.i("BuyBooks_Activity",xuebi01_02+"");
                if(xuebi01_02<bookPrice){
                    Toast.makeText(BuyBooks_Activity.this, "学币不够，请前去充值！", Toast.LENGTH_SHORT).show();
                }else{
                    Integer shengxuMoney = xuebi01_02-bookPrice;
                    shengxuMoney01 = String.valueOf(shengxuMoney);

                    Userinfo userinfo = new Userinfo();
                    userinfo.setXuebi01(shengxuMoney01);
                    userinfo.update(objectId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                xuebi_Editor.putString("xuebi01",shengxuMoney01);
                                xuebi_Editor.putString("xuebi02",xuebi02);
                                xuebi_Editor.apply();

                                Order order = new Order();
                                order.setBuyerId(objectId);
                                order.setPublisherId(publisherId);
                                order.setBuyerName(realname);
                                order.setBuyerTele(telephone);
                                order.setBuyerAddress(address);
                                order.setPictureBookUrl(commodityPicture);
                                order.setBookName(commodityName);
                                order.setTotalMoney(commodityPrice);
                                order.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if(e==null){
                                            Toast.makeText(BuyBooks_Activity.this, "购买成功！", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent();
                                            intent.setClass(BuyBooks_Activity.this,PurchaseBooks_successActivity.class);
                                            startActivity(intent);

                                        }else{
                                            Toast.makeText(BuyBooks_Activity.this, "购买失败！", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(BuyBooks_Activity.this, "购买失败！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }

    public void init(){
        back_commodity_detail_activity = findViewById(R.id.back_commodity_detail_activity);
        bu_order_book_purchase = findViewById(R.id.bu_order_book_purchase);
        order_username = findViewById(R.id.order_username);
        order_telephone = findViewById(R.id.order_telephone);
        order_address = findViewById(R.id.order_address);
        order_name = findViewById(R.id.order_name);
        order_price = findViewById(R.id.order_price);
        order_picture = findViewById(R.id.order_picture);
    }
}
