/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.gui.Reclamation.ajouterReclamationForm;

/**
 *
 * @author Firas
 */
public class MenuUserAbRec extends Form {
    Form current;
    public MenuUserAbRec() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Gestion Des Réclamations et Abonnements");
        setLayout(BoxLayout.y());

   
        Button addRec = new Button("Passer Une Réclamation");

        addRec.addActionListener(e -> new ajouterReclamationForm(current).show());

        addAll(addRec);

    }
}
