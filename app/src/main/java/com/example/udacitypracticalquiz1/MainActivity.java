package com.example.udacitypracticalquiz1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {
    Toolbar toolbar;
    EditText mEditName, mEditEmail, mEditDesc;
    final String mNameKey = "NAME", mEmailKey = "EMAIL", mDescKey = "DESC";
    Button mBtnNext;
    private ProgressBar mLoadingIndicator;
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mLoadingIndicator.setVisibility(View.VISIBLE); // Loading indicator

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditName = findViewById(R.id.edit_username);
        mEditEmail = findViewById(R.id.edit_email);
        mEditDesc = findViewById(R.id.edit_about);
        mBtnNext = findViewById(R.id.btn_next);
        mImage = findViewById(R.id.imageView);

        Glide.with(getApplicationContext())
                .load(R.drawable.cat)
                .into(mImage);

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(mNameKey);
            String email = savedInstanceState.getString(mEmailKey);
            String desc = savedInstanceState.getString(mDescKey);

            mEditName.setText(name);
            mEditEmail.setText(email);
            mEditDesc.setText(desc);
        }

        mBtnNext.setOnClickListener(this);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String name = mEditName.getText().toString();
        String email = mEditEmail.getText().toString();
        String desc = mEditDesc.getText().toString();
        if(!TextUtils.isEmpty(name)) {
            outState.putString(mNameKey, name);
        }
        if(!TextUtils.isEmpty(email)) {
            outState.putString(mEmailKey, email);
        }
        if(!TextUtils.isEmpty(desc)) {
            outState.putString(mDescKey, desc);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account:
                Intent intent = new Intent(this, DetailsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                mLoadingIndicator.setVisibility(View.VISIBLE);
                // Save data to shared preferences
                SharedPreferences sharedPref = this.getSharedPreferences(getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                String name = mEditName.getText().toString();
                String email = mEditEmail.getText().toString();
                String desc = mEditDesc.getText().toString();
                if(!TextUtils.isEmpty(name)) {
                    editor.putString(mNameKey, name);
                }
                if(!TextUtils.isEmpty(email)) {
                    editor.putString(mEmailKey, email);
                }
                if(!TextUtils.isEmpty(desc)) {
                    editor.putString(mDescKey, desc);
                }
                editor.commit();

                // Clear EditText fields
                mEditName.setText("");
                mEditEmail.setText("");
                mEditDesc.setText("");

                mLoadingIndicator.setVisibility(View.INVISIBLE);
                // Navigate to details screen
                Intent intent = new Intent(this, DetailsActivity.class);
                startActivity(intent);
        }
    }
}
