package zeddysoft.com.javdev.javadevListing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import zeddysoft.com.javdev.R;
import zeddysoft.com.javdev.application.App;
import zeddysoft.com.javdev.interfaces.RecyclerViewClickListener;
import zeddysoft.com.javdev.javaDevDetail.ProfileDetailsActivity;
import zeddysoft.com.javdev.models.JavaDev;
import zeddysoft.com.javdev.util.Constant;

public class LagJavaDevListActivity
        extends AppCompatActivity
        implements LagJavaDevListContract.View, RecyclerViewClickListener {

    private RecyclerView javaDevRV;
    private ProgressBar javaDevPB;
    private JavaDevListPresenter mJavaDevListPresenter;
    private JavaDevListingAdapter mJavaDevListAdapter;
    private List<JavaDev> javaDevs;
    private final String JAVA_DEVS = "javaDevs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lag_java_dev_list);
        initViews();
        mJavaDevListPresenter = new JavaDevListPresenter(this);
        if( savedInstanceState != null){
            this.javaDevs  = savedInstanceState.getParcelableArrayList(JAVA_DEVS);
            showJavaDevList(javaDevs);
        }else{
            mJavaDevListPresenter.openDetailOfJavaDev();
        }

    }

    private void initViews() {
        javaDevRV = (RecyclerView) findViewById(R.id.dev_list_RV);
        javaDevPB = (ProgressBar) findViewById(R.id.dev_list_PB);
    }

    @Override
    public void showServerErrorMessage(String message) {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList(JAVA_DEVS, (ArrayList<? extends Parcelable>) javaDevs);
    }

    @Override
    public void showJavaDevList(List<JavaDev> javaDevs) {
        this.javaDevs = javaDevs;
        displayJavaDevs();
    }

    private void displayJavaDevs() {
        mJavaDevListAdapter =  new JavaDevListingAdapter(javaDevs,this, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(App.getAppContext());
        javaDevRV.setLayoutManager(layoutManager);
        javaDevRV.setItemAnimator(new DefaultItemAnimator());
        javaDevRV.setAdapter(mJavaDevListAdapter);
    }

    @Override
    public void dismissProgressBar() {
        javaDevPB.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        javaDevPB.setVisibility(View.VISIBLE);
    }


    @Override
    public void showErrorMessage(int stringId) {
            new AlertDialog.Builder(this)
                    .setTitle("Error Message")
                    .setMessage(getString(stringId))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            mJavaDevListPresenter.openDetailOfJavaDev();
                        }
                    })
                    .show();

    }

    @Override
    public void recyclerViewListClicked(View v, JavaDev javaDev) {
        Intent intent = new Intent(this, ProfileDetailsActivity.class);
        intent.putExtra(Constant.JAVA_DEV_EXTRA_DATA, javaDev);
        startActivity(intent) ;
    }
}