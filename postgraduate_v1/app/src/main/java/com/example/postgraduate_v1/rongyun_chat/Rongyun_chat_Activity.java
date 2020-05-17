package com.example.postgraduate_v1.rongyun_chat;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.entity.Friend;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class Rongyun_chat_Activity extends AppCompatActivity implements View.OnClickListener, RongIM.UserInfoProvider{

    private static final String token1 ="5dG7KrjnYYgHdjE+WPmgaBAoWeG98P03PV0qPUMQ/D8=@5pyy.cn.rongnav.com;5pyy.cn.rongcfg.com";
//    qmOSSqJZ/W8GI2wY2/pvtBAoWeG98P03NTDNmTXSnu4=@5pyy.cn.rongnav.com;5pyy.cn.rongcfg.com
    private static final String token2 ="qmOSSqJZ/W8GI2wY2/pvtBAoWeG98P03NTDNmTXSnu4=@5pyy.cn.rongnav.com;5pyy.cn.rongcfg.com";
//    private static final String token1 = "uFHTGcZMzSCSugxRmRA7lRf1AH3ArXbvLbNoacl+xLf5jFwBPsU0d+uBXWEKpLMlrv/cTWb4cdSdzE0shl1/oA==";
//    private static final String token2 = "0L3LhZvmEhmeY/O6z+jqkDdw425PLr6tFkyLhkSfXOmlDCw9tNfLfUcyTHdC9vWaTz2z6Hb0nwEYYnzkBXdBuw==";

    private List<Friend> userIdList;
    private static final String TAG = "MainActivity";

    private Button mUser1, mUser2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rongyun_chat_);

        //融云的知识本地版开始
//        RongIM.connect(token1, new RongIMClient.ConnectCallback() {
//            @Override
//            public void onTokenIncorrect() {
//
//            }
//
//            @Override
//            public void onSuccess(String s) {
//                Log.e("onSuccess","onSuccess userid:"+s);
//
//            }
//
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                Log.e("onError","onError userid:"+errorCode.getValue());//获取错误的错误码
//
//            }
//        });
        //融云的知识本地版结束

        //初始化组件

        mUser1 = (Button) findViewById(R.id.connect_10010);
        mUser2 = (Button) findViewById(R.id.connect_10086);
        mUser1.setOnClickListener(this);
        mUser2.setOnClickListener(this);

        initUserInfo();
    }


    private void connectRongServer(String token) {
//        Log.i(TAG, token);
        RongIM.connect(token, new RongIMClient.ConnectCallback() {


            @Override
            public void onSuccess(String userId) {

//                Log.i(TAG, userId);

                if (userId.equals("f9337403dd")) {
//                    Log.i("MainActivity", userId);
                    mUser1.setText("用户1连接服务器成功");
                    if (RongIM.getInstance()!=null){
//                        RongIM.getInstance().startPrivateChat(getActivity(),"0210488fcc","私人聊天");
                        RongIM.getInstance().startPrivateChat(Rongyun_chat_Activity.this,"0210488fcc","私人聊天");
                    }
//                    startActivity(new Intent(Rongyun_chat_Activity.this, HomeActivity.class));
//                    Toast.makeText(Rongyun_chat_Activity.this, "connect server success f9337403dd", Toast.LENGTH_SHORT).show();

                } else {
//                    Log.i("MainActivity", userId);
                    RongIM.getInstance().startPrivateChat(Rongyun_chat_Activity.this,"f9337403dd","私人聊天");
//                    startActivity(new Intent(Rongyun_chat_Activity.this, HomeActivity.class));
//                    Toast.makeText(Rongyun_chat_Activity.this, "connect server success 0210488fcc", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                // Log.e("onError", "onError userid:" + errorCode.getValue());//获取错误的错误码
                Log.e(TAG, "connect failure errorCode is : " + errorCode.getValue());
            }


            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "token is error ,please check token and appkey");
            }
        });

    }



    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.connect_10010) {
            connectRongServer(token1);
        } else if (v.getId() == R.id.connect_10086) {
            connectRongServer(token2);
        }
    }


    private void initUserInfo() {
        userIdList = new ArrayList<Friend>();
        userIdList.add(new Friend("f9337403dd", "佛系~", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587550058178&di=9adc5c3ab3ed6195f62fda0ed715bf99&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180920%2Ff31cf9d4b7e44a0c8c7279c0cc241803.jpeg"));//联通图标
        userIdList.add(new Friend("0210488fcc", "一粒尘埃", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587549809353&di=8b82c9b35cff274bd9d32bc7789720a0&imgtype=0&src=http%3A%2F%2Fh.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Faec379310a55b319fc68576b47a98226cffc171e.jpg"));//移动图标
        RongIM.setUserInfoProvider(this, true);
    }


    @Override
    public UserInfo getUserInfo(String s) {

        for (Friend i : userIdList) {
            if (i.getUserId().equals(s)) {
                Log.e(TAG, i.getPortraitUri());
                return new UserInfo(i.getUserId(), i.getName(), Uri.parse(i.getPortraitUri()));
            }
        }


        Log.e("MainActivity", "UserId is : " + s);
        return null;
    }


}
