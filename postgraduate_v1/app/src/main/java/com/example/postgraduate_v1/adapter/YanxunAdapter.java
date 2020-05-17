package com.example.postgraduate_v1.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.bmob.YanxunInfo;
import com.example.postgraduate_v1.utilityClass.ApplicationController;

import java.util.List;

public class YanxunAdapter extends BaseAdapter {

    private NetworkImageView yanxun_image;
    private TextView yanxun_title,yanxun_time;

    private Activity activity;
    private List<YanxunInfo> yanxunInfoList;

    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public  YanxunAdapter(Activity activity,List<YanxunInfo> list){
        this.activity = activity;
        yanxunInfoList = list;
    }

    @Override
    public int getCount() {
        return yanxunInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return yanxunInfoList.get(position);
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
            convertView = inflater.inflate(R.layout.yanxun_adapter, null);

        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        //初始化组件
        init(convertView);

        //给组件赋值
        //        private NetworkImageView yanxun_image;
//        private TextView yanxun_title,yanxun_time;
//        Log.i("YanxunAdapter",yanxunInfoList.get(position).getYanxunImage());
        yanxun_image.setImageUrl(yanxunInfoList.get(position).getYanxunImage(),imageLoader);
        yanxun_title.setText(yanxunInfoList.get(position).getYanxunName());
        yanxun_time.setText(yanxunInfoList.get(position).getCreatedAt());


        return convertView;
    }

    public void init(View view){


        yanxun_image = view.findViewById(R.id.yanxun_image);
        yanxun_title = view.findViewById(R.id.yanxun_title);
        yanxun_time = view.findViewById(R.id.yanxun_time);

    }

}
