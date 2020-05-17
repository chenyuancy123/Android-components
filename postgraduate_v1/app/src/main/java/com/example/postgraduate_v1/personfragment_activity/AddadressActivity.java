package com.example.postgraduate_v1.personfragment_activity;

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
import com.example.postgraduate_v1.bmob.Shouhuoaddress;
import com.example.postgraduate_v1.mainfragment_activity.Addcommodity_Activity;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddadressActivity extends AppCompatActivity {
    private Button back_shouhuo_address_activity,bu_submit_address;

    private EditText et_add_realname,et_add_telephone,et_add_address;

    private String realname,telephone,address;

    //用户的所有信息
    private SharedPreferences uSharedPreferences;
    private SharedPreferences.Editor uEditor;

    //用户的收货地址
    private SharedPreferences address_SharedPreferences;
    private SharedPreferences.Editor address_Editor;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(AddadressActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_addadress);

        Bmob.initialize(this,"1d06cc42b24841bcda24570e21b24998");

        //存放该用户的所有的信息
        uSharedPreferences = getSharedPreferences("rem_allUserInfo", Context.MODE_PRIVATE);
        uEditor = uSharedPreferences.edit();

        //存放该用户的用户的收货地址
        address_SharedPreferences = getSharedPreferences("rem_UserAddress", Context.MODE_PRIVATE);
        address_Editor = address_SharedPreferences.edit();

        final String objectId = uSharedPreferences.getString("objectId","");

        //初始化组件
        init();

        back_shouhuo_address_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(AddadressActivity.this,Shouhuo_addressActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bu_submit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                   private EditText et_add_realname,et_add_telephone,et_add_address;
//                private String realname,telephone,address;
                realname = et_add_realname.getText().toString();
                telephone = et_add_telephone.getText().toString();
                address = et_add_address.getText().toString();
                Shouhuoaddress shouhuoaddress = new Shouhuoaddress();
                shouhuoaddress.setBuyerId(objectId);
                shouhuoaddress.setReaName(realname);
                shouhuoaddress.setRealTelephone(telephone);
                shouhuoaddress.setRealAddress(address);
                shouhuoaddress.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            address_Editor.putString("objectId",objectId);
                            address_Editor.putString("realname",realname);
                            address_Editor.putString("telephone",telephone);
                            address_Editor.putString("address",address);
                            address_Editor.apply();
                            Toast.makeText(AddadressActivity.this, "添加地址成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(AddadressActivity.this,Shouhuo_addressActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(AddadressActivity.this, "添加地址失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    public void  init(){
        back_shouhuo_address_activity = findViewById(R.id.back_shouhuo_address_activity);
        bu_submit_address = findViewById(R.id.bu_submit_address);
        et_add_realname = findViewById(R.id.et_add_realname);
        et_add_telephone = findViewById(R.id.et_add_telephone);
        et_add_address = findViewById(R.id.et_add_address);
    }
}
