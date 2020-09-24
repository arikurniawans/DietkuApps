package com.example.dietkuapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dietkuapps.R;

public class DetailInfoDietActivity extends AppCompatActivity {
    private WebView myWebview;
    ProgressBar progressBar;
    String pathFile1 = "file:///android_asset/Diet_1/desk_1.html";
    String pathFile2 = "file:///android_asset/Diet_2/desk_2.html";
    String pathFile3 = "file:///android_asset/Diet_3/desk_3.html";
    String pathFile4 = "file:///android_asset/Diet_4/desk_4.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info_diet);
        setTitle("Detail Informasi Diet");
        Intent intent = getIntent();
        String id = intent.getStringExtra("no_diet");

        myWebview = findViewById(R.id.konten_view);
        progressBar = findViewById(R.id.progressBar);
        myWebview.setWebViewClient(new WebViewClient());
        WebSettings settings = myWebview.getSettings();
        settings.setJavaScriptEnabled(true);

        if(id.equals("1"))
        {
            myWebview.loadUrl(pathFile1);
        }
        else if(id.equals("2"))
        {
            myWebview.loadUrl(pathFile2);
        }
        else if(id.equals("3"))
        {
            myWebview.loadUrl(pathFile3);
        }
        else if(id.equals("4"))
        {
            myWebview.loadUrl(pathFile4);
        }

    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

}