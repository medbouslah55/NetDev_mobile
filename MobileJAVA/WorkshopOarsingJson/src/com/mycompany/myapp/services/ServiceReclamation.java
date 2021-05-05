/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Firas
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> reclamations;
    public static ServiceReclamation instance = null;
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

    public boolean addReclamation(Reclamation t) {
        String url = Statics.BASE_URL + "/reclamation/addReclamation?" + "nom=" + t.getNom_rec() + "&prenom=" + t.getPrenom_rec() + "&mail=" + t.getEmail_rec() + "type=" + t.getType_rec() + "&description=" + t.getDescription_rec();
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

    public boolean updateReclamation(Reclamation t) {
        String url = Statics.BASE_URL + "/reclamation/updateReclamation/" + t.getId_rec() + "?" + "nom=" + t.getNom_rec() + "&prenom=" + t.getPrenom_rec() + "&mail=" + t.getEmail_rec() + "type=" + t.getType_rec() + "&description=" + t.getDescription_rec();
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

    public boolean deleteReclamation(Reclamation t) {
        String url = Statics.BASE_URL + "/reclamation/deleteReclamation/" + t.getId_rec();
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

    public ArrayList<Reclamation> getAllReclamation() {
        String url = Statics.BASE_URL + "/reclamation/allReclamation";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseREC(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }

    public ArrayList<Reclamation> parseREC(String jsonText) {
        try {
            reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation t = new Reclamation();
                t.setId_rec((int) Double.parseDouble(obj.get("id").toString()));
                t.setNom_rec(obj.get("nom").toString());
                t.setPrenom_rec(obj.get("prenom").toString());
                t.setEmail_rec(obj.get("mail").toString());
                t.setType_rec(obj.get("type").toString());
                
                String sDate1 = obj.get("date").toString();
                String Date2 =sDate1.substring(0, 10);
                System.out.println(Date2);
                //2021-04-04T00:00:00+00:00//format date mta3i ena 
                t.setDate_rec(new SimpleDateFormat("yyyy-MM-dd").parse(Date2));
                t.setDescription_rec(obj.get("description").toString());
                t.setEtat_rec(obj.get("etat").toString());
                reclamations.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

       
    }   catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
         return reclamations;
    }
}



