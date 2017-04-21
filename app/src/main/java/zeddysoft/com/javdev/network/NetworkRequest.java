package zeddysoft.com.javdev.network;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by azeez on 4/21/17.
 */
public class NetworkRequest {

    private RequestQueue queue;
    private RequestCallback callback;
    private StringRequest req;
    private String tag;

    public NetworkRequest(String tag){
        queue = VolleySingleton.getInstance().getRequestQueue();
        this.tag = tag;
    }

    public NetworkRequest setCallback(RequestCallback callback){
        this.callback = callback;
        return this;
    }


    public void setTag(String tag){
        this.tag = tag;
    }

    public String getTag(){
        return this.tag;
    }

    public void execute(String url, int requestMethod){
        execute(url, requestMethod, null);
    }

    public void execute(String url, int requestMethod, final Map<String, String> params){
        callback.onRequestStart(tag);
        req = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            callback.onRequestCompleted(tag, response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onRequestError(tag, error);
            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String res = new String(response.data, "UTF-8");
                    return Response.success(res, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return Response.error(new ParseError(response));
                }
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if(params != null)
                    return params;
                else
                    return super.getParams();
            }



        };
        int socketTimeout = 70000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        req.setRetryPolicy(policy);
        req.setTag(tag);
        queue.add(req);
    }

    /**
     * Cancel the request
     */
    public void cancelRequest(){
        if(req != null){
            req.cancel();
            queue.cancelAll(tag);
            callback.onRequestCancelled();
        }

    }

    public interface RequestCallback {
        public void onRequestStart(String tag);
        public void onRequestCompleted(String tag, String response) throws JSONException;
        public void onRequestError(String tag, VolleyError error);
        public void onRequestCancelled();
    }
}