/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.gui;

import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author maminizer
 */
public class HomeForm extends Form {
   Form current;
    public HomeForm() {
        current= this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnReservation = new Button("Add Reservation");
        Button btnListReservation = new Button("List Reservations");
        
        
        btnReservation.addActionListener(e-> new addReservationForm(current).show());
        btnListReservation.addActionListener(e-> new ListReservationForm(current).show());
        
        
        addAll(btnListReservation, btnReservation);
        
        
    }
}
