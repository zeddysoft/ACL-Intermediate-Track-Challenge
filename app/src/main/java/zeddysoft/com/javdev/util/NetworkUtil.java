package zeddysoft.com.javdev.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by azeez on 4/21/17.
 */

public class NetworkUtil {
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
        // NetworkInfo info

        if (networkInfo != null && networkInfo.isConnected()
                && networkInfo.isAvailable()) {
            return true;
        }
        return false;

    }
}
