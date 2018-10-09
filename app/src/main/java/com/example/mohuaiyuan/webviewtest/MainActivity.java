package com.example.mohuaiyuan.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText weburl;
    private WebView webshow;
    private Button searchurl;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weburl = (EditText) findViewById(R.id.weburl);
        webshow = (WebView) findViewById(R.id.webshow);
        webshow.getSettings().setJavaScriptEnabled(true);
        webshow.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webshow.getSettings().setSupportMultipleWindows(true);
        webshow.getSettings().setBuiltInZoomControls(true);
        webshow.setWebViewClient(new WebViewClient());
        webshow.setWebChromeClient(new WebChromeClient());
        webshow.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                Log.d("browser","motionEvent:"+motionEvent.toString());
                return false;
            }
        });
        webshow.loadUrl("http://cn.bing.com");

        searchurl = (Button) findViewById(R.id.searchurl);
        searchurl.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String url = weburl.getText().toString();
                Log.d("browser", "url:" + url);
                webshow.getSettings().setJavaScriptEnabled(true);
                webshow.loadUrl(url);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_ENTER)
        {
            String url = weburl.getText().toString();
            Log.d("browser", "url:" + url);
            webshow.getSettings().setJavaScriptEnabled(true);
            webshow.loadUrl(url);
        }
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webshow.canGoBack())
        {
            webshow.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
