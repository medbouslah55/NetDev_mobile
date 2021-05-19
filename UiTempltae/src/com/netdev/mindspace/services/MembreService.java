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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.netdev.mindspace.entites.Membre;
import com.netdev.mindspace.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author mohamedbouslah
 */
public class MembreService {
    public ArrayList<Membre> membres;
    public Map<String, Object> resultatCnx;
    public static MembreService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    String json;

    private MembreService() {
         req = new ConnectionRequest();
    }

    public static MembreService getInstance() {
        if (instance == null) {
            instance = new MembreService();
        }
        return instance;
    }
    
    public ArrayList<Membre> parseUsers(String jsonText) {
        try {
            membres = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Membre udata = new Membre();
                udata.setCin((int) Float.parseFloat(obj.get("cin").toString()));
                udata.setNom(obj.get("nom").toString());
                udata.setPrenom(obj.get("prenom").toString());
                udata.setSexe(obj.get("sexe").toString());
                //udata.setDatee(obj.get("date").toString());
                udata.setTaille((int) Float.parseFloat(obj.get("taille").toString()));
                udata.setPoids((int) Float.parseFloat(obj.get("poids").toString()));
                udata.setEmail(obj.get("email").toString());
                udata.setPassword(obj.get("password").toString());
                udata.setTelephone((int) Float.parseFloat(obj.get("telephone").toString()));

                
                membres.add(udata);
            }

        } catch (IOException ex) {
            ex.getMessage();
        }
        return membres;
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
    
    public void signin(TextField email ,TextField password ,Resources res)
    {
        String url = Statics.BASE_URL + "/membre/signin?" + "email=" + email.getText().toString() + "&password=" + password.getText().toString(); //création de l'URL
        req.setUrl(url);
        
        req.addResponseListener((e)-> {
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) +"";
            
            try{
            if(json.equals("failed"))
            {
                Dialog.show("Echec d'authontification", "Email ou password incorrect", "OK",null);
            }
            else {
                System.out.println("data ====>"+json);
//                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
//                if(user.size() >0)
                    Dialog.show("succes", "login cb", "ok",null);
            }
            }catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public Map<String, Object> parseConnexion(String jsonText) {
        Membre u = new Membre();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            return tasksListJson;

        } catch (IOException ex) {

        }
        return null;
    }
    
    public String connect(TextField email ,TextField password) {
        String url = Statics.BASE_URL + "/membre/signin?" + "email=" + email.getText().toString() + "&password=" + password.getText().toString(); //création de l'URL
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                //Users = parseUsers(new String(req.getResponseData()));
                resultatCnx = parseConnexion(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultatCnx.get("resultat").toString();
    }
    
    public ArrayList<Membre> getUsers() {

        String url = Statics.BASE_URL + "/membre/AfficherUsers/";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                membres = parseUsers(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return membres;
    }
    
    public Membre getUser(String text) {
        List<Membre> l = getUsers();
        boolean test = false;
        int i;
        for (i = 0; i < l.size(); i++) {
            if (l.get(i).getEmail().compareTo(text) == 0) {
                return l.get(i);
            }
        }
        return null;
    }
    public Membre getUser(int cin) {
        List<Membre> l = getUsers();
        int i;
        for (i = 0; i < l.size(); i++) {
            if (l.get(i).getCin()== cin) {
                return l.get(i);
            }
        }
        return null;
    }
    
    public void update(int cin, String nom, String prenom,Float taille, Float poids,String email,int telephone) {
        String url = Statics.BASE_URL + "/membre/UpdateUserMobile/" + cin + "/" + nom + "/" + prenom + "/" + taille + "/" + poids + "/" + email + "/" + telephone;
        System.out.print(url);
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);

        NetworkManager.getInstance().addToQueueAndWait(req);

    }
    
    
    
    public String getPasswordByEmail(String email, Resources res){
        String url = Statics.BASE_URL + "/membre/getPasswordByEmail?email=" +email;
        req =new ConnectionRequest(url, false);
        req.setUrl(url);
        
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();

             json = new String(req.getResponseData()) + "";

                try {

               
                    System.out.println("data ==" + json);

                    Map<String, Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    
                   } catch (IOException ex) {
                    ex.printStackTrace();
                
            }
        });
        
         NetworkManager.getInstance().addToQueueAndWait(req);
         return json;
    }
    
    public void updatePassword(int cin, String password) {
        String url = Statics.BASE_URL + "/membre/UpdatePasswordMobile/" + cin + "/" + password ;
        System.out.print(url);
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);

        NetworkManager.getInstance().addToQueueAndWait(req);

    }
}

