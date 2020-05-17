package com.example.postgraduate_v1.rongyun_chat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.postgraduate_v1.R;

import io.rong.imkit.RongIM;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {

    public static FriendFragment instance = null;//单例模式


    public FriendFragment() {
        // Required empty public constructor

    }


    public static FriendFragment getInstance() {
        if (instance == null) {
            instance = new FriendFragment();
        }

        return instance;
    }

    private View mView;
    private Button mButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_friend,null);
        mButton = (Button) mView.findViewById(R.id.friend);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RongIM.getInstance()!=null){
                    RongIM.getInstance().startPrivateChat(getActivity(),"0210488fcc","私人聊天");
                }
            }
        });

        return mView;

    }

}
