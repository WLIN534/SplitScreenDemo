package com.zl.splitscreendemo;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements ScrollViewListener{
    LinearLayout mainLayout;
    TextView textView1, textView2;
    ObservableScrollView scrollView1, scrollView2;
    ObservableWebView webView1,webView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

        setData();
        clickListener();

        if (screenState()) {
            mainLayout.setOrientation(LinearLayout.VERTICAL);
        } else {
            mainLayout.setOrientation(LinearLayout.HORIZONTAL);
        }


    }

    private void setwebView(WebView webView,String html) {
        // 添加js交互接口类，
//        webView.addJavascriptInterface(new JavascriptInterface(bf), Js2JavaInterfaceName);
        webView.setWebViewClient(new WebViewClient());
        // 启用javascript
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        webView.getSettings().setLoadsImagesAutomatically(true); //图片自动加载
        webView.getSettings().setUseWideViewPort(false);// 图片缩放大小
        webView.getSettings().setAllowFileAccess(true); //获取文件


        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", "");
    }


    /**
     * scrollView滑动监听
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {

        scrollView1.scrollTo(x, y);
        scrollView2.scrollTo(x, y);
    }

    /**
     * webview滑动监听
     * @param webView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(ObservableWebView webView, int x, int y, int oldx, int oldy) {
        webView1.scrollTo(x, y);
        webView2.scrollTo(x, y);
    }



    private void setData() {
        try {
            String string =convertStreamToString(getAssets().open("11.html")) ;
            setwebView(webView1,string);
            setwebView(webView2,string);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clickListener() {
        scrollView1.setScrollViewListener(this);
        scrollView2.setScrollViewListener(this);

        webView1.setScrollViewListener(this);
        webView2.setScrollViewListener(this);
    }

    private void findView() {
        mainLayout = (LinearLayout) findViewById(R.id.activity_main);
        textView1 = (TextView) findViewById(R.id.textview1);
        textView2 = (TextView) findViewById(R.id.textview2);
        scrollView1 = (ObservableScrollView) findViewById(R.id.scrollView1);
        scrollView2 = (ObservableScrollView) findViewById(R.id.scrollView2);
        webView1 = (ObservableWebView) findViewById(R.id.webview1);
        webView2 = (ObservableWebView) findViewById(R.id.webview2);
    }

    /**
     * 判断横竖屏
     *
     * @return true 竖屏 false 横屏
     */
    private boolean screenState() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // 竖屏doSomrthing
            return true;
        } else {
            // 横屏时dosomething
            return false;
        }
    }
    public static String convertStreamToString(InputStream is) {
        /*
          * To convert the InputStream to String we use the BufferedReader.readLine()
          * method. We iterate until the BufferedReader return null which means
          * there's no more data to read. Each line will appended to a StringBuilder
          * and returned as String.
          */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }


}
