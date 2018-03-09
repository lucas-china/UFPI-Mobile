package ufpi.br.ufpimobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

public class Onibus extends AppCompatActivity implements
        OnPageChangeListener,OnLoadCompleteListener {

    private static final String TAG = Onibus.class.getSimpleName();
    private String SAMPLE_FILE = "";
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    private String toolbarName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onibus);

        Intent intent = getIntent();
        String bus = intent.getStringExtra("bus");

        switch (bus){
            case "ot": SAMPLE_FILE = "onibusUFPI_TERESINA.pdf";
                toolbarName = "Ônibus Teresina";
                break;
            case "op": SAMPLE_FILE = "onibusCSHNB_PICOS.pdf";
                toolbarName = "Ônibus Picos";
                break;
            case "obj": SAMPLE_FILE = "onibusBJ.pdf";
                toolbarName = "Ônibus Bom Jesus";
                break;
            case "ctf": SAMPLE_FILE = "onibusCTF_FLORIANO.pdf";
                toolbarName = "Ônibus CT Floriano";
                break;
            case "ctbj": SAMPLE_FILE = "onibusCTBJ_BOMJESUS.pdf";
                toolbarName = "Ônibus CT Bom Jesus";
                break;
            default: SAMPLE_FILE = "onibusUFPI_TERESINA.pdf";
                toolbarName = "Ônibus Teresina";


        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Alterar o texto da toolbar para a data selecionada
        toolbar = (Toolbar) findViewById(R.id.toolbar_Onibus);
        toolbar.setTitle(toolbarName);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(getApplicationContext(), TelaHome2.class);
                finish();
                startActivity(home);
            }
        });

        pdfView= (PDFView)findViewById(R.id.pdfView);
        displayFromAsset(SAMPLE_FILE);

    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }


    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

}
