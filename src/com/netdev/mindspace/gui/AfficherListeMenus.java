/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.netdev.mindspace.entites.Menu;
import com.netdev.mindspace.services.MenuServices;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author trabe
 */
public class AfficherListeMenus extends Form {
    Form current;
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;
    ImageViewer iv = null;
    EncodedImage ec;
    
    public AfficherListeMenus(Form previous, int id_regime) {        
        current = this;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_PIE_CHART, e-> new PieChart(current).show());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SEARCH, e-> new SearchMenuForm(current).show());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SETTINGS, e-> new SettingsForm(current).show());
        
        current.setTitle("Nos Menus");
        
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
                voirMenu.getAllStyles().setFgColor(0xffbe5aff);
                
                FontImage.setMaterialIcon(voirMenu, FontImage.MATERIAL_RESTAURANT_MENU, 4);
                
                voirMenu.addActionListener(e -> {
//                    Dialog.show("Confirmation", "Vous êtes sur de consulter le Menu "+ p.getDescription(), new Command("Oui"));
                    int idMenu = (int)Float.parseFloat(p.getIdMenu());
                    new AfficherMenuSolo(current, id_regime, idMenu).show();
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
