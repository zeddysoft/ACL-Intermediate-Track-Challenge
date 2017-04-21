package zeddysoft.com.javdev.application;

/**
 * Created by azeez on 4/21/17.
 */

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static App mInstance;
    private static Context mContext;

    public static final String TAG = App.class
            .getSimpleName();

    @Override
    public void onCreate() {

        super.onCreate();

        mInstance = this;
        setAppContext(getApplicationContext());
    }

    private static class AppHelper {

        private static App INSTANCE = mInstance;

    }
    public static Context getAppContext() {
        return mContext;
    }

    private void setAppContext(Context appContext) {
        mContext = appContext;
    }

    public static App getInstance() {
        return mInstance;
    }
}