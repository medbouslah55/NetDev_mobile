/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
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
import java.util.ArrayList;

/**
 *
 * @author trabe
 */
public class AfficherListeMenus extends BaseForm {
    Form current;
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;
    ImageViewer iv = null;
    EncodedImage ec;
    
    public AfficherListeMenus(Resources res, int id_regime) {        
        super("Nos Menus", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Nos Menus");
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
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_PIE_CHART, e-> new PieChart(res).show());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SEARCH, e-> new SearchMenuForm(res).show());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SETTINGS, e-> new SettingsForm(res).show());
//        getToolbar().addCommandToOverflowMenu("Settings", null ,  e-> new SettingsForm(current).show());
                
        ArrayList<Menu> pd = MenuServices.getInstance().getAllMenus(id_regime);
        for(Menu p : pd){
//            Container cnHor = new Container(BoxLayout.x());
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
                
                Container welcome = FlowLayout.encloseCenter(
                    new Label("Menu Jour "+p.getNumJour())
                );
                
                Button voirMenu = new Button("More details...");
                voirMenu.getAllStyles().setFgColor(0xfffffff);
                
                FontImage.setMaterialIcon(voirMenu, FontImage.MATERIAL_RESTAURANT_MENU, 4);
                
                voirMenu.addActionListener(e -> {
//                    Dialog.show("Confirmation", "Vous êtes sur de consulter le Menu "+ p.getDescription(), new Command("Oui"));
                    int idMenu = (int)Float.parseFloat(p.getIdMenu());
                    new AfficherMenuSolo(res, id_regime, idMenu).show();
                });
                
                cnVer.add(welcome);
                cnVer.add(iv);
                cnVer.add(voirMenu);
                add(cnVer);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
