package com.example.postgraduate_v1.personfragment_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.postgraduate_v1.MainActivity;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.adapter.Commodity_Adapter;
import com.example.postgraduate_v1.bmob.Commodity;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SelfPurchase_BooksActivity extends AppCompatActivity {

    private ListView tv_selfpublish;

    private List<Commodity> commodityList = new ArrayList<Commodity>();
    private Commodity_Adapter commodity_adapter;

    private SharedPreferences uSharedPreferences;
    private SharedPreferences.Editor uEditor;

    private Button back_mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(SelfPurchase_BooksActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_self_purchase__books);

        Bmob.initialize(SelfPurchase_BooksActivity.this,"1d06cc42b24841bcda24570e21b24998");

        //存放该用户的所有的信息
        uSharedPreferences = getSharedPreferences("rem_allUserInfo", Context.MODE_PRIVATE);
        uEditor = uSharedPreferences.edit();

        String objectId = uSharedPreferences.getString("objectId","");

        //初始化组件
        init();

        back_mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(SelfPurchase_BooksActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        BmobQuery<Commodity> bmobQuery = new BmobQuery();
        bmobQuery.addWhereEqualTo("publisherId",objectId);
        bmobQuery.findObjects(new FindListener<Commodity>() {
            @Override
            public void done(List<Commodity> list, BmobException e) {
                if(e==null){
                    commodity_adapter = new Commodity_Adapter(SelfPurchase_BooksActivity.this,list);
                    tv_selfpublish.setAdapter(commodity_adapter);
                }else{
                    Toast.makeText(SelfPurchase_BooksActivity.this, "查询失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void init(){
        tv_selfpublish = findViewById(R.id.tv_selfpublish);
        back_mainActivity = findViewById(R.id.back_mainActivity);
    }
}
