package com.codename1.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.entities.Reservation;

import com.codename1.services.ServiceReservation;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Firas
 */
public class ModifierReservationForm extends Form {

    String date,rowindex,price;
    public ModifierReservationForm(Reservation ab) {
        Form previous = null;
        setTitle("Modifier Reservation");
        Container y = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        TextField tfnom = new TextField(ab.getNom(), "nom");
        TextField tfprenom = new TextField(ab.getPrenom(), "prenom");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);

        datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                date = datePicker.getText();
            }
        });
        TextField tfnbrPlace = new TextField(String.valueOf(ab.getNb_place()), "nbPlace");
        
        ComboBox<Map<String, Object>> combo = new ComboBox<>(
                createListEntry("Zumba", "100"),
                createListEntry("Massage", "50"),
                createListEntry("Yoga", "50"),
                createListEntry("Meditation", "75"),
                createListEntry("Thalassotherapie", "100")
        );

        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
        
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                rowindex = combo.getSelectedItem().get("Line1").toString();
                price = combo.getSelectedItem().get("Line2").toString();
                System.out.println(rowindex);
            }
        });
        
        Button btnValider = new Button("Enregistrer");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                Reservation a = new Reservation(ab.getId_reservation(), tfnom.getText(), tfprenom.getText(), date, rowindex, Integer.parseInt(tfnbrPlace.getText()));
                if (ServiceReservation.getInstance().updateAbonnement(a)) {
                    Dialog.show("Success", "Votre reservation a été modifier avec succès !", new Command("OK"));
                    new HomeForm().show();
                } else {
                    Dialog.show("ERROR", "Erreur au moment de la modification", new Command("OK"));
                }
            }
        });

        y.addAll(tfnom, tfprenom, datePicker, tfnbrPlace,combo, btnValider);

        addAll(y);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack());

    }
     private Map<String, Object> createListEntry(String name, String date) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        return entry;
    }
    

}
