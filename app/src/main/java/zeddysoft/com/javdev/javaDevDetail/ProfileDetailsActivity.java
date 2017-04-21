package zeddysoft.com.javdev.javaDevDetail;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import zeddysoft.com.javdev.R;
import zeddysoft.com.javdev.interfaces.ChangeableToolbarTitle;
import zeddysoft.com.javdev.javadevListing.LagJavaDevListActivity;
import zeddysoft.com.javdev.models.JavaDev;
import zeddysoft.com.javdev.util.Constant;

public class ProfileDetailsActivity extends AppCompatActivity implements ChangeableToolbarTitle{

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        Bundle data = getIntent().getExtras();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Fragment fragment = new ProfileDetailsFragment();
        fragment.setArguments(data);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left
                , R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.container, fragment);

        fragmentTransaction.commitAllowingStateLoss();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        int count = manager.getBackStackEntryCount();
        if (count > 0) {
            manager.popBackStack();
            return;
        }

        super.onBackPressed();
    }


    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

}