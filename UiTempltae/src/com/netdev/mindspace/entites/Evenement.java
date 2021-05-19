/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.entities;


//package com.netdev.mindspace.Publication;




/**
 *
 * @author mehdi
 */
public class Evenement  {
    private int idPub;
    private String datePub;
    private String dateEven;
     private String image;
    private String sujet;
    private int id_centre;

    public int getIdPub() {
        return idPub;
    }

    public void setIdPub(int idPub) {
        this.idPub = idPub;
    }

    public String getDatePub() {
        return datePub;
    }

    public void setDatePub(String datePub) {
        this.datePub = datePub;
    }

    public Evenement() {
    }

    public Evenement(int idPub, String datePub, String dateEven, String image, String sujet, int id_centre) {
        this.idPub = idPub;
        this.datePub = datePub;
        this.dateEven = dateEven;
        this.image = image;
        this.sujet = sujet;
        this.id_centre = id_centre;
    }

    public String getDateEven() {
        return dateEven;
    }

    public void setDateEven(String dateEven) {
        this.dateEven = dateEven;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idPub=" + idPub + ", datePub=" + datePub + ", dateEven=" + dateEven + ", image=" + image + ", sujet=" + sujet + ", id_centre=" + id_centre + '}';
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public int getId_centre() {
        return id_centre;
    }

    public void setId_centre(int id_centre) {
        this.id_centre = id_centre;
    }
    

    
    
    
    
    
   
  
    
    
}
