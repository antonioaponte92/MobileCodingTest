package com.antonio.mobilecodingtest.ui.list.mvp;

import com.antonio.mobilecodingtest.commons.MVPContract;
import com.antonio.mobilecodingtest.data.local.PointTable;
import com.antonio.mobilecodingtest.data.models.Data;
import com.antonio.mobilecodingtest.data.models.PointDetails;

import java.util.List;

public class ListContract extends MVPContract{
    public interface ModelResultListener{
        void onGetRemoteListSuccess(Data data);
        void onGetRemoteListFailed(String reason);
    }

    public interface Presenter extends MVPContract.Presenter<View>{
        void getData();
    }

    public interface View extends MVPContract.View{
        void showData(List<PointTable> data);
    }
}
