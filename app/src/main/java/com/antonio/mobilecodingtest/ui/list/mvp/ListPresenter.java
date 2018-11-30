package com.antonio.mobilecodingtest.ui.list.mvp;

import android.content.Context;

import com.antonio.mobilecodingtest.data.models.Data;

public class ListPresenter implements ListContract.Presenter,ListContract.ModelResultListener{
    private ListContract.View view;
    private ListModel model;
    private Context context;

    public ListPresenter(ListContract.View view, Context context) {
        this.view = view;
        this.context = context;
        model = new ListModel();
    }

    @Override
    public void onAttach(ListContract.View view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    @Override
    public void onGetRemoteListSuccess(Data data) {
        if (view == null) return;
        view.showData(data);
    }

    @Override
    public void onGetRemoteListFailed(String reason) {
        if (view == null) return;
        view.showError(reason);
    }

    @Override
    public void getData() {
        if (view == null) return;
        model.getListRemote(this);
    }
}
