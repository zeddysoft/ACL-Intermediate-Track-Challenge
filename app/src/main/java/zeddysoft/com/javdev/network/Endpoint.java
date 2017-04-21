package zeddysoft.com.javdev.network;

/**
 * Created by azeez on 4/21/17.
 */

public class Endpoint {

    public static final String BASE_URL = "https://api.github.com";
    public static final String USER_SEARCH = "/search/users";

    public static String getLagosDevSearchFilterUrl() {
        return BASE_URL+ USER_SEARCH + "?q=location:lagos";
    }
}
