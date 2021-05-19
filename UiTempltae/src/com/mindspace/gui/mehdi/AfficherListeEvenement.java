/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mindspace.gui.mehdi;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.netdev.mindspace.entities.Evenement;
import com.netdev.mindspace.services.EvenementServices;
import com.teknikindustries.bulksms.SMS;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author trabe
 */
public class AfficherListeEvenement extends BaseForm{
    Form current;
    Image img = null;
    ImageViewer iv = null;
    EncodedImage ec;
    
    public AfficherListeEvenement(Resources res) {
        super("Nos Evenement", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Nos Evenement");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 12) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 12);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        add(LayeredLayout.encloseIn(
                sl
        ));
        
        ArrayList<Evenement> pd = EvenementServices.getInstance().getAllEvenements();
      for(Evenement p : pd){
            Container cnHor = new Container(BoxLayout.x());
            Container cnVer = new Container(BoxLayout.y());
            
            
            Button part = new Button("Participer");
            
            
            part.addActionListener(new ActionListener(){
        @Override
       public void actionPerformed(ActionEvent evt) {
           SMS sms=new SMS();
                 String nt= "+21621698195502";
                 sms.SendSMS("mehddag","Nour2021", "Votre publication a été ajoute avec succes", nt ,"https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
       }
       });
            
            //Add image
           String url = "http://127.0.0.1:8001/admin/img/evenement/"+ p.getImage();
            try {
                ec = EncodedImage.create("/load.png");
                img = URLImage.createToStorage(ec, url, url,URLImage.RESIZE_SCALE);
                iv = new ImageViewer(img);
                cnHor.add(iv);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
            TextArea lbType = new TextArea("Evenement : "+p.getSujet());
            lbType.setUIID("Test");
            lbType.setEditable(false);
            lbType.getStyle().setAlignment(Component.CENTER);

            TextArea lbDesc = new TextArea("Description : "+ p.getDateEven());
            lbDesc.setUIID("Label");
            lbDesc.setEditable(false);
            lbDesc.getStyle().setAlignment(Component.CENTER);
            
            Button voirMenu = new Button("Joindre ce even");
            voirMenu.getAllStyles().setFgColor(0xfffffff);
            FontImage.setMaterialIcon(voirMenu, FontImage.MATERIAL_RESTAURANT, 4);
            
            Command back = new Command("Non") {
            @Override
                public void actionPerformed(ActionEvent evt) {
                    
                }
            };
            
            Command oui = new Command("Oui"){
                @Override
                public void actionPerformed(ActionEvent evt) {
                    super.actionPerformed(evt); //To change body of generated methods, choose Tools | Templates.
                }
            };
            
            voirMenu.addActionListener(e -> {
                Dialog.show("Confirmation", "Vous êtes sur de consulter l  Evenement "+ p.getSujet(), oui, back);
                
            });
            
            cnVer.add(lbType);
            cnVer.add(lbDesc);
            cnVer.add(voirMenu);
            cnHor.add(cnVer);
            add(cnHor);
            add(part);
        }
    }
}
