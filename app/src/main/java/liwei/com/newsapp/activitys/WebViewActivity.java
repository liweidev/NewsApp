package liwei.com.newsapp.activitys;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import liwei.com.newsapp.R;

public class WebViewActivity extends ParentActivity {


    @BindView(R.id.webView)
    WebView webView;

    @Override
    public int onLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initView() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
            View decorView = getWindow().getDecorView();
            if(Build.VERSION.SDK_INT >= 21) {
                int options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(options);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }
        String url=getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView .getSettings();
        webSettings.setJavaScriptEnabled(true);
        //WebView加载web资源
        webView.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void initData() {

    }


}
