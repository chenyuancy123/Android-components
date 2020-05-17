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

import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.bmob.Userinfo;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class Chongzhi_XuebiActivity extends AppCompatActivity {

    private Button back_toWalletActivity,confirm_money;

    private EditText money_number;

    //存放该用户的所有的信息
    private SharedPreferences uSharedPreferences;
    private SharedPreferences.Editor uEditor;
    private String objectId;

    //存放该用户的学币信息
    private SharedPreferences xuebi_SharedPreferences;
    private SharedPreferences.Editor xuebi_Editor;
    private String xuebi01,xuebi02;

    private String total01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(Chongzhi_XuebiActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_chongzhi__xuebi);

        Bmob.initialize(this,"1d06cc42b24841bcda24570e21b24998");

        //存放该用户的所有的信息
        xuebi_SharedPreferences = getSharedPreferences("rem_UserXuebi", Context.MODE_PRIVATE);
        xuebi_Editor = xuebi_SharedPreferences.edit();

        //存放该用户的所有的信息
        uSharedPreferences = getSharedPreferences("rem_allUserInfo",Context.MODE_PRIVATE);
        uEditor = uSharedPreferences.edit();

        //初始化组件
        init();

        xuebi01 = xuebi_SharedPreferences.getString("xuebi01","");
        xuebi02 = xuebi_SharedPreferences.getString("xuebi02","");

        objectId = uSharedPreferences.getString("objectId","");


        confirm_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money_zhi = money_number.getText().toString();
                Integer money_zhi01 = Integer.valueOf(money_zhi);
                Integer xuebi01_02 = Integer.valueOf(xuebi01);
                Integer total = money_zhi01+xuebi01_02;
                total01 = String.valueOf(total);

                Userinfo userinfo = new Userinfo();
                userinfo.setXuebi01(total01);
                userinfo.update(objectId, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            xuebi_Editor.putString("xuebi01",total01);
                            xuebi_Editor.putString("xuebi02",xuebi02);
                            xuebi_Editor.apply();
                            Toast.makeText(Chongzhi_XuebiActivity.this, "充值成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(Chongzhi_XuebiActivity.this,Wallet_Activity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Chongzhi_XuebiActivity.this, "充值失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                Toast.makeText(Chongzhi_XuebiActivity.this, total01, Toast.LENGTH_SHORT).show();
            }
        });



        back_toWalletActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(Chongzhi_XuebiActivity.this, Wallet_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        back_toWalletActivity = findViewById(R.id.back_toWalletActivity);
        confirm_money = findViewById(R.id.confirm_money);
        money_number = findViewById(R.id.money_number);
    }

}
