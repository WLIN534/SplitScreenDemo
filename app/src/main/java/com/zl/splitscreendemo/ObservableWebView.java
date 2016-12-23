package com.zl.splitscreendemo;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * 作者：zhanglin on 2016/12/23 09:39
 * 邮箱：weiwei_534@163.com
 */

public class ObservableWebView extends WebView {
//    private OnScrollChangedCallback mOnScrollChangedCallback;
private ScrollViewListener scrollViewListener = null;
    public ObservableWebView(Context context) {
        super(context);
    }

    public ObservableWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
//    @Override
//    protected void onScrollChanged(final int l, final int t, final int oldl,
//                                   final int oldt) {
//        super.onScrollChanged(l, t, oldl, oldt);
//
//        if (mOnScrollChangedCallback != null) {
//            mOnScrollChangedCallback.onScroll(l - oldl, t - oldt);
//        }
//    }
//
//    public OnScrollChangedCallback getOnScrollChangedCallback() {
//        return mOnScrollChangedCallback;
//    }
//
//    public void setOnScrollChangedCallback(
//            final OnScrollChangedCallback onScrollChangedCallback) {
//        mOnScrollChangedCallback = onScrollChangedCallback;
//    }
//
//    /**
//     * Impliment in the activity/fragment/view that you want to listen to the webview
//     */
//    public static interface OnScrollChangedCallback {
//        public void onScroll(int dx, int dy);
//    }
}
