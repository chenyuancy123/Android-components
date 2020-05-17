package com.example.postgraduate_v1.rongyun_chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.postgraduate_v1.MainActivity;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.mainfragment_activity.Chat_Activity;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

public class ConversationActivity extends AppCompatActivity {

    private TextView tv_liantianbeizhu;
    private Button back_toChat_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(ConversationActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_conversation);

        //初始组件
        init();

//        tv_liantianbeizhu

        back_toChat_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(ConversationActivity.this, Chat_Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void init(){
        tv_liantianbeizhu = findViewById(R.id.tv_liantianbeizhu);
        back_toChat_activity = findViewById(R.id.back_toChat_activity);
    }
}
