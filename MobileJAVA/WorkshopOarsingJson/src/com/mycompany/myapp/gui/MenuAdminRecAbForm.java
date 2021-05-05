/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.gui.Reclamation.StatistiquesForm;
import com.mycompany.myapp.gui.Abonnement.ListedesabonnementsForm;
import com.mycompany.myapp.gui.Abonnement.ajouterAbonnementForm;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.gui.Reclamation.ListedesreclamationsForm;

/**
 *
 * @author Firas
 */
public class MenuAdminRecAbForm extends Form{
    Form current;
    public MenuAdminRecAbForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Gestion Des Réclamations et Abonnements");
        setLayout(BoxLayout.y());

        Button allRec = new Button("Liste des reclamations");
        Button allAb = new Button("Liste des abonnements");
        Button Stat = new Button("Statistiques");
        Button addAb = new Button("Ajouter nouveau type d'abonnements");

        allAb.addActionListener(e -> new ListedesabonnementsForm(current).show());
        allRec.addActionListener(e -> new ListedesreclamationsForm(current).show());
        addAb.addActionListener(e -> new ajouterAbonnementForm(current).show());
        Stat.addActionListener(e -> new StatistiquesForm(current).show());
        addAll(allRec,allAb,Stat,addAb);

    }
    
}
