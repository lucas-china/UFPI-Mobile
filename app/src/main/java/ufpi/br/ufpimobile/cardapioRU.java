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

public class cardapioRU extends AppCompatActivity {

    private WebView noticia;
    private Toolbar toolbar;
    ProgressBar progressBar;
    private String url="";
    String tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_noticia);

        Intent intent = getIntent();
        String ru = intent.getStringExtra("ru");

        switch (ru){
            case "the": url = "http://mobile.ufpi.br/pdfs/cardapio_teresina.pdf";
                tb = "Cardápio Teresina";
                break;
            case "picos": url = "http://mobile.ufpi.br/pdfs/cardapio_picos.pdf";
                tb = "Cardápio Picos";
                break;
            case "bj": url = "http://mobile.ufpi.br/pdfs/cardapio_bomjesus.pdf";
                tb = "Cardápio Bom Jesus";
                break;
            case "phb": url = "http://mobile.ufpi.br/pdfs/cardapio_parnaiba.pdf";
                tb = "Cardápio Parnaíba";
                break;
            case "flo": url = "http://mobile.ufpi.br/pdfs/cardapio_floriano.pdf";
                tb = "Cardápio Floriano";
                break;
            default: url = "http://mobile.ufpi.br/pdfs/cardapio_teresina.pdf";
                tb = "Cardápio Teresina";

        }



        toolbar = (Toolbar) findViewById(R.id.toolbar_NovaNot);
        toolbar.setTitle(tb);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), CardapioSemana.class);
                finish();
                startActivity(home);
            }
        });


        noticia = (WebView) findViewById(R.id.activity_main_webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        WebSettings webSettings = noticia.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webSettings.setDisplayZoomControls(true);
        //webSettings.setSupportZoom(true);
        noticia.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        noticia.setWebViewClient(new cardapioRU.HelloWebViewClient());
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
        if((keyCode == KeyEvent.KEYCODE_BACK)&& noticia.canGoBack())
        {
            noticia.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }

    public void onBackPressed() {
        cardapioRU.this.finish();
    }
}
