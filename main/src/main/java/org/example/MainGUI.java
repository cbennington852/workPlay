package org.example;

import org.apache.poi.ss.formula.atp.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class MainGUI {

    public static JFrame frame;

    CollegeList collegeList;

    JTextArea collegeListTextArea;

    public static final int NUM_GENERATOR_PANELS = 3;

    public static final Dimension DISPLAY_WINDOW_DIMENSTIONS = new Dimension(300, 500);
    public static final Dimension COLLEGE_LIST_WINDOW = new Dimension(500,80);

    public final String topTextbox = "Please enter college names " +
            "\n names can be aliases,  " +
            "\n EX: UW, WSU, SAPC" +
            "\n State names automatically interpreted as universities. " +
            "\n EX: Washington -> Washington State University";


    public JPanel mainPanel()
    {
        JPanel cont = new JPanel(new BorderLayout());
        //West is the main college list
        JPanel east = new JPanel(new BorderLayout());

        JTextArea typingtext = new JTextArea(topTextbox);
        typingtext.setEnabled(false);
        east.add(typingtext, BorderLayout.NORTH);
        east.add(collegeListDisplayWindow(), BorderLayout.CENTER);
        east.setPreferredSize(COLLEGE_LIST_WINDOW);

        cont.add(east,BorderLayout.WEST);
        collegeList = new CollegeList();

        //East is the info panels for each person
        JPanel west = new JPanel();
        JTabbedPane generatorInfoPanels = new JTabbedPane();
        generatorInfoPanels.addTab("State",infoDisplayWindow("State", "getAllState"));
        generatorInfoPanels.addTab("City",infoDisplayWindow("City", "getAllCity"));
        generatorInfoPanels.addTab("Virtual Tours",infoDisplayWindow("Virtual Tour Links", "getAllVirtualTourLinks"));
        west.add(generatorInfoPanels);
        cont.add(west,BorderLayout.CENTER);

        //North is Blank for now
        JPanel whiteSpace = new JPanel();
        Dimension whiteSpaceD = new Dimension(600,30);
        whiteSpace.setPreferredSize(whiteSpaceD);
        cont.add(whiteSpace, BorderLayout.NORTH);

        return cont;
    }

    public JPanel collegeListDisplayWindow()
    {
        JPanel cont = new JPanel(new BorderLayout());

        cont.add(new JLabel("College List"), BorderLayout.NORTH);

        collegeListTextArea = new JTextArea();
        collegeListTextArea.setBorder(new JTextField().getBorder());

        cont.add(collegeListTextArea, BorderLayout.CENTER);

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
        area.setBorder(new JTextField().getBorder());
        area.setPreferredSize(new Dimension(80,400));
        cont.add(area, BorderLayout.CENTER);

        //buttons to change text area.
        JButton generatorButton = new JButton("Find " + name);
        generatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collegeList.collegeListInput(collegeListTextArea.getText() + '\n');
                area.setText(methodHandler(methodCalled));
            }
        });

        //button to copy to clipboard
        JButton copyButton = new JButton("Copy");
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(area.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(generatorButton, BorderLayout.CENTER);
        bottom.add(copyButton, BorderLayout.EAST);

        cont.add(bottom, BorderLayout.SOUTH);
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
        frame.setSize(new Dimension(780,680));

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
