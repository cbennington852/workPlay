package org.example;

import org.apache.poi.ss.formula.atp.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {

    public static JFrame frame;

    CollegeList collegeList;

    public static final int NUM_GENERATOR_PANELS = 3;

    public static final Dimension DISPLAY_WINDOW_DIMENSTIONS = new Dimension(100, 500);


    public JPanel mainPanel()
    {
        JPanel cont = new JPanel(new BorderLayout());
        //West is the main college list
        cont.add(collegeListDisplayWindow(),BorderLayout.WEST);

        //East is the info panels for each person
        JPanel west = new JPanel(new GridLayout(1,NUM_GENERATOR_PANELS, 1,1));
        west.add(infoDisplayWindow("State", "getAllState"));
        west.add(infoDisplayWindow("City", "getAllCity"));
        west.add(infoDisplayWindow("Virtual Tour Links", "getAllVirtualTourLinks"));
        cont.add(west, BorderLayout.EAST);

        return cont;
    }

    public JPanel collegeListDisplayWindow()
    {
        JPanel cont = new JPanel(new BorderLayout());

        cont.add(new JLabel("College List"), BorderLayout.NORTH);



        return cont;
    }

    public JPanel infoDisplayWindow(String name, String methodCalled)
    {
        JPanel cont = new JPanel(new BorderLayout());
        cont.setPreferredSize(DISPLAY_WINDOW_DIMENSTIONS);

        //area label
        cont.add(new JLabel(name), BorderLayout.NORTH);

        //editable text area
        JTextArea area = new JTextArea();
        area.setPreferredSize(new Dimension(80,400));
        cont.add(area, BorderLayout.CENTER);

        //buttons to change text area.
        JButton generatorButton = new JButton("Generate " + name);
        generatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText(methodHandler(methodCalled));
            }
        });
        cont.add(generatorButton, BorderLayout.SOUTH);
        return cont;
    }

    private String methodHandler (String methodCalled)
    {
        switch(methodCalled) {
            case "getAllState":
                return   collegeList.getAllState();
            case "getAllCity":
                return  collegeList.getAllCity();
            case "getAllVirtualTourLinks":
                return  collegeList.getAllVirtualTourLinks();
            default:
                return  "";
        }
    }


    public static void displayPages() {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("College List Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.setSize(new Dimension(500,500));

        ///////////////////////////////////
        //Splash screen
        ///////////////////////////////////

        MainGUI mainGUI = new MainGUI();
        JPanel main = mainGUI.mainPanel();
        frame.setContentPane(main);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


}
