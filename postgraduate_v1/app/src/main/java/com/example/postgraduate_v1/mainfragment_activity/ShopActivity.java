package com.example.postgraduate_v1.mainfragment_activity;

import android.content.Intent;
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
import com.example.postgraduate_v1.adapter.CommodityAdapter;
import com.example.postgraduate_v1.bmob.Commodity;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ShopActivity extends AppCompatActivity {

    private Button bu_back_shopactivity,bu_addcommodity;
    private ListView lv_commodity;

    private List<Commodity> commodityList = new ArrayList<Commodity>();
    private CommodityAdapter commodityAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(ShopActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_shop);

        Bmob.initialize(this,"1d06cc42b24841bcda24570e21b24998");

        //初始化组件
        init();

        //查询所有的商品在ListView中进行展示
        BmobQuery<Commodity> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<Commodity>() {
            @Override
            public void done(List<Commodity> list, BmobException e) {
                if(e==null){
                    commodityList = list;
//                    Log.i("commodity",commodityList.size()+"   1");
                    commodityAdapter = new CommodityAdapter(ShopActivity.this,list);
                    lv_commodity.setAdapter(commodityAdapter);
                }else{
                    Toast.makeText(ShopActivity.this,"查询失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });

//        Log.i("commodity","进入了此函数1");
//        commodityAdapter = new CommodityAdapter(ShopActivity.this,commodityList);
//        lv_commodity.setAdapter(commodityAdapter);
//        Log.i("commodity",commodityList.size()+"    2");
//        Log.i("commodity","进入了此函数2");
//        for(int i=0;i<10000;i++){
//            if(i==9999){
//                Log.i("commodity",commodityList.size()+"    3");
//            }
//        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(500);
//                    Log.i("commodity",commodityList.size()+"    4");
//                    commodityAdapter = new CommodityAdapter(ShopActivity.this,commodityList);
//                    lv_commodity.setAdapter(commodityAdapter);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

//        commodityAdapter = new CommodityAdapter(ShopActivity.this,commodityList);
//        Log.i("commodity",commodityList.size()+"    3");
//        lv_commodity.setAdapter(commodityAdapter);

        //对ListView的每个item进行操作
        lv_commodity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(view.getContext(),position+"",Toast.LENGTH_SHORT).show();
                Commodity commodity = commodityList.get(position);
//                Log.i("commodity",commodity.getObjectId());
                Intent intent = new Intent(ShopActivity.this,Commodity_xiangxi_Activity.class);
                intent.putExtra("commodityId",commodity.getObjectId());
                intent.putExtra("publisherId",commodity.getPublisherId());
                intent.putExtra("commodityPicture",commodity.getCommodityPicture());
                intent.putExtra("commodityName",commodity.getCommodityName());
                intent.putExtra("commodityPrice",commodity.getCommodityPrice());
                intent.putExtra("commodityDescribe",commodity.getCommodityDescribe());
                startActivity(intent);

            }
        });


//        lv_commodity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i("commodity",commodityList.size()+"");
//                Log.i("commodity","进入了此函数3");
//                Toast.makeText(view.getContext(),position+"",Toast.LENGTH_SHORT).show();
//                Commodity commodity = commodityList.get(position);
//                Log.i("commodity",commodity.getObjectId());
//
//
//
//            }
//        });


        bu_back_shopactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(ShopActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bu_addcommodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ShopActivity.this, Addcommodity_Activity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        bu_back_shopactivity = findViewById(R.id.back_shopactivity);
        lv_commodity = findViewById(R.id.lv_commodity);
        bu_addcommodity = findViewById(R.id.bu_addcommodity);
    }
}
