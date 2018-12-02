package com.antonio.mobilecodingtest.ui.list.mvp;

import android.content.Context;
import android.util.Log;

import com.antonio.mobilecodingtest.data.local.PointTable;
import com.antonio.mobilecodingtest.data.models.Data;
import com.antonio.mobilecodingtest.data.models.Point;

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
        view.hideProgress();
        for (Point point : data.getList()){
            PointTable pointTable = new PointTable(point.getId(),point.getTitle(),point.getGeocoordinates());
            pointTable.save();
        }
        view.showData(model.getListLocal());
    }

    @Override
    public void onGetRemoteListFailed(String reason) {
        if (view == null) return;
        view.hideProgress();
        view.showError(reason);
    }

    @Override
    public void getData() {
        if (view == null) return;
        view.showProgress();
        if (model.getListLocal().size()==0){
            model.getListRemote(this);
        }else{
            view.hideProgress();
            view.showData(model.getListLocal());
        }
    }

    @Override
    public void getDataFiltered(String filter) {
        if (view == null) return;
        view.showData(model.getListFiltered(filter));
    }
}
