package com.example.postgraduate_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.postgraduate_v1.login_register.LoginActivity;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;

public class WebViewActivity extends AppCompatActivity {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(WebViewActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_web_view);

        webView=findViewById(R.id.webView);
        String pic_url=getIntent().getStringExtra("content_url");
//        String pic_url= "https://m.gmw.cn/toutiao/2020-04/30/content_1301192590.htm?tt_group_id=6821318658107638286";
        webView.loadUrl(pic_url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }
}
