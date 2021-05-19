/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.BaseForm;
import com.netdev.mindspace.entites.Menu;
import com.netdev.mindspace.services.MenuServices;
import java.util.ArrayList;

/**
 *
 * @author moez
 */
public class SearchMenuForm extends BaseForm
{
    Form current;
    String search="";
    public ArrayList<Menu> menus = new ArrayList<>();
    
    public SearchMenuForm(Resources res)
    {
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
        
        setTitle("Recherche :'"+search+"'");
        setLayout(BoxLayout.y());
        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        TextField tfsearch = new TextField("","rechercher");
        tfsearch.setText(search);
        Button btnsearch = new Button("Rechercher");
        
        C1.add(btnsearch);
        C1.add(tfsearch);
        btnsearch.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfsearch.getText().length()>0)
                {
                    try {
                        search = tfsearch.getText();
                        new AfficherMenuSoloSearch(res, search).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "", new Command("OK"));
                    }
                }
            }
        });
        
        addAll(C1);
    }
}