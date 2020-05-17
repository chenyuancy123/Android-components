package com.example.postgraduate_v1.rongyun_chat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.postgraduate_v1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static HomeFragment instance = null;//单例模式


    public static HomeFragment getInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }

        return instance;
    }


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        String userId = getActivity().getIntent().getStringExtra("userId");
//        String name = getActivity().getIntent().getStringExtra("name");
//        String portraitUri = getActivity().getIntent().getStringExtra("portraitUri");
//        Log.i("HomeFragment",userId);
//        Log.i("HomeFragment",name);
//        Log.i("HomeFragment",portraitUri);



//        Log.i("HomeActivity",userId);
//        Log.i("HomeActivity",name);
//        Log.i("HomeActivity",portraitUri);

        TextView tv = new TextView(getActivity());
        tv.setText("第一页");
        return tv;

//        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
