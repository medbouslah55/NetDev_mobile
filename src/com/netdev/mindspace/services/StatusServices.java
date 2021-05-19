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
import com.netdev.mindspace.entities.Status;
import com.netdev.mindspace.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trabe
 */
public class StatusServices {

    private ConnectionRequest req;
    public ArrayList<Status> status;
    public static StatusServices instance = null;

    private StatusServices(){
        req = new ConnectionRequest();
     }
     
    public static StatusServices getInstance() {
        if (instance == null) {
            instance = new StatusServices();
        }
        return instance;
    }

     public ArrayList<Status> parseStatus(String jsonText) {
        try {
            status = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> statusListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) statusListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                Status t = new Status();

               // t.setDate_pub(int) Float.parseFloat(obj.get("datePub").toString()));

                // t.setId_pub(id);
                t.setDate_pub((obj.get("datePub").toString()));
                
                t.setText(obj.get("text").toString());
               
                
                status.add(t);
            }

        } catch (IOException ex) {

        }
        return status;
    }

    public ArrayList<Status> getAllStatus() {
        String url = Statics.BASE_URL + "/mobile/status";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                status = parseStatus(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return status;
    }

}
