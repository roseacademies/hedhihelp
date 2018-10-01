package org.roseacademies.hedhihelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class Forum extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "org.roseacademies.hedhihelp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        String url = "https://www.facebook.com/groups/208309499770336/";

        Intent intent = getIntent();
        String message = intent.getStringExtra(HomeActivity.EXTRA_MESSAGE);

        WebView webview = (WebView) findViewById(R.id.myWebView);
        webview.setWebViewClient(new MyWebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }
}

