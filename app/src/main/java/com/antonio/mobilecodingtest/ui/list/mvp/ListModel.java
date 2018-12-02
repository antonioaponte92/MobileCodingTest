package com.antonio.mobilecodingtest.ui.list.mvp;

import android.util.Log;

import com.antonio.mobilecodingtest.BuildConfig;
import com.antonio.mobilecodingtest.commons.RetrofitClient;
import com.antonio.mobilecodingtest.data.local.PointTable;
import com.antonio.mobilecodingtest.data.models.Data;
import com.antonio.mobilecodingtest.data.remote.RemoteApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListModel {
    private static final String TAG = ListModel.class.getSimpleName();
    RemoteApi api = RetrofitClient.getClient(BuildConfig.BASE_URL).create(RemoteApi.class);

    public void getListRemote(final ListContract.ModelResultListener listener){
        api.getPoints().enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Log.d(TAG,"onResponse: "+response.code());
                if (response.body()!=null){
                    listener.onGetRemoteListSuccess(response.body());
                }else{
                    listener.onGetRemoteListFailed("Error code: "+response.code());
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e(TAG,t.getLocalizedMessage());
                listener.onGetRemoteListFailed(t.getMessage());
            }
        });
    }

    public List<PointTable> getListLocal(){
        return PointTable.listAll(PointTable.class);
    }
}
