/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.netdev.mindspace.entities.Status;
import com.netdev.mindspace.services.StatusServices;
import java.util.ArrayList;


/**
 *
 * @author trabe
 */
public class AfficherListeStatus extends BaseForm{
    Form current;
    Image img = null;
    ImageViewer iv = null;
    EncodedImage ec;
    
    public AfficherListeStatus(Resources res) {
        super("Nos status", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Nos status");
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
        
        ArrayList<Status> pd = StatusServices.getInstance().getAllStatus();
      for(Status p : pd){
            Container cnHor = new Container(BoxLayout.x());
            Container cnVer = new Container(BoxLayout.y());
            
            TextArea lbType = new TextArea("Status : "+p.getDate_pub());
            lbType.setUIID("Test");
            lbType.setEditable(false);
            lbType.getStyle().setAlignment(Component.CENTER);

            TextArea lbDesc = new TextArea("text : "+ p.getText());
            lbDesc.setUIID("Label");
            lbDesc.setEditable(false);
            lbDesc.getStyle().setAlignment(Component.CENTER);
            
            
            
            cnVer.add(lbType);
            cnVer.add(lbDesc);
            cnHor.add(cnVer);
            add(cnHor);
        }
    }
}
    
