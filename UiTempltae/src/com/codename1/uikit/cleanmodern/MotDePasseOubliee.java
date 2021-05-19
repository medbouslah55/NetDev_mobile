/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
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
import com.netdev.mindspace.entites.User;
import com.netdev.mindspace.services.MembreService;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author hp
 */
public class MotDePasseOubliee extends BaseForm {
       int randomNum=ThreadLocalRandom.current().nextInt(100000,999999);
       TextField email;
    public MotDePasseOubliee(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("Logo1.png"), "LogoLabel"),
                        new Label("Awsome Thanks!", "LogoLabel")
                )
        );
      
        email = new TextField("", "Enter Email");
        email.setSingleLineTextArea(false);
     
        
           TextField code = new TextField("", "Enter code");
        code.setSingleLineTextArea(false);
     
       
        
       
           Button valider = new Button("valider");
              Button change = new Button("change");
           
               TextField pass = new TextField("", "Enter new password");
             pass.setSingleLineTextArea(false);
             pass.setVisible(true);
        Button resend = new Button("Resend");
        resend.setUIID("CenterLink");
        
        Label alreadHaveAnAccount = new Label("Already have an account?");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("CenterLink");
         ;
         Button postuler = new Button("Apply");
        postuler.addActionListener(l -> {
            sendMail(res);
            //  System.out.println(in.getLibelle());
            System.out.println("sent mail !");
          
            
        });
          change.setEnabled(false);
         valider.addActionListener(l -> {
         
         if ((int)Float.parseFloat(code.getText()) == randomNum)
         {
                
        
             pass.setVisible(true);
            change.setEnabled(true);
             System.out.print("yessssssssss");
                 Dialog.show("succes", "success code", "ok",null);
                 change.addActionListener(e -> {
                     User u = MembreService.getInstance().getUser(email.getText());
                      MembreService.getInstance().updatePassword(u.getCin(),pass.getText());
            Dialog.show("succes", "password changed successfuly", "ok",null);
                 });
                 
         }
         else{
              pass.setVisible(false);
              change.setVisible(false);
               change.setEnabled(false);
                  Dialog.show("failed", "not the right code", "ok",null);
                          System.out.print("nooo");

             
         }
            
        });
       
        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                createLineSeparator(),
                new SpanLabel("We've sent the password  to your email. Please check your inbox", "CenterLabel"),
                resend,
                 createLineSeparator(),
             
                postuler,
                 new FloatingHint(code),
                createLineSeparator(),
                valider,
                pass,
                change,
               
                  
                  
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
      
        
    }

    
    //send email
    public void sendMail(Resources res) {
        try {
            Properties props = new Properties();
               props.put("mail.transport.protocol", "smtp"); //SMTP protocol
               props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
               props.put("mail.smtps.auth", "true"); //enable authentication

            Session session = Session.getInstance(props, null);
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("Renitialisation du mot de passe <monEmail@domain.com>"));
            msg.setRecipients(javax.mail.Message.RecipientType.TO, email.getText().toString());
            msg.setSubject("Mindspace : recuperation du mot de passe ");
            msg.setSentDate(new Date(System.currentTimeMillis()));

            //String mp = MembreService.getInstance().getPasswordByEmail(email.getText().toString(), res);
            String txt = "Bienvenue sur MindSpace : Voici votre mot de passe : "+randomNum;

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
