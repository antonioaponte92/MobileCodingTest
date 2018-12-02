package com.antonio.mobilecodingtest.ui.detail.mvp;

import android.util.Log;

import com.antonio.mobilecodingtest.BuildConfig;
import com.antonio.mobilecodingtest.commons.RetrofitClient;
import com.antonio.mobilecodingtest.data.local.PointDetailsTable;
import com.antonio.mobilecodingtest.data.models.PointDetails;
import com.antonio.mobilecodingtest.data.remote.RemoteApi;
import com.antonio.mobilecodingtest.ui.list.mvp.ListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailModel {
    private static final String TAG = ListModel.class.getSimpleName();
    private RemoteApi api = RetrofitClient.getClient(BuildConfig.BASE_URL).create(RemoteApi.class);

    public void getDetailRemote(String id, final DetailContract.ModelResultListener listener){
        api.getPointDetail(id).enqueue(new Callback<PointDetails>() {
            @Override
            public void onResponse(Call<PointDetails> call, Response<PointDetails> response) {
                Log.d(TAG, "onResponse: "+response.code());
                if (response.body()!=null)
                    listener.onGetRemoteDataSuccess(response.body());
                else
                    listener.onGetRemoteDataFailed("Error Code: "+response.code());
            }

            @Override
            public void onFailure(Call<PointDetails> call, Throwable t) {
                Log.e(TAG,t.getLocalizedMessage());
                listener.onGetRemoteDataFailed(t.getMessage());
            }
        });
    }

    public List<PointDetailsTable> getPoint(String id){
        return PointDetailsTable.find(PointDetailsTable.class,"identifier = ? ",id);
    }
}
