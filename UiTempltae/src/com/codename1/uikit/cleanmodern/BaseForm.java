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
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mindspace.gui.cyrine.ListCentre;
import com.mindspace.gui.mehdi.AfficherListeEvenement;
import com.mindspace.gui.mehdi.AfficherListeStatus;
import com.netdev.mindspace.gui.AfficherListRegime;
import com.netdev.mindspace.gui.SettingsForm;
import com.netdev.mindspace.utils.Session;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
        ));
        
        tb.addMaterialCommandToSideMenu("Newsfeed", FontImage.MATERIAL_UPDATE, e -> new NewsfeedForm(res).show());
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res).show());
        tb.addMaterialCommandToSideMenu("Regimes", FontImage.MATERIAL_RESTAURANT_MENU, e -> new AfficherListRegime(res).show());
        tb.addMaterialCommandToSideMenu("Son", FontImage.MATERIAL_MUSIC_OFF, e-> new SettingsForm(res).show());
        tb.addMaterialCommandToSideMenu("Centre", FontImage.MATERIAL_HOME_WORK, e -> new ListCentre(res).show());
        tb.addMaterialCommandToSideMenu("Evenement", FontImage.MATERIAL_EXIT_TO_APP, e -> new AfficherListeEvenement(res).show());
        tb.addMaterialCommandToSideMenu("Status", FontImage.MATERIAL_EXIT_TO_APP, e -> new AfficherListeStatus(res).show());
        tb.addMaterialCommandToSideMenu("Passer Une R??clamation", FontImage.MATERIAL_RATE_REVIEW, e -> new PasserReclamation(res).show());
        tb.addMaterialCommandToSideMenu("Liste Des R??clamations", FontImage.MATERIAL_LIST, e -> new ListeReclamation(res).show());
        tb.addMaterialCommandToSideMenu("Passer Une Reservation", FontImage.MATERIAL_BOOKMARK, e -> new PasserReservation(res).show());
        tb.addMaterialCommandToSideMenu("Listes  Des Reservations", FontImage.MATERIAL_LIST, e -> new ListeReservation(res).show());

        tb.addMaterialCommandToSideMenu("Liste Des Abonnements", FontImage.MATERIAL_LIST, e -> new ListeDesAbonnements(res).show());
        tb.addMaterialCommandToSideMenu("Statistiques", FontImage.MATERIAL_PIE_CHART, e -> new Statistique(res).show());
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, (e) -> { 
            Session.getSession().clearSession();
            Form signIn = new SignInForm(res);
            signIn.show();
        });
    }
}
