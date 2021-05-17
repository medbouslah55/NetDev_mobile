/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Abonnement;

import com.codename1.messaging.Message;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.services.ServiceAbonnement;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.NumericSpinner;

/**
 *
 * @author Firas
 */
public class ajouterAbonnementForm extends Form {
    
    public ajouterAbonnementForm(Form previous) {
        
        
        setTitle("Ajouter Nouveau Abonnement");
        setLayout(BoxLayout.y());

        TextField tfTitre = new TextField("", "Titre");
        TextField tfType = new TextField("", "Type");
        Label prix = new Label("Prix");
        NumericSpinner n1 = new NumericSpinner();
        n1.setMin(60);
        n1.setMax(220);
        n1.setStep(40);
        
        TextField tfPrix = new TextField("", "Prix");
        TextField tfDes = new TextField("", "Description");
        Button btnValider = new Button("Ajouter");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length() == 0) || (tfType.getText().length() == 0) || (tfPrix.getText().isEmpty())) {
                    Dialog.show("Alert", "Remplir Tous Les Champs SVP !", new Command("OK"));
                } else {
                    try {
                        //Abonnement a = new Abonnement(tfTitre.getText(), tfType.getText(), Float.parseFloat(), tfDes.getText());
                        Double d =n1.getValue();
                        Abonnement a = new Abonnement(tfTitre.getText(), tfType.getText(),d.floatValue() , tfDes.getText());
                        if (ServiceAbonnement.getInstance().addAbonnement(a)) {
                            Dialog.show("Success", "Votre abonnement a été ajouter avec succès !", new Command("OK"));
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

        addAll(tfTitre, n1,tfType, tfPrix, tfDes, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                 e -> previous.showBack()); // Revenir vers l'interface précédente
        
    }
    
    
    public void sendMail(){
        Message m = new Message("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        m.setMimeType(Message.MIME_HTML);
        boolean success = m.sendMessageViaCloudSync("fg7@students.kiron.ngo","firasgacha8@gmail.com", "firas", "Message Subject","Check out Codename One at https://www.codenameone.com/");
    }

}
