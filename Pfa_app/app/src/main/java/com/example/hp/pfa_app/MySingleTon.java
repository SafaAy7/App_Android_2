package com.example.hp.pfa_app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by hp on 22/04/2021.
 */

public class MySingleTon {
    static MySingleTon mInstance;
    RequestQueue requestQueue;
    static Context mCtx;

    private MySingleTon(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue==null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }    return requestQueue;

    }


    public static synchronized MySingleTon getmInstance(Context context){
        if(mInstance == null){
            mInstance = new MySingleTon(context);
        }
        return mInstance;
    }

    public<T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }


}
