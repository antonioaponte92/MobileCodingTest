package com.antonio.mobilecodingtest.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.antonio.mobilecodingtest.R;
import com.antonio.mobilecodingtest.commons.BaseActivity;
import com.antonio.mobilecodingtest.data.local.PointTable;
import com.antonio.mobilecodingtest.ui.adapters.PointsAdapter;
import com.antonio.mobilecodingtest.ui.detail.DetailActivity;
import com.antonio.mobilecodingtest.ui.list.mvp.ListContract;
import com.antonio.mobilecodingtest.ui.list.mvp.ListPresenter;

import java.util.List;

import butterknife.BindView;

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
        adapter = new PointsAdapter(this,getBaseContext());
        presenter = new ListPresenter(this,getBaseContext());
        presenter.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListActivity.this);
        recycler_points.setHasFixedSize(true);
        recycler_points.setNestedScrollingEnabled(true);
        recycler_points.setLayoutManager(linearLayoutManager);
        recycler_points.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
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
    public void onItemClick(String id) {
        Intent intent = new Intent(ListActivity.this, DetailActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showData(List<PointTable> data) {
        adapter.setData(data);
    }
}
