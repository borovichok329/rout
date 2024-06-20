package com.example.ag.helpers;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface  RetrofitService {


    @POST("/__idp/oauth2/token")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("grant_type") String login, @Field("audience") String pwd, @Field("scope") String pas);


    @POST("/__backend/rpc")
    Call<ArrayList<RouteResponse>> getRoutes(@Header("Authorization") String token, @Body RoutesBody body);

    @POST("/__backend/rpc")
    Call<Void> getRoutesString(@Header("Authorization") String token, @Body RoutesBody body);


    @POST("setRoute")
    Call<Void> setRoute(@Field("token") String token, @Field("route") String route);

}