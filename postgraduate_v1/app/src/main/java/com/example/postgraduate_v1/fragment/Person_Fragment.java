package com.example.postgraduate_v1.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.postgraduate_v1.R;
import com.example.postgraduate_v1.UserInfoEdit_Activity;
import com.example.postgraduate_v1.login_register.LoginActivity;
import com.example.postgraduate_v1.personfragment_activity.SelfCreate_DynamicActivity;
import com.example.postgraduate_v1.personfragment_activity.SelfPurchase_BooksActivity;
import com.example.postgraduate_v1.personfragment_activity.Shouhuo_addressActivity;
import com.example.postgraduate_v1.personfragment_activity.Yi_maibooks_Activity;
import com.example.postgraduate_v1.personfragment_activity.Yimaibooks_Activity;
import com.example.postgraduate_v1.utilityClass.ApplicationController;

/**
 * A simple {@link Fragment} subclass.
 */
public class Person_Fragment extends Fragment {

    private View view;
    private TextView to_UserInfoEditActivity;

    private ImageLoader imageLoader;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    private NetworkImageView topImage;

    private Button bu_loginOut;
    private TextView person_name,person_idolgragh,zijifabu_dynamic,zigou_books,tv_shouhuo_address,yi_mai_books,yimai_books;


    public Person_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_person_, container, false);

        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        mSharedPreferences = getActivity().getSharedPreferences("rem_allUserInfo", Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();

        String userInfoPicture =mSharedPreferences.getString("userInfoPicture","");
        String username =mSharedPreferences.getString("username","");
        String idiograph =mSharedPreferences.getString("idiograph","");


        //初始化组件
        init(view);

        person_name.setText(username);
        person_idolgragh.setText(idiograph);

        topImage.setImageUrl(userInfoPicture,imageLoader);

        to_UserInfoEditActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), UserInfoEdit_Activity.class);
                startActivity(intent);
            }
        });

        bu_loginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(view.getContext(), LoginActivity.class);
                startActivity(intent);
                }
        });

        zijifabu_dynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), SelfCreate_DynamicActivity.class);
                startActivity(intent);
            }
        });

        tv_shouhuo_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Shouhuo_addressActivity.class);
                startActivity(intent);
            }
        });

        zigou_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), SelfPurchase_BooksActivity.class);
                startActivity(intent);
            }
        });

        yi_mai_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Yi_maibooks_Activity.class);
                startActivity(intent);
            }
        });

        yimai_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Yimaibooks_Activity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    public void init(View view){
        to_UserInfoEditActivity = view.findViewById(R.id.to_UserInfoEditActivity);
        topImage = view.findViewById(R.id.TopPicture);
        bu_loginOut = view.findViewById(R.id.bu_loginOut);
//        person_name  person_idolgragh
        person_name = view.findViewById(R.id.person_name);
        person_idolgragh = view.findViewById(R.id.person_idolgragh);
        zijifabu_dynamic = view.findViewById(R.id.zijifabu_dynamic);
        zigou_books = view.findViewById(R.id.zigou_books);
        tv_shouhuo_address = view.findViewById(R.id.tv_shouhuo_address);

//        yi_mai_books,yimai_books;
        yi_mai_books = view.findViewById(R.id.yi_mai_books);
        yimai_books = view.findViewById(R.id.yimai_books);
    }

}
