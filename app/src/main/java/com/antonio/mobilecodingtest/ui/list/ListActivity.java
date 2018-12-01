package com.antonio.mobilecodingtest.ui.list;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.antonio.mobilecodingtest.R;
import com.antonio.mobilecodingtest.commons.BaseActivity;
import com.antonio.mobilecodingtest.data.models.Data;
import com.antonio.mobilecodingtest.ui.adapters.PointsAdapter;
import com.antonio.mobilecodingtest.ui.list.mvp.ListContract;
import com.antonio.mobilecodingtest.ui.list.mvp.ListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ListActivity extends BaseActivity implements ListContract.View
        ,PointsAdapter.PointListener
        ,SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = ListActivity.class.getSimpleName();
    @BindView(R.id.progressBar)             ProgressBar progressBar;
    @BindView(R.id.recycler_view)           RecyclerView recycler_points;
    @BindView(R.id.swipe)                   SwipeRefreshLayout swipeRefreshLayout;
    private ListPresenter presenter;
    private PointsAdapter adapter;

    @Override
    public int getLayout() {
        return R.layout.activity_list;
    }

    @Override
    public void onCreateView(@Nullable Bundle savedInstanceState) {
        presenter = new ListPresenter(this,getBaseContext());
        presenter.getData();
        adapter = new PointsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListActivity.this);
        recycler_points.setHasFixedSize(true);
        recycler_points.setNestedScrollingEnabled(true);
        recycler_points.setLayoutManager(linearLayoutManager);
        recycler_points.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void showData(Data data) {
        Log.d(TAG, "showData");
        adapter.setData(data.getList());
    }

    @Override
    public void showNoResult() {
        Log.i(TAG, "showNoResult");
    }

    @Override
    public void showError(String reason) {
        Log.e(TAG, "showError: "+reason);
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
    public void onItemClick(int id) {

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
