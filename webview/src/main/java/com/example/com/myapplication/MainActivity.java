package com.example.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    Button moveBtn, backBtn;
    EditText editText;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveBtn = (Button)findViewById(R.id.moveBtn);
        backBtn = (Button)findViewById(R.id.backBtn);
        editText = (EditText)findViewById(R.id.urlText);
        webView = (WebView)findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        WebSettings webSet = webView.getSettings();
        webSet.setBuiltInZoomControls(true);

        moveBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                webView.loadUrl("http://" + editText.getText().toString());
            }
        });

        backBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                webView.goBack();
            }
        });


    }

    public class MyWebViewClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
             return super.shouldOverrideUrlLoading(view, url);
            //return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            editText.setText(url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
