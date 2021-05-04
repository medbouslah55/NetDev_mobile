/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Abonnement;

import com.mycompany.myapp.gui.Abonnement.ModifierAbonnementForm;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Abonnement;
import com.mycompany.myapp.services.ServiceAbonnement;
import java.util.ArrayList;

/**
 *
 * @author Firas
 */
public class ListedesabonnementsForm extends Form{
Form current;
ArrayList<Abonnement> data = new ArrayList<>();
ArrayList<Abonnement> datatrier = new ArrayList<>();
Container all = new Container(new BoxLayout(BoxLayout.Y_AXIS));
Container tri = new Container(new BoxLayout(BoxLayout.Y_AXIS));



 public void findAbonnement(String s,boolean show){
     ArrayList<Abonnement> data = ServiceAbonnement.getInstance().getAllabonnements();
     ArrayList<Abonnement> res = new ArrayList<>();
     for (int i=0;i<data.size();i++){
            if(s.contains(data.get(i).getTitre_ab()))
                res.add(data.get(i));
            }
     System.out.println(res);
    for (int i=0;i<res.size();i++){
        Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Abonnement ab = new Abonnement();
        ab.setId_ab(res.get(i).getId_ab());
        ab.setTitre_ab(res.get(i).getTitre_ab());
        ab.setType_ab(res.get(i).getType_ab());
        ab.setPrix_ab(res.get(i).getPrix_ab());
        ab.setDesc_ab(res.get(i).getDesc_ab());
        Label separation = new Label("----------------------------");
        Label titre = new Label("Titre : " + res.get(i).getTitre_ab());
        Label type = new Label("Type : "+ res.get(i).getType_ab());
        Label prix = new Label("Prix : "+ res.get(i).getPrix_ab());
        Label des = new Label("Description : "+ res.get(i).getDesc_ab());
        Button modif = new Button("Modifier");
        Button supp = new Button("Supprimer");
        modif.addActionListener(e -> new ModifierAbonnementForm(ab).show()); 
        supp.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt) {
            ServiceAbonnement.getInstance().deleteAbonnement(ab);
            Dialog.show("Success", "Memory Deleted Successfully.", "OK", "Cancel");
        }
        });
        x.addAll(titre,type,prix);
        xx.addAll(supp,modif);
        tri.addAll(x,des,xx,separation);
    }
     res.clear();
     if (show==true)
        addAll(tri);
    else
        tri.removeAll();
        }
 
 private void listeab(boolean show){
    data = ServiceAbonnement.getInstance().getAllabonnements();
    for (int i=0;i<data.size();i++){
        Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Abonnement ab = new Abonnement();
        ab.setId_ab(data.get(i).getId_ab());
        ab.setTitre_ab(data.get(i).getTitre_ab());
        ab.setType_ab(data.get(i).getType_ab());
        ab.setPrix_ab(data.get(i).getPrix_ab());
        ab.setDesc_ab(data.get(i).getDesc_ab());
        Label separation = new Label("----------------------------");
        Label titre = new Label("Titre : " + data.get(i).getTitre_ab());
        Label type = new Label("Type : "+ data.get(i).getType_ab());
        Label prix = new Label("Prix : "+ data.get(i).getPrix_ab());
        Label des = new Label("Description : "+ data.get(i).getDesc_ab());
        Button modif = new Button(FontImage.MATERIAL_EDIT);
        Button supp = new Button(FontImage.MATERIAL_DELETE);
        modif.addActionListener(e -> new ModifierAbonnementForm(ab).show()); 
        supp.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt) {
            ServiceAbonnement.getInstance().deleteAbonnement(ab);
            Dialog.show("Success", "Memory Deleted Successfully.", "OK", "Cancel");
        }
        });
        x.addAll(titre,type,prix);
        xx.addAll(supp,modif);
        all.addAll(x,des,xx,separation);
    }
    if (show==true)
        addAll(all);
    else
        all.removeAll();    
 }
public ListedesabonnementsForm(Form previous) {
    
    setTitle("Listes  Des Abonnements");

    TextField Chercher = new TextField("","Chercher");
    Button refresh = new Button("Actualiser");

    
    
    
    addAll(refresh,Chercher);
    listeab(true);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack());
    
    
    
    
    
    
    
    
    
    Chercher.addActionListener(new ActionListener(){
            @Override
        public void actionPerformed(ActionEvent evt) {
//            all.setHidden(true);
//            all.getParent().animateLayout(0);
//            findAbonnement(Chercher.getText());
//            tri.setHidden(false);
//            tri.getParent().animateLayout(0);
//addAll(refresh,Chercher,all,tri);
listeab(false);
findAbonnement(Chercher.getText(),true);
        }
    });
    refresh.addActionListener(new ActionListener(){
            @Override
        public void actionPerformed(ActionEvent evt) {
//            tri.setHidden(true);
//            tri.getParent().animateLayout(0);
//            listeab();
//            all.setHidden(false);
//            all.getParent().animateLayout(0);
  //remove();
  findAbonnement("",false);
  listeab(true);
//removeAllCommands();
//removeAllShowListeners();

              

        }
    });
}
}