/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.entites;

/**
 *
 * @author trabe
 */
public class Regime {
    
    private String idRegime;
    private String type;
    private String description;
    private String image;

    public Regime() {
    }

    public Regime(String idRegime, String type, String description, String image) {
        this.idRegime = idRegime;
        this.type = type;
        this.description = description;
        this.image = image;
    }

    public String getIdRegime() {
        return idRegime;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setIdRegime(String idRegime) {
        this.idRegime = idRegime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Regime{" + "idRegime=" + idRegime + ", type=" + type + ", description=" + description + ", image=" + image + '}';
    }
    
    
}
