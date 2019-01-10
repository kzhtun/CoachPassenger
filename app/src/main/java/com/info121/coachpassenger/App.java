package com.info121.coachpassenger;

import android.app.Application;
import android.media.RingtoneManager;
import android.net.Uri;

public class App extends Application {


    public static  String CONST_APP_TITLE = "Coach Passenger App";
    public static  String CONST_REST_API_URL = "http://alexisinfo121.noip.me:83/PassengerAppRestAPI/PassengerAppService.svc/";
    public static  String CONST_SIGNUP_URL = "http://alexisinfo121.noip.me:83/CoachPassengerApp/iSignUp.aspx";
    public static  String CONST_FORGOT_PASS_URL = "http://alexisinfo121.noip.me:83/CoachPassengerApp/iForgotPassword.aspx";
    public static  String CONST_WEB_URL = "http://alexisinfo121.noip.me:83/CoachPassengerApp/iVerifyOTP.aspx";



    //  http://alexisinfo121.noip.me:83/CoachPassengerApp/iVerifyOTP.aspx?LogInUser=username1&bookno=BK00000004

    public static Uri DEFAULT_SOUND_URI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    public static Uri NOTIFICATION_SOUND_URI = null;

    public static String USER_NAME = "";
    public static String DEVICE_ID = "";
    public static String BookNo = "";

    public static String CONST_PLATFORM = "ANDROID";
    public static String CONST_COMP_CODE = "COACH";
    public static String CONST_USER_NAME = "USER_NAME";
    public static String CONST_DEVICE_ID = "DEVICE_ID";
    public static String CONST_TIMER_DELAY = "TIMER_DELAY";
    public static String CONST_REMEMBER_ME = "REMEMBER_ME";

    @Override
    public void onCreate() {
        super.onCreate();

        App.NOTIFICATION_SOUND_URI = App.DEFAULT_SOUND_URI;



        // DEV 83

        // Staging 81 ------------------------------------------------//
        CONST_REST_API_URL = CONST_REST_API_URL.replace("83", "81");
        CONST_SIGNUP_URL = CONST_SIGNUP_URL.replace("83", "81");
        CONST_FORGOT_PASS_URL = CONST_FORGOT_PASS_URL.replace("83", "81");
        CONST_WEB_URL = CONST_WEB_URL.replace("83", "81");



        // End ------------------------------------------------//

    }
}
