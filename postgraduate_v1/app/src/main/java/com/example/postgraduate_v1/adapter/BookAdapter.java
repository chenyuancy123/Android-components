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
import com.example.postgraduate_v1.bmob.Order;
import com.example.postgraduate_v1.utilityClass.ApplicationController;

import java.util.List;

public class BookAdapter extends BaseAdapter {

    private TextView books_adapter_name,books_adapter_price,
            books_adapter_username,books_adapter_telephone,books_adapter_address;
    private NetworkImageView books_adapter_picture;

    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    private Activity activity;
    private List<Order> orderList;

    public BookAdapter(Activity activity,List list){
        this.activity = activity;
        orderList = list;
    }


    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
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
            convertView = inflater.inflate(R.layout.books_adapter, null);

        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        //初始化组件
        init(convertView);

        //给组件赋值
        books_adapter_picture.setImageUrl(orderList.get(position).getPictureBookUrl(),imageLoader);
        books_adapter_name.setText(orderList.get(position).getBookName());
        books_adapter_price.setText(orderList.get(position).getTotalMoney());
        books_adapter_username.setText(orderList.get(position).getBuyerName());
        books_adapter_telephone.setText(orderList.get(position).getBuyerTele());
        books_adapter_address.setText(orderList.get(position).getBuyerAddress());


        return convertView;
    }

    //初始化组件
    public void init(View view){
        books_adapter_name = view.findViewById(R.id.books_adapter_name);
        books_adapter_price = view.findViewById(R.id.books_adapter_price);
        books_adapter_username = view.findViewById(R.id.books_adapter_username);
        books_adapter_telephone = view.findViewById(R.id.books_adapter_telephone);
        books_adapter_address = view.findViewById(R.id.books_adapter_address);
        books_adapter_picture = view.findViewById(R.id.books_adapter_picture);

    }
}
