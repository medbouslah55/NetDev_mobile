/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Reclamation;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author Firas
 */
public class modifierReclamationForm extends Form {

    public modifierReclamationForm(Reclamation ab) {
        Form previous = null;
        //ObservableList<String> listeTrie = FXCollections.observableArrayList("Tout", "Titre", "Prix", "Type");

        setTitle("Modifier Reclamation");
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        TextField tfnom = new TextField(ab.getNom_rec(), "Nom");
        TextField tfprenom = new TextField(ab.getPrenom_rec(), "Prénom");
        TextField tfmail = new TextField(ab.getEmail_rec(), "E-mail");
        TextField tftype = new TextField(ab.getType_rec(), "Type");
        ComboBox etat = new ComboBox("En cours", "En_Traitemet", "Traiter_Avec_succes");
        //TextField tfetat = new TextField(ab.getEtat_rec(), "Etat");
        TextField tfdescription = new TextField(ab.getDescription_rec(), "Description");
        Button btnValidee = new Button("Enregistrer");

        //etat.getSelectedItem().toString()
        btnValidee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Reclamation a = new Reclamation(ab.getId_rec(), tfnom.getText(), tfprenom.getText(), tfmail.getText(), tftype.getText(), tfdescription.getText(),etat.getSelectedItem().toString());
                if (ServiceReclamation.getInstance().ChangerEtatReclamation(a)) {
                    Dialog.show("Success", "Votre Reclamation a été modifier avec succès !", new Command("OK"));
                } else {
                    Dialog.show("ERROR", "Erreur au moment de la modification", new Command("OK"));
                }
            }
        });
        y.addAll(tfnom, tfprenom, tfmail, tftype, tfdescription, etat, btnValidee);
        addAll(y);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                 e -> previous.showBack());
    }
}
