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
import com.netdev.mindspace.entites.Regime;
import com.netdev.mindspace.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trabe
 */
public class RegimeServices {
    
    private ConnectionRequest req;
    public ArrayList<Regime> regimes;
    public static RegimeServices instance = null;
    
    private RegimeServices(){
          req = new ConnectionRequest();
     }
     
    public static RegimeServices getInstance() {
        if (instance == null) {
            instance = new RegimeServices();
        }
        return instance;
    }
    
    public ArrayList<Regime> parseRegime(String jsonText){
        try {
            regimes = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> regimesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)regimesListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                Regime t = new Regime();
                String id = obj.get("idRegime").toString();
                t.setIdRegime(id);
                t.setType(obj.get("type").toString());
                t.setDescription((obj.get("description").toString()));
                t.setImage((obj.get("image").toString()));
                regimes.add(t);
            }
            
        } catch (IOException ex) {
            
        }

        return regimes;
        
    }
    
    public ArrayList<Regime> getAllRegimes(){        
        String url = Statics.BASE_URL+"/api/regimes";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                regimes = parseRegime(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return regimes;
    }
    
}
