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
public class Menu {
    
    private String idMenu;
    private String description;
    private int numJour;
    private String matin;
    private String matinImg;
    private String dejeuner;
    private String dejeunerImg;
    private String dinner;
    private String dinnerImg;
    private int totalCalories;
    private String idRegime;

    public Menu() {
    }

    public Menu(String idMenu, String description, int numJour, String matin, String matinImg, String dejeuner, String dejeunerImg, String dinner, String dinnerImg, int totalCalories, String idRegime) {
        this.idMenu = idMenu;
        this.description = description;
        this.numJour = numJour;
        this.matin = matin;
        this.matinImg = matinImg;
        this.dejeuner = dejeuner;
        this.dejeunerImg = dejeunerImg;
        this.dinner = dinner;
        this.dinnerImg = dinnerImg;
        this.totalCalories = totalCalories;
        this.idRegime = idRegime;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public String getDescription() {
        return description;
    }

    public int getNumJour() {
        return numJour;
    }

    public String getMatin() {
        return matin;
    }

    public String getMatinImg() {
        return matinImg;
    }

    public String getDejeuner() {
        return dejeuner;
    }

    public String getDejeunerImg() {
        return dejeunerImg;
    }

    public String getDinner() {
        return dinner;
    }

    public String getDinnerImg() {
        return dinnerImg;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public String getIdRegime() {
        return idRegime;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumJour(int numJour) {
        this.numJour = numJour;
    }

    public void setMatin(String matin) {
        this.matin = matin;
    }

    public void setMatinImg(String matinImg) {
        this.matinImg = matinImg;
    }

    public void setDejeuner(String dejeuner) {
        this.dejeuner = dejeuner;
    }

    public void setDejeunerImg(String dejeunerImg) {
        this.dejeunerImg = dejeunerImg;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public void setDinnerImg(String dinnerImg) {
        this.dinnerImg = dinnerImg;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void setIdRegime(String idRegime) {
        this.idRegime = idRegime;
    }

    @Override
    public String toString() {
        return "Menu{" + "idMenu=" + idMenu + ", description=" + description + ", numJour=" + numJour + ", matin=" + matin + ", matinImg=" + matinImg + ", dejeuner=" + dejeuner + ", dejeunerImg=" + dejeunerImg + ", dinner=" + dinner + ", dinnerImg=" + dinnerImg + ", totalCalories=" + totalCalories + ", idRegime=" + idRegime + '}';
    }    
    
}
