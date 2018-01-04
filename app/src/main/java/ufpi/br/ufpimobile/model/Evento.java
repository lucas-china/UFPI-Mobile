package ufpi.br.ufpimobile.model;

/**
 * Created by lucas_brito on 30/12/17.
 */

public class Evento {


    /**
     * title : Feira de Ciência da Química
     * location : Bloco de Química - UFPI
     * startDate : 2018-03-03T12:30:00.000Z
     * createdAt : 2018-01-04T18:49:56.636Z
     * data : 03/03/2018
     * hora : 09:30
     */

    private String title;
    private String location;
    private String startDate;
    private String createdAt;
    private String data;
    private String hora;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
