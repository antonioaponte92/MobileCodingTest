package com.antonio.mobilecodingtest.ui.list;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.antonio.mobilecodingtest.BuildConfig;
import com.antonio.mobilecodingtest.R;
import com.antonio.mobilecodingtest.commons.BaseActivity;
import com.antonio.mobilecodingtest.data.local.PointTable;
import com.antonio.mobilecodingtest.ui.adapters.PointsAdapter;
import com.antonio.mobilecodingtest.ui.detail.DetailActivity;
import com.antonio.mobilecodingtest.ui.list.mvp.ListContract;
import com.antonio.mobilecodingtest.ui.list.mvp.ListPresenter;
import com.google.android.gms.maps.MapView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnTextChanged;
import butterknife.OnTouch;

public class ListActivity extends BaseActivity implements ListContract.View
        ,PointsAdapter.PointListener
        ,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.progressBar)             ProgressBar progressBar;
    @BindView(R.id.recycler_view)           RecyclerView recycler_points;
    @BindView(R.id.swipe)                   SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.etSearch)                EditText etSearch;
    @BindView(R.id.layoutNoResults)         FrameLayout noResults;

    private ListPresenter presenter;
    private PointsAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        adapter = new PointsAdapter(this,getBaseContext());
        presenter = new ListPresenter(this,getBaseContext());
        presenter.getData();
        recycler_points.setHasFixedSize(true);
        recycler_points.setNestedScrollingEnabled(true);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            recycler_points.setLayoutManager(new GridLayoutManager(this,3));
        else
            recycler_points.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        recycler_points.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void showError(String reason) {
        showSnackBar(reason);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(String id, MapView sharedMap) {
        Intent intent = new Intent(ListActivity.this, DetailActivity.class);
        intent.putExtra("id",id);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedMap,
                ViewCompat.getTransitionName(sharedMap));
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        if (etSearch.getText().toString().isEmpty())
            presenter.getData();
        else
            presenter.getDataFiltered(etSearch.getText().toString());
    }

    @Override
    public void showData(List<PointTable> data) {
        adapter.setData(data);
        noResults.setVisibility(data.size()== 0 ? View.VISIBLE : View.GONE);

    }

    @OnTouch({R.id.recycler_view,R.id.layoutNoResults})
    boolean onTouch(View v){
        etSearch.clearFocus();
        try{
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @OnTextChanged(value = R.id.etSearch, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterTextChanged(Editable editable){
        if (editable.toString().isEmpty()){
            presenter.getData();
        }else{
            presenter.getDataFiltered(editable.toString());
        }
    }
}
