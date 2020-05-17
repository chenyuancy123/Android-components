package com.example.postgraduate_v1.Slapsh_Guide_Handle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.postgraduate_v1.R;


public class Slapsh_Activity extends AppCompatActivity {

    //是否是第一次使用
    private boolean isFirstUse;


//    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        StatusBarUtils.setWindowStatusBarColor(Slapsh_Activity.this,R.color.colorTouBlue);
        setContentView(R.layout.activity_slapsh_);

        //检测是否为第一次使用
        SharedPreferences preferences = getSharedPreferences("isFirstUse", Context.MODE_PRIVATE);
        isFirstUse = preferences.getBoolean("isFirstUse", true);

        /**
         *如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
         */
        if (isFirstUse) {
            startActivity(new Intent(Slapsh_Activity.this, Guide_Activity.class));
        } else {
            startActivity(new Intent(Slapsh_Activity.this, Handle_Activity.class));
        }
        finish();
        //实例化Editor对象
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putBoolean("isFirstUse", false);
        //提交修改
        editor.commit();


    }

//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            intent = new Intent(Slapsh_Activity.this, Handle_Activity.class);
//            startActivity(intent);
//            //执行一次后销毁本页面
//            finish();
//        }
//    };
}
