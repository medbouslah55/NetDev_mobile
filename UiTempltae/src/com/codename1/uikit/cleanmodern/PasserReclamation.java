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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
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
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Reclamation;
import com.netdev.mindspace.services.ServiceReclamation;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class PasserReclamation extends BaseForm {

    public PasserReclamation(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Passer Une Réclamation");
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

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                facebook,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                                twitter
                        )
                )
        ));

        Validator v = new Validator();

        TextField tfnom = new TextField();
        tfnom.setUIID("TextFieldBlack");
        addStringValue("Nom", tfnom);
        ////////////////////////////////////////////////////////////////////////////////////////
        TextField tfprenom = new TextField();
        tfprenom.setUIID("TextFieldBlack");
        addStringValue("Prénom", tfprenom);
        ////////////////////////////////////////////////////////////////////////////////////////
        TextField tfmail = new TextField();
        tfmail.setUIID("TextFieldBlack");
        addStringValue("E-mail", tfmail);
        ///////////////////////////////////////////////////////////////////////////////////////
        ComboBox tftype = new ComboBox("Type De Probléme", "Probleme technique", "Probleme de connexion", "Probleme Au Niveau De Payemant", "Probleme Au Niveau De Reservation");
        //tftype.setUIID("TextFieldBlack");
        addStringValue("", tftype);
        ////////////////////////////////////////////////////////////////////////////////////////
        TextField tfdescription = new TextField();
        tfdescription.setUIID("TextFieldBlack");
        addStringValue("Description", tfdescription);
        ////////////////////////////////////////////////////////////////////////////////////////

        Label notice = new Label("* Votre Nom & Prénom doivent étre:");
        Label notice1 = new Label("-Entre 3 et 20 caractéres");
        Label notice2 = new Label("-Contenir des caractére alphabétique");
        notice.setUIID("Label");
        notice1.setUIID("Label");
        notice2.setUIID("Label");
        /////////////////////////////////////////////////////////////////////////////////////////

        Button btnValidee = new Button("Enregistrer");
        addStringValue("", btnValidee);

        /////////////////////////////////////////////////////////////////////////////////////////
        //regex check 
        v.addConstraint(tfnom, new RegexConstraint("[a-zA-Z]{6,20}", "Format Nom Invalide"));
        v.addConstraint(tfprenom, new RegexConstraint("[a-zA-Z]{6,20}", "Format Prénom Invalide"));
        v.addConstraint(tfmail, new RegexConstraint("^(.+)@(.+)$", "Format E-mail Invalide"));
        v.addSubmitButtons(btnValidee);
        btnValidee.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent evt) {
                try {
                    Reclamation a = new Reclamation(tfnom.getText(), tfprenom.getText(), tfmail.getText(), tftype.getSelectedItem().toString(), tfdescription.getText(), "En Cours");

                    if (ServiceReclamation.getInstance().addReclamation(a)) {

                        Dialog.show("Success", "Votre reclamation a été ajouter avec succès !", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Erreur au moment de l'ajout", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }

            }
        });
        addStringValue("", notice);
        addStringValue("", notice1);
        addStringValue("", notice2);

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
