package com.example.user.guruforstudent;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.user.guruforstudent.Models.User;

public class searchCoursesWebview extends AppCompatActivity {
    private WebView wv1;
    private ProgressDialog progDailog;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_courses_webview);
            user = new User();
            String userid = Integer.toString(user.getCurUserId());
            String Url = "https://gurugedara.ga/app/search/";
            progDailog = ProgressDialog.show(this, "Loading","Please wait...", true);
            progDailog.setCancelable(false);
            wv1=(WebView)findViewById(R.id.mcqWeb);
            wv1.getSettings().setJavaScriptEnabled(true);
            wv1.getSettings().setLoadWithOverviewMode(true);
            wv1.getSettings().setUseWideViewPort(true);
            wv1.setWebViewClient(new WebViewClient(){

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    progDailog.show();
                    view.loadUrl(url);
                    return true;
                }
                @Override
                public void onPageFinished(WebView view, final String url) {
                    progDailog.dismiss();
                }
            });
            wv1.loadUrl(Url);
        }
}
