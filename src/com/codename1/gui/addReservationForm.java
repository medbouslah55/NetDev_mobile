/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.components.MultiButton;
import com.codename1.entities.Reservation;

import com.codename1.services.ServiceReservation;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author maminizer
 */
public class addReservationForm extends Form {

    String rowindex;
    String date;
    String price,name;
    Form current;
    Validator v = new Validator();
    public addReservationForm(Form previous) {
        current = this;
        setTitle("Ajouter Reservation");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField("", "nom");
        TextField tfprenom = new TextField("", "prenom");
        TextField tfnbrPlace = new TextField("", "nbPlace");
        
        
        v.addConstraint(tfnom, new RegexConstraint("[a-zA-Z]{3,20}", "Format Nom Invalide"));
        v.addConstraint(tfprenom, new RegexConstraint("[a-zA-Z]{3,20}", "Format Prénom Invalide"));
        v.addConstraint(tfnbrPlace, new RegexConstraint("[1-9]", "Format Nbr de place Invalide"));
        
        Label notice = new Label ("Votre Nom doit contenir plus de 3 caracteres");
        Label notice2 = new Label ("Votre Prenom doit contenir plus de 3 caracteres");
        Label notice3 = new Label ("Le nombre de place doit etre un entier");
        
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);

        datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                date = datePicker.getText();
            }
        });

        ComboBox<Map<String, Object>> combo = new ComboBox<>(
                createListEntry("Zumba", "100"),
                createListEntry("Massage", "50"),
                createListEntry("Yoga", "50"),
                createListEntry("Meditation", "75"),
                createListEntry("Thalassotherapie", "100")
        );

        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                rowindex = combo.getSelectedItem().get("Line1").toString();
                price = combo.getSelectedItem().get("Line2").toString();
                System.out.println(rowindex);
            }
        });
            
        Button btnValider = new Button("Add Reservation");
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

        btnpanier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length() == 0) || (tfprenom.getText().length() == 0) || (datePicker.getText().length() == 0 || (tfnbrPlace.getText().length() == 0))) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Reservation u = new Reservation(tfnom.getText(), tfprenom.getText(), date, rowindex, Integer.parseInt(tfnbrPlace.getText()));
                        
                        new PanierForm(rowindex,price,tfnbrPlace.getText(),current).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });
        addAll(tfnom, tfprenom, tfnbrPlace, datePicker, combo, btnValider,btnpanier);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente
    }

    private Map<String, Object> createListEntry(String name, String date) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        return entry;
    }
        //regex check 
       

}
