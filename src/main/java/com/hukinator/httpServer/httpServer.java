package com.hukinator.httpServer;

import ch.qos.logback.core.net.ObjectWriter;
import com.hukinator.GUI.GUI;
import com.hukinator.httpServer.config.Configuration;
import com.hukinator.httpServer.config.ConfigurationManager;
import com.hukinator.httpServer.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.CharBuffer;

/*
Driver Class for HTTP Server
 */
public class httpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(httpServer.class);

    public static void main(String[] args) {
        String htmlFile = "";
        String content = "";
        LOGGER.info("test");
        File test = new File (".");
        String pathString = test.getAbsolutePath();
        String fileName = "config.json";
        File config = null;
        //creates a config file if none exists
        //important for starting up without GUI
        config = new File(fileName);
        if(!(config.exists())){
            LOGGER.info("creating new config");
            LOGGER.info("config at " + config.getAbsolutePath());
            Configuration defaultConfig = new Configuration();
            ConfigurationManager.getInstance().writeConfiguration(defaultConfig, config.getAbsolutePath());
        }
        // getting the config from file
        ConfigurationManager.getInstance().loadConfigurationFile(config.getAbsolutePath());
        LOGGER.info("config location" + config.getAbsolutePath());
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("saved config startup: " + conf.isInstantStartup());
        LOGGER.info("webroot: " + conf.getWebroot());
        LOGGER.info("port: " + conf.getPort());
        LOGGER.info("darkMode is: " + conf.isDarkMode());
        LOGGER.info("reverse direction is: " + conf.isReverse());

        boolean temp = false;
        //GUI when instant startup is off
        if (conf.isInstantStartup()){
            String val;
            try {
                FileReader fileReader = new FileReader("content.html");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder stringBuffer = new StringBuilder(512);

                    val = bufferedReader.readLine();
                    stringBuffer.append(val);
                htmlFile = stringBuffer.toString();
                LOGGER.info("html code found: " + htmlFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            GUI mainGUI = new GUI();
            //waiting for GUI to finish while user configures
            do {
                try {
                    Thread.sleep(100);
                    temp = mainGUI.isready();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } while (!temp);
            System.out.println("done with GUI");
            conf.setPort(mainGUI.getPort());
            LOGGER.info("Using port :" + conf.getPort());
            conf.setWebroot(mainGUI.getWebroot());
            LOGGER.info("Using Webroot :" + conf.getWebroot());


            //No Loop because it would still take the same amount of code
            conf.setInstantStartup(mainGUI.getConfig(0));
            LOGGER.info("Setting startup without Menu :" + conf.isInstantStartup());
            conf.setReverse(mainGUI.getConfig(1));
            LOGGER.info("Setting reverse Ticker direction :" + conf.isReverse());
            conf.setDarkMode(mainGUI.getConfig(2));
            LOGGER.info("Setting darkMode :" + conf.isDarkMode());
            conf.setTextMode(mainGUI.getConfig(3));
            LOGGER.info(("Setting TextMode :" + conf.isTextMode()));
            conf.setWahlModus(mainGUI.getConfig(4));
            LOGGER.info(("Setting WahlModus :" + conf.isWahlModus()));
            if (conf.isWahlModus()) {
                conf.setBundesland(mainGUI.getBundesland());
                LOGGER.info("Setting Bundesland for Wahl: " + conf.getBundesland());
                content = "Hier könnte ihre Werbung stehen";
            }
            ConfigurationManager.getInstance().writeConfiguration(conf, config.getAbsolutePath());

            if(conf.isTextMode() && !conf.isInstantStartup()){
                content = mainGUI.getHtmlText();
            }else if (conf.isTextMode() && conf.isInstantStartup()){
                if(htmlFile != ""){
                    LOGGER.info("successfully read html file ");
                }else {
                    content = content + mainGUI.getHtmlText();
                }
            }
        }
        //TODO scraping twitter feed of like 10 different accounts for images and using text recognition for that...

        //TODO interrim solution with file which will be handmade, preparing for scraper adding data the same way for consistency

        //html Generator which will be passed on to HTTPConnectionWorkerThread through ServerlistenerThread,
        // so the HTTPConnectionWorker does not have to open the file each time ( maybe higher ram usage, but lower overhead, which is ok)
        if (htmlFile == "") {
            htmlFile = "<html>" +
                    "<head>" +
                    "<style> body { padding: 25px;background-color: ";

            if (conf.isDarkMode()) {
                htmlFile = htmlFile + "black; color:white;font-size: 25px; }</style>" + "<title>Leon toller WahlWidget</title>" +
                        "</head>";
            } else {
                htmlFile = htmlFile + "white; color:black;font-size: 25px; }</style>" + "<title>Leon toller WahlWidget</title>" +
                        "</head>";
            }

            if (conf.isReverse()) {
                htmlFile = htmlFile + "<body><marquee direction= \"right\">" + content + "</marquee></body>" +
                        "</html>";

            } else {
                htmlFile = htmlFile + "<body><marquee direction= \"left\">" + content + "</marquee></body>" +
                        "</html>";
            }


            LOGGER.info(htmlFile);
            //Saving the HTML file when you don't want to see the menu again
            try {
                FileWriter fileWriter = new FileWriter("content.html");
                fileWriter.write(htmlFile);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ServerListenerThread serverListenerThread = null;
        try {
            serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot(), htmlFile);
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
