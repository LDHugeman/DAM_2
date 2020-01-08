package com.example.intent_filter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Main2Activity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //CookieManager.getInstance().setAcceptCookie(true);
        Intent intent = getIntent();
        Uri URLrecibida = intent.getData(); // devuelve un objeto URI
        webView.loadUrl(URLrecibida.toString());
    }
}
