package com.example.postgraduate_v1.login_register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.bmob.Userinfo;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import java.util.HashMap;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_register_username,et_register_phonenumber,
            et_register_password,et_register_confirmpassword;
    private Button bu_register;

    //-----注册成功的返回值需要在这里进行定义，在这里定义才是全局-----
    private boolean value;

    private String username,phonenumber,password,confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(RegisterActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_register);
        Bmob.initialize(this,"1d06cc42b24841bcda24570e21b24998");

        //初始化控件
        init();

        bu_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取EditText里面的数据
                username = et_register_username.getText().toString();
                phonenumber = et_register_phonenumber.getText().toString();
                password = et_register_password.getText().toString();
                confirm_password = et_register_confirmpassword.getText().toString();

                if(username !=null &&phonenumber !=null&&password!=null&&confirm_password!=null ){
                    if(password.equals(confirm_password)){
//                        boolean value1 = Register(username,phonenumber,password);
//                        Log.i("urlString",value1+" 3");
//                        Toast.makeText(RegisterActivity.this, value1+"注册成功！", Toast.LENGTH_SHORT).show();
                        Userinfo userinfo = new Userinfo();
                        userinfo.setIdiograph("未编辑~~");
                        userinfo.setUserInfoPicture("http://a0.att.hudong.com/78/52/01200000123847134434529793168.jpg");
                        userinfo.setUserInfoGrade("未填");
                        userinfo.setUserInfoDegree("未填");
                        userinfo.setUserInfoSchool("未填");
                        userinfo.setUserInfoMajor("未填");
                        userinfo.setXuebi01("0");
                        userinfo.setXuebi02("0");
                        userinfo.setToken(" ");
                        userinfo.setUsername(username);
                        userinfo.setTelephonenumber(phonenumber);
                        userinfo.setPassword(password);
                        userinfo.save(new SaveListener<String>() {
                            @Override
                            public void done(String objectId, BmobException e) {
                                if(e==null){
//                                    test(true);
//                                    Log.i("urlString",value+"1");
                                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.setClass(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
//                                    test(false);
//                                    Log.i("urlString",value+"2");
                                    Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
//                        if(value == true){
//                            Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent();
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                            intent.setClass(RegisterActivity.this,LoginActivity.class);
//                            startActivity(intent);
//                        }else{
//                            Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
//                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "请填完整你的信息!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    //初始化控件
    private void init() {
        et_register_username = findViewById(R.id.et_register_username);
        et_register_phonenumber = findViewById(R.id.et_phoneNumber);
        et_register_password = findViewById(R.id.et_register_password);
        et_register_confirmpassword = findViewById(R.id.et_register_confirmPassword);
        bu_register = findViewById(R.id.bu_register);

    }

    public void test(boolean value){
        this.value=value;
    }

//    public boolean Register(String username,String phonenumber,String password){
//
//        Log.i("urlString",username+" "+phonenumber+" "+password+" ");
////        final boolean value;
//        Userinfo userinfo = new Userinfo();
//        userinfo.setUsername(username);
//        userinfo.setTelephonenumber(phonenumber);
//        userinfo.setPassword(password);
//        userinfo.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//                    test(true);
//                    Log.i("urlString",value+"1");
//                }else{
//
//                    test(false);
//                    Log.i("urlString",value+"2");
//                }
//
//            }
//        });
//
//        Log.i("urlString",value+"4");
//
//        return value;
//    }
}
