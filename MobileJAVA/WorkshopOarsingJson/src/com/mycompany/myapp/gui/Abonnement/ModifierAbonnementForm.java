/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Abonnement;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.services.ServiceAbonnement;
/**
 *
 * @author Firas
 */
public class ModifierAbonnementForm extends Form{
    public ModifierAbonnementForm(Abonnement ab) {
        Form previous = null;
        setTitle("Modifier Abonnement");
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        TextField tfTitre = new TextField(ab.getTitre_ab(),"Titre");
        TextField tfType = new TextField(ab.getType_ab(),"Type");
        TextField tfPrix = new TextField(String.valueOf(ab.getPrix_ab()),"Prix");
        TextField tfDes = new TextField(ab.getDesc_ab(),"Description");
        Button btnValider = new Button("Enregistrer");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                        Abonnement a = new Abonnement(ab.getId_ab(),tfTitre.getText(), tfType.getText(), Float.parseFloat(tfPrix.getText()), tfDes.getText());
                        if( ServiceAbonnement.getInstance().updateAbonnement(a))
                        {
                        Dialog.show("Success","Votre abonnement a été modifier avec succès !",new Command("OK"));
                        }
                        else
                            Dialog.show("ERROR", "Erreur au moment de la modification", new Command("OK"));
                }
        });

        y.addAll(tfTitre,tfType,tfPrix,tfDes,btnValider);
        
        addAll(y);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> previous.showBack());
    
    }
    
}
