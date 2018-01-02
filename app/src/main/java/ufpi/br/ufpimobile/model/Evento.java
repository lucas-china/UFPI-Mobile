package ufpi.br.ufpimobile.model;

/**
 * Created by lucas_brito on 30/12/17.
 */

public class Evento {

    private String titulo;
    private String local;
    private String data;

    public Evento(String titulo, String local, String data){
        this.titulo = titulo;
        this.local = local;
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
