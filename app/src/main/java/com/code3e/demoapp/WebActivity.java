package com.code3e.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView webView = (WebView)findViewById(R.id.webView);
        webView.loadUrl("http://www.ine.mx/");
        webView.setWebViewClient(new WebViewClient());
    }

}
