package com.jenny.github.Acitivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jenny.github.Models.Repo;

public class RepoWebViewActivity extends AppCompatActivity {
    private WebView mWebView;
    private static Repo repo;

    public static void setRepo(Repo newRepo) {
        repo = newRepo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        mWebView = new WebView(this);
        mWebView.loadUrl(repo.getUrl());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        this.setContentView(mWebView);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
