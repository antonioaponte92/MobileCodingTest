package com.antonio.mobilecodingtest.ui.detail.mvp;

import android.content.Context;

import com.antonio.mobilecodingtest.data.models.PointDetails;

public class DetailPresenter implements DetailContract.Presenter,DetailContract.ModelResultListener{
    private DetailContract.View view;
    private DetailModel model;
    private Context context;

    public DetailPresenter(DetailContract.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new DetailModel();
    }

    @Override
    public void onAttach(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public void onGetRemoteDataSuccess(PointDetails data) {
        if (view==null) return;
        view.hideProgress();
        view.showData(data);
    }

    @Override
    public void onGetRemoteDataFailed(String reason) {
        if (view==null) return;
        view.hideProgress();
        view.showError(reason);
    }

    @Override
    public void getData(String id) {
        if (view==null) return;
        view.showProgress();
        model.getDetail(id,this);
    }
}
