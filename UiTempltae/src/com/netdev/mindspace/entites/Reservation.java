/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;






/**
 *
 * @author HAMZA
 */
public class Reservation {
    private int id_reservation;
    private String nom;
    private String prenom;
    private String date_act, nom_act;

    public Reservation(String nom, String prenom, String date_act, String nom_act, int nb_place) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_act = date_act;
        this.nom_act = nom_act;
        this.nb_place = nb_place;
    }

    public Reservation(int id_reservation, String nom, String prenom, String date_act, String nom_act, int nb_place) {
        this.id_reservation = id_reservation;
        this.nom = nom;
        this.prenom = prenom;
        this.date_act = date_act;
        this.nom_act = nom_act;
        this.nb_place = nb_place;
    }

    public String getNom_act() {
        return nom_act;
    }

    public void setNom_act(String nom_act) {
        this.nom_act = nom_act;
    }
    private int nb_place;

    public Reservation() {
    }

    public Reservation(int id_reservation, String nom, String prenom, String date_act, int nb_place) {
        this.id_reservation = id_reservation;
        this.nom = nom;
        this.prenom = prenom;
        this.date_act = date_act;
        this.nb_place = nb_place;
    }

    public Reservation(String nom, String prenom, String date_act, int nb_place) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_act = date_act;
        this.nb_place = nb_place;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDate_act() {
        return date_act;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_act(String date_act) {
        this.date_act = date_act;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_reservation=" + id_reservation + ", nom=" + nom + ", prenom=" + prenom + ", date_act=" + date_act + ", nb_place=" + nb_place + '}';
    }

    
    
    
    
    
    
    
}
