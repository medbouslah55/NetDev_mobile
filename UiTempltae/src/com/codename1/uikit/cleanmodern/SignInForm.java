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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.netdev.mindspace.services.MembreService;
import com.netdev.mindspace.utils.Session;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    public SignInForm(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo1.png"), "LogoLabel"));
        
        TextField email = new TextField("", "Email", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        Button mp = new Button("mot de passe oublier","centerLabel1");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp),
                FlowLayout.encloseCenter(mp)
                
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        signIn.addActionListener((e)->{
           //MembreService.getInstance().signin(email, password, res);
           //pour tester mehdi
           String cnxResultat = MembreService.getInstance().connect(email, password);
           if (cnxResultat.compareTo("password") == 0)
           {
               Dialog.show("Erreur", "votre password est incorrect", "ok",null);
           }
           else if (cnxResultat.compareTo("false") == 0)
           {
               Dialog.show("Erreur", "votre Email ou password incorrect", "ok",null);
           }else if(cnxResultat.compareTo("true") == 0)
           {
               Dialog.show("succes", "tous va bien", "ok",null);
               Session.StartSession();
               Session.getSession().SetSessionUser(MembreService.getInstance().getUser(email.getText()));
               System.out.print(Session.getSession().getSessionUser());
               
               Form profil = new ProfileForm(res);
               profil.show();
           }
           
           
           
           
            
        });
        mp.addActionListener((e) -> {
            new ActivateForm(res).show();
        });
    }
    
}
