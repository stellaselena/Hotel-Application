package com.example.stellajovanovic.hotelapplication.OutAndABout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.stellajovanovic.hotelapplication.R;

public class TripAdvisorActivity extends AppCompatActivity {
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_and_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        web = (WebView) findViewById(R.id.webView1);

        WebSettings wbset = web.getSettings();
        wbset.setJavaScriptEnabled(true);
        web.setWebViewClient(new MyWebViewClient());
        String url = "https://www.tripadvisor.com/Attractions-g190479-Activities-Oslo_Eastern_Norway.html";

        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);

    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
