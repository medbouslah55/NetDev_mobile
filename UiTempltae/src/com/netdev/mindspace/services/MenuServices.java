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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.netdev.mindspace.entites.Menu;
import com.netdev.mindspace.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trabe
 */
public class MenuServices {
    
    private ConnectionRequest req;
    public ArrayList<Menu> menus;
    Menu m = new Menu();
    ArrayList<Menu> msearch;
    public static MenuServices instance = null;
    
    private MenuServices(){
          req = new ConnectionRequest();
     }
     
    public static MenuServices getInstance() {
        if (instance == null) {
            instance = new MenuServices();
        }
        return instance;
    }
    
    public ArrayList<Menu> parseMenu(String jsonText){
        try {
            menus = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
            Map<String,Object> menusListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)menusListJson.get("root");
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                Menu t = new Menu();
                String id = obj.get("idMenu").toString();
                t.setIdMenu(id);
                
                t.setDescription((obj.get("description").toString()));
                
                t.setNumJour((int)Float.parseFloat(obj.get("numJour").toString()));
                t.setMatin((obj.get("matin").toString()));
                t.setMatinImg((obj.get("matinImg").toString()));
                t.setDejeuner((obj.get("dejeuner").toString()));
                t.setDejeunerImg((obj.get("dejeunerImg").toString()));
                t.setDinner((obj.get("dinner").toString()));
                t.setDinnerImg((obj.get("dinnerImg").toString()));
                t.setTotalCalories((int)Float.parseFloat(obj.get("totalCalories").toString()));
                
                String idR = obj.get("idRegime").toString();
                t.setIdRegime(idR);
                
                menus.add(t);
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return menus;
    }
    
    public Menu parseOneMenu(String jsonText) throws ParseException{
            
        Menu t = new Menu();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            String id = obj.get("idMenu").toString();
            t.setIdMenu(id);

            t.setDescription((obj.get("description").toString()));

            t.setNumJour((int)Float.parseFloat(obj.get("numJour").toString()));
            t.setMatin((obj.get("matin").toString()));
            t.setMatinImg((obj.get("matinImg").toString()));
            t.setDejeuner((obj.get("dejeuner").toString()));
            t.setDejeunerImg((obj.get("dejeunerImg").toString()));
            t.setDinner((obj.get("dinner").toString()));
            t.setDinnerImg((obj.get("dinnerImg").toString()));
            t.setTotalCalories((int)Float.parseFloat(obj.get("totalCalories").toString()));

            String idR = obj.get("idRegime").toString();
            t.setIdRegime(idR);
                
        } catch (IOException ex) {
            
        }
        return t;
    }
    
    public ArrayList<Menu> getAllMenus(int idRegime){       
        String url = Statics.BASE_URL+"/api/regimes/"+idRegime+"/menus";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                menus = parseMenu(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return menus;
    }
    
    public Menu getSingleMenu(int idRegime, int idMenu){
        String url = Statics.BASE_URL+"/api/regimes/"+idRegime+"/menus/"+idMenu;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    m = parseOneMenu(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                   
                }
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return m;
    }
    
    public ArrayList<Menu> searchMenu(String search){
        String url = Statics.BASE_URL+"/api/regimes/menu/search/"+search;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                msearch = parseMenu(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return msearch;
    }
    
    public Menu getHigh(){
        String url = Statics.BASE_URL + "/api/regimes/menus/stat_high";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    m = parseOneMenu(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                   
                }
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return m;
    }
    
    public Menu getLow(){
        String url = Statics.BASE_URL + "/api/regimes/menus/stat_low";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    m = parseOneMenu(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                   
                }
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return m;
    }
    
    public Menu getAvrage (){
        String url = Statics.BASE_URL + "/api/regimes/menus/stat_avrage";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    m = parseOneMenu(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                   
                }
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return m;
    }
    
}
