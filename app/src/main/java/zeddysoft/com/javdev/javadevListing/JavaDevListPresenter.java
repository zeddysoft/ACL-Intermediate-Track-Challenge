package zeddysoft.com.javdev.javadevListing;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import zeddysoft.com.javdev.R;
import zeddysoft.com.javdev.application.App;
import zeddysoft.com.javdev.network.Endpoint;
import zeddysoft.com.javdev.network.NetworkRequest;
import zeddysoft.com.javdev.parsers.UserSearchParser;
import zeddysoft.com.javdev.util.NetworkUtil;

/**
 * Created by azeez on 4/21/17.
 */

public class JavaDevListPresenter implements LagJavaDevListContract.UserActionsListener {

    LagJavaDevListContract.View view;

    public JavaDevListPresenter(LagJavaDevListContract.View view) {
        this.view = view;
    }

    @Override
    public void openDetailOfJavaDev() {

        if (!NetworkUtil.isConnected(App.getAppContext())) {
            view.showErrorMessage(R.string.error_network_unavailable);
            return;
        }

        final NetworkRequest networkRequest = new NetworkRequest("userSearch").setCallback(new NetworkRequest.RequestCallback() {

            @Override
            public void onRequestStart(String tag) {
                view.showProgressBar();
            }

            @Override
            public void onRequestCompleted(String tag, String response) throws JSONException {
                view.dismissProgressBar();
                view.showJavaDevList(UserSearchParser.getJavaDevs(response));
            }

            @Override
            public void onRequestError(String tag, VolleyError error) {
                view.dismissProgressBar();
                view.showErrorMessage(R.string.github_error);
            }

            @Override
            public void onRequestCancelled() {

                view.dismissProgressBar();

            }
        });

        networkRequest.execute(Endpoint.getLagosDevSearchFilterUrl(), Request.Method.GET);

    }


}
