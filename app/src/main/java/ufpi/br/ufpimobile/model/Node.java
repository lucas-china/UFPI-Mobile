package ufpi.br.ufpimobile.model;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa a estrutura de um no.
 * Created by HugoPiauilino on 07/05/15.
 */

public class Node {

    private Integer id;
    private String name;
    private String description;
    private Integer type;
    private String services;
    private LatLng localization;
    private String email;
    private String website;
    private String phone;
    private List<String> neighbors;

    public void setNeighbors(List<String> neighbors) {
        this.neighbors = neighbors;
    }

    public List<String> getNeighbors() {
        return neighbors;
    }



    /**
     * Construtor da classe Node com suas tipificações.
     *
     * @param id
     * @param name
     * @param description
     * @param type
     * @param services
     * @param localization
     * @param email
     * @param website
     * @param phone
     */
    public Node(Integer id, String name, String description, Integer type, String services, LatLng localization, String email, String website, String phone, List<String> a) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.services = services;
        this.localization = localization;
        this.email = email;
        this.website = website;
        this.phone = phone;
        this.neighbors = new ArrayList<String>();
        for(String s: a)
            this.neighbors.add(s);
    }

    /**
     * Construtor vazio da classe Node.
     */
    public Node() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public LatLng getLocalization() {
        return localization;
    }

    public void setLocalization(LatLng localization) {
        this.localization = localization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



}
