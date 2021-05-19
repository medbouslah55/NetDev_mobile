/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mindspace.gui.cyrine;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.BubbleTransition;
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
public class ListCentre extends BaseForm {

    public ListCentre(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Informations centre");
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

        ///affichage des centres 
        setTitle("Liste des centre");
        List<Centre> L = ServiceCentre.getInstance().getAllCentres();
        List<Button> bt = new ArrayList<Button>();
        for (int i = 0; i < L.size(); i++) {
            Button b1 = new Button(L.get(i).getNom_centre() + "                                                ");
            bt.add(b1);
            int id = L.get(i).getId_centre();
            add(b1);
            b1.addActionListener(e -> new showCentreInfo(res, id).show());
        }
        //  add((Component) bt);
        /*  SpanLabel sp = new SpanLabel();
        sp.setText(ServiceCentre.getInstance().getAllCentres().toString());
        add(sp);*/
 /*   try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
        //do things with exception
    }
     /*      LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
    
    }*/

        Button showBubble = new Button("+");
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
                xPos = rect.getX() + (actualWidth - actualHeight) / 2;
                size = actualHeight;
            } else {
                yPos = rect.getY() + (actualHeight - actualWidth) / 2;
                xPos = rect.getX();
                size = actualWidth;
            }
            g.setAntiAliased(true);
            g.fillArc(xPos, yPos, size, size, 0, 360);
        });
        add(showBubble);
        setTintColor(0);
        showBubble.addActionListener((e) -> {
            Dialog dlg = new Dialog("Aide");
            dlg.setLayout(new BorderLayout());
            SpanLabel s2 = new SpanLabel("Vous trouvez à ce niveau nos centres ainsi que leurs activités", "DialogBody");
            s2.getTextUnselectedStyle().setFgColor(0x913d88);
            dlg.add(BorderLayout.CENTER, s2);
            dlg.setTransitionInAnimator(new BubbleTransition(500, "BubbleButton"));
            dlg.setTransitionOutAnimator(new BubbleTransition(500, "BubbleButton"));
            dlg.setDisposeWhenPointerOutOfBounds(true);
            dlg.getTitleStyle().setFgColor(0x913d88);

            Style dlgStyle = dlg.getDialogStyle();
            dlgStyle.setBorder(Border.createEmpty());
            dlgStyle.setBgColor(0xfde3a7);
            dlgStyle.setBgTransparency(0xff);
            dlg.showPacked(BorderLayout.NORTH, true);
        });

    }
}
