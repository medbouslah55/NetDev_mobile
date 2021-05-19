/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mindspace.gui.cyrine;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
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
import com.netdev.mindspace.entites.Centre;
import com.netdev.mindspace.services.ServiceCentre;
import com.codename1.uikit.cleanmodern.BaseForm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class showCentreInfo extends BaseForm {

    public showCentreInfo(Resources res, int id) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });
        Image img = res.getImage("cyrine.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        add(LayeredLayout.encloseIn(
                sl
        ));

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
        showBubble.addActionListener(e -> new ListCentre(res).show());
        add(showBubble);

        ///affichage info 
        // Centre L= ServiceCentre.getInstance().getCentreByID(id);
        /*  SpanLabel sp = new SpanLabel();
        sp.setText(ServiceCentre.getInstance().getCentreByID(id).toString());
        add(sp);*/
        Centre k = ServiceCentre.getInstance().getCentreByID(id);
        setTitle("Infos du centre : " + k.getNom_centre());
        /* TextField email = new TextField(Integer.toString(k.getId_centre()), "id_centre", 20, TextField.EMAILADDR);
         email.setEditable(false);
        email.setUIID("TextFieldBlack");
        addStringValue("id_centre", email);*/
        TextField nom = new TextField(k.getNom_centre(), "Nom centre", 20, TextField.EMAILADDR);
        nom.setEditable(false);
        nom.setUIID("TextFieldBlack");
        addStringValue("Nom centre", nom);

        TextField adr = new TextField(k.getAdr_centre(), "Adresse centre", 20, TextField.EMAILADDR);
        adr.setEditable(false);
        adr.setUIID("TextFieldBlack");
        addStringValue("Adresse centre", adr);

        TextField tel = new TextField(k.getTel_centre(), "Numéro téléphonique", 20, TextField.EMAILADDR);
        tel.setEditable(false);
        tel.setUIID("TextFieldBlack");
        addStringValue("Numéro téléphonique", tel);

        TextField email = new TextField(k.getMail_centre(), "Mail centre", 20, TextField.EMAILADDR);
        email.setEditable(false);
        email.setUIID("TextFieldBlack");
        addStringValue("Mail ", email);

        TextField type = new TextField(k.getType_centre(), "Type centre", 20, TextField.EMAILADDR);
        type.setEditable(false);
        type.setUIID("TextFieldBlack");
        addStringValue("Type  centre", type);

        Button b1 = new Button("              Activité associés                 ");
        if (k.isA_des_centres() == false) {
            b1.setEnabled(false);
        } else {
            b1.addActionListener(e -> new ListActivite(res, id, k).show());
        }
        add(b1);

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
