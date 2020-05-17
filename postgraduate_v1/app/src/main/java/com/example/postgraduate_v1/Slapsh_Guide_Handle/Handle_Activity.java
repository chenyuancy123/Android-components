package com.example.postgraduate_v1.Slapsh_Guide_Handle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.login_register.LoginActivity;

public class Handle_Activity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_handle_);

        handler.sendEmptyMessageDelayed(0, 1000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            intent = new Intent(Handle_Activity.this, LoginActivity.class);
            startActivity(intent);
            //执行一次后销毁本页面
            finish();
        }
    };
}
