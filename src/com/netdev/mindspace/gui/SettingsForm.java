/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netdev.mindspace.gui;

import com.codename1.components.OnOffSwitch;
import com.codename1.components.Switch;
import com.codename1.media.MediaManager;
import com.codename1.ui.Command;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.netdev.mindspace.MyApplication;
import static com.netdev.mindspace.MyApplication.m;
import java.io.IOException;

/**
 *
 * @author Imen BenAbderrahmen
 */
public class SettingsForm extends Form {

    static Resources res;

    public SettingsForm(Form previous) {
        super("Réglages", new BorderLayout());
        this.res = MyApplication.theme;
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        Label hint = new Label("Arrêter ou démarrer la musique");
        hint.setUIID("SingUpLabel");
        Switch pause = new Switch();

        if (MyApplication.m != null) {
            pause.setOff();
            pause.setOn();
        } else {
            pause.setOff();
            pause.setOn();
        }

        pause.setValue(true);
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (MyApplication.m != null) {
                    if (pause.isValue() == true) {
                        m.play();
                    } else {
                        m.pause();
                    }
                } else {
                    try {
                        m = MediaManager.createMedia("file://C:\\Users\\trabe\\Desktop\\Pidev_mobile\\audio\\2.mp3", false);
                        if (m != null) {
                            m.play();
                        }

                    } catch (IOException ex) {

                    }
                }

            }
        });
        this.add(CENTER, hint);
        this.add(BOTTOM, pause);


    }

}
