/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.services;

import com.mycompany.myapp.entities.Reservation;
import com.codename1.io.ConnectionRequest;

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
public class ServiceReservation {

    public ArrayList<Reservation> reservationss;
    public static ServiceReservation instance = null;
    private ConnectionRequest req;
    public boolean resultOK;

    private ServiceReservation() {
        req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }

    public boolean addReservation(Reservation r) {
        String url = Statics.BASE_URL + "/api/v1/reservationnew/" + r.getNom() + "/" + r.getPrenom() + "/" + r.getDate_act() + "/" + r.getNb_place() + "/" + r.getNom_act();
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

    public boolean updateAbonnement(Reservation r) {
        String url = Statics.BASE_URL + "/api/v1/editreservation/" +r.getId_reservation()+"/" + r.getNom() + "/" + r.getPrenom() + "/" + r.getDate_act() + "/" + r.getNb_place()+"/" + r.getNom_act();
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

    public boolean deleteReservation(Reservation t) {
        String url = Statics.BASE_URL + "/api/v1/reservationdelete/" + t.getId_reservation();
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

    public ArrayList<Reservation> parseReservation(String jsonText) {
        try {
            reservationss = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reservation r = new Reservation();
                r.setId_reservation((int) Double.parseDouble(obj.get("idReservation").toString()));
                r.setNom(obj.get("nom").toString());
                r.setPrenom(obj.get("prenom").toString());
                r.setDate_act((obj.get("dateAct").toString()));
                r.setNb_place((int) Double.parseDouble(obj.get("nbPlace").toString()));

                reservationss.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reservationss;
    }

    public ArrayList<Reservation> getAllReservations() {
        String url = Statics.BASE_URL + "/api/v1/reservationall";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservationss = parseReservation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservationss;
    }
}
