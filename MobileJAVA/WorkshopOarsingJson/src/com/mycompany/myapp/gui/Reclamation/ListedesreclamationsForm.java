/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Reclamation;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class ListedesreclamationsForm extends Form {

    ArrayList<Reclamation> data = new ArrayList<>();
    Container all = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public void listedesreclamations() {
        data = ServiceReclamation.getInstance().getAllReclamation();
        System.out.println(data);
        for (int i = 0; i < data.size(); i++) {
            Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Reclamation ab = new Reclamation();
            ab.setId_rec(data.get(i).getId_rec());
            ab.setNom_rec(data.get(i).getNom_rec());
            ab.setPrenom_rec(data.get(i).getPrenom_rec());
            ab.setEmail_rec(data.get(i).getEmail_rec());
            ab.setType_rec(data.get(i).getType_rec());
            ab.setDescription_rec(data.get(i).getDescription_rec());
            ab.setEtat_rec(data.get(i).getEtat_rec());
            Label separation = new Label("----------------------------");
            Label nom = new Label("Nom : " + data.get(i).getNom_rec());
            Label prenom = new Label("Prenom : " + data.get(i).getPrenom_rec());
            Label mail = new Label("Emial : " + data.get(i).getEmail_rec());
            Label type = new Label("Type : " + data.get(i).getType_rec());
            String d1 = data.get(i).getDate_rec().toString().substring(0, 10);
            Label date = new Label("Date : " + d1);
            Label description = new Label("Description : " + data.get(i).getDescription_rec());
            Label etat = new Label("Etat : " + data.get(i).getEtat_rec());
            Button valider = new Button(FontImage.MATERIAL_UPDATE);
            Button supp = new Button(FontImage.MATERIAL_DELETE);
            x.addAll(nom, prenom, mail, type, etat);
            xx.addAll(valider, supp);
            all.addAll(x, date, description, xx, separation);
            
            supp.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    ServiceReclamation.getInstance().deleteReclamation(ab);
                    Dialog.show("Success", "Memory Deleted Successfully.", "OK", "Cancel");
                }
            });
        }

    }

    public ListedesreclamationsForm(Form previous) {
        setTitle("Listes  Des Reclamations");
        listedesreclamations();
        addAll(all);
        getContentPane().addPullToRefresh(new Runnable() {
                        @Override
                        public void run() {
                            
                            listedesreclamations();
                            //addAll(all);
                        }
                    });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                 e -> previous.showBack());
    }

}
