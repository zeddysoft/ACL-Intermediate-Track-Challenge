package zeddysoft.com.javdev.javaDevDetail;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import zeddysoft.com.javdev.R;
import zeddysoft.com.javdev.interfaces.ChangeableToolbarTitle;
import zeddysoft.com.javdev.models.JavaDev;
import zeddysoft.com.javdev.util.Constant;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileDetailsFragment extends Fragment {


    private ImageView profileImageView;
    private TextView usernameView;
    private TextView githubUrlView;
    private Button shareBtn;
    private JavaDev javaDev;

    private final String GITHUB_BASE_URL = "https://github.com/";

    public ProfileDetailsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_profile_details, container, false);
        initViews(view);

        Bundle bundle = getArguments();
        javaDev = (JavaDev) bundle.getParcelable(Constant.JAVA_DEV_EXTRA_DATA);
        ((ChangeableToolbarTitle)getActivity()).setToolbarTitle(javaDev.getUsername());
        loadDataIntoViews(javaDev);

        return  view;
    }


    private void loadDataIntoViews(JavaDev javaDev) {

        usernameView.setText(javaDev.getUsername());
        githubUrlView.setText(GITHUB_BASE_URL+ javaDev.getUsername());

        Picasso.with(getContext())
                .load(javaDev.getAvatarUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(profileImageView);

    }

    private void initViews(View view) {
        profileImageView = (ImageView) view.findViewById(R.id.frag_profile_image);
        usernameView = (TextView) view.findViewById(R.id.frag_username);
        githubUrlView = (TextView) view.findViewById(R.id.frag_github_url);
        shareBtn = (Button) view.findViewById(R.id.shareBtn);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome developer @"
                +javaDev.getUsername() + ", " + GITHUB_BASE_URL + javaDev.getUsername());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

}
