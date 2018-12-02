package com.antonio.mobilecodingtest.ui.detail.mvp;

import com.antonio.mobilecodingtest.data.local.PointDetailsTable;
import com.antonio.mobilecodingtest.data.models.PointDetails;

import java.util.List;

public class DetailPresenter implements DetailContract.Presenter,DetailContract.ModelResultListener{
    private DetailContract.View view;
    private DetailModel model;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
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

        PointDetailsTable record = new PointDetailsTable();
        record.setIdientifier(data.getId());
        record.setTitle(data.getTitle());
        record.setAddress(data.getAddress());
        record.setTransport(data.getTransport());
        record.setEmail(data.getEmail());
        record.setGeocoordinates(data.getGeocoordinates());
        record.setDescription(data.getDescription());
        record.setPhone(data.getPhone());
        record.save();

        view.showData(record);
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
        List<PointDetailsTable> list = model.getPoint(id);
        if (list.size()==0){
            view.showProgress();
            model.getDetailRemote(id,this);
        }else{
            view.hideProgress();
            view.showData(list.get(0));
        }
    }
}
