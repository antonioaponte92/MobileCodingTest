package com.antonio.mobilecodingtest.data.remote;

import com.antonio.mobilecodingtest.data.models.Data;
import com.antonio.mobilecodingtest.data.models.PointDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RemoteApi {

    @Headers("Content-Type:application/json")
    @GET("points")
    Call<Data> getPoints();

    @Headers("Content-Type:application/json")
    @GET("points/{id}")
    Call<PointDetails> getPointDetail(@Path("id") String id);
}
