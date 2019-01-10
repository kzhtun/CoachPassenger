package com.info121.coachpassenger.api;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.info121.coachpassenger.App;
import com.info121.coachpassenger.models.LoginRes;
import com.info121.coachpassenger.models.UpdateDeviceRes;


import retrofit2.Call;



public class APIClient {

    public static void GetAuthentication(String userName) {
        Call<LoginRes> call = RestClient.LIMO().getApiService().getAuthentication(userName, App.CONST_COMP_CODE);
        call.enqueue(new APICallback<LoginRes>() {
        });
    }

    public static void updatedevice(String userName, String deviceId, String platform) {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("Firebase Token : ", fcmToken);
      //  Call<UpdateDeviceRes> call = RestClient.LIMO().getApiService().updateDevice(userName, App.DEVICE_ID, App.CONST_PLATFORM, fcmToken);

        Call<UpdateDeviceRes> call = RestClient.LIMO().getApiService().updateDevice(userName, deviceId, platform, fcmToken);

        Log.e("updatedevice", "user : " + userName);
        Log.e("updatedevice", "device id : " +  App.DEVICE_ID);
        Log.e("updatedevice", "platform : " +  App.CONST_PLATFORM);
        Log.e("updatedevice", "fcmToken : " +  fcmToken);

        call.enqueue(new APICallback<UpdateDeviceRes>() {
        });
    }

}
