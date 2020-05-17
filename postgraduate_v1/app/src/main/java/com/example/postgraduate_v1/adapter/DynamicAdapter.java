package com.example.postgraduate_v1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.bmob.Dynamic_bmob;
import com.example.postgraduate_v1.entity.Dynamic;
import com.example.postgraduate_v1.utilityClass.ApplicationController;

import java.util.List;

public class DynamicAdapter extends BaseAdapter {
    private Context mContext;
    private List<Dynamic_bmob> dynamicList;

    private Activity activity;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    private NetworkImageView posterTopPicture;
    private TextView posterName,posterSchool,posterMajor,posterTime,postContext;

    public DynamicAdapter(Activity activity,List list){
        this.activity = activity;
        dynamicList = list;
    }

    @Override
    public int getCount() {
        return dynamicList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.dynamic_adapter, null);

        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        //初始化组件
        init(convertView);


        posterTopPicture.setImageUrl(dynamicList.get(position).getPosterTopPicture(),imageLoader);
        posterName.setText(dynamicList.get(position).getPosterName());
        posterSchool.setText(dynamicList.get(position).getPosterSchool());
        posterMajor.setText(dynamicList.get(position).getPosterMajor());
        posterTime.setText(dynamicList.get(position).getCreatedAt());
        postContext.setText(dynamicList.get(position).getPostContext());



        return convertView;
    }

    public void init(View view){
        posterTopPicture = view.findViewById(R.id.posterTopPicture);
        posterName = view.findViewById(R.id.posterName);
        posterSchool = view.findViewById(R.id.posterSchool);
        posterMajor = view.findViewById(R.id.posterMajor);
        posterTime = view.findViewById(R.id.posterTime);
        postContext = view.findViewById(R.id.postContext);

    }
}
