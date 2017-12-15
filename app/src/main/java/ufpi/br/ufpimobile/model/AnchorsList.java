package ufpi.br.ufpimobile.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que cria a lista de âncoras
 * Created by HugoPiauilino on 14/05/15.
 */

public class AnchorsList {

    /**
     * Array estático de âncoras.
     */
    public static List<Anchor> ITEMS = new ArrayList<Anchor>();

    /**
     * Hash Map estático de Ancoras, por ID.
     */
    public static Map<String, Anchor> ITEM_MAP = new HashMap<String, Anchor>();

    /**
     * Criação estática da lista de âncoras
     */
    static {
        addItem(new Anchor("Órgãos Centrais", "Órgãos Centrais"));
        addItem(new Anchor("Centros", "Centros"));
        addItem(new Anchor("Departamentos", "Departamentos"));
        addItem(new Anchor("Cursos", "Cursos"));
        addItem(new Anchor("Pos-Graduacao", "Pos-Graduacao"));
        addItem(new Anchor("Salas de Aula", "Salas de Aula"));
        addItem(new Anchor("Laboratorio", "Laboratorio"));
        addItem(new Anchor("Auditorios", "Auditorios"));
        addItem(new Anchor("Banheiros", "Banheiros"));
        addItem(new Anchor("Bibliotecas", "Bibliotecas"));
        addItem(new Anchor("Lanchonetes", "Lanchonetes"));
        addItem(new Anchor("Estacoes de Onibus", "Estacoes de Onibus"));
    }

    /**
     * Método estático para adicionar um item na lista de âncoras
     *
     * @param item Âncora
     */
    private static void addItem(Anchor item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }
}
