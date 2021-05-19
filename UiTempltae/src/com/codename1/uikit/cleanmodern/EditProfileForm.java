/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.netdev.mindspace.services.MembreService;
import com.netdev.mindspace.utils.Session;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class EditProfileForm extends BaseForm {

    public EditProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Edit Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        
        Button twitter = new Button("save", res.getImage("twitter-logo.png"), "BottomPad");
        
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));

        int cin = Session.getSession().getSessionUser().getCin();
        
        TextField nom = new TextField(Session.getSession().getSessionUser().getNom());
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom", nom);
        
        TextField prenom = new TextField(Session.getSession().getSessionUser().getPrenom());
        prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom", prenom);
        
        TextField taille = new TextField(Float.toString(Session.getSession().getSessionUser().getTaille()));
        taille.setUIID("TextFieldBlack");
        addStringValue("Taille", taille);
        
        TextField poids = new TextField(Float.toString(Session.getSession().getSessionUser().getPoids()));
        poids.setUIID("TextFieldBlack");
        addStringValue("Poids", poids);

        TextField email = new TextField(Session.getSession().getSessionUser().getEmail());
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField telephone = new TextField(Integer.toString(Session.getSession().getSessionUser().getTelephone()));
        telephone.setUIID("TextFieldBlack");
        addStringValue("Telephone", telephone);
        
        twitter.addActionListener((e) -> {
            MembreService.getInstance().update(cin, nom.getText(), prenom.getText(), Float.parseFloat(taille.getText()), Float.parseFloat(poids.getText()), email.getText(), Integer.parseInt(telephone.getText()));
            Session.getSession().SetSessionUser(MembreService.getInstance().getUser(cin));
        });

        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
