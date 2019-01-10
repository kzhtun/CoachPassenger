package com.info121.coachpassenger.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.info121.coachpassenger.App;
import com.info121.coachpassenger.R;
import com.info121.coachpassenger.utils.PrefDB;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;
    Toolbar mToolbar;
    ProgressBar mProgressBar;
    Context mContext;

    String url = "";

    PrefDB prefDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        initializeControls();
    }

    void initializeControls() {

        prefDB = new PrefDB(getApplicationContext());
        // App.userName = prefDB.getString(App.CONST_USER_NAME);

//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle(App.CONST_APP_TITLE);

        setSupportActionBar(mToolbar);

        mWebView = (WebView) findViewById(R.id.web_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        WebSettings webSettings = mWebView.getSettings();

        mWebView.setVisibility(View.GONE);

        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        mWebView.setInitialScale(100);
        mWebView.addJavascriptInterface(new JavaScriptInterface(this), "Android");

        mContext = WebViewActivity.this;

        String bookNo = getIntent().getExtras().getString("BOOKNO");
        String userName = getIntent().getExtras().getString("USERNAME");
        Boolean isSignUp = getIntent().getExtras().getBoolean("SIGNUP");
        Boolean isLogin = getIntent().getExtras().getBoolean("LOGIN");
        Boolean isForgotPassword = getIntent().getExtras().getBoolean("FORGOTPASSWORD");

        url = App.CONST_WEB_URL;

        if(isLogin)
            url = url + "?LogInUser=" + App.USER_NAME;

        if(isForgotPassword)
            url = App.CONST_FORGOT_PASS_URL;

        if(isSignUp)
            url = App.CONST_SIGNUP_URL;

        if(bookNo != null && bookNo.length() > 0)
            url = url + "?LogInUser=" + userName + "&bookno=" + bookNo;

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                mWebView.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });

        mWebView.loadUrl(url);


//        App.targetContent = WebViewActivity.this;

//        showAlertDialog();
//        //16.8293621,96.1528663
//        showAppSelectionDialog(16.8293621,96.1528663);
    }


    public class JavaScriptInterface {
        private final WebViewActivity webViewActivity;

        JavaScriptInterface(WebViewActivity webViewActivity) {
            this.webViewActivity = webViewActivity;
        }


        @JavascriptInterface
        public void logout() {
            finish();
        }

    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        alertDialog.setTitle("Warning");
        alertDialog.setMessage("Please do not use this function in Passenger application.");

        // On pressing Settings button
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

}
