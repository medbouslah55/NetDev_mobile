/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.entities.Reservation;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maminizer
 */
public class PanierForm extends Form {

    Form current;

    public PanierForm(String name, String Price, String nbPlace,Form previous) {
        current = this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Activité : " +name));
        add(new Label("Prix de l'activité : " +Price));
        add(new Label("Nbr de places réservées : " +nbPlace));
        
        Button btnReservation = new Button("Confirmer la reservation");
        Button retour = new Button("modifer votre choix");

        btnReservation.addActionListener(e
                -> new addReservationForm(current).show());
        retour.addActionListener(e
                -> previous.showBack());

        addAll(retour, btnReservation);

    }

}
