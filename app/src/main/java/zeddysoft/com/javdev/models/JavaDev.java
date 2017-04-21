package zeddysoft.com.javdev.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by azeez on 4/21/17.
 */

public class JavaDev implements Parcelable{

    private String id;
    private String username;
    private String url;
    private String avatarUrl;


    public JavaDev(JSONObject jsonObject) throws JSONException {
        setId(jsonObject.getString("id"));
        setUsername(jsonObject.getString("login"));
        setAvatarUrl(jsonObject.getString("avatar_url"));
        setUrl(jsonObject.getString("url"));
    }

    protected JavaDev(Parcel in) {
        id = in.readString();
        username = in.readString();
        url = in.readString();
        avatarUrl = in.readString();
    }

    public static final Creator<JavaDev> CREATOR = new Creator<JavaDev>() {
        @Override
        public JavaDev createFromParcel(Parcel in) {
            return new JavaDev(in);
        }

        @Override
        public JavaDev[] newArray(int size) {
            return new JavaDev[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(username);
        dest.writeString(url);
        dest.writeString(avatarUrl);
    }
}