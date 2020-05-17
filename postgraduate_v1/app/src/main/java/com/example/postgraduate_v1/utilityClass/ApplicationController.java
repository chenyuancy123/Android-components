package com.example.postgraduate_v1.utilityClass;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import io.rong.imkit.RongIM;


//-------------------volley框架显示图片--------------------------------------
public class ApplicationController extends Application {

    private static final String TAG= ApplicationController.class.getSimpleName();

    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private static ApplicationController mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;


        //融云
        String appKey = "bmdehs6pba7xs";
        RongIM.init(getApplicationContext(), appKey);
        //融云

    }

    public static synchronized ApplicationController getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null)
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        return requestQueue;
    }

    public ImageLoader getImageLoader(){
        getRequestQueue();
        if(imageLoader==null){
            imageLoader=new ImageLoader(requestQueue,new LruBitmapCache());
        }
        return imageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    public void cancelPendingRequest(Object tag){
        if(requestQueue!=null){
            requestQueue.cancelAll(tag);
        }
    }
}
