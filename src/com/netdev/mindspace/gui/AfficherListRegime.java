/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.netdev.mindspace.MyApplication;
import com.netdev.mindspace.entites.Regime;
import com.netdev.mindspace.services.RegimeServices;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author trabe
 */
public class AfficherListRegime extends Form{
    Form current;
    Image img = null;
    ImageViewer iv = null;
    EncodedImage ec;
    
    public AfficherListRegime(Form previous) {
        current = this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SETTINGS, e-> new SettingsForm(current).show());
        
        setTitle("Nos Regimes");
        setLayout(BoxLayout.y());
        ArrayList<Regime> pd = RegimeServices.getInstance().getAllRegimes();
        for(Regime p : pd){
            Container cnHor = new Container(BoxLayout.x());
            Container cnVer = new Container(BoxLayout.y());
            
            //Add image
            String url = "http://127.0.0.1:8000/admin/img/regime/"+ p.getImage();
            try {
                ec = EncodedImage.create("/load.png");
                img = URLImage.createToStorage(ec, url, url,URLImage.RESIZE_SCALE);
                iv = new ImageViewer(img);
                cnHor.add(iv);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
            TextArea lbType = new TextArea("Regime : "+p.getType());
            lbType.setUIID("Test");
            lbType.setEditable(false);
            lbType.getStyle().setAlignment(Component.CENTER);

            TextArea lbDesc = new TextArea("Description : "+ p.getDescription());
            lbDesc.setUIID("Label");
            lbDesc.setEditable(false);
            lbDesc.getStyle().setAlignment(Component.CENTER);
            
            Button voirMenu = new Button("Joindre ce Regime");
            voirMenu.getAllStyles().setFgColor(0xffbe5aff);
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
                Dialog.show("Confirmation", "Vous êtes sur de consulter le regime "+ p.getType(), oui, back);
                new AfficherListeMenus(current, (int)Float.parseFloat(p.getIdRegime())).show();
            });
            
            cnVer.add(lbType);
            cnVer.add(lbDesc);
            cnVer.add(voirMenu);
            cnHor.add(cnVer);
            add(cnHor);
        }
    }
}
