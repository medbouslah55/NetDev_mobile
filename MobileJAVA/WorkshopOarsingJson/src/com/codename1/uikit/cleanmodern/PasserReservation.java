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

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
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
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.services.ServiceReservation;
import java.util.HashMap;
import java.util.Map;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class PasserReservation extends BaseForm {
    String rowindex;
    String date;
    String price,name;
    Form current;
    Validator v = new Validator();

    public PasserReservation(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Passer Une Reservation");
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
        ///////////////////////////////////////////////////////////////////////////////////////
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setUIID("TextFieldBlack");
        addStringValue("", datePicker);

        datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                date = datePicker.getText();
            }
        });
          ////////////////////////////////////////////////////////////////////////////////////////
        TextField tfnbrPlace = new TextField();
        tfnbrPlace.setUIID("TextFieldBlack");
        addStringValue("nbPlace", tfnbrPlace);
        

        ComboBox<Map<String, Object>> combo = new ComboBox<>(
                createListEntry("Zumba", "100"),
                createListEntry("Massage", "50"),
                createListEntry("Yoga", "50"),
                createListEntry("Meditation", "75"),
                createListEntry("Thalassotherapie", "100")
        );
        addStringValue("", combo);
        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                rowindex = combo.getSelectedItem().get("Line1").toString();
                price = combo.getSelectedItem().get("Line2").toString();
                System.out.println(rowindex);
            }
        });
            
      
        ////////////////////////////////////////////////////////////////////////////////////////

        Label notice = new Label("* Votre Nom & Prénom doivent étre:");
        Label notice1 = new Label("-Entre 3 et 20 caractéres");
        Label notice2 = new Label("-Contenir des caractére alphabétique");
        notice.setUIID("Label");
        notice1.setUIID("Label");
        notice2.setUIID("Label");
        /////////////////////////////////////////////////////////////////////////////////////////

        Button btnValider = new Button("Enregistrer");
        addStringValue("", btnValider);

        /////////////////////////////////////////////////////////////////////////////////////////
        //regex check 
        
        v.addConstraint(tfnom, new RegexConstraint("[a-zA-Z]{3,20}", "Format Nom Invalide"));
        v.addConstraint(tfprenom, new RegexConstraint("[a-zA-Z]{3,20}", "Format Prénom Invalide"));
        v.addConstraint(tfnbrPlace, new RegexConstraint("[1-9]", "Format Nbr de place Invalide"));
        v.addSubmitButtons(btnValider);
  

     btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length() == 0) || (tfprenom.getText().length() == 0) || (datePicker.getText().length() == 0 || (tfnbrPlace.getText().length() == 0))) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Reservation u = new Reservation(tfnom.getText(), tfprenom.getText(), date, rowindex, Integer.parseInt(tfnbrPlace.getText()));
                        if (ServiceReservation.getInstance().addReservation(u)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });
        Button btnpanier = new Button("procéder au paiment");
        addStringValue("", btnpanier);

        btnpanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length() == 0) || (tfprenom.getText().length() == 0) || (datePicker.getText().length() == 0 || (tfnbrPlace.getText().length() == 0))) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Reservation u = new Reservation(tfnom.getText(), tfprenom.getText(), date, rowindex, Integer.parseInt(tfnbrPlace.getText()));
                        
                       new Panier(res,rowindex,price,tfnbrPlace.getText()).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

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
       private Map<String, Object> createListEntry(String name, String date) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        return entry;
    }
}
