/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.entites;





/**
 *
 * @author mohamedbouslah
 */
public class Coach extends User {
    int cin;
    public Coach() {
    }
    
    public Coach(int cin) {
        super(cin);
    }

    public Coach(int cin, String nom, String prenom, String sexe) {
        super(cin, nom, prenom, sexe);
    }

    @Override
    public int getCin() {
        return super.getCin(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDatee() {
        return super.getDatee(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNom() {
        return super.getNom(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPrenom() {
        return super.getPrenom(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSexe() {
        return super.getSexe(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCin(int cin) {
        super.setCin(cin); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDatee(String datee) {
        super.setDatee(datee); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNom(String nom) {
        super.setNom(nom); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrenom(String prenom) {
        super.setPrenom(prenom); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSexe(String sexe) {
        super.setSexe(sexe); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
