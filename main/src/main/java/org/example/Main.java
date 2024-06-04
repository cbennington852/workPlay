package org.example;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        /*
            Testing List
            UW
            EWU
            Gonzaga University
            WSU

            Notes:
            CIPTFBS1 tuition 2023
            UG enrollment actual.


            Url scraper notes:
            use https://stackoverflow.com/questions/18134718/java-quickest-way-to-check-if-url-exists
            To check and see if link exists. Should be very easy. maybe only check it for one link
            EX: student life, and then have it break for a certain amount of time, so its not a cyber attack.

            3409
         */
        FlatLightLaf.setup();

        //add the manger
        try {
            UIManager UIManager = new UIManager();
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        MainGUI.displayPages();
    }
}