package org.example;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.IntelliJTheme;
import org.apache.poi.ss.formula.atp.Switch;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainGUI {

    public static JFrame frame;

    CollegeList collegeList;

    JTextArea collegeListTextArea;

    public static final int NUM_GENERATOR_PANELS = 3;

    public static String help_youtube_video_link = "http://www.codejava.net";

    public static final Dimension DISPLAY_WINDOW_DIMENSTIONS = new Dimension(400, 500);
    public static final Dimension COLLEGE_LIST_WINDOW = new Dimension(400,80);

    public final String topTextbox = "Please enter college names " +
            "\n names can be aliases,  " +
            "\n EX: UW, WSU, SAPC" +
            "\n State names automatically interpreted as universities. " +
            "\n EX: Washington -> Washington State University";


    public JPanel settingsPanel ()
    {
        JPanel cont = new JPanel();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));

        //Dark mode / light mode Stuff.
        JPanel apperance = new JPanel();
        apperance.setBorder(new TitledBorder("Appearance"));
        JCheckBox darkMode = new JCheckBox();
        darkMode.setBorder(new TitledBorder("Appearance"));
        darkMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (darkMode.isSelected())
                {
                    try {
                        UIManager.setLookAndFeel(new FlatDarculaLaf());
                    } catch (UnsupportedLookAndFeelException ex) {
                        throw new RuntimeException(ex);
                    }
                    SwingUtilities.updateComponentTreeUI(frame);
                    //frame.pack();
                }
                else
                {
                    try {
                        UIManager.setLookAndFeel(new FlatIntelliJLaf());
                    } catch (UnsupportedLookAndFeelException ex) {
                        throw new RuntimeException(ex);
                    }
                    SwingUtilities.updateComponentTreeUI(frame);
                    //frame.pack();
                }
            }
        });
        darkMode.setText("Dark Mode Enabled");
        apperance.add(darkMode);

        //College List settings
        int mumCollegeListSettings = 2;
        JPanel collegeListSettings = new JPanel(new GridLayout(mumCollegeListSettings, 0));
        collegeListSettings.setBorder(new TitledBorder("College List Settings"));
        //college list settings componets
        JPanel j1 = collegeListSettingsComponets("Toggle Weather the Aliases are Automatically converted into school names\n EX: WSU -> Washington State University", "Do not Convert Alias", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CollegeList.readAliases(!College.readAliases);
            }
        });
        collegeListSettings.add(j1);
        collegeListSettings.add(collegeListSettingsComponets("Interpret university names by name" +
                "\n EX: Gonzaga -> Gonzaga University", "Do not append university names", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CollegeList.appendUniversityToMatches(!College.appendUniversityToMatches);
            }
        }));



        cont.add(apperance);
        cont.add(collegeListSettings);

        //North is Blank for now
        JPanel whiteSpace = new JPanel();
        Dimension whiteSpaceD = new Dimension(600,30);
        whiteSpace.setPreferredSize(whiteSpaceD);
        cont.add(whiteSpace, BorderLayout.NORTH);

        return cont;
    }

    public JPanel collegeListSettingsComponets(String description,String buttonTitle, ActionListener A1)
    {
        JPanel cont = new JPanel(new BorderLayout());

        JTextArea descriptionL = new JTextArea(description);
        descriptionL.setEnabled(false);
        cont.add(descriptionL, BorderLayout.NORTH);

        JRadioButton secection = new JRadioButton(buttonTitle);
        secection.addActionListener(A1);
        cont.add(secection, BorderLayout.SOUTH);
        cont.setBorder(new EtchedBorder());

        return cont;
    }

    public JPanel helpPanel()
    {
        JPanel cont = new JPanel(new BorderLayout());

        JPanel help = new JPanel();
        help.setLayout(new BoxLayout(help, BoxLayout.Y_AXIS));
        JTextArea j1 = new JTextArea("Confused on how to use this application? " +
                "\n There is a helpful tutorial linked below");
        help.add(j1);
        j1.setEnabled(false);

        JLabel hyperlink = new JLabel("Tutorial Link");
        hyperlink.setForeground(Color.BLUE.darker());
        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hyperlink.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Desktop.getDesktop().browse(new URI(help_youtube_video_link));

                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        help.add(j1, BorderLayout.NORTH);
        help.add(hyperlink, BorderLayout.NORTH);

        JPanel bugReport = new JPanel();
        bugReport.setLayout(new BoxLayout(bugReport, BoxLayout.Y_AXIS));

        cont.add(help, BorderLayout.NORTH);
        cont.add(bugReport, BorderLayout.CENTER);
        return cont;
    }

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
        generatorInfoPanels.addTab("Net Price Links", infoDisplayWindow("Net Price Calculator Links", "getAllNetPriceCalcLink"));
        generatorInfoPanels.addTab("Enrollment", infoDisplayWindow("Enrollment", "getAllEnrollement"));
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

        //buttons to change text area.
        JButton generatorButton = new JButton("Find " + name);
        generatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //disable the whole screen
                area.setEnabled(false);
                generatorButton.setEnabled(false);
                copyButton.setEnabled(false);
                //frame.repaint();

                //call backend stuff
                collegeList.collegeListInput(collegeListTextArea.getText() + '\n');
                area.setText(methodHandler(methodCalled));

                //re-enable who screen.
                area.setEnabled(true);
                generatorButton.setEnabled(true);
                copyButton.setEnabled(true);
                //frame.repaint();
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
            case "getAllNetPriceCalcLink":
                return collegeList.getAllNetPriceCalcLink();
            case "getAllEnrollement":
                return collegeList.getAllEnrollement();
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

    public JPanel searchPanel ()
    {
        JPanel cont = new JPanel(new BorderLayout());

        //Search Bar
        JPanel search = new JPanel(new FlowLayout());


        return cont;
    }    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("College List Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.setSize(new Dimension(830,660));

        //make panels
        MainGUI mainGUI = new MainGUI();
        JPanel main = mainGUI.mainPanel();
        JPanel settings = mainGUI.settingsPanel();
        JPanel help = mainGUI.helpPanel();
        JPanel searchBar = mainGUI.searchPanel();

        //make Jtabbed pane
        JTabbedPane mainFrame = new JTabbedPane();
        mainFrame.addTab("College List", main);
        mainFrame.addTab("Settings", settings);
        mainFrame.addTab("Help", help);
        mainFrame.addTab("Search bar", searchBar);
        frame.setContentPane(mainFrame);

        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }


}
