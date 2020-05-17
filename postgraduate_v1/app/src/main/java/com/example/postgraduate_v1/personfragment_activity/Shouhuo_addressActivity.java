package com.example.postgraduate_v1.personfragment_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.postgraduate_v1.MainActivity;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

public class Shouhuo_addressActivity extends AppCompatActivity {
    private Button bu_addshouhuo_address,bu_back_main_activity;
    private TextView tv_show_realname,tv_show_telephone,tv_show_realaddress;

    //用户的收货地址
    private SharedPreferences address_SharedPreferences;
    private SharedPreferences.Editor address_Editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(Shouhuo_addressActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_shouhuo_address);

        //存放该用户的用户的收货地址
        address_SharedPreferences = getSharedPreferences("rem_UserAddress", Context.MODE_PRIVATE);
        address_Editor = address_SharedPreferences.edit();

        String realname,telephone,address;
        realname = address_SharedPreferences.getString("realname","");
        telephone = address_SharedPreferences.getString("telephone","");
        address = address_SharedPreferences.getString("address","");

        //初始化组件
        init();

        //        private TextView tv_show_realname,tv_show_telephone,tv_show_realaddress;
        tv_show_realname.setText(realname);
        tv_show_telephone.setText(telephone);
        tv_show_realaddress.setText(address);

        bu_back_main_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(Shouhuo_addressActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bu_addshouhuo_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Shouhuo_addressActivity.this,AddadressActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        bu_addshouhuo_address = findViewById(R.id.bu_addshouhuo_address);
        bu_back_main_activity = findViewById(R.id.bu_back_main_activity);


        tv_show_realname = findViewById(R.id.tv_show_realname);
        tv_show_telephone = findViewById(R.id.tv_show_telephone);
        tv_show_realaddress = findViewById(R.id.tv_show_realaddress);
    }
}
