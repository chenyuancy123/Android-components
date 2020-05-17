package com.example.postgraduate_v1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.postgraduate_v1.bmob.Dynamic_bmob;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class DynamicEdit_Activity extends AppCompatActivity {
    private EditText et_postContent;
    private TextView tv_postDynamic;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    private String userInfoPicture,username,userInfoSchool,userInfoMajor;

    private String posterId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(DynamicEdit_Activity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_dynamic_edit_);

        Bmob.initialize(this,"1d06cc42b24841bcda24570e21b24998");

        //初始化组件
        init();

        mSharedPreferences = getSharedPreferences("rem_allUserInfo", Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();

//        private String userInfoPicture,username,userInfoSchool,userInfoMajor;
        posterId = mSharedPreferences.getString("objectId","");

        userInfoPicture = mSharedPreferences.getString("userInfoPicture","");
        username = mSharedPreferences.getString("username","");
        userInfoSchool = mSharedPreferences.getString("userInfoSchool","");
        userInfoMajor = mSharedPreferences.getString("userInfoMajor","");



        tv_postDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postContent = et_postContent.getText().toString();
                Dynamic_bmob dynamic_bmob = new Dynamic_bmob();
                dynamic_bmob.setPostId(posterId);
                dynamic_bmob.setPosterTopPicture(userInfoPicture);
                dynamic_bmob.setPosterName(username);
                dynamic_bmob.setPosterSchool(userInfoSchool);
                dynamic_bmob.setPosterMajor(userInfoMajor);
                dynamic_bmob.setPostContext(postContent);
                dynamic_bmob.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(DynamicEdit_Activity.this,"发布成功！",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(DynamicEdit_Activity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
//                            Log.i("12345","success");
                        }else{
//                            Log.i("12345","failed");
                            Toast.makeText(DynamicEdit_Activity.this,"发布成功！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    public void init(){

//        private EditText et_postContent;
//        private TextView tv_postDynamic;

        et_postContent = findViewById(R.id.et_postContent);
        tv_postDynamic = findViewById(R.id.tv_postDynamic);

    }
}
