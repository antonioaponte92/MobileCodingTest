package com.antonio.mobilecodingtest.ui.list.mvp;

import com.antonio.mobilecodingtest.commons.MVPContract;
import com.antonio.mobilecodingtest.data.models.Data;

public class ListContract extends MVPContract{
    public interface ModelResultListener{
        void onGetRemoteListSuccess(Data data);
        void onGetRemoteListFailed(String reason);
    }

    public interface Presenter extends MVPContract.Presenter<View>{
        void getData();
    }

    public interface View extends MVPContract.View{
        void showData(Data data);
    }
}
