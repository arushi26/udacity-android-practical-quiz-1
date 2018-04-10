package com.example.udacitypracticalquiz1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by arushi on 09/04/18.
 */

public class DetailsActivity extends AppCompatActivity{
    Toolbar toolbar;
    TextView mTvName, mTvEmail, mTvDesc;
    final String mNameKey = "NAME", mEmailKey = "EMAIL", mDescKey = "DESC";
    private ProgressBar mLoadingIndicator;
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mLoadingIndicator.setVisibility(View.VISIBLE); // Loading indicator

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        initViews();
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

    private void initViews(){
        mTvName = findViewById(R.id.demoUserName);
        mTvEmail = findViewById(R.id.demoEmail);
        mTvDesc = findViewById(R.id.demoAbout);
        mImage = findViewById(R.id.imageView);
        Glide.with(getApplicationContext())
                .load(R.drawable.bird)
                .into(mImage);

        SharedPreferences sharedPref = this.getSharedPreferences(getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        String name = sharedPref.getString(mNameKey,getResources().getString(R.string.label_details_username));
        String email = sharedPref.getString(mEmailKey,getResources().getString(R.string.label_details_email));
        String desc = sharedPref.getString(mDescKey,getResources().getString(R.string.label_desc));
        mTvName.setText(name);
        mTvEmail.setText(email);
        mTvDesc.setText(desc);
    }
}
