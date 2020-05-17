package com.example.postgraduate_v1.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.WebViewActivity;
import com.example.postgraduate_v1.adapter.YanxunAdapter;
import com.example.postgraduate_v1.bmob.YanxunInfo;
import com.example.postgraduate_v1.mainfragment_activity.Chat_Activity;
import com.example.postgraduate_v1.mainfragment_activity.ShopActivity;
import com.example.postgraduate_v1.mainfragment_activity.Wallet_Activity;
import com.example.postgraduate_v1.rongyun_chat.Rongyun_chat_Activity;
import com.oragee.banners.BannerView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class Main_Fragment extends Fragment {
    private View view;

    //轮播需要的
    private int[] imgs = {R.drawable.banner4,R.drawable.banner5,R.drawable.banner6 };
    private List<View> viewList;
    BannerView bannerView;

    private ImageView Ima_xuezhangshuo,Ima_shop,Ima_xuebi;

//    private Button bu_nihao;

    private ListView great_yanNews;
    private List<YanxunInfo> yanxunInfoList = new ArrayList<YanxunInfo>();
    private YanxunAdapter yanxunAdapter;



    public Main_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_, container, false);

        //初始化组件
        init(view);

//        bu_nihao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(view.getContext(), WebViewActivity.class);
//                startActivity(intent);
//            }
//        });

        BmobQuery<YanxunInfo> bmobQuery = new BmobQuery<YanxunInfo>();
        bmobQuery.findObjects(new FindListener<YanxunInfo>() {
            @Override
            public void done(List<YanxunInfo> list, BmobException e) {
                if(e==null){
                    yanxunInfoList = list;
                    yanxunAdapter = new YanxunAdapter(getActivity(),list);
                    great_yanNews.setAdapter(yanxunAdapter);
                }else{
                    Toast.makeText(getActivity(), "查询失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        great_yanNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                YanxunInfo yanxunInfo = yanxunInfoList.get(position);
                Intent intent=new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("content_url",yanxunInfo.getYanxunUrl());
//                Toast.makeText(view.getContext(),movie.getWebviewurl(),Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

        //to到ShopActivity页面
        Ima_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), ShopActivity.class);
                startActivity(intent);
            }
        });

        //to到Wallet_Activity页面
        Ima_xuebi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Wallet_Activity.class);
                startActivity(intent);
            }
        });

        //to到Chat_Activity页面
        Ima_xuezhangshuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Chat_Activity.class);
                startActivity(intent);
            }
        });


        //        顶部图片轮播
        viewList = new ArrayList<View>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView image = new ImageView(view.getContext());
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageResource(imgs[i]);
            viewList.add(image);
        }
        bannerView = (BannerView)view.findViewById(R.id.banner);
        bannerView.startLoop(true);

        bannerView.setViewList(viewList);


        return view;
    }

    public void init(View view){

//        private ImageView Ima_xuezhangshuo,Ima_shop,Ima_xuebi;
        Ima_xuezhangshuo = view.findViewById(R.id.xuezhangshuo);
        Ima_shop = view.findViewById(R.id.shop);
        Ima_xuebi = view.findViewById(R.id.xuebi);

//        bu_nihao = view.findViewById(R.id.bu_nihao);

        great_yanNews = view.findViewById(R.id.great_yanNews);
    }

}
