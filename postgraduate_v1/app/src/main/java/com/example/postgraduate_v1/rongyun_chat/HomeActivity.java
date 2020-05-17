package com.example.postgraduate_v1.rongyun_chat;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.postgraduate_v1.R;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class HomeActivity extends AppCompatActivity {


    private String userId ,name,portraitUri;

    private ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;//将tab页面持久在内存中
    private Fragment mConversationList;
    private Fragment mConversationFragment = null;
    private List<Fragment> mFragment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        intent.putExtra("userId",userinfo.getObjectId());
//        intent.putExtra("name",userinfo.getUsername());
//        intent.putExtra("portraitUri",userinfo.getUserInfoPicture());

         userId = getIntent().getStringExtra("userId");
         name = getIntent().getStringExtra("name");
         portraitUri = getIntent().getStringExtra("portraitUri");
//        Log.i("HomeActivity",userId);
//        Log.i("HomeActivity",name);
//        Log.i("HomeActivity",portraitUri);


        mConversationList = initConversationList();//获取融云会话列表的对象
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mFragment.add(HomeFragment.getInstance());//加入第一页
        mFragment.add(mConversationList);//加入会话列表
        mFragment.add(FriendFragment.getInstance());//加入第三页



        //配置ViewPager的适配器
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);

    }


    private Fragment initConversationList() {

        /**
         * appendQueryParameter对具体的会话列表做展示
         */
        if (mConversationFragment == null) {
//            ConversationListFragment listFragment = ConversationListFragment.getInstance();
            ConversationListFragment listFragment = new ConversationListFragment();
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationList")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")//设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置私聊会是否聚合显示
                    .build();
            listFragment.setUri(uri);
            return listFragment;
        } else {
            return mConversationFragment;
        }
    }


}
