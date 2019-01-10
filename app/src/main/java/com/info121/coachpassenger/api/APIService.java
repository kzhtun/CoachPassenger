package com.info121.coachpassenger.api;


import com.info121.coachpassenger.models.LoginRes;
import com.info121.coachpassenger.models.UpdateDeviceRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface APIService {

    @GET("validateuser/{user},{companycode}")
    Call<LoginRes> getAuthentication(@Path("user") String user, @Path("companycode") String companycode);


    @GET("updatedevice/{user},{deviceId},{platform},{fcm_token}")
    Call<UpdateDeviceRes> updateDevice(@Path("user") String user, @Path("deviceId") String deviceId, @Path("platform") String deviceType, @Path("fcm_token") String fcm_token);



}
