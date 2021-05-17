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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author Firas
 */
public class ajouterReclamationForm extends Form {

    public ajouterReclamationForm(Form previous) {
        Validator validator = new Validator();
        setTitle("Ajouter Nouveau Reclamation");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField("", "Nom");
        TextField tfprenom = new TextField("", "Prénom");
        TextField tfmail = new TextField("", "E-mail");
        
        ComboBox tftype = new ComboBox("Type De Probléme","Probleme technique", "Probleme de connexion", "Probleme Au Niveau De Payemant", "Probleme Au Niveau De Reservation");
        //TextField tftype = new TextField("", "Type");
        TextField tfdescription = new TextField("", "Description");
        Button btnValidee = new Button("Enregistrer");
        
        validator.addConstraint(tfmail, RegexConstraint.validEmail());
        btnValidee.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom.getText().length() == 0) || (tfprenom.getText().length() == 0)) {
                    Dialog.show("Alert", "Remplir Tous Les Champs SVP !", new Command("OK"));
               
                } else if (!validator.isValid())
                    Dialog.show("Alert", "Vérifier Votre E-mail SVP !", new Command("OK"));
                else {
                    try {
                        Reclamation a = new Reclamation(tfnom.getText(), tfprenom.getText(), tfmail.getText(), tftype.getSelectedItem().toString(), tfdescription.getText(), "En Cours");
                        if (ServiceReclamation.getInstance().addReclamation(a)) {
                            Dialog.show("Success", "Votre reclamation a été ajouter avec succès !", new Command("OK"));
                            previous.showBack();
                        } else {
                            Dialog.show("ERROR", "Erreur au moment de l'ajout", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfnom, tfprenom, tfmail, tftype, tfdescription, btnValidee);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente
    }
}
