/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Abonnement;

import com.codename1.ads.AdsService;
import com.codename1.components.Ads;
import com.codename1.social.FacebookConnect;
import com.codename1.social.GoogleConnect;
import com.codename1.social.Login;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.services.ServiceAbonnement;
import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class ListedesabonnementsForm extends Form {

    Form current;
    ArrayList<Abonnement> data = new ArrayList<>();
    ArrayList<Abonnement> datatrier = new ArrayList<>();
    Container all = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Container tri = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    

    private void listeab() {
        data = ServiceAbonnement.getInstance().getAllabonnements();
        
        for (int i = 0; i < data.size(); i++) {
            Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Abonnement ab = new Abonnement();
            ab.setId_ab(data.get(i).getId_ab());
            ab.setTitre_ab(data.get(i).getTitre_ab());
            ab.setType_ab(data.get(i).getType_ab());
            ab.setPrix_ab(data.get(i).getPrix_ab());
            ab.setDesc_ab(data.get(i).getDesc_ab());
            Label separation = new Label("----------------------------");
            Label titre = new Label("Titre : " + data.get(i).getTitre_ab());
            Label type = new Label("Type : " + data.get(i).getType_ab());
            Label prix = new Label("Prix : " + data.get(i).getPrix_ab());
            Label des = new Label("Description : " + data.get(i).getDesc_ab());
            Button share = new Button(FontImage.MATERIAL_FACE);
            Button achat = new Button(FontImage.MATERIAL_CARD_GIFTCARD);
            
            
            share.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                String clientId = "1171134366245722";
                String redirectURI = "http://www.codenameone.com/";
                String clientSecret = "XXXXXXXXXXXXXXXXXXXXXXXXXX";
                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                if(!fb.isUserLoggedIn()){
                    fb.doLogin();
                }else{
                    //get the token and now you can query the facebook API
                    String token = fb.getAccessToken().getToken();
                }
  

                }
            });
            
            
            x.addAll(titre, type, prix);
            xx.addAll(achat,share);
            all.addAll(x, des, xx, separation);

        }
        
        addAll(all);

    }

    public ListedesabonnementsForm(Form previous) {

        setTitle("Listes  Des Abonnements");
        listeab();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                 e -> previous.showBack());
    }
}
