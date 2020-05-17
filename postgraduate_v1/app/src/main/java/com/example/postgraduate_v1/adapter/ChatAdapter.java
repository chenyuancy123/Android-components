package com.example.postgraduate_v1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.bmob.Userinfo;
import com.example.postgraduate_v1.entity.ChatUser;
import com.example.postgraduate_v1.utilityClass.ApplicationController;

import java.util.List;

public class ChatAdapter extends BaseAdapter {
//    private Context mContext;
    private List<Userinfo> chatUserList;

    private Activity activity;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    private NetworkImageView chatUserTopPicture;
    private TextView chatUsername,chatUsergrade,chatUserdegree,chatUserschool,chatUsermajor;
//    private Button button_consult;

    public ChatAdapter(Activity activity,List list){
        this.activity = activity;
        chatUserList = list;
    }

    //返回chatUserList的个数
    @Override
    public int getCount() {
        return chatUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return chatUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.chat_adapter_layout, null);

        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        //初始化组件
        init(convertView);

        //组件设置展示数据
        chatUserTopPicture.setImageUrl(chatUserList.get(position).getUserInfoPicture(),imageLoader);
        chatUsername.setText(chatUserList.get(position).getUsername());
        chatUsergrade.setText(chatUserList.get(position).getUserInfoGrade());
        chatUserdegree.setText(chatUserList.get(position).getUserInfoDegree());
        chatUserschool.setText(chatUserList.get(position).getUserInfoSchool());
        chatUsermajor.setText(chatUserList.get(position).getUserInfoMajor());

        return convertView;
    }

    //初始化view界面包含的组件
    public void init(View convertView){
        chatUserTopPicture = convertView.findViewById(R.id.chatUserTopPicture);
        chatUsername = convertView.findViewById(R.id.chatUsername);
        chatUsergrade = convertView.findViewById(R.id.chatUsergrade);
        chatUserdegree = convertView.findViewById(R.id.chatUserdegree);
        chatUserschool = convertView.findViewById(R.id.chatUserschool);
        chatUsermajor = convertView.findViewById(R.id.chatUsermajor);
//        button_consult = convertView.findViewById(R.id.button_consult);
    }
}
