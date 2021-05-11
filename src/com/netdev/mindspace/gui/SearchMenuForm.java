/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.netdev.mindspace.entites.Menu;
import com.netdev.mindspace.services.MenuServices;
import java.util.ArrayList;

/**
 *
 * @author moez
 */
public class SearchMenuForm extends Form
{
    Form current;
    String search="";
    public ArrayList<Menu> menus = new ArrayList<>();
    
    public SearchMenuForm(Form previous)
    {
        current=this;
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SETTINGS, e-> new SettingsForm(current).show());
        
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
                        new AfficherMenuSoloSearch(current, search).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "", new Command("OK"));
                    }
                }
            }
        });
        
        addAll(C1);
    }
}