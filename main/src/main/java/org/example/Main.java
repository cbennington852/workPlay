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

            3409
         */
        FlatLightLaf.setup();

        //add the manger
        try {
            UIManager UIManager = new UIManager();
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        MainGUI.displayPages();
    }
}