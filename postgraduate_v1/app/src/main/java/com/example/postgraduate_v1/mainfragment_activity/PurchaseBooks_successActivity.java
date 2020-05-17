package com.example.postgraduate_v1.mainfragment_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

public class PurchaseBooks_successActivity extends AppCompatActivity {
    private Button bu_back_ShopActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(PurchaseBooks_successActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_purchase_books_success);

        //初始化组件
        init();

        bu_back_ShopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(PurchaseBooks_successActivity.this,ShopActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void init(){
        bu_back_ShopActivity = findViewById(R.id.bu_back_ShopActivity);
    }
}
