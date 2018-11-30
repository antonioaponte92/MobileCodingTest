package com.antonio.mobilecodingtest.ui.list;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;

import com.antonio.mobilecodingtest.R;
import com.antonio.mobilecodingtest.commons.BaseActivity;
import com.antonio.mobilecodingtest.data.models.Data;
import com.antonio.mobilecodingtest.ui.list.mvp.ListContract;
import com.antonio.mobilecodingtest.ui.list.mvp.ListPresenter;

import butterknife.OnClick;

public class ListActivity extends BaseActivity implements ListContract.View{
    private static final String TAG = ListActivity.class.getSimpleName();
    private ListPresenter presenter;

    @Override
    public int getLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        presenter = new ListPresenter(this,getBaseContext());

    }

    @Override
    public void showData(Data data) {
        Log.d(TAG, "showData");
    }

    @Override
    public void showNoResult() {
        Log.i(TAG, "showNoResult");
    }

    @Override
    public void showError(String reason) {
        Log.e(TAG, "showError: "+reason);
    }
}
