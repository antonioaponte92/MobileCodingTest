package com.antonio.mobilecodingtest.commons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * This class allows you to avoid writing repeated methods for all activities.
 *
 * I have left an example of this in showSnackBar
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        onCreateView(savedInstanceState);

    }

    public abstract int getLayout();
    public abstract void onCreateView(@Nullable Bundle savedInstanceState);

    protected void showSnackBar(int intRes){
        showSnackBar(getString(intRes));
    }
    protected void showSnackBar(String text){
        Snackbar.make(findViewById(android.R.id.content),text,Snackbar.LENGTH_SHORT).show();
    }
}
