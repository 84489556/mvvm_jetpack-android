package com.yuanda.cy_professional_select_stock.view.echarts;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayInputStream;

public class EChartsWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            refreshLineChart(view);
        }



        /**
         * shouldInterceptRequest WebView
         * 中调用的每个请求都会经过该拦截器，如果一个页面有超链接，那么依然会经过该拦截器
         * 参数说明：
         *
         * @param view 接收WebViewClient的那个实例，前面看到webView.setWebViewClient(new
         *             MyAndroidWebViewClient())，即是这个webview。
         * @param url  raw url 制定的资源
         * @return 返回WebResourceResponse包含数据对象，或者返回null
         */
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view,
                                                          String url) {
            if (url.contains("http") || url.contains("www")
                    || url.contains("https")) {
                String response = "<html><body>该数据不存在</body></html>";
                WebResourceResponse weResourceResponse = new WebResourceResponse(
                        "text/html", "utf-8", new ByteArrayInputStream(
                        response.getBytes()));
                return weResourceResponse;
            } else {
                return super.shouldInterceptRequest(view, url);
            }
        }

        /*
         * url重定向会执行此方法以及点击页面某些链接也会执行此方法
         * 当加载的网页需要重定向的时候就会回调这个函数告知我们应用程序是否需要接管控制网页加载，如果应用程序接管，并且return
         * true意味着主程序接管网页加载，如果返回false让webview自己处理。
         *
         * @param view 当前webview
         *
         * @param url 即将重定向的url
         *
         * @return true:表示当前url已经加载完成，即使url还会重定向都不会再进行加载, false
         * 表示此url默认由系统处理，该重定向还是重定向，直到加载完成
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains("http") || url.contains("www")
                    || url.contains("https")) {
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
    }