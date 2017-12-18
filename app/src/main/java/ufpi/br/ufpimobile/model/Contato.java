package ufpi.br.ufpimobile.model;

/**
 * Created by UFPI 236345 on 18/12/2017.
 */

public class Contato {

    private int id;
    private String nome;
    private String contato;
    private String email;

    //Constructor

    public Contato(int id, String nome, String contato, String email) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.email = email;
    }

    //Setter, getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
