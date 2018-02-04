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

public class DadosAbertos extends AppCompatActivity {

    private WebView dadosAbertos;
    private Toolbar toolbar;
    ProgressBar progressBar;
    private String url="http://ufpi.br/dados-abertos-ufpi-2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_abertos);

        toolbar = (Toolbar) findViewById(R.id.toolbar_DadosAberto);
        toolbar.setTitle("Dados Abertos");
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

        dadosAbertos = (WebView) findViewById(R.id.activity_main_webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        if (new TestConnection(getApplicationContext()).isConnected()) {

            WebSettings webSettings = dadosAbertos.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDisplayZoomControls(true);
            webSettings.setSupportZoom(true);
            dadosAbertos.loadUrl(url);
            dadosAbertos.setWebViewClient(new DadosAbertos.HelloWebViewClient());
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
        if((keyCode == KeyEvent.KEYCODE_BACK)&& dadosAbertos.canGoBack())
        {
            dadosAbertos.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }

    public void onBackPressed() {
        DadosAbertos.this.finish();
    }
}
