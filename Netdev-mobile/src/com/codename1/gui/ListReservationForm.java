/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.entities.Reservation;
import com.codename1.services.ServiceReservation;
import com.codename1.ui.Button;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.services.ServiceReservation;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class ListReservationForm extends Form {

    Form current;
    ArrayList<Reservation> data = new ArrayList<>();

    Container all = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//Container tri = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public ListReservationForm(Form previous) {
        setTitle("Listes  Des Reservations");
        data = ServiceReservation.getInstance().getAllReservations();
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (int i = 0; i < data.size(); i++) {
            Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Reservation u = new Reservation();
            u.setId_reservation(data.get(i).getId_reservation());
            u.setNom(data.get(i).getNom());
            u.setPrenom(data.get(i).getPrenom());
            u.setDate_act(data.get(i).getDate_act());
            u.setNb_place(data.get(i).getNb_place());
            Label separation = new Label("----------------------------");
            Label nom = new Label("Nom : " + data.get(i).getNom());
            Label prenom = new Label("Prenom : " + data.get(i).getPrenom());
            Label dateAct = new Label("Date Activite : " + data.get(i).getDate_act());
            Label nbPlace = new Label("Nbr Place : " + data.get(i).getNb_place());
            Button modif = new Button(FontImage.MATERIAL_EDIT);
            Button supp = new Button(FontImage.MATERIAL_DELETE);
            
            modif.addActionListener(e -> new ModifierReservationForm(u).show());
            supp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceReservation.getInstance().deleteReservation(u);
                    Dialog.show("Success", "Voulez vous supprimer cette reservation ?", "OK", "Cancel");
                    new HomeForm().show();
                }
            });

            x.addAll(nom, prenom);
            xx.addAll(supp, modif);
            y.addAll(x, dateAct, nbPlace, xx, separation);

        }

        addAll(y);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente
    }
}
