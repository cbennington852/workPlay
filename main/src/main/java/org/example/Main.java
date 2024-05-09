package org.example;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        /*
            Testing List
            UW
            EWU
            Gonzaga University
            WSU
         */

        FlatLightLaf.setup();
        try {
            UIManager UIManager = new UIManager();
            UIManager.setLookAndFeel( new FlatIntelliJLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        MainGUI.displayPages();
    }
}