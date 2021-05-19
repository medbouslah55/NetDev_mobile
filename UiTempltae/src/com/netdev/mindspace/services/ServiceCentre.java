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
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ServiceCentre {
      public ArrayList<Centre> tasks;
      public Centre c ;
    
    public static ServiceCentre instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCentre() {
         req = new ConnectionRequest();
    }

    public static ServiceCentre getInstance() {
        if (instance == null) {
            instance = new ServiceCentre();
        }
        return instance;
    }

  /*  public boolean addTask(Task t) {
        String url = Statics.BASE_URL + "/tasks/" + t.getName() + "/" + t.getStatus(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion        
        //req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            
      /*  });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }*/

    public ArrayList<Centre> parseCentres(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Centre t = new Centre();
                float id = Float.parseFloat(obj.get("idCentre").toString());
                //int idd = Integer.parseInt(obj.get("id").toString());
                t.setId_centre((int)id);
                t.setNom_centre(obj.get("nomCentre").toString());
              /*  t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setName(obj.get("name").toString());*/
                //Ajouter la tâche extraite de la réponse Json à la liste
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return tasks;
    }
    
    public ArrayList<Centre> getAllCentres(){
        String url = Statics.BASE_URL+"/mobile/cyrine";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseCentres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
    
    
      public Centre parseCentre(String jsonText){
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
             
             if(act.isEmpty()){
             t.setA_des_centres(false);
             }else{  t.setA_des_centres(true);}
           System.out.println("heeeeeeeeeeeeeeeelllo");
             System.out.println(act);
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
      
       public Centre getCentreByID(int id ){
        String url = Statics.BASE_URL+"/mobile/centre/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               c = parseCentre(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return c;
    }
}
