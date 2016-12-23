package com.zl.splitscreendemo;

/**
 * 监听ScrollView的滑动情况，比如滑动了多少距离，是否滑到布局的顶部或者底部
 * 作者：zhanglin on 2016/12/23 09:18
 * 邮箱：weiwei_534@163.com
 */

public interface ScrollViewListener {
    void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);
    void onScrollChanged(ObservableWebView webView, int x, int y, int oldx, int oldy);
}
