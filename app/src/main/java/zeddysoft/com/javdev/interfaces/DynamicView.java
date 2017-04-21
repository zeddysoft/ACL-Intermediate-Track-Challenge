package zeddysoft.com.javdev.interfaces;

/**
 * Created by azeez on 4/21/17.
 */

public interface DynamicView {
    void showServerErrorMessage(String message);

    //shows error when trying to make a request to a server
    // and network is unavailable or any other error that ain't server related
}
