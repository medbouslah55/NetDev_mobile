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
import com.netdev.mindspace.entites.Activite;
import com.netdev.mindspace.entites.Centre;
import com.netdev.mindspace.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ServiceActivite {
    
      public ArrayList<Centre> tasks;
      public Activite c ;
     public ArrayList<Activite> act;
     
    public static ServiceActivite instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceActivite() {
         req = new ConnectionRequest();
    }

    public static ServiceActivite getInstance() {
        if (instance == null) {
            instance = new ServiceActivite();
        }
        return instance;
    }
    
    
    
    
      public ArrayList<Activite> parseActivite(String jsonText){
         Centre t = new Centre() ;
         ArrayList<Activite> act = new ArrayList<Activite>() ;
          try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
         
            Map<String,Object> ctr = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          
           double id = (double) ctr.get("idCentre") ;
           t.setId_centre((int) id);
           t.setAdr_centre((String) ctr.get("adrCentre"));
           t.setMail_centre((String) ctr.get("mailCentre"));
           t.setNom_centre((String) ctr.get("nomCentre"));
           t.setTel_centre((String) ctr.get("telCentre"));
           t.setType_centre((String) ctr.get("typeCentre"));
        
           act = (ArrayList<Activite>) ctr.get("idacts") ;
           System.out.println("heeeeeeeeeeeeeeeelllo");
            System.out.println(t);
           /* Centre list = (Centre)tasksListJson.get("root");
              System.out.println("heeeeeeeeeeeeeeeelllo");
            System.out.println(list);
            //Parcourir la liste des tâches Json
           /* for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Centre t = new Centre();
                float id = Float.parseFloat(obj.get("idCentre").toString());
                //int idd = Integer.parseInt(obj.get("id").toString());
                t.setId_centre((int)id);
                t.setNom_centre(obj.get("nomCentre").toString());
              /*  t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setName(obj.get("name").toString());*/
                //Ajouter la tâche extraite de la réponse Json à la liste
            //  tasks.add(t);
           // }
             
            
        } catch (IOException ex) {
            
        }
       
        return act;
    }
      
       public ArrayList<Activite> getActByCentre(int id ){
        String url = Statics.BASE_URL+"/mobile/centre/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               act = parseActivite(new String(req.getResponseData()));
                System.out.println("salem");
             System.out.println(act);
                req.removeResponseListener(this);
            }
        });
            
        NetworkManager.getInstance().addToQueueAndWait(req);
        return act;
    }
       
       
       
          
      public Activite parseActiviteByid(String jsonText){
         Activite t = new Activite() ;
       //  ArrayList<Activite> act = new ArrayList<Activite>() ;
          try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
         
            Map<String,Object> ctr = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
          
           double id = (double) ctr.get("idAct") ;
           t.setId_act((int) id);
           t.setCategorie_act((String) ctr.get("categorieAct"));
           t.setCapacite((int) Float.parseFloat(ctr.get("capacite").toString()));
           t.setDescription((String) ctr.get("description"));
           t.setPrix_reservation(Float.parseFloat(ctr.get("prixReservation").toString()));
           t.setDate_act((String) ctr.get("dateAct"));
           t.setNom_act((String) ctr.get("nomAct"));
        
        
           /* Centre list = (Centre)tasksListJson.get("root");
              System.out.println("heeeeeeeeeeeeeeeelllo");
            System.out.println(list);
            //Parcourir la liste des tâches Json
           /* for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Centre t = new Centre();
                float id = Float.parseFloat(obj.get("idCentre").toString());
                //int idd = Integer.parseInt(obj.get("id").toString());
                t.setId_centre((int)id);
                t.setNom_centre(obj.get("nomCentre").toString());
              /*  t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setName(obj.get("name").toString());*/
                //Ajouter la tâche extraite de la réponse Json à la liste
            //  tasks.add(t);
           // }
             
            
        } catch (IOException ex) {
            
        }
       
        return t;
    }
      
       public Activite getActByid(int id ){
        String url = Statics.BASE_URL+"/mobile/activite/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               c =  parseActiviteByid(new String(req.getResponseData()));
                System.out.println("salem");
             System.out.println(act);
                req.removeResponseListener(this);
            }
        });
            
        NetworkManager.getInstance().addToQueueAndWait(req);
        return c;
    }
}
