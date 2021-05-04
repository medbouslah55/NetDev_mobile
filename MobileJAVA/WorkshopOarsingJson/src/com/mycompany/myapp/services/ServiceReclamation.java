/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.entities.Reclamation;
import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class ServiceReclamation {
    public ArrayList<Reclamation> reclamation;
    public static ServiceReclamation instance=null;
    private ConnectionRequest req;
    public boolean resultOK;
    private ServiceReclamation() {
         req = new ConnectionRequest();
    }
    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
}
