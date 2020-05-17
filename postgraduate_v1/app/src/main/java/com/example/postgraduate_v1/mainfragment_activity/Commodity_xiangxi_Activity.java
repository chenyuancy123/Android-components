package com.example.postgraduate_v1.mainfragment_activity;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.postgraduate_v1.DynamicEdit_Activity;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.utilityClass.ApplicationController;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

public class Commodity_xiangxi_Activity extends AppCompatActivity {
    private Button back_shop_activity,bu_book_purchase;
    private NetworkImageView book_Picture;
    private TextView book_Name,book_Price,book_Describe;

    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(Commodity_xiangxi_Activity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_commodity_xiangxi_);

        if(imageLoader==null){
            imageLoader= ApplicationController.getInstance().getImageLoader();
        }

        //初始化组件
        init();

        String commodityId=getIntent().getStringExtra("commodityId");
        final String publisherId=getIntent().getStringExtra("publisherId");
        final String commodityPicture=getIntent().getStringExtra("commodityPicture");
        final String commodityName=getIntent().getStringExtra("commodityName");
        final String commodityPrice=getIntent().getStringExtra("commodityPrice");
        final String commodityDescribe=getIntent().getStringExtra("commodityDescribe");

        book_Picture.setImageUrl(commodityPicture,imageLoader);
        book_Name.setText(commodityName);
        book_Price.setText(commodityPrice);
        book_Describe.setText(commodityDescribe);

        back_shop_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(Commodity_xiangxi_Activity.this, ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //去买书页面
        bu_book_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Commodity_xiangxi_Activity.this, BuyBooks_Activity.class);
                intent.putExtra("publisherId",publisherId);
                intent.putExtra("commodityPicture",commodityPicture);
                intent.putExtra("commodityName",commodityName);
                intent.putExtra("commodityPrice",commodityPrice);
                intent.putExtra("commodityDescribe",commodityDescribe);
                startActivity(intent);
            }
        });

//        Log.i("commodityId",commodityId);
//        Log.i("commodityId",buyerId);
//        Log.i("commodityId",commodityPicture);
//        Log.i("commodityId",commodityName);
//        Log.i("commodityId",commodityPrice);
//        Log.i("commodityId",commodityDescribe);


    }

    public void init(){
        back_shop_activity = findViewById(R.id.back_shop_activity);
        bu_book_purchase = findViewById(R.id.bu_book_purchase);
        book_Picture = findViewById(R.id.book_Picture);
        book_Name = findViewById(R.id.book_Name);
        book_Price = findViewById(R.id.book_Price);
        book_Describe = findViewById(R.id.book_Describe);
    }
}
