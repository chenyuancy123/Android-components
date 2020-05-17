package com.example.postgraduate_v1.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.postgraduate_v1.DynamicEdit_Activity;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.adapter.DynamicAdapter;
import com.example.postgraduate_v1.bmob.Dynamic_bmob;
import com.example.postgraduate_v1.entity.Dynamic;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class Video_Fragment extends Fragment {

    private View view;

    private Button bu_postDynamic;
    private ListView lv_dynamicInfo;

    private List<Dynamic_bmob> dynamicList = new ArrayList<Dynamic_bmob>();
    private DynamicAdapter dynamicAdapter;


    public Video_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_video_, container, false);

        Bmob.initialize(view.getContext(),"1d06cc42b24841bcda24570e21b24998");

        //初始化组件
        init(view);

        BmobQuery<Dynamic_bmob> bmobQuery = new BmobQuery<>();
        bmobQuery.findObjects(new FindListener<Dynamic_bmob>() {
            @Override
            public void done(List<Dynamic_bmob> list, BmobException e) {
                if(e==null){
                    dynamicAdapter = new DynamicAdapter(getActivity(),list);
                    lv_dynamicInfo.setAdapter(dynamicAdapter);
//                    for(int i = 0;i<list.size();i++){
//                        Dynamic_bmob dynamic_bmob = new Dynamic_bmob();
//                        dynamic_bmob = list.get(i);
//                        Log.i("123456",dynamic_bmob.getObjectId()+"");
//                        Log.i("123456",dynamic_bmob.getCreatedAt()+"");
//                        Log.i("123456",dynamic_bmob.getPosterTopPicture()+"");
//                        Log.i("123456",dynamic_bmob.getPosterName()+"");
//                        Log.i("123456",dynamic_bmob.getPosterSchool()+"");
//                        Log.i("123456",dynamic_bmob.getPosterMajor()+"");
//                        Log.i("123456",dynamic_bmob.getPostContext()+"");
//
//                    }

                }else{
                    Log.i("123456","查询失败！");
                }

            }
        });

//        Toast.makeText(view.getContext(),"帅气1",Toast.LENGTH_SHORT).show();

//        lv_dynamicInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(view.getContext(),position+"",Toast.LENGTH_SHORT).show();
//                Log.i("lv_dynamicInfo",position+"");
//            }
//        });

//        Toast.makeText(view.getContext(),"帅气2",Toast.LENGTH_SHORT).show();

//        Dynamic dynamic = new Dynamic();
//        dynamic.setPosterTopPicture("http://a0.att.hudong.com/78/52/01200000123847134434529793168.jpg");
//        dynamic.setPosterName("倔强的鑫鑫");
//        dynamic.setPosterSchool("南通大学");
//        dynamic.setPosterMajor("软件工程[085400]");
//        dynamic.setPostTime("2020.4.23");
//        dynamic.setPostContext("考研很难！");

//        dynamicList.add(dynamic);
//        Log.i("1234",dynamicList.size()+" ");

//        dynamicAdapter = new DynamicAdapter(getActivity(),dynamicList);
//        lv_dynamicInfo.setAdapter(dynamicAdapter);

        //转换到postDynamic的页面
        bu_postDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), DynamicEdit_Activity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    public void init(View view){

        bu_postDynamic = view.findViewById(R.id.bu_postDynamic);
        lv_dynamicInfo = view.findViewById(R.id.lv_dynamicInfo);

    }


}
