/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.netdev.mindspace.entites.Menu;
import com.netdev.mindspace.services.MenuServices;
import java.io.IOException;

/**
 *
 * @author trabe
 */
public class AfficherMenuSolo extends BaseForm {
    Form current;
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;
    ImageViewer iv = null;
    EncodedImage ec;

    public AfficherMenuSolo(Resources res, int idRegime, int idMenu) {
        super("Recherche", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Recherche");
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
        
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SETTINGS, e-> new SettingsForm(res).show());
        
        Menu p = MenuServices.getInstance().getSingleMenu(idRegime, idMenu);
        
        Container cnVer = new Container(BoxLayout.y());
        
        //Add image
        String url1 = "http://127.0.0.1:8000/admin/img/menu/"+ p.getMatinImg();
        String url2 = "http://127.0.0.1:8000/admin/img/menu/"+ p.getDejeunerImg();
        String url3 = "http://127.0.0.1:8000/admin/img/menu/"+ p.getDinnerImg();

        try {
            ec = EncodedImage.create("/load.png");
            img1 = URLImage.createToStorage(ec, url1, url1,URLImage.RESIZE_SCALE);
            img2 = URLImage.createToStorage(ec, url2, url2,URLImage.RESIZE_SCALE);
            img3 = URLImage.createToStorage(ec, url3, url3,URLImage.RESIZE_SCALE);

            DefaultListModel<Image> listeImages = new DefaultListModel<>();
            listeImages.addItem(img1);
            listeImages.addItem(img2);
            listeImages.addItem(img3);

            iv = new ImageViewer();
            iv.setImage(listeImages.getItemAt(0));
            iv.setImageList(listeImages);
            
            Label lbNbj = new Label("Menu Jour : "+p.getNumJour());
            Label lbMatin = new Label("Matin : "+p.getMatin());
            Label lbDej = new Label("Dejeuner : "+p.getDejeuner());
            Label lbDinner = new Label("Dinner : "+p.getDinner());
            Label lbCalories = new Label("Total Calorique : "+p.getTotalCalories()+" Calories");
            
            lbNbj.getAllStyles().setFgColor(0xffbe5aff);
            lbMatin.getAllStyles().setFgColor(0xff0000);
            lbDej.getAllStyles().setFgColor(0xff0000);
            lbDinner.getAllStyles().setFgColor(0xff0000);
            lbCalories.getAllStyles().setFgColor(0x00b300);
            
            Container cnNbj = FlowLayout.encloseCenter(lbNbj);
            Container imgg = FlowLayout.encloseCenter(iv);
            Container cnMatin = FlowLayout.encloseCenter(lbMatin);
            Container cnDej = FlowLayout.encloseCenter(lbDej);
            Container cnDinner = FlowLayout.encloseCenter(lbDinner);
            Container cnCal = FlowLayout.encloseCenter(lbCalories);
            
            //for skin iphone x
            cnNbj.getAllStyles().setMarginLeft(180);
            iv.getAllStyles().setMarginLeft(180);
            iv.getAllStyles().setMarginTop(75);
            cnMatin.getAllStyles().setMarginTop(100);
            cnMatin.getAllStyles().setMarginLeft(180);
            cnDej.getAllStyles().setMarginLeft(180);
            cnDinner.getAllStyles().setMarginLeft(180);
            cnCal.getAllStyles().setMarginLeft(180);
            cnCal.getAllStyles().setMarginTop(100);
            
            cnVer.add(cnNbj);
            cnVer.add(imgg);
            cnVer.add(cnMatin);
            cnVer.add(cnDej);
            cnVer.add(cnDinner);
            cnVer.add(cnCal);
            add(cnVer);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

