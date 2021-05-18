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

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.netdev.mindspace.services.MembreService;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {
        TextField email;
    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField cin = new TextField("", "cin", 8, TextField.ANY);
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
        TextField sexe = new TextField("", "Sexe", 20, TextField.ANY);
        TextField taille = new TextField("", "Taille", 20, TextField.ANY);
        TextField poids = new TextField("", "Poids", 20, TextField.ANY);
                   email = new TextField("", "Email", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        TextField telephone = new TextField("", "telephone", 20, TextField.ANY);

        cin.setSingleLineTextArea(false);
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        sexe.setSingleLineTextArea(false);
        taille.setSingleLineTextArea(false);
        poids.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        telephone.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                
                new FloatingHint(cin),
                createLineSeparator(),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(sexe),
                createLineSeparator(),
                new FloatingHint(taille),
                createLineSeparator(),
                new FloatingHint(poids),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                new FloatingHint(telephone),
                createLineSeparator()
                
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e)-> {
            MembreService.getInstance().signup(cin, nom, prenom, sexe, taille, poids, email, password, telephone, res);
            sendMail(res);
            Dialog.show("succes", "maintenant vous etes Inscri", "ok",null);
            new SignInForm(res).show();
        });
        
    }
    
    public void sendMail(Resources res) {
        try {
            Properties props = new Properties();
               props.put("mail.transport.protocol", "smtp"); //SMTP protocol
               props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
               props.put("mail.smtps.auth", "true"); //enable authentication

            Session session = Session.getInstance(props, null);
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("MindSpace <monEmail@domain.com>"));
            msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
            msg.setSubject("Bienvenu chez MindSpace ");
            msg.setSentDate(new Date(System.currentTimeMillis()));

            
            String txt = "Bienvenue sur MindSpave : Votre compte a été cree avec succès ";

            msg.setText(txt);

            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");

            st.connect("smtp.gmail.com", 465, "cite.de.la.culturec@gmail.com", "21039010");
            st.sendMessage(msg, msg.getAllRecipients());

            System.out.println("server response" + st.getLastServerResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
