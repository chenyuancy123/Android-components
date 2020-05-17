package com.example.postgraduate_v1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.postgraduate_v1.bmob.Shouhuoaddress;
import com.example.postgraduate_v1.bmob.YanxunInfo;
import com.example.postgraduate_v1.fragment.Main_Fragment;
import com.example.postgraduate_v1.fragment.Person_Fragment;
import com.example.postgraduate_v1.fragment.Video_Fragment;
import com.example.postgraduate_v1.utilityClass.StatusBarUtils;
import com.example.postgraduate_v1.bmob.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


public class MainActivity extends AppCompatActivity {

    //底部导航最外层container
    @InjectView(R.id.main_bottom_switcher_container)
    LinearLayout mainBottomSwitcherContainer;

    //底部导航container中孩子的个数
    private int childCount;

    private List<Fragment> fragments = new ArrayList<>();

    //用户的所有信息
    private SharedPreferences uSharedPreferences;
    private SharedPreferences.Editor uEditor;

    //用户的收货地址
    private SharedPreferences address_SharedPreferences;
    private SharedPreferences.Editor address_Editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(MainActivity.this, R.color.colorTouBlue);
        setContentView(R.layout.activity_main);

        //存放该用户的用户的收货地址
        address_SharedPreferences = getSharedPreferences("rem_UserAddress", Context.MODE_PRIVATE);
        address_Editor = address_SharedPreferences.edit();

        //存放该用户的所有的信息
        uSharedPreferences = getSharedPreferences("rem_allUserInfo", Context.MODE_PRIVATE);
        uEditor = uSharedPreferences.edit();

        final String objectId = uSharedPreferences.getString("objectId","");

        ButterKnife.inject(this);
        initFragment();
        initMainBottomSwitcher();//初始化底部导航


        Bmob.initialize(this,"1d06cc42b24841bcda24570e21b24998");

        //添加收货地址
        BmobQuery<Shouhuoaddress> bmobQuery = new BmobQuery<Shouhuoaddress>();
        bmobQuery.addWhereEqualTo("buyerId",objectId);
        bmobQuery.findObjects(new FindListener<Shouhuoaddress>() {
            @Override
            public void done(List<Shouhuoaddress> list, BmobException e) {
                if(e==null){
                    Shouhuoaddress shouhuoaddress = list.get(0);
                    address_Editor.putString("objectId",shouhuoaddress.getBuyerId());
                    address_Editor.putString("realname",shouhuoaddress.getReaName());
                    address_Editor.putString("telephone",shouhuoaddress.getRealTelephone());
                    address_Editor.putString("address",shouhuoaddress.getRealAddress());
                    address_Editor.apply();
                }else{
                    address_Editor.putString("objectId",objectId);
                    address_Editor.putString("realname","未填");
                    address_Editor.putString("telephone","未填");
                    address_Editor.putString("address","未填");
                    address_Editor.apply();
                }
            }
        });

        //        添加Yanxun的数据
//        YanxunInfo yanxunInfo = new YanxunInfo();
//        yanxunInfo.setYanxunImage("http://p1.pstatp.com/large/pgc-image/e7076fd58fa14c6e928274df8caba362");
//        yanxunInfo.setYanxunName("考研在即，2020年将有341万考生共渡“研关”，你准备好了吗");
//        yanxunInfo.setYanxunUrl("https://www.toutiao.com/a6771690816176914956/");
//        yanxunInfo.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e==null){
//
//                }else{
//
//                }
//            }
//        });




//        添加person的数据
//        Person p2 = new Person();
//        p2.setName("chenyuan");
//        p2.setAddress("江苏扬州");
//        p2.save(new SaveListener<String>() {
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//                    Log.i("123","success");
//                }else{
//                    Log.i("123","failed");
//                }
//            }
//        });


        //查找Person表里面id为6b6c11c537的数据
//        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
//
//        bmobQuery.getObject("35dbb49c64", new QueryListener<Person>() {
//            @Override
//            public void done(Person object,BmobException e) {
//                if(e==null){
//                    Log.i("123","success");
//                    Log.i("123"," "+object.getAddress());
//                    Log.i("123"," "+object.getName());
//                    Toast.makeText(MainActivity.this, "you are right", Toast.LENGTH_SHORT).show();
//                }else{
//                    Log.i("123","failed");
//                    Toast.makeText(MainActivity.this, "you are wrong", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
//        Person p2 = new Person();
//        p2.setAddress("北京朝阳");
//        p2.update("35dbb49c64", new UpdateListener() {
//
//            @Override
//            public void done(BmobException e) {
//                if(e==null){
//                    Log.i("123","success");
//                }else{
//                    Log.i("123","failed");
//                }
//            }
//        });

        //删除一行数据
//        Person p2 = new Person();
//        p2.setObjectId("35dbb49c64");
//        p2.delete(new UpdateListener() {
//
//            @Override
//            public void done(BmobException e) {
//                if(e==null){
//                    Log.i("123","success");
//                }else{
//                    Log.i("123","failed");
//                }
//            }
//
//        });
    }

    private void initFragment() {
        fragments.add(new Main_Fragment());
        fragments.add(new Video_Fragment());
        fragments.add(new Person_Fragment());

        onClickListener.onClick(mainBottomSwitcherContainer.getChildAt(0));//默认在首页
    }

    /**
     * 初始化底部导航
     */
    private void initMainBottomSwitcher() {
        //获取底部container中孩子个数
        childCount = mainBottomSwitcherContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {//给每个孩子设置点击事件
            FrameLayout childAt = (FrameLayout) mainBottomSwitcherContainer.getChildAt(i);
            childAt.setOnClickListener(onClickListener);
        }
        setEnable(mainBottomSwitcherContainer.getChildAt(0), false);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int indexOfChild = mainBottomSwitcherContainer.indexOfChild(view);
            changeUi(indexOfChild);      //改变底部导航状态
            changeFragment(indexOfChild);//切换Fragment
        }
    };

    /**
     * 改变点击的index对应控件和它所有孩子的状态  enable=false
     * 改变其他控件和它们所有孩子的状态   enable=true
     *
     * @param index
     */
    private void changeUi(int index) {
        for (int i = 0; i < childCount; i++) {
            if (index == i) {
                setEnable(mainBottomSwitcherContainer.getChildAt(i), false);

//                Log.i("HomeFragment","false");
            } else {
                setEnable(mainBottomSwitcherContainer.getChildAt(i), true);
//                Log.i("HomeFragment","true");
            }
        }
    }


    private void changeFragment(int index) {
        Fragment fragment = fragments.get(index);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();
        //      .addToBackStack(fragment.getClass().getSimpleName())
    }

    /**
     * 将每个导航按钮中的所有控件状态改变
     * 由于我们处理一个通用的代码，那么导航按钮可能会有很多层控件，所以我们需要使用递归
     *
     * @param item
     * @param b
     */
    private void setEnable(View item, boolean b) {
        item.setEnabled(b);//解决的是：点击的导航按钮(我们用的是FrameLayout)是否还能点击
        if (item instanceof ViewGroup) {
            int childCount = ((ViewGroup) item).getChildCount();
            for (int i = 0; i < childCount; i++) {
                setEnable(((ViewGroup) item).getChildAt(i), b);//解决的是：每个导航按钮的孩子的状态的改变，如图片文字根据enable切换
            }
        }
    }
}
