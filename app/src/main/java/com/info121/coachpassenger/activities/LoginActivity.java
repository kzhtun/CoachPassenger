package com.info121.coachpassenger.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.info121.coachpassenger.AbstractActivity;
import com.info121.coachpassenger.App;
import com.info121.coachpassenger.R;
import com.info121.coachpassenger.api.APIClient;
import com.info121.coachpassenger.models.LoginRes;
import com.info121.coachpassenger.models.UpdateDeviceRes;
import com.info121.coachpassenger.utils.PrefDB;
import com.info121.coachpassenger.utils.Util;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AbstractActivity {


    @BindView(R.id.user_name)
    EditText mUserName;

    @BindView(R.id.ui_version)
    TextView mUiVersion;

    @BindView(R.id.api_version)
    TextView mApiVersion;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.remember_me)
    CheckBox mRemember;

    @BindView(R.id.sign_up)
    TextView mSignup;


    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.forgot_password)
    TextView mForgotPassword;

    PrefDB prefDB = null;
    Context mContext;

//@BindView(R.id.fb_token)
//EditText mToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mContext = LoginActivity.this;

        prefDB = new PrefDB(mContext);

        //   mUserName.setText("username1");

        mApiVersion.setText("Api " + Util.getVersionCode(mContext));
        mUiVersion.setText("Ver " + Util.getVersionName(mContext));

        if (App.CONST_REST_API_URL.indexOf("83") > 0)
            mTitle.setTextColor(ContextCompat.getColor(mContext, R.color.dark_green));

    }

    @OnClick(R.id.forgot_password)
    public void forgotPasswordOnClick() {
        Intent intent = new Intent(new Intent(LoginActivity.this, WebViewActivity.class));

        intent.putExtra("FORGOTPASSWORD", true);

        startActivity(intent);
        mProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.sign_up)
    public void signupOnClick() {
        Intent intent = new Intent(new Intent(LoginActivity.this, WebViewActivity.class));

        intent.putExtra("SIGNUP", true);

        startActivity(intent);
        mProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.login)
    public void loginOnClick() {

        mProgressBar.setVisibility(View.VISIBLE);

        if (mUserName.getText().length() > 0) {
            APIClient.GetAuthentication(mUserName.getText().toString());
        } else {
            mUserName.setError("User name should not be blank.");
            mProgressBar.setVisibility(View.GONE);
        }
    }


    @Subscribe
    public void onEvent(LoginRes res) {
        if (res.getValidateuserResult().length() == 0) {
            mUserName.setError("User account does not exist");
            mProgressBar.setVisibility(View.GONE);
        } else if (res.getValidateuserResult().length() > 0) {
            // Add to Appication Varialbles
            App.USER_NAME = res.getValidateuserResult();
            App.DEVICE_ID = Util.getDeviceID(getApplicationContext());

            prefDB.putString(App.CONST_USER_NAME, App.USER_NAME);
            prefDB.putString(App.CONST_DEVICE_ID, App.DEVICE_ID);

            Log.e("Login ", "Success");
            APIClient.updatedevice(App.USER_NAME, App.DEVICE_ID, App.CONST_PLATFORM);

            if (mRemember.isChecked())
                prefDB.putBoolean(App.CONST_REMEMBER_ME, true);
            else
                prefDB.putBoolean(App.CONST_REMEMBER_ME, false);

        } else {

        }
    }

    @Subscribe
    public void onEvent(UpdateDeviceRes res) {
        if (res.getUpdatedeviceResult().equalsIgnoreCase("Success"))
            LoginSuccessful();
    }


    private void LoginSuccessful() {
        Intent intent = new Intent(new Intent(LoginActivity.this, WebViewActivity.class));

        intent.putExtra("LOGIN", true);

        startActivity(intent);
        mProgressBar.setVisibility(View.GONE);
    }

}
