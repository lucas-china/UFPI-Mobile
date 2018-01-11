package ufpi.br.ufpimobile.model;

/**
 * Created by UFPI 236345 on 13/12/2017.
 */

public class NoticiaEspecifica {

    public String text;
    public String href;
    public String img;
    public Boolean link;
    public Boolean eimg;
    public Boolean etext;

    public NoticiaEspecifica(String texto, String href, String img, Boolean link, Boolean eimg, Boolean etext) {
        this.text = texto;
        this.href = href;
        this.img = img;
        this.link = link;
        this.eimg = eimg;
        this.etext = etext;
    }

    public String getTexto() {
        return text;
    }

    public void setTexto(String texto) {
        this.text = texto;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getLink() {
        return link;
    }

    public void setLink(Boolean link) {
        this.link = link;
    }

    public Boolean getEimg() {
        return eimg;
    }

    public void setEimg(Boolean eimg) {
        this.eimg = eimg;
    }

    public Boolean getEtext() {
        return etext;
    }

    public void setEtext(Boolean etext) {
        this.etext = etext;
    }
}
