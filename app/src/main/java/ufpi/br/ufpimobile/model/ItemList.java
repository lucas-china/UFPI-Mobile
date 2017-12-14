package ufpi.br.ufpimobile.model;

/**
 * Método que auxilia a criação da lista mostrada no drawer.
 * Created by HugoPiauilino on 12/05/15.
 */
public class ItemList {

    private String itemTitle;

    public String getItemTitle() {
        return itemTitle;
    }

    /**
     * Construtor da classe ItemList
     * @param title
     */
    public ItemList(String title){
        this.itemTitle = title;
    }
}