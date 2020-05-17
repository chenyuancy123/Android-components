package com.example.postgraduate_v1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.postgraduate_v1.bmob.Userinfo;
import com.example.postgraduate_v1.login_register.LoginActivity;
import com.example.postgraduate_v1.utilityClass.ApplicationController;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UserInfoEdit_Activity extends AppCompatActivity {

    private EditText PersonInfo_TicPicture,PersonInfoName,PersonInfo_idolgragh,
            PersonInfo_grade,PersonInfo_degree,PersonInfo_school,PersonInfo_major;
    private Button PersonInfo_submit;

    private String password,objectId,telephonenumber;

//    private NetworkImageView UserInfoPicture;

//    private ImageLoader imageLoader;
//
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(UserInfoEdit_Activity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_user_info_edit_);

        Bmob.initialize(this,"1d06cc42b24841bcda24570e21b24998");


//        if (imageLoader == null)
//            imageLoader = ApplicationController.getInstance().getImageLoader();
        mSharedPreferences = getSharedPreferences("rem_allUserInfo", Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();

        password = mSharedPreferences.getString("password","");
        objectId = mSharedPreferences.getString("objectId","");
        telephonenumber = mSharedPreferences.getString("telephonenumber","");

//        String userInfoPicture =mSharedPreferences.getString("userInfoPicture","");
//        Log.i("123",userInfoPicture+"   1");

        //初始化组件
        init();

        //首先复制到EditText中
        PersonInfo_TicPicture.setText(mSharedPreferences.getString("userInfoPicture",""));
        PersonInfoName.setText(mSharedPreferences.getString("username",""));
        PersonInfo_idolgragh.setText(mSharedPreferences.getString("idiograph",""));
        PersonInfo_grade.setText(mSharedPreferences.getString("userInfoGrade",""));
        PersonInfo_degree.setText(mSharedPreferences.getString("userInfoDegree",""));
        PersonInfo_school.setText(mSharedPreferences.getString("userInfoSchool",""));
        PersonInfo_major.setText(mSharedPreferences.getString("userInfoMajor",""));


        PersonInfo_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这边获取EditText里面的值
                //修改个人信息
                Userinfo userinfo = new Userinfo();
                userinfo.setUserInfoPicture(PersonInfo_TicPicture.getText().toString());
                userinfo.setUsername(PersonInfoName.getText().toString());
                userinfo.setIdiograph(PersonInfo_idolgragh.getText().toString());
                userinfo.setUserInfoGrade(PersonInfo_grade.getText().toString());
                userinfo.setUserInfoDegree(PersonInfo_degree.getText().toString());
                userinfo.setUserInfoSchool(PersonInfo_school.getText().toString());
                userinfo.setUserInfoMajor(PersonInfo_major.getText().toString());
                userinfo.update(objectId, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            editor.putString("objectId", objectId);
                            editor.putString("username",PersonInfoName.getText().toString());
                            editor.putString("telephonenumber", telephonenumber);
                            editor.putString("password", password);
                            editor.putString("userInfoPicture", PersonInfo_TicPicture.getText().toString());
                            editor.putString("idiograph", PersonInfo_idolgragh.getText().toString());
                            editor.putString("userInfoGrade",PersonInfo_grade.getText().toString());
                            editor.putString("userInfoDegree",PersonInfo_degree.getText().toString());
                            editor.putString("userInfoSchool", PersonInfo_school.getText().toString());
                            editor.putString("userInfoMajor", PersonInfo_major.getText().toString());
                            editor.apply();

                            //转换页面
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(UserInfoEdit_Activity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(UserInfoEdit_Activity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(UserInfoEdit_Activity.this,"修改失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });




//        UserInfoPicture.setImageUrl(userInfoPicture,imageLoader);

//        UserInfoPicture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(UserInfoEdit_Activity.this,"登录成功",Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    //初始化组件
    public void init(){
        PersonInfo_TicPicture = this.findViewById(R.id.PersonInfo_TicPicture);
        PersonInfoName = this.findViewById(R.id.PersonInfoName);
        PersonInfo_idolgragh= this.findViewById(R.id.PersonInfo_idolgragh);
        PersonInfo_grade= this.findViewById(R.id.PersonInfo_grade);
        PersonInfo_degree= this.findViewById(R.id.PersonInfo_degree);
        PersonInfo_school= this.findViewById(R.id.PersonInfo_school);
        PersonInfo_major= this.findViewById(R.id.PersonInfo_major);
        PersonInfo_submit = this.findViewById(R.id.PersonInfo_submit);
//        UserInfoPicture = this.findViewById(R.id.UserInfoPicture);
    }
}