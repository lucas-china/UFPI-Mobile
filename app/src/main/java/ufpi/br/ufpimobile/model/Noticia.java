package ufpi.br.ufpimobile.model;

/**
 * Created by UFPI 236345 on 13/12/2017.
 */

public class Noticia {

    public String titulo;
    public String href;
    public String _data;
    public String hora;

    public Noticia(String titulo, String href, String _data, String hora){
        this.titulo = titulo;
        this.href = href;
        this._data = _data;
        this.hora = hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String get_data() {
        return _data;
    }

    public void set_data(String _data) {
        this._data = _data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
