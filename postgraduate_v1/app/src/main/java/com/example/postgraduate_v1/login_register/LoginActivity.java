package com.example.postgraduate_v1.login_register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.postgraduate_v1.MainActivity;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.bmob.Userinfo;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_register;
    private EditText et_login_phonenumber,et_login_password;
    private Button bu_login;
    private CheckBox ckb_remember_password;

    private String login_phonenumber;
    private String login_password;

    //存放用户名和密码
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    //存放该用户的所有的信息
    private SharedPreferences uSharedPreferences;
    private SharedPreferences.Editor uEditor;

    //存放该用户的学币信息
    private SharedPreferences xuebi_SharedPreferences;
    private SharedPreferences.Editor xuebi_Editor;

    //存放该用户的token
    private SharedPreferences token_SharedPreferences;
    private SharedPreferences.Editor token_Editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(LoginActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_login);

        Bmob.initialize(this,"1d06cc42b24841bcda24570e21b24998");

        //初始化控件
        init();

        //记住密码
        mSharedPreferences = getSharedPreferences("rem_username_password", Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
        boolean isRemember =mSharedPreferences.getBoolean("remember_password",false);
        //调用getBoolean()方法获取remember_password这个键对应的值
        if(isRemember){//将账号和密码都设置到文本框中
            String phonenumber =mSharedPreferences.getString("phonenumber","");
            String pass= mSharedPreferences.getString("password","");
            et_login_phonenumber.setText(phonenumber);
            et_login_password.setText(pass);
            ckb_remember_password.setChecked(true);
        }

        //存放该用户的所有的信息
        uSharedPreferences = getSharedPreferences("rem_allUserInfo",Context.MODE_PRIVATE);
        uEditor = uSharedPreferences.edit();

        //存放该用户的所有的信息
        xuebi_SharedPreferences = getSharedPreferences("rem_UserXuebi",Context.MODE_PRIVATE);
        xuebi_Editor = xuebi_SharedPreferences.edit();

        //存放该用户的token
        token_SharedPreferences = getSharedPreferences("rem_UserToken",Context.MODE_PRIVATE);
        token_Editor = token_SharedPreferences.edit();

        //获取用户名和密码的值,写在外面是获取不到值的
//        login_phonenumber = et_login_phonenumber.getText().toString();
//        login_password = et_login_password.getText().toString();

        //实现登录
        bu_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户名和密码的值
                login_phonenumber = et_login_phonenumber.getText().toString();
                login_password = et_login_password.getText().toString();
//                String sql = "select * from Userinfo where telephonenumber="+login_phonenumber+"and password="+login_password;
//                String sql = "select * from Userinfo where telephonenumber=17826155350 and password=123456";
//                BmobQuery<Userinfo> query=new BmobQuery<Userinfo>();
//                query.setSQL(sql);
//                query.doSQLQuery(new SQLQueryListener<Userinfo>() {
//                    @Override
//                    public void done(BmobQueryResult<Userinfo> bmobQueryResult, BmobException e) {
//                        if(e==null){
//                            List<Userinfo> list=(List<Userinfo>) bmobQueryResult.getResults();
//                            Log.i("123",list.size()+"");
//                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                BmobQuery<Userinfo> bmobQuery1 = new BmobQuery<Userinfo>();
                bmobQuery1.addWhereEqualTo("telephonenumber",login_phonenumber);
                BmobQuery<Userinfo> bmobQuery2 = new BmobQuery<Userinfo>();
                bmobQuery2.addWhereEqualTo("password",login_password);
                List<BmobQuery<Userinfo>> andQuerys = new ArrayList<BmobQuery<Userinfo>>();
                andQuerys.add(bmobQuery1);
                andQuerys.add(bmobQuery2);

                BmobQuery<Userinfo> query = new BmobQuery<Userinfo>();
                query.and(andQuerys);
//                Log.i("123","query1");
                query.findObjects(new FindListener<Userinfo>() {
                    @Override
                    public void done(List<Userinfo> list, BmobException e) {
//                        Log.i("123","query2");
                        int value = list.size();
//                        Log.i("123",value+"   query2");
                        if(value != 0){

                            for(int i=0;i<list.size();i++){
                                Userinfo userinfo = new Userinfo();
                                userinfo = list.get(i);
                                Log.i("123",userinfo.getUserInfoPicture());
                                //不需要在userinfo类中写ObjectId的set和get方法
                                Log.i("123",userinfo.getObjectId());
//                                editor.putString("userInfoPicture", userinfo.getUserInfoPicture());
                                uEditor.putString("objectId", userinfo.getObjectId());
                                uEditor.putString("username", userinfo.getUsername());
                                uEditor.putString("telephonenumber", userinfo.getTelephonenumber());
                                uEditor.putString("password", userinfo.getPassword());
                                uEditor.putString("userInfoPicture", userinfo.getUserInfoPicture());
                                uEditor.putString("idiograph", userinfo.getIdiograph());
                                uEditor.putString("userInfoGrade", userinfo.getUserInfoGrade());
                                uEditor.putString("userInfoDegree", userinfo.getUserInfoDegree());
                                uEditor.putString("userInfoSchool", userinfo.getUserInfoSchool());
                                uEditor.putString("userInfoMajor", userinfo.getUserInfoMajor());
                                xuebi_Editor.putString("xuebi01",userinfo.getXuebi01());
                                xuebi_Editor.putString("xuebi02",userinfo.getXuebi02());
                                token_Editor.putString("token",userinfo.getToken());
//                                Log.i("123",userinfo.getTelephonenumber());
                            }
//                            Log.i("123",login_phonenumber+"");
//                            Log.i("123",login_password+"");
//                            Log.i("123",list.size()+"");
//                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();

                            if(ckb_remember_password.isChecked()) {//检查复选框是否被选中
                                editor.putBoolean("remember_password", true);//用户想要记住密码,将remember_password设置为true
                                editor.putString("phonenumber", login_phonenumber);
                                editor.putString("password", login_password);
                            }
                            else{
                                editor.clear();//复选框没有被选中，清除账号和密码
                            }
                            editor.apply();
                            uEditor.apply();
                            xuebi_Editor.apply();
                            token_Editor.apply();

                            Intent intent =new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
//                BmobUser userlogin=new BmobUser();
//                userlogin.setUsername(login_phonenumber);
//                userlogin.setPassword(login_password);
//                userlogin.login(new SaveListener<BmobUser>() {
//                    @Override
//                    public void done(BmobUser bmobUser, BmobException e) {
//                        if(e==null){
//                            Toast.makeText(LoginActivity.this,bmobUser.getUsername()+"登录成功",Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


            }
        });

        //进入注册的页面
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    //初始化控件
    private void init(){
        tv_register = findViewById(R.id.tv_register);
        et_login_phonenumber = findViewById(R.id.et_login_phonenumber);
        et_login_password = findViewById(R.id.et_login_password);
        bu_login = findViewById(R.id.bu_login);
        ckb_remember_password = findViewById(R.id.ckb_remember_password);

    }
}
