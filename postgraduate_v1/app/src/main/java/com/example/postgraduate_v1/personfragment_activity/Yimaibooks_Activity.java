package com.example.postgraduate_v1.personfragment_activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.adapter.BookAdapter;
import com.example.postgraduate_v1.bmob.Order;
import com.example.postgraduate_v1.login_register.LoginActivity;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Yimaibooks_Activity extends AppCompatActivity {

    private ListView lv_yimai_books;

    private List<Order> orderList = new ArrayList<Order>();
    private BookAdapter bookAdapter;

    private SharedPreferences uSharedPreferences;
    private SharedPreferences.Editor uEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(Yimaibooks_Activity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_yimaibooks_);

        Bmob.initialize(Yimaibooks_Activity.this,"1d06cc42b24841bcda24570e21b24998");

        //存放该用户的所有的信息
        uSharedPreferences = getSharedPreferences("rem_allUserInfo", Context.MODE_PRIVATE);
        uEditor = uSharedPreferences.edit();

        String objectId = uSharedPreferences.getString("objectId","");

        //初始化组件
        init();

        BmobQuery<Order> bmobQuery = new BmobQuery();
        bmobQuery.addWhereEqualTo("buyerId",objectId);
        bmobQuery.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if(e==null){
                    bookAdapter = new BookAdapter(Yimaibooks_Activity.this,list);
                    lv_yimai_books.setAdapter(bookAdapter);
                }else{
                    Toast.makeText(Yimaibooks_Activity.this, "查询失败！", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void init(){
        lv_yimai_books = findViewById(R.id.lv_yimai_books);
    }
}
