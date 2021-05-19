/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mindspace.gui.cyrine;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.netdev.mindspace.entites.Activite;
import com.netdev.mindspace.entites.Centre;
import com.netdev.mindspace.services.ServiceActivite;
import com.netdev.mindspace.services.ServiceCentre;
import com.codename1.uikit.cleanmodern.BaseForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ShowAct extends BaseForm {

    public ShowAct(Resources res, int id, Centre k) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Informations centre");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });
        Image img = res.getImage("activite.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        add(LayeredLayout.encloseIn(
                sl
        ));
        setTitle("Informations activité");

        //btn retour
        Button showBubble = new Button("↩");
        showBubble.setName("BubbleButton");
        Style buttonStyle = showBubble.getAllStyles();
        buttonStyle.setBorder(Border.createEmpty());
        buttonStyle.setFgColor(0xf2f1ef);
        buttonStyle.setBgPainter((g, rect) -> {
            g.setColor(0xd5b8ff);
            int actualWidth = rect.getWidth();
            int actualHeight = rect.getHeight();
            int xPos, yPos;
            int size;
            if (actualWidth > actualHeight) {
                yPos = rect.getY();
                xPos = rect.getX() + (actualWidth - actualHeight) + 25;
                size = actualHeight;
            } else {
                yPos = rect.getY() + (actualHeight - actualWidth);
                xPos = rect.getX();
                size = actualWidth;
            }
            g.setAntiAliased(true);
            g.fillArc(xPos, yPos, size, size, 0, 360);
        });
        showBubble.addActionListener(e -> new ListActivite(res, k.getId_centre(), k).show());
        add(showBubble);

        //afficher info 
        Activite ac = ServiceActivite.getInstance().getActByid(id);
        setTitle("Infos activité : " + ac.getNom_act());
        /* TextField email = new TextField(Integer.toString(k.getId_centre()), "id_centre", 20, TextField.EMAILADDR);
         email.setEditable(false);
        email.setUIID("TextFieldBlack");
        addStringValue("id_centre", email);*/
        TextField nom = new TextField(ac.getNom_act(), "Nom activité", 20, TextField.EMAILADDR);
        nom.setEditable(false);
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom activité", nom);

        TextField cat = new TextField(ac.getCategorie_act(), "Catégorie activité", 20, TextField.EMAILADDR);
        cat.setEditable(false);
        cat.setUIID("TextFieldBlack");
        addStringValue("Adresse activité", cat);

        TextField dt = new TextField(ac.getDate_act(), "Date activité", 20, TextField.EMAILADDR);
        dt.setEditable(false);
        dt.setUIID("TextFieldBlack");
        addStringValue("Date activité", dt);

        TextField cap = new TextField(Integer.toString(ac.getCapacite()), "Capacité activité", 20, TextField.EMAILADDR);
        cap.setEditable(false);
        cap.setUIID("TextFieldBlack");
        addStringValue("Capacité activité", cap);

        TextField pr = new TextField(Double.toString(ac.getPrix_reservation()), "prix activité", 20, TextField.EMAILADDR);
        pr.setEditable(false);
        pr.setUIID("TextFieldBlack");
        addStringValue("prix activité", pr);

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
