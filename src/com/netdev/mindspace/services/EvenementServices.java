/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.netdev.mindspace.entities.Evenement;
import com.netdev.mindspace.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trabe
 */
public class EvenementServices {

    private ConnectionRequest req;
    public ArrayList<Evenement> evenements;
    public static EvenementServices instance = null;

    private EvenementServices(){
        req = new ConnectionRequest();
     }
     
    public static EvenementServices getInstance() {
        if (instance == null) {
            instance = new EvenementServices();
        }
        return instance;
    }

     public ArrayList<Evenement> parseEvenement(String jsonText) {
        try {
            evenements = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> regimesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) regimesListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                Evenement t = new Evenement();

                t.setIdPub((int) Float.parseFloat(obj.get("idPub").toString()));

                // t.setId_pub(id);
                t.setDateEven((obj.get("dateEven").toString()));
                t.setDatePub((obj.get("datePub").toString()));
                t.setSujet(obj.get("sujet").toString());
                t.setImage((obj.get("image").toString()));
               
             
                
                evenements.add(t);
            }

        } catch (IOException ex) {

        }
        return evenements;
    }

    public ArrayList<Evenement> getAllEvenements() {
        String url = Statics.BASE_URL + "/mobile/evenements";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                evenements = parseEvenement(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return evenements;
    }

}
