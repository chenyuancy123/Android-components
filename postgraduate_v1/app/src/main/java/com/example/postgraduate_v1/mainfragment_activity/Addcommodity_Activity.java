package com.example.postgraduate_v1.mainfragment_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.postgraduate_v1.DynamicEdit_Activity;
import com.example.postgraduate_v1.MainActivity;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.bmob.Commodity;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Addcommodity_Activity extends AppCompatActivity {

    private Button bu_backshopActivity,bu_add_aBook;
    private EditText et_commodityPicture,et_commodityName,et_commodityDescribe,et_commodityPrice;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    private String publisherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(Addcommodity_Activity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_addcommodity_);

        Bmob.initialize(this,"1d06cc42b24841bcda24570e21b24998");

        //初始化组件
        init();

        mSharedPreferences = getSharedPreferences("rem_allUserInfo", Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();

        publisherId = mSharedPreferences.getString("objectId","");

        bu_add_aBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commodity commodity = new Commodity();
                commodity.setPublisherId(publisherId);
                commodity.setCommodityPicture(et_commodityPicture.getText().toString());
                commodity.setCommodityName(et_commodityName.getText().toString());
                commodity.setCommodityDescribe(et_commodityDescribe.getText().toString());
                commodity.setCommodityPrice(et_commodityPrice.getText().toString());
                commodity.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(Addcommodity_Activity.this,"添加商品成功！",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(Addcommodity_Activity.this,ShopActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Addcommodity_Activity.this,"添加商品失败！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        bu_backshopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(Addcommodity_Activity.this, ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){

        bu_backshopActivity = findViewById(R.id.back_shopactivity);
        bu_add_aBook = findViewById(R.id.bu_add_aBook);
        et_commodityPicture = findViewById(R.id.et_commodityPicture);
        et_commodityName = findViewById(R.id.et_commodityName);
        et_commodityDescribe = findViewById(R.id.et_commodityDescribe);
        et_commodityPrice = findViewById(R.id.et_commodityPrice);


    }
}
