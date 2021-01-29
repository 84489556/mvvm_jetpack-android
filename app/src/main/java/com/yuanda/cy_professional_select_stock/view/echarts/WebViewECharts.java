package com.yuanda.cy_professional_select_stock.view.echarts;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * WebViewECharts
 * Created by sh on 2021-01-07.
 *
 * @date $
 */

public class WebViewECharts extends WebView {
    public WebViewECharts(Context context) {
        super(context);
        init();
    }

    public WebViewECharts(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WebViewECharts(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        WebSettings webSettings = getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setAllowFileAccess(false);
        webSettings.setAllowFileAccessFromFileURLs(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        webSettings.setDefaultTextEncodingName("gbk");
        webSettings.setSupportZoom(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);


        removeJavascriptInterface("searchBoxJavaBridge_");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        setWebViewClient(new EChartsWebViewClient());
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        loadUrl("file:///android_asset/web/WebChart/index.html");

//        loadUrl("file:///android_asset/demonew.html");
    }

    public void refreshLineChart(String optionString) {
        Log.e("WebViewECharts",optionString);
        String call = "javascript:loadEcharts('" + optionString + "')";
        loadUrl(call);
    }
}
