/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mindspace.gui.cyrine;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
public class ListActivite extends BaseForm {

    public ListActivite(Resources res, int id, Centre k) {
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

        setTitle("Liste des activités");

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
        showBubble.addActionListener(e -> new showCentreInfo(res, id).show());
        add(showBubble);

        //affichage act
        List<Activite> L = new ArrayList<Activite>();
        L = ServiceActivite.getInstance().getActByCentre(id);

        Map<Integer, Object> map = new HashMap<>();
        int j = 0;
        for (Object i : L) {
            j++;
            map.put(j, i);
        }

        List<Activite> L2 = new ArrayList<Activite>();

        for (Map.Entry me : map.entrySet()) {
            Map<String, Object> o = (Map<String, Object>) me.getValue();

            Activite tac = new Activite();
            for (Map.Entry hh : o.entrySet()) {

                tac.setId_act((int) Float.parseFloat(o.get("idAct").toString()));

                tac.setNom_act((String) o.get("nomAct"));
                if (!L2.contains(tac)) {
                    L2.add(tac);
                }
                System.out.println("id====");
                System.out.println(tac);
            }

        }

        List<Button> bt = new ArrayList<Button>();
        for (int i = 0; i < L2.size(); i++) {
            Button b1 = new Button(L2.get(i).getNom_act() + "                                                ");
            bt.add(b1);
            int iid = L2.get(i).getId_act();

            add(b1);
            b1.addActionListener(e -> new ShowAct(res, iid, k).show());

            /*  SpanLabel sp = new SpanLabel();
        sp.setText(L2.get(i).getNom_act());
        add(sp);*/
        }

        //affichage des acts
        Button showBubbles = new Button("+");
        showBubbles.setName("BubbleButton");
        Style buttonStyles = showBubbles.getAllStyles();
        buttonStyles.setBorder(Border.createEmpty());
        buttonStyles.setFgColor(0xf2f1ef);
        buttonStyles.setBgPainter((g, rect) -> {
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
        add(showBubbles);
        setTintColor(0);
        showBubbles.addActionListener((e) -> {
            Dialog dlg = new Dialog("Aide");
            dlg.setLayout(new BorderLayout());
            SpanLabel s2 = new SpanLabel("Vous trouvez à ce niveau les activités du centre " + k.getNom_centre(), "DialogBody");
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
