package com.antonio.mobilecodingtest.ui.detail.mvp;

import com.antonio.mobilecodingtest.commons.MVPContract;
import com.antonio.mobilecodingtest.data.local.PointDetailsTable;
import com.antonio.mobilecodingtest.data.models.PointDetails;

public class DetailContract extends MVPContract {
    public interface ModelResultListener{
        void onGetRemoteDataSuccess(PointDetails data);
        void onGetRemoteDataFailed(String reason);
    }

    public interface Presenter extends MVPContract.Presenter<DetailContract.View>{
        void getData(String id);
    }

    public interface View extends MVPContract.View{
        void showData(PointDetailsTable data);
    }
}
