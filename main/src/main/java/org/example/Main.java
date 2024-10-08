package org.example;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.valkryst.VTerminal.plaf.VTerminalLookAndFeel;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
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

            USE THIS FOR DATA????
            https://www.prepscholar.com/sat/s/colleges/University-of-Washington-admission-requirements
            Legal? possibly
            might get blocked by them tho.
            Perchance???

            Notes:
            CIPTFBS1 tuition 2023
            UG enrollment actual.


            SAT/ACT test optianl scores
            https://fairtest.org/test-optional-list/

            A thing that needs webscraping for SAT/ACT scores
            https://www.compassprep.com/college-profiles

            or

            For student population add some sort of toggle between grad and undergrad

            https://www.ivywise.com/blog/colleges-going-test-optional/
            https://docs.google.com/spreadsheets/u/0/d/e/2PACX-1vTFLFL8qntn-YuMOV4g0ul7SG07LyH33gUUFnFJuM-0obWmJcteVimY3k5dYqaz9fEsguC7kaf_6E5U/pubhtml/sheet?headers=false&gid=0
            Updated March 2024.

            Url scraper notes:
            use https://stackoverflow.com/questions/18134718/java-quickest-way-to-check-if-url-exists
            To check and see if link exists. Should be very easy. maybe only check it for one link
            EX: student life, and then have it break for a certain amount of time, so its not a cyber attack.
         */
        FlatLightLaf.setup();



        //add the manger
        try {
            UIManager UIManager = new UIManager();
            //UIManager.setLookAndFeel( VTerminalLookAndFeel.getInstance(12));

            //Terminal

            //UIManager.setLookAndFeel(new SynthLookAndFeel());
            //broken

            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            //cute

            //UIManager.setLookAndFeel(new MetalLookAndFeel());

            //UIManager.setLookAndFeel(new NimbusLookAndFeel());
            //funny

        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        MainGUI.displayPages();
    }
}