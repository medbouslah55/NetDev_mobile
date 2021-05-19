/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Abonnement;
import com.netdev.mindspace.services.ServiceAbonnement;


import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class ListeDesAbonnements extends BaseForm {
      ArrayList<Abonnement> data = new ArrayList<>();
  
    Container all = new Container(new BoxLayout(BoxLayout.Y_AXIS));
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
            Button share = new Button(FontImage.MATERIAL_SHARE);
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
        
        

    }
    public ListeDesAbonnements(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste Des Abonnements");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });
        
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        int nb = ServiceAbonnement.getInstance().getAllabonnements().size();
        Label NB = new Label(nb+"  Réclamations");
        
        //Label facebook = new Label("786 followers", FontImage.MATERIAL_DELETE, "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        NB.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                NB,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                                twitter
                        )
                )
        ));
        listeab();
        add(all);
}
}
