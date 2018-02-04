package ufpi.br.ufpimobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import ufpi.br.ufpimobile.controllers.TestConnection;

public class Tecnico extends AppCompatActivity {

    private WebView tec;
    private Toolbar toolbar;
    ProgressBar progressBar;
    private String url="https://sigaa.ufpi.br/sigaa/public/curso/lista.jsf?nivel=T&aba=p-tecnico";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico);

        toolbar = (Toolbar) findViewById(R.id.toolbar_tecnico);
        toolbar.setTitle("Ensino Técnico");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), Ensino.class);
                finish();
                startActivity(home);
            }
        });

        tec = (WebView) findViewById(R.id.activity_main_webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        if (new TestConnection(getApplicationContext()).isConnected()) {
            WebSettings webSettings = tec.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDisplayZoomControls(true);
            webSettings.setSupportZoom(true);
            tec.loadUrl(url);
            tec.setWebViewClient(new Tecnico.HelloWebViewClient());
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Sem acesso a Internet!!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private class HelloWebViewClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url)
        {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            progressBar.setVisibility(view.GONE);
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    { //if back key is pressed
        if((keyCode == KeyEvent.KEYCODE_BACK)&& tec.canGoBack())
        {
            tec.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }

    public void onBackPressed() {
        Tecnico.this.finish();
    }
}
