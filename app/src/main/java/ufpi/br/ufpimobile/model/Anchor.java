package ufpi.br.ufpimobile.model;

/**
 * Classe que representa uma âncora
 * Created by HugoPiauilino on 14/05/15.
 */

public class Anchor {

    private String id;
    private String content;

    /**
     * Construtor da classe Anchor
     * @param id Posição da âncora
     * @param content
     */
    public Anchor(String id, String content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
