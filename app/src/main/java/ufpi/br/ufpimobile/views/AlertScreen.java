package ufpi.br.ufpimobile.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class AlertScreen extends Activity {

    Context context;

    private String titulo;
    private String mensagem;
    private Intent acaoPositiva;
    private Intent acaoNegativa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            titulo = getIntent().getStringExtra("titulo");
            mensagem = getIntent().getStringExtra("mensagem");
            acaoPositiva = getIntent().getParcelableExtra("acaoPositiva");
            acaoNegativa = getIntent().getParcelableExtra("acaoNegativa");
            criarTelaDeAlerta();
        } else {
            finish();
        }

    }


    public void criarTelaDeAlerta() {

        AlertDialog.Builder alerta = new AlertDialog.Builder(context);

        alerta.setTitle(titulo)
                .setMessage(mensagem)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent returnIntent = new Intent();
                        if (acaoPositiva != null) {
                            context.startActivity(acaoPositiva);
                        }
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent returnIntent = new Intent();
                        if (acaoNegativa != null) {
                            context.startActivity(acaoNegativa);
                            dialog.cancel();
                        }
                        setResult(RESULT_CANCELED, returnIntent);
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

}
