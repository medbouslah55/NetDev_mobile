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
import com.mycompany.myapp.entities.Reclamation;
import com.netdev.mindspace.services.ServiceReclamation;

import java.util.ArrayList;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class ListeReclamation extends BaseForm {
    ArrayList<Reclamation> data = new ArrayList<>();
    Container all = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    
    
     public void listedesreclamations() {
        data = ServiceReclamation.getInstance().getAllReclamation();
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
            nom.setUIID("Label");
            Label prenom = new Label("Prenom : " + data.get(i).getPrenom_rec());
            prenom.setUIID("Label");
            Label mail = new Label("E-mail : " + data.get(i).getEmail_rec());
            mail.setUIID("Label");
            Label type = new Label("Type : " + data.get(i).getType_rec());
            type.setUIID("Label");
            String d1 = data.get(i).getDate_rec().toString().substring(0, 10);
            Label date = new Label("Date : " + d1);
            date.setUIID("Label");
            Label description = new Label("Description : " + data.get(i).getDescription_rec());
            description.setUIID("Label");
            Label etat = new Label("Etat : " + data.get(i).getEtat_rec());
            etat.setUIID("Label");
            Button supp = new Button(FontImage.MATERIAL_DELETE);
            x.addAll(nom, prenom);
            xx.addAll(supp);
            all.addAll(x, mail, type, description, date, etat, xx, separation);
            supp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceReclamation.getInstance().deleteReclamation(ab);
                    Dialog.show("Success", "Memory Deleted Successfully.", "OK", "Cancel");
                    refreshTheme();
                }
            });
        }

    }
    public ListeReclamation(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste Des Réclamations");
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
        int nb = ServiceReclamation.getInstance().getAllReclamation().size();
        Label NB = new Label(nb+"  Réclamations");
        
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
        listedesreclamations();
        add(all);
}
}
