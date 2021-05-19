/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.services;

import com.codename1.io.ConnectionRequest;
import com.mycompany.myapp.entities.Abonnement;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.netdev.mindspace.utils.Statics;
import java.util.ArrayList;
import com.codename1.io.JSONParser;
import java.util.Map;
import com.codename1.io.CharArrayReader;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author Firas
 */
public class ServiceAbonnement {
    
    
    public ArrayList<Abonnement> abonnements;
    public static ServiceAbonnement instance=null;
    private ConnectionRequest req;
    public boolean resultOK;
    private ServiceAbonnement() {
         req = new ConnectionRequest();
    }
    public static ServiceAbonnement getInstance() {
        if (instance == null) {
            instance = new ServiceAbonnement();
        }
        return instance;
    }
     
     
    public boolean addAbonnement(Abonnement t) {
        String url = Statics.BASE_URL + "/abonnement/addAbonnements?" +"titre="+t.getTitre_ab() + "&type=" + t.getType_ab()+ "&prix=" + t.getPrix_ab()+ "&descr_ab=" + t.getDesc_ab();
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean updateAbonnement(Abonnement t) {
        String url = Statics.BASE_URL + "/abonnement/updateAbonnements/"+t.getId_ab()+"?" +"titre="+t.getTitre_ab() + "&type=" + t.getType_ab()+ "&prix=" + t.getPrix_ab()+ "&descr_ab=" + t.getDesc_ab();
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean deleteAbonnement(Abonnement t) {
        String url = Statics.BASE_URL + "/abonnement/deleteAbonnements/"+t.getId_ab();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
        public ArrayList<Abonnement> parseAb(String jsonText){
        try {
            abonnements=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Abonnement t = new Abonnement();
                t.setId_ab((int) Double.parseDouble(obj.get("id").toString()));
                t.setTitre_ab(obj.get("titre").toString());
                t.setType_ab(obj.get("type").toString());
                t.setPrix_ab( Float.parseFloat(obj.get("prix").toString()));
                t.setDesc_ab(obj.get("descrAb").toString());
                abonnements.add(t);
            }
                     
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return abonnements;
    }
        
        
    public ArrayList<Abonnement> getAllabonnements(){
        String url = Statics.BASE_URL+"/abonnement/allAbonnements";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                abonnements = parseAb(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return abonnements;
    }
}
