package com.android.diy.app.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.android.diy.app.R;
import com.android.diy.app.base.BaseActivity;
import com.android.diy.app.databinding.ActivityAboutBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by cheng on 2017/3/9.
 * 关于我们
 */
public class AboutActivity extends BaseActivity<ActivityAboutBinding> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initActionBar(mDataBinding.appToolbar.toolbar);
        // 加载 asset 文件
        String about = getFromAssets("html/about.html");
        mDataBinding.webView.loadDataWithBaseURL(null, about, "text/html", "utf-8", null);
        mDataBinding.webView.setWebChromeClient(webChromeClient);
    }

    /*
    * 获取html文件
    */
    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(
                    getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    WebChromeClient webChromeClient = new WebChromeClient() {
        @Override public void onProgressChanged(WebView view, int progress) {
            if (progress < 100) {
                mDataBinding.progressBar.setVisibility(View.VISIBLE);
                mDataBinding.progressBar.setProgress(progress);
            } else if (progress == 100) {
                mDataBinding.progressBar.setVisibility(View.GONE);
            }
        }

        @Override public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }

        @Override public void onGeolocationPermissionsShowPrompt(final String origin,
                                                                 final GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
        @Override public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture,
                                                Message resultMsg) {
            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(view);
            resultMsg.sendToTarget();
            return true;
        }
    };
}
