/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reservation;
import com.netdev.mindspace.services.ServiceReservation;

import java.util.ArrayList;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class ListeReservation extends BaseForm {
    ArrayList<Reservation> data = new ArrayList<>();
    Container all = new Container(new BoxLayout(BoxLayout.Y_AXIS));
 
    
    
//   public void ListedesReservation() {
//        setTitle("Listes  Des Reservations");
//        data = ServiceReservation.getInstance().getAllReservations();
//        //Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//
//        for (int i = 0; i < data.size(); i++) {
//            Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
//            Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
//            Reservation u = new Reservation();
//            u.setId_reservation(data.get(i).getId_reservation());
//            u.setNom(data.get(i).getNom());
//            u.setPrenom(data.get(i).getPrenom());
//            u.setDate_act(data.get(i).getDate_act());
//            u.setNb_place(data.get(i).getNb_place());
//            Label separation = new Label("----------------------------");
//            Label nom = new Label("Nom : " + data.get(i).getNom());
//            nom.setUIID("Label");
//            Label prenom = new Label("Prenom : " + data.get(i).getPrenom());
//            prenom.setUIID("Label");
//            Label dateAct = new Label("Date Activite : " + data.get(i).getDate_act());
//            dateAct.setUIID("Label");
//            Label nbPlace = new Label("Nbr Place : " + data.get(i).getNb_place());
//            nbPlace.setUIID("Label");
//            Button modif = new Button(FontImage.MATERIAL_EDIT);
//            Button supp = new Button(FontImage.MATERIAL_DELETE);
//            
//            
//            
//           // modif.addActionListener(e -> new ModifierReservation(u).show());
//            supp.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    ServiceReservation.getInstance().deleteReservation(u);
//                    Dialog.show("Success", "Voulez vous supprimer cette reservation ?", "OK", "Cancel");
//                    refreshTheme();
//                }
//            });
//
//            x.addAll(nom, prenom);
//            xx.addAll(supp, modif);
//            //y.addAll(x, dateAct, nbPlace, xx, separation);
//            all.addAll(x, dateAct, nbPlace, xx, separation);
//
//        }
//    }
   
   
   
    public ListeReservation(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Passer Une Réclamation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        int nb = ServiceReservation.getInstance().getAllReservations().size();
        Label NB = new Label(nb+"  Reservations");
        
        //Label facebook = new Label("786 followers", FontImage.MATERIAL_DELETE, "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        NB.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                NB,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                                twitter
                        )
                )
        ));
        setTitle("Listes  Des Reservations");
        data = ServiceReservation.getInstance().getAllReservations();
        //Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (int i = 0; i < data.size(); i++) {
            Container x = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Reservation u = new Reservation();
            u.setId_reservation(data.get(i).getId_reservation());
            u.setNom(data.get(i).getNom());
            u.setPrenom(data.get(i).getPrenom());
            u.setDate_act(data.get(i).getDate_act());
            u.setNb_place(data.get(i).getNb_place());
            Label separation = new Label("----------------------------");
            Label nom = new Label("Nom : " + data.get(i).getNom());
            nom.setUIID("Label");
            Label prenom = new Label("Prenom : " + data.get(i).getPrenom());
            prenom.setUIID("Label");
            Label dateAct = new Label("Date Activite : " + data.get(i).getDate_act());
            dateAct.setUIID("Label");
            Label nbPlace = new Label("Nbr Place : " + data.get(i).getNb_place());
            nbPlace.setUIID("Label");
            Button modif = new Button(FontImage.MATERIAL_EDIT);
            Button supp = new Button(FontImage.MATERIAL_DELETE);
            
            
            
            modif.addActionListener(e -> new ModifierReservation(res,u).show());
            supp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceReservation.getInstance().deleteReservation(u);
                    Dialog.show("Success", "Voulez vous supprimer cette reservation ?", "OK", "Cancel");
                    refreshTheme();
                }
            });

            x.addAll(nom, prenom);
            xx.addAll(supp, modif);
            //y.addAll(x, dateAct, nbPlace, xx, separation);
            addAll(x, dateAct, nbPlace, xx, separation);
       
}
    }
}
