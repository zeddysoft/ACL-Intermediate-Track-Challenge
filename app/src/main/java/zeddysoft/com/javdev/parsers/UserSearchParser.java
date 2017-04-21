package zeddysoft.com.javdev.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zeddysoft.com.javdev.models.JavaDev;

/**
 * Created by azeez on 4/21/17.
 */

public class UserSearchParser {
    public static List<JavaDev> getJavaDevs(String response) {

        List<JavaDev> javaDevs = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for(int i = 0;i<jsonArray.length(); ++i){
                javaDevs.add(new JavaDev(jsonArray.getJSONObject(i)));
            }

        } catch (JSONException e) {

        }
        return javaDevs;
    }
}
