package com.example.postgraduate_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.postgraduate_v1.adapter.ChatAdapter;
import com.example.postgraduate_v1.entity.ChatUser;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ArrayList<ChatUser> chatUsers = new ArrayList<ChatUser>();
    private ChatAdapter chatAdapter;
    private ListView chatUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(ChatActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_chat);

        //初始化组件
        init();

        ChatUser chatUser = new ChatUser();
        chatUser.setChatUserTopPicture("http://imgsrc.baidu.com/forum/w%3D580/sign=f96acdfc08f79052ef1f47363cf1d738/cb2fc65c10385343223c39ce9213b07ecb808870.jpg");
        chatUser.setChatUserName("一粒尘埃");
        chatUser.setChatUserGrade("2020级");
        chatUser.setChatUserDegree("研究生");
        chatUser.setChatUserSchool("南通大学");
        chatUser.setChatUserMajor("软件工程");

        chatUsers.add(chatUser);
        chatAdapter = new ChatAdapter(this,chatUsers);

        chatUserList.setAdapter(chatAdapter);

    }

    public void init(){
        chatUserList = this.findViewById(R.id.chat_User);
    }
}
