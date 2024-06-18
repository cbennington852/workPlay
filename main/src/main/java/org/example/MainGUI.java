package org.example;


import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class MainGUI {

    public static JFrame frame;

    CollegeList collegeList;

    public  JTextArea  collegeListTextArea;

    public JTextArea  collegeListTextAreaSAT;




    JProgressBar bar = new JProgressBar(JProgressBar.HORIZONTAL,0,100);

    public static final int NUM_GENERATOR_PANELS = 3;

    public static String help_youtube_video_link = "http://www.codejava.net";

    public static final Dimension DISPLAY_WINDOW_DIMENSTIONS = new Dimension(400, 500);
    public static final Dimension DISPLAY_WINDOW_DIMENSTIONS_MULTI_SEARCH = new Dimension(400,100);
    public static final Dimension COLLEGE_LIST_WINDOW = new Dimension(400,80);

    public ArrayList<JButton> generateButtonList = new ArrayList<>();
    public int fontSize;
    public Font standardFont = new Font("Arial", Font.PLAIN, fontSize);

    ArrayList<JComponent> fontChangableComps = new ArrayList<>();
    public final String topTextbox = "Please enter college names " +
            "\n names can be aliases,  " +
            "\n EX: UW, WSU" +
            "\n State names automatically interpreted as state universities. " +
            "\n EX: Washington -> Washington State University" +
            "\n University names automaticly appended" +
            "\n EX: gonzaga -> Gonzaga Univeristy" +
            "\n not case sensitive"+
            "\n Spaces are ignored.";


    public JPanel settingsPanel ()
    {
        JPanel cont = new JPanel();
        cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));

        //Dark mode / light mode Stuff.
        JPanel apperance = new JPanel(new GridLayout(2,1));
        apperance.setBorder(new TitledBorder("Appearance"));
        fontChangableComps.add(apperance);
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
        fontChangableComps.add(darkMode);
        apperance.add(darkMode);
        JPanel justSldier = new JPanel();
        justSldier.setBorder(new TitledBorder("Text Size"));
        fontChangableComps.add(justSldier);
        JSlider fontSizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 20, 12);
        fontSizeSlider.setMinorTickSpacing(2);
        fontSizeSlider.setMajorTickSpacing(5);
        fontSizeSlider.setPaintTicks(true);
        fontSizeSlider.setPaintLabels(true);
        fontSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fontSize = fontSizeSlider.getValue();
                standardFont = new Font("Arial", Font.PLAIN, fontSize);
                for (int x =0; x < fontChangableComps.size(); x++)
                {
                    fontChangableComps.get(x).setFont(standardFont);
                }
                frame.pack();
                System.out.println(fontSize);
            }
        });
        justSldier.add(fontSizeSlider);
        JLabel temp = new JLabel("Text Size");
        justSldier.setSize(new Dimension(300,20));
        //apperance.add(justSldier);

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

        Font font1 = new Font("Arial" , Font.PLAIN, 17);

        JPanel help = new JPanel();
        help.setLayout(new BoxLayout(help, BoxLayout.Y_AXIS));
        JTextArea j1 = new JTextArea("Confused on how to use this application? " +
                "\n There is a helpful tutorial linked below");
        fontChangableComps.add(j1);
        help.add(j1);
        j1.setEditable(false);

        //make links to youtube tut
        JLabel hyperlink = new JLabel("Tutorial Link");
        fontChangableComps.add(hyperlink);
        hyperlink.setForeground(Color.BLUE.darker());
        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouse(hyperlink);

        help.add(j1, BorderLayout.NORTH);
        help.add(hyperlink, BorderLayout.NORTH);

        //bug reports and such
        JPanel bugReport = new JPanel();
        bugReport.setLayout(new BoxLayout(bugReport, BoxLayout.Y_AXIS));
        JTextArea text = new JTextArea("Found a Bug? \n Report the bug here, by emailing me \n c.bennington852@gmail.com \n\n\n" +
                "This project is made and maintained by Charles Bennington, a current student at Gonzaga" +
                "\n and part of the graduating class of 2026. ");

        text.setEditable(false);
        fontChangableComps.add(text);

        JPanel sources = new JPanel();
        JTextArea sourcesText = new JTextArea("");

        bugReport.add(text);

        //cont.add(help, BorderLayout.NORTH);
        cont.add(bugReport, BorderLayout.CENTER);
        return cont;
    }

    private void addMouse(JComponent hyperlink)
    {
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
    }

    public JPanel mainPanel()
    {
        JPanel cont = new JPanel(new BorderLayout());
        //West is the main college list
        JPanel east = new JPanel(new BorderLayout());

        JTextArea typingtext = new JTextArea(topTextbox);
        fontChangableComps.add(typingtext);
        //JButton menu = new JButton("Show Instructions");
        /*
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typingtext.setVisible(!typingtext.isVisible());
            }
        });
         */
        typingtext.setEnabled(false);
        JPanel eastNorth = new JPanel(new BorderLayout());
        eastNorth.setBorder(new TitledBorder("Show Instructions"));
        eastNorth.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                typingtext.setVisible(!typingtext.isVisible());
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
        eastNorth.add(typingtext, BorderLayout.CENTER);

        east.add(eastNorth, BorderLayout.NORTH);
        east.add(collegeListDisplayWindow(1), BorderLayout.CENTER);
        east.setPreferredSize(COLLEGE_LIST_WINDOW);

        cont.add(east,BorderLayout.WEST);
        collegeList = new CollegeList();

        //East is the info panels for each person
        JPanel west = new JPanel();
        JTabbedPane generatorInfoPanels = new JTabbedPane();
        fontChangableComps.add(generatorInfoPanels);
        generatorInfoPanels.addTab("State",infoDisplayWindow("State", "getAllState", false,0));
        generatorInfoPanels.addTab("City",infoDisplayWindow("City", "getAllCity", false,0));
        generatorInfoPanels.addTab("Virtual Tours",infoDisplayWindow("Virtual Tour Links", "getAllVirtualTourLinks", false,0));
        generatorInfoPanels.addTab("Net Price Links", infoDisplayWindow("Net Price Calculator Links", "getAllNetPriceCalcLink", false,0));
        generatorInfoPanels.addTab("Requirements", infoDisplayWindow("College Admission Requirments", "getUniversityRequirmentsLinkCod", false,0));
        west.add(generatorInfoPanels);
        cont.add(west,BorderLayout.CENTER);

        //North is Blank for now
        JPanel whiteSpace = new JPanel();
        Dimension whiteSpaceD = new Dimension(600,30);
        whiteSpace.setPreferredSize(whiteSpaceD);
        cont.add(whiteSpace, BorderLayout.NORTH);

        return cont;
    }

    public JPanel mainPanelSAT()
    {
        JPanel cont = new JPanel(new BorderLayout());
        //West is the main college list
        JPanel east = new JPanel(new BorderLayout());

        String funny =
                        "The same Naming Convetions apply to this section                             \n" +
                        "SAT Deviation range:                                                         \n" +
                        "       *Shows the SAT total 25th Percentile, and the 75th perentile range.   \n" +
                        "This data is from                                                            \n" +
                        "https://www.compassprep.com/college-profiles/                                \n" +
                        "And is from May 2024. ";

        JTextArea typingtext = new JTextArea(funny);
        fontChangableComps.add(typingtext);
        typingtext.setEnabled(false);
        JPanel eastNorth = new JPanel(new BorderLayout());
        eastNorth.setBorder(new TitledBorder("Show Instructions"));
        eastNorth.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                typingtext.setVisible(!typingtext.isVisible());
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
        eastNorth.add(typingtext, BorderLayout.CENTER);
        JTextArea thisText = new JTextArea();
        east.add(eastNorth, BorderLayout.NORTH);
        east.add(collegeListDisplayWindow(0), BorderLayout.CENTER);
        east.setPreferredSize(COLLEGE_LIST_WINDOW);

        cont.add(east,BorderLayout.WEST);
        collegeList = new CollegeList();

        //East is the info panels for each person
        JPanel west = new JPanel();
        JTabbedPane generatorInfoPanels = new JTabbedPane();
        fontChangableComps.add(generatorInfoPanels);

        generatorInfoPanels.add("Testing Policy", infoDisplayWindow("SAT/ACT Testing Policy", "getAllSATPolicy",false,1));
        generatorInfoPanels.add("SAT Range", infoDisplayWindow("SAT 25th Percentile, and 75th percentile", "getAllSATDeviationRange", false,1));
        generatorInfoPanels.add("Acceptance Rate", infoDisplayWindow("College Accpetance rate", "getAllAcceptanceRates",false,1));

        west.add(generatorInfoPanels);
        cont.add(west,BorderLayout.CENTER);

        //North is Blank for now
        JPanel whiteSpace = new JPanel();
        Dimension whiteSpaceD = new Dimension(600,30);
        whiteSpace.setPreferredSize(whiteSpaceD);
        cont.add(whiteSpace, BorderLayout.NORTH);

        return cont;
    }

    public JPanel collegeListDisplayWindow(int panel)
    {
        JPanel cont = new JPanel(new BorderLayout());
        JLabel funnn = new JLabel("College List");
        fontChangableComps.add(funnn);
        cont.add(funnn, BorderLayout.NORTH);

        if (panel == 1)
        {
            collegeListTextAreaSAT = new JTextArea();
            fontChangableComps.add(collegeListTextAreaSAT);
            collegeListTextAreaSAT.setBorder(new JTextField().getBorder());
            cont.add(collegeListTextAreaSAT, BorderLayout.CENTER);
        }
        else
        {
            collegeListTextArea = new JTextArea();
            fontChangableComps.add(collegeListTextArea);
            collegeListTextArea.setBorder(new JTextField().getBorder());
            cont.add(collegeListTextArea, BorderLayout.CENTER);
        }
        return cont;
    }

    public JPanel infoDisplayWindow(String name, String methodCalled, boolean addToList, int panel)
    {
        JPanel cont = new JPanel(new BorderLayout());
        if (addToList)
        {
            cont.setPreferredSize(DISPLAY_WINDOW_DIMENSTIONS_MULTI_SEARCH);
        }
        else
        {
            cont.setPreferredSize(DISPLAY_WINDOW_DIMENSTIONS);
        }

        //area label
        JLabel nameee = new JLabel(name);
        cont.add(nameee, BorderLayout.NORTH);
        fontChangableComps.add(nameee);

        //editable text area
        JTextArea area = new JTextArea();
        fontChangableComps.add(area);
        area.setBorder(new JTextField().getBorder());
        area.setPreferredSize(new Dimension(80,400));
        cont.add(area, BorderLayout.CENTER);

        //button to copy to clipboard
        JButton copyButton = new JButton("Copy");
        fontChangableComps.add(copyButton);
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(area.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
        });
        JPanel bottom = new JPanel(new BorderLayout());
        //buttons to change text area.
        JButton generatorButton = new JButton("Find " + name);
        fontChangableComps.add(generatorButton);
        generatorButton.setPreferredSize(new Dimension(100,40));
        generatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //disable the whole screen
                area.setEnabled(false);
                generatorButton.setEnabled(false);
                copyButton.setEnabled(false);
                frame.repaint();
                //System.out.println("HERE?");

                //make a proggress bar
                bar.setValue(10);
                bottom.add(bar, BorderLayout.SOUTH);

                //call backend stuff
                //calls on a thread so the user can still use the buttons while this is running
                Thread thread = new Thread(() -> {
                    String collegeListInput = "";
                    if(panel == 1)
                    {
                        collegeListInput = collegeListTextArea.getText();
                    }
                    else {
                        collegeListInput = collegeListTextAreaSAT.getText();
                    }

                    // Stuff you want to do.

                    collegeList.collegeListInput(collegeListInput + '\n');

                    area.setText(methodHandler(methodCalled));
                    bottom.remove(bar);
                });
                thread.start();

                fontChangableComps.add(copyButton);
                //re-enable who screen.
                area.setEnabled(true);
                generatorButton.setEnabled(true);
                copyButton.setEnabled(true);
                frame.repaint();


            }
        });
        if (addToList)
        {
            generateButtonList.add(generatorButton);
        }

        bottom.add(generatorButton, BorderLayout.CENTER);
        bottom.add(copyButton, BorderLayout.EAST);

        cont.add(bottom, BorderLayout.SOUTH);
        return cont;
    }


    private String methodHandler (String methodCalled)
    {
        switch(methodCalled) {
            case "getAllState":
                return   collegeList.getAllState(bar);
            case "getAllCity":
                return  collegeList.getAllCity(bar);
            case "getAllVirtualTourLinks":
                return  collegeList.getAllVirtualTourLinks(bar);
            case "getAllNetPriceCalcLink":
                return collegeList.getAllNetPriceCalcLink(bar);
            case "getAllEnrollement":
                return collegeList.getAllEnrollement(bar);
            case "getAllSATPolicy":
                return collegeList.getAllSATPolicy(bar);
            case "getUniversityRequirmentsLink":
                return collegeList.getAllUniversityRequirmentsLink(bar);
            case "getAllAcceptanceRates":
                return collegeList.getAllAcceptanceRates(bar);
            case "getAllSATDeviationRange":
                return collegeList.getAllSATDeviationRange(bar);
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
        frame = new JFrame("College List Helper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.setSize(new Dimension(830,660));

        //make panels
        MainGUI mainGUI = new MainGUI();
        JPanel main = mainGUI.mainPanel();
        JPanel settings = mainGUI.settingsPanel();
        JPanel help = mainGUI.helpPanel();

        JPanel SATMain = mainGUI.mainPanelSAT();
        //JPanel searchBar = mainGUI.searchPanel();

        //make Jtabbed pane
        int iconWidth = 25;
        int iconHeight = 25;
        JTabbedPane mainFrame = new JTabbedPane();
        mainFrame.addTab("Basic Attributes", main);
        mainFrame.addTab("SAT Data", SATMain);
        mainFrame.addTab("Settings", settings);
        mainFrame.addTab("Help", help);
        mainFrame.setIconAt(0, mainGUI.getImage(MainGUI.class.getResource("/org/example/small_list (1).png"), iconWidth,iconHeight));
        mainFrame.setIconAt(1, mainGUI.getImage(MainGUI.class.getResource("/org/example/SATicon.png"), iconWidth,iconHeight));
        mainFrame.setIconAt(2, mainGUI.getImage(MainGUI.class.getResource("/org/example/gear.png"), iconWidth,iconHeight));
        mainFrame.setIconAt(3, mainGUI.getImage(MainGUI.class.getResource("/org/example/output-onlinepngtools.png"), iconWidth,iconHeight));
        frame.setContentPane(mainFrame);

        ImageIcon img = new ImageIcon(MainGUI.class.getResource("/org/example/icon.png"));
        System.out.println();

        frame.setIconImage(img.getImage());

        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }


    public ImageIcon getImage(URL fileName, int width, int height)
    {
        URL diceFaceFileName = fileName;
        //image handling for play button
        ImageIcon wIcon = new ImageIcon(diceFaceFileName);
        Image image = wIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  Image.SCALE_SMOOTH); // scale it the smooth way
        wIcon = new ImageIcon(newimg);
        return wIcon;
    }

}
