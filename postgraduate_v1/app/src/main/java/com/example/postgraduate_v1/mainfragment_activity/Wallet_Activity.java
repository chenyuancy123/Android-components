package com.example.postgraduate_v1.mainfragment_activity;

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

public class Wallet_Activity extends AppCompatActivity {

    private Button back_toMain_fragment;
    private TextView tv_xuebi_01,tv_xuebi_02,chongzhi_xuebi;

    //存放该用户的学币信息
    private SharedPreferences xuebi_SharedPreferences;
    private SharedPreferences.Editor xuebi_Editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(Wallet_Activity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_wallet_);

        //存放该用户的所有的信息
        xuebi_SharedPreferences = getSharedPreferences("rem_UserXuebi", Context.MODE_PRIVATE);
        xuebi_Editor = xuebi_SharedPreferences.edit();

        //初始化组件
        init();

        //取该用户的学币信息
        String xuebi01 = xuebi_SharedPreferences.getString("xuebi01","");
        String xuebi02 = xuebi_SharedPreferences.getString("xuebi02","");

        //给组件赋学币信息
        tv_xuebi_01.setText(xuebi01);
        tv_xuebi_02.setText(xuebi02);

        chongzhi_xuebi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Wallet_Activity.this, Chongzhi_XuebiActivity.class);
                startActivity(intent);
            }
        });

        back_toMain_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(Wallet_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        back_toMain_fragment = findViewById(R.id.back_toMain_fragment);
        tv_xuebi_01 = findViewById(R.id.tv_xuebi_01);
        tv_xuebi_02 = findViewById(R.id.tv_xuebi_02);
        chongzhi_xuebi = findViewById(R.id.chongzhi_xuebi);
    }
}
