/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.netdev.mindspace.entites.Membre;
import com.netdev.mindspace.utils.Statics;
import java.util.ArrayList;


/**
 *
 * @author mohamedbouslah
 */
public class MembreService {
    public ArrayList<Membre> membre;
    
    public static MembreService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private MembreService() {
         req = new ConnectionRequest();
    }

    public static MembreService getInstance() {
        if (instance == null) {
            instance = new MembreService();
        }
        return instance;
    }
    
    public void signup(TextField cin,TextField nom ,TextField prenom ,TextField sexe ,TextField taille ,TextField poids,TextField email,TextField password , TextField telephone ,Resources res)
    {
        String url = Statics.BASE_URL + "/membre/signup?" + "cin=" + cin.getText().toString() + "&nom=" + nom.getText().toString()
                + "&prenom=" + prenom.getText().toString() + "&sexe=" + sexe.getText().toString() +  "&taille=" + taille.getText().toString()
                + "&poids=" + poids.getText().toString() + "&email=" + email.getText().toString() + "&password=" + password.getText().toString() 
                + "&telephone=" + telephone.getText().toString(); //création de l'URL
        req.setUrl(url);
        if(cin.getText().equals("") && nom.getText().equals("") && prenom.getText().equals("") && sexe.getText().equals("") && taille.getText().equals("")
                && poids.getText().equals("") && email.getText().equals("") && password.getText().equals("") && telephone.getText().equals(""))
        {
            Dialog.show("Erreur", "Veillez remplire tous les champs", "ok",null);
        }
        
        req.addResponseListener((e)-> {
            byte[]data = (byte[])e.getMetaData();
            String responceData =new String(data);
            System.out.println("data =====>"+responceData);
                      
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
    }
    
}
