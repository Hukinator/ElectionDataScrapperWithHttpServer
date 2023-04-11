package com.hukinator.GUI;

import com.hukinator.httpServer.httpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(GUI.class);


    private JFrame frame;
    private JLabel label;
    private JPanel jPanel;
    private JPanel textPanel;
    private JPanel advancedOptions;
    private JButton button;
    private JCheckBox start_without_menu;

    private JCheckBox reverse;

    private JCheckBox darkMode;
    private JComboBox states;
    private JTextField textField;
    private JCheckBox wahlen;
    private JCheckBox selfTextTicker;
    private JCheckBox advancedConfig;
    private JLabel selectionBoxText;
    private boolean ready;
    private boolean[] config = new boolean[5];

    private String bundesland;
    private int port = 8080;
    private String webroot = "";

    private String htmlText = "";

    public static void main(String[] args){
        GUI test = new GUI();
    }

    public GUI (){
        config[3] = true;
        selectionBoxText = new JLabel("Bundesland:   ");
        selectionBoxText.setHorizontalAlignment(SwingConstants.RIGHT);
        selectionBoxText.setVisible(false);
        frame = new JFrame();
        wahlen = new JCheckBox("Wahlmodus nutzen");
        wahlen.addActionListener(this);
        selfTextTicker = new JCheckBox("Ticker Text Modus");
        selfTextTicker.addActionListener(this);
        selfTextTicker.setSelected(true);
        button = new JButton("Start Server");
        button.addActionListener(this);
        start_without_menu = new JCheckBox("Einstellungen nicht mehr anzeigen");
        start_without_menu.addActionListener(this);
        reverse = new JCheckBox("Die Bewegungsrichtung umkehren");
        reverse.addActionListener(this);
        darkMode = new JCheckBox("Dark Mode aktivieren");
        darkMode.addActionListener(this);
        textField = new JTextField("", 20);
        label = new JLabel("Text für Ticker:   ");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setToolTipText("Hier Bitte die Ticker Nachrichten eintragen, nach jeder Nachricht Enter drücken");
        textField.addActionListener(this);
        textPanel = new JPanel();

        jPanel = new JPanel();
        jPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        jPanel.setLayout(new GridLayout(4, 1));

        // Creating the selection Box
        String[] bundeslaender = {"Baden-Württemberg", "Bayern", "Niedersachsen", "Sachsen", "Sachsen-Anhalt", "Berlin", "Brandenburg", "Rheinland-Pfalz", "Hessen", "Mecklenburg-Vorpommern",
                                    "Nordrhein-Westfalen", "Saarland", "Bremen", "Habmburg", "thüringen", "Schleswig-Holstein" };

        states = new JComboBox(bundeslaender);
        states.setSelectedIndex(0);
        states.addActionListener(this);
        states.setVisible(false);

        //adding the Components to the panel
        jPanel.add(start_without_menu);
        jPanel.add(reverse);
        jPanel.add(darkMode);
        jPanel.add(selfTextTicker);
        jPanel.add(label);
        jPanel.add(textField);
        jPanel.add(wahlen);
        jPanel.add(selectionBoxText);
        jPanel.add(states);

        textPanel.add(button);

        frame.add(jPanel, BorderLayout.CENTER);
        frame.add(textPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wahlticker");
        frame.pack();
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object temp = e.getSource();
        if (temp == button) {
            ready = true;
            LOGGER.info("close window");
            frame.dispose();
        } else if (temp == start_without_menu) {
            if (start_without_menu.isSelected()) {
                LOGGER.info("instant startup an");
                config[0] = true;
            } else {
                LOGGER.info("instant startup aus");
                config[0] = false;
            }
            config[0] = true;
            //LOGGER.info("start without menu");
        } else if (temp == states) {
            bundesland = states.getSelectedItem().toString();
            LOGGER.info("Bundesland: " + bundesland);
            //Reverse direction Config
        } else if (temp == reverse) {
            if (reverse.isSelected()) {
                config[1] = true;
                LOGGER.info("Reverse an");
            } else {
                config[1] = false;
                LOGGER.info("Reverse aus");
            }
        } else if (temp == textField) {
            htmlText = htmlText + textField.getText().trim() + "  //  ";
            LOGGER.info("text for Ticker: " + htmlText);
            textField.setText("");
            //dark Mode Config
        } else if (temp == darkMode) {
            if (darkMode.isSelected()) {
                config[2] = true;
                LOGGER.info("Dark Mode an");
            } else {
                config[2] = false;
                LOGGER.info("Dark Mode aus");
            }
            //text Modus Config
        } else if (temp == selfTextTicker) {
            if (selfTextTicker.isSelected()) {
                textField.setVisible(true);
                label.setVisible(true);
                config[3] = true;
                LOGGER.info("Text Modus an");
            } else {
                textField.setVisible(false);
                label.setVisible(false);
                LOGGER.info("Text Modus aus");
            }
            //Wahl Modus Config
        } else if (temp == wahlen) {
            if (wahlen.isSelected()) {
                states.setVisible(true);
                selectionBoxText.setVisible(true);
                config[4] = true;
                LOGGER.info("Wahl Modus an");
            } else {
                states.setVisible(false);
                selectionBoxText.setVisible(false);
                config[4] = false;
                LOGGER.info("Wahl Modus aus");
            }
        }
    }
    public boolean isready(){
        return ready;
    }

    public String  getBundesland(){
        return bundesland;
    }
    public boolean getConfig(int selector){
        return config[selector];
    }

    public int getPort() {
        return port;
    }

    public String getWebroot() {
        return webroot;
    }
    public String getHtmlText() {
        return htmlText;
    }
}
