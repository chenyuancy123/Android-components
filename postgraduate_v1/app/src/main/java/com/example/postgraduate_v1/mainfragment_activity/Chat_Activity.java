package com.example.postgraduate_v1.mainfragment_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.postgraduate_v1.MainActivity;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.adapter.ChatAdapter;
import com.example.postgraduate_v1.bmob.Userinfo;
import com.example.postgraduate_v1.utilityClass.GetToken;
import com.example.postgraduate_v1.utilityClass.Get_Token;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class Chat_Activity extends AppCompatActivity {

//    private static final String token2 ="qmOSSqJZ/W8GI2wY2/pvtBAoWeG98P03NTDNmTXSnu4=@5pyy.cn.rongnav.com;5pyy.cn.rongcfg.com";
    private String token;

    private String objectId;

    //存放该用户的token
    private SharedPreferences token_SharedPreferences;
    private SharedPreferences.Editor token_Editor;

    private String lianxiren;
    private static final String TAG = "Chat_Activity";

    private Button back_toMain_fragment;

    private Get_Token get_Token = new Get_Token();
//    private GetToken getToken = new GetToken();

    //存放该用户的所有的信息
    private SharedPreferences uSharedPreferences;
    private SharedPreferences.Editor uEditor;

    //适配器
    private List<Userinfo> userinfoList = new ArrayList<Userinfo>();
    private ChatAdapter chatAdapter;

    private ListView chat_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(Chat_Activity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_chat_);

        Bmob.initialize(this,"1d06cc42b24841bcda24570e21b24998");

        //存放该用户的所有的信息
        uSharedPreferences = getSharedPreferences("rem_allUserInfo", Context.MODE_PRIVATE);
        uEditor = uSharedPreferences.edit();

        //存放该用户的token
        token_SharedPreferences = getSharedPreferences("rem_UserToken",Context.MODE_PRIVATE);
        token_Editor = token_SharedPreferences.edit();
        token = token_SharedPreferences.getString("token","");

        //初始化组件
        init();

        objectId = uSharedPreferences.getString("objectId","");
        final String username = uSharedPreferences.getString("username","");
        final String portraitUri = uSharedPreferences.getString("userInfoPicture","");


//        String token1 = get_Token.getRongCloud_Token(objectId,username,portraitUri);
//        Log.i("Get_Token",token1);



        //GetToken
//        网络请求是个耗时操作，不能再主线程中操作，你们懂的
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String token = getToken.GetRongCloudToken(username);
//                Log.i("GetToken",token);
//            }
//        }).start();


//        Log.i("Chat_Activity",objectId);

        BmobQuery<Userinfo> bmobQuery = new BmobQuery<Userinfo>();
        bmobQuery.addWhereNotEqualTo("objectId",objectId);
        bmobQuery.findObjects(new FindListener<Userinfo>() {
            @Override
            public void done(List<Userinfo> list, BmobException e) {
                if(e==null){
                    userinfoList = list;
                    chatAdapter = new ChatAdapter(Chat_Activity.this,list);
                    chat_User.setAdapter(chatAdapter);
//                    Log.i("Chat_Activity",list.size()+"");
//                    Toast.makeText(Chat_Activity.this, list.size()+"查询成功！", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Chat_Activity.this, "查询失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chat_User.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Chat_Activity.this, position+"", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Chat_Activity.this, HomeActivity.class);
                Userinfo userinfo = userinfoList.get(position);
                lianxiren = userinfo.getObjectId();
//                Log.i(TAG, lianxiren);
//                intent.putExtra("userId",userinfo.getObjectId());
//                intent.putExtra("name",userinfo.getUsername());
//                intent.putExtra("portraitUri",userinfo.getUserInfoPicture());
//                startActivity(intent);
                connectRongServer(token);

            }
        });

        back_toMain_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(Chat_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void connectRongServer(String token){
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "token is error ,please check token and appkey");

            }

            @Override
            public void onSuccess(String userId) {
                if (userId.equals(objectId)){
                    if (RongIM.getInstance()!=null){
                        RongIM.getInstance().startPrivateChat(Chat_Activity.this,lianxiren,"私人聊天");
                    }
                }else{
                    Log.i(TAG, "连接失败！");
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, "connect failure errorCode is : " + errorCode.getValue());
            }
        });
    }

    public void init(){
        back_toMain_fragment = findViewById(R.id.back_toMain_fragment);
        chat_User = findViewById(R.id.chat_User);
    }


}
