package zeddysoft.com.javdev.javadevListing;

import java.util.List;

import zeddysoft.com.javdev.interfaces.DynamicView;
import zeddysoft.com.javdev.models.JavaDev;

/**
 * Created by azeez on 4/21/17.
 */

public class LagJavaDevListContract {

    interface View extends DynamicView {
        void showJavaDevList(List<JavaDev> javaDevs);
        void dismissProgressBar();
        void showProgressBar();
        void showErrorMessage(int stringId);
    }

    interface UserActionsListener {

        void openDetailOfJavaDev();
    }
}
