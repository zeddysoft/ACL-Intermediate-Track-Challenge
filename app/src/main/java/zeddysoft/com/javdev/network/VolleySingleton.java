package zeddysoft.com.javdev.network;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

import zeddysoft.com.javdev.application.App;

/**
 * Created by azeez on 4/21/17.
 */
public class VolleySingleton {
    private static VolleySingleton mInstance = null;
    private RequestQueue mRequestQueue;

    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(App.getAppContext());
        VolleyLog.DEBUG = false;
    }

    public static VolleySingleton getInstance(){
        if(mInstance == null){
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return this.mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}