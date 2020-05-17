package com.example.postgraduate_v1.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.bmob.Commodity;
import com.example.postgraduate_v1.personfragment_activity.SelfPurchase_BooksActivity;
import com.example.postgraduate_v1.utilityClass.ApplicationController;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class Commodity_Adapter extends BaseAdapter {

    private Activity activity;
    private List<Commodity> commoditylist;

    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    private NetworkImageView commodity_picture;
    private TextView commodity_name,commodity_price;

    private Button bu_delete_commodity;


    public  Commodity_Adapter(Activity activity,List<Commodity> list){
        this.activity = activity;
        commoditylist = list;
    }

    @Override
    public int getCount() {
        return commoditylist.size();
    }

    @Override
    public Object getItem(int position) {
        return commoditylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.commodityadapter, null);

        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        //初始化组件
        init(convertView);

        //给组件赋值
        commodity_picture.setImageUrl(commoditylist.get(position).getCommodityPicture(),imageLoader);
        commodity_name.setText(commoditylist.get(position).getCommodityName());
        commodity_price.setText(commoditylist.get(position).getCommodityPrice());

        final View finalConvertView = convertView;
        bu_delete_commodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Commodity commodity = new Commodity();
                commodity.setObjectId(commoditylist.get(position).getObjectId());
                commodity.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(finalConvertView.getContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setClass(finalConvertView.getContext(), SelfPurchase_BooksActivity.class);
                            finalConvertView.getContext().startActivity(intent);
                        }else{
                            Toast.makeText(finalConvertView.getContext(), "删除失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Toast.makeText(finalConvertView.getContext(), commoditylist.get(position).getObjectId()+"", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public void init(View view){
//        private NetworkImageView commodity_picture;
//        private TextView commodity_name,commodity_price;
        commodity_picture = view.findViewById(R.id.commotity_picture);
        commodity_name = view.findViewById(R.id.commotity_name);
        commodity_price = view.findViewById(R.id.commotity_price);
        bu_delete_commodity = view.findViewById(R.id.bu_delete_commodity);

    }
}
