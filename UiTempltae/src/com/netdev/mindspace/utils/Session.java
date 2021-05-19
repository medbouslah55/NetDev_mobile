/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.utils;

import com.netdev.mindspace.entites.Membre;

/**
 *
 * @author ASUS
 */
public final class Session {

    private static Session instance;
    private Membre SessionUser;

    private Session() {
    }

    private Session(Membre SessionUser) {
        this.SessionUser = SessionUser;
    }

    public static Session StartSession() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public static Session StartSession(Membre SessionUser) {
        if (instance == null) {
            instance = new Session(SessionUser);
        }
        return instance;
    }

    public static Session getSession() {
        return instance;
    }

    public void clearSession() {
        SessionUser = null;
    }

    public void setSessionUser(Membre SessionUser) {
        this.SessionUser = SessionUser;
    }

    public Membre getSessionUser() {
        return this.SessionUser;
    }

    public void SetSessionUser(Membre SessionUser) {
        this.SessionUser = SessionUser;
    }

}
