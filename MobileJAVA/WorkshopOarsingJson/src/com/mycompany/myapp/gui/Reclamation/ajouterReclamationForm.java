/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Reclamation;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author Firas
 */
public class ajouterReclamationForm extends Form {

    public ajouterReclamationForm() {
        Form val = new Form();
        TableLayout tl;
        tl = new TableLayout(TOP,CENTER);
        
        tl.setGrowHorizontally(true);
        val.setLayout(tl);
        Validator v = new Validator();
        setTitle("Ajouter Nouveau Reclamation");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField("", "Nom");

        TextField tfprenom = new TextField("", "Prénom");
        TextField tfmail = new TextField("", "E-mail");

        ComboBox tftype = new ComboBox("Type De Probléme", "Probleme technique", "Probleme de connexion", "Probleme Au Niveau De Payemant", "Probleme Au Niveau De Reservation");
        //TextField tftype = new TextField("", "Type");
        TextField tfdescription = new TextField("", "Description");
        Label notice = new Label("* Votre Nom & Prénom doivent étre:" + "\n" + "-Entre 6 et 20 caractéres" + "\n" + "-Contenir des caractére alphabétique");
        Button btnValidee = new Button("Enregistrer");
        
        
        //regex check 
        v.addConstraint(tfnom, new RegexConstraint("[a-zA-Z]{6,20}", "Format Nom Invalide"));
        v.addConstraint(tfprenom, new RegexConstraint("[a-zA-Z]{6,20}", "Format Prénom Invalide"));
        v.addConstraint(tfmail, new RegexConstraint("^(.+)@(.+)$", "Format E-mail Invalide"));
        v.addSubmitButtons(btnValidee);
        btnValidee.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent evt) {
                
//                if (!v.isValid()) {
//                    String msg = v.getErrorMessageUIID();
//                    Dialog.show("ERROR", msg, new Command("OK"));
//                } 
//                
//                else {
                    try {
                        Reclamation a = new Reclamation(tfnom.getText(), tfprenom.getText(), tfmail.getText(), tftype.getSelectedItem().toString(), tfdescription.getText(), "En Cours");

                        if (ServiceReclamation.getInstance().addReclamation(a)) {

                            Dialog.show("Success", "Votre reclamation a été ajouter avec succès !", new Command("OK"));
                            val.showBack();
                        } else {
                            Dialog.show("ERROR", "Erreur au moment de l'ajout", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            //}
        });

        val.addAll(tfnom, tfprenom, tfmail, tftype, tfdescription, btnValidee, notice);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> val.showBack()); // Revenir vers l'interface précédente
    }
    
    
    
    
    
 
}
