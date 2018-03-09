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

public class SIC extends AppCompatActivity {

    private WebView sic;
    private Toolbar toolbar;
    ProgressBar progressBar;
    private String url="https://esic.cgu.gov.br/sistema/site/index.aspx?ReturnUrl=%2fsistema%2f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sic);

        toolbar = (Toolbar) findViewById(R.id.toolbar_SIC);
        toolbar.setTitle("Serviço de Informação ao Cidadão");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), Informacao.class);
                finish();
                startActivity(home);
            }
        });

        sic = (WebView) findViewById(R.id.activity_main_webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        if (new TestConnection(getApplicationContext()).isConnected()) {

            WebSettings webSettings = sic.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDisplayZoomControls(true);
            webSettings.setSupportZoom(true);
            sic.loadUrl(url);
            sic.setWebViewClient(new SIC.HelloWebViewClient());
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Verifique sua conexão com a internet!", Toast.LENGTH_LONG);
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
        if((keyCode == KeyEvent.KEYCODE_BACK)&& sic.canGoBack())
        {
            sic.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }

    public void onBackPressed() {
        SIC.this.finish();
    }
}
