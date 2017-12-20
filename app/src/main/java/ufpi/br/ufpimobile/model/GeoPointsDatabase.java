package ufpi.br.ufpimobile.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atendimento on 18/12/17.
 */

public class GeoPointsDatabase extends SQLiteOpenHelper {

    private static final String TABLE_NODE = "node";
    private static final String COLUMN_NODE_ID = "id";
    private static final String COLUMN_NODE_NAME = "name";
    private static final String COLUMN_NODE_DESCRIPTION = "descricao";
    private static final String COLUMN_NODE_TYPE = "tipo";
    private static final String COLUMN_NODE_SERVICES = "servicos";
    private static final String COLUMN_NODE_LATITUDE = "latitude";
    private static final String COLUMN_NODE_LONGITUDE = "longitude";
    private static final String COLUMN_NODE_EMAIL = "email";
    private static final String COLUMN_NODE_WEBSITE = "site";
    private static final String COLUMN_NODE_PHONE = "telefone";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GeoPointsDatabase.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " REAL";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String COMMA_SEP = ",";
    private static final String COLUMN_NODE_NEIGHBORS = "neighbors";

    /**
     * String para criar da tabela de nos
     */
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NODE + " (" +
                    COLUMN_NODE_ID + INTEGER_TYPE + PRIMARY_KEY + COMMA_SEP +
                    COLUMN_NODE_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_TYPE + INTEGER_TYPE + COMMA_SEP +
                    COLUMN_NODE_SERVICES + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_LATITUDE + DOUBLE_TYPE + COMMA_SEP +
                    COLUMN_NODE_LONGITUDE + DOUBLE_TYPE + COMMA_SEP +
                    COLUMN_NODE_EMAIL + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_WEBSITE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_PHONE + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NODE_NEIGHBORS + TEXT_TYPE +
                    " )";

    /**
     * String para deletar a tabela de nos
     */
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NODE;

    /**
     * Construtor da Classe GeoPointsDatabase
     *
     * @param context
     */
    public GeoPointsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Método que cria o banco de dados.
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * Método que atualiza o banco de dados para uma versão superior.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * Método que atualiza o banco de dados para uma versão inferior.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * Método que adiciona um nó ao Banco de Dados.
     *
     * @param no Tipo Nó
     */
    public void addNode(Node no) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NODE_ID, no.getId());
        values.put(COLUMN_NODE_NAME, no.getName());
        values.put(COLUMN_NODE_DESCRIPTION, no.getDescription());
        values.put(COLUMN_NODE_TYPE, no.getType());
        values.put(COLUMN_NODE_SERVICES, no.getServices());
        values.put(COLUMN_NODE_LATITUDE, no.getLocalization().latitude);
        values.put(COLUMN_NODE_LONGITUDE, no.getLocalization().longitude);
        values.put(COLUMN_NODE_EMAIL, no.getEmail());
        values.put(COLUMN_NODE_WEBSITE, no.getWebsite());
        values.put(COLUMN_NODE_PHONE, no.getPhone());
        Gson gson = new Gson();
        String inputString = gson.toJson(no.getNeighbors());
        values.put(COLUMN_NODE_NEIGHBORS, inputString);


        db.insert(TABLE_NODE, null, values);
        db.close();
    }

    /**
     * Método que retorna uma lista com o nome de todos os nós existentes no banco de dados.
     *
     * @return Lista de Nomes dos Nós existentes no Banco de Dados
     */
    public ArrayList<String> getNodesNames() {

        ArrayList<String> nodesDescriptionsList = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_NODE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                nodesDescriptionsList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return nodesDescriptionsList;
    }

    /**
     * Método que retorna uma lista com o nome de todos os nós existentes no banco de dados.
     *
     * @return Lista de Nomes dos Nós existentes no Banco de Dados
     */
    public ArrayList<Node> getAllNodes() {

        ArrayList<Node> nodes = new ArrayList<Node>();
        String selectQuery = "SELECT * FROM " + TABLE_NODE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            Node node = new Node();

            node.setId(cursor.getInt(0));
            node.setName(cursor.getString(1));
            node.setDescription(cursor.getString(2));
            node.setType(cursor.getInt(3));
            node.setServices(cursor.getString(4));
            node.setLocalization(new LatLng(cursor.getDouble(5), cursor.getDouble(6)));
            node.setEmail(cursor.getString(7));
            node.setWebsite(cursor.getString(8));
            node.setPhone(cursor.getString(9));
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            Gson gson = new Gson();
            ArrayList<String> finalOutputString = gson.fromJson(cursor.getString(10), type);
            node.setNeighbors(finalOutputString);

            nodes.add(node);
        }
        return nodes;

    }

    /**
     * Método que verifica se determinado nó existe no banco de dados.
     *
     * @param name Nome do nó escolhido
     * @return True - Nó existe | False - Nó não existe
     */
    public boolean hasNode(String name) {
        String selectQuery = "SELECT * FROM " + TABLE_NODE + " WHERE " + COLUMN_NODE_NAME + " = '" + name + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor.moveToFirst();
    }

    /**
     * Método que retorna um nó com um determinado nome.
     *
     * @param name Nome do Nó
     * @return Nó com o nome escolhido
     */
    public Node selectByName(String name) {
        String selectQuery = "SELECT * FROM " + TABLE_NODE + " WHERE " + COLUMN_NODE_NAME + " = '" + name + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Node node = new Node();
            node.setId(Integer.parseInt(cursor.getString(0)));
            node.setName(cursor.getString(1));
            LatLng localization = new LatLng(cursor.getDouble(5), cursor.getDouble(6));
            node.setLocalization(localization);
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            Gson gson = new Gson();
            ArrayList<String> finalOutputString = gson.fromJson(cursor.getString(10), type);
            node.setNeighbors(finalOutputString);
            return node;

        }
        return null;

    }

    /**
     * Metodo que retorna uma lista de nós de determinado tipo.
     *
     * @param type Tipo do Nó
     * @return Lista de Nós do tipo escolhido
     */
    public List<Node> selectByType(String type) {

        List<Node> nodes = new ArrayList<Node>();

        String selectQuery = "SELECT * FROM " + TABLE_NODE + " WHERE " + COLUMN_NODE_TYPE + " = '" + type + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            Node node = new Node();

            node.setId(cursor.getInt(0));
            node.setName(cursor.getString(1));
            node.setDescription(cursor.getString(2));
            node.setType(cursor.getInt(3));
            node.setServices(cursor.getString(4));
            node.setLocalization(new LatLng(cursor.getDouble(5), cursor.getDouble(6)));
            node.setEmail(cursor.getString(7));
            node.setWebsite(cursor.getString(8));
            node.setPhone(cursor.getString(9));

            nodes.add(node);
        }
        return nodes;

    }

    /**
     * Método que popula o banco de dados interno da aplicação utilizando o Firebase
     *
     * @param nodes Contém a ultima versão do banco de dados do web service
     */
    public void populateDatabase(List<Node> nodes) {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, DATABASE_VERSION, DATABASE_VERSION + 1);

        if (nodes != null) {
            for (Node n : nodes) {
                addNode(n);
            }
        }
    }
}