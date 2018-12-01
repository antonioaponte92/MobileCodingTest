package com.antonio.mobilecodingtest.ui.detail;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;

import com.antonio.mobilecodingtest.R;
import com.antonio.mobilecodingtest.commons.BaseActivity;

public class DetailActivity extends BaseActivity {
    static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        if (getIntent().getExtras()!=null){
            String id = getIntent().getStringExtra("id");
            Log.d(TAG, "Traigo ID: "+id);
        }
    }
}
