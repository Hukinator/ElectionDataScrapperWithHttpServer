package com.hukinator.httpServer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.hukinator.httpServer.config.util.Json;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;
    private ConfigurationManager(){

    }
    public static ConfigurationManager getInstance(){
        if(myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }
    /*
    used to load config file by provided file path
     */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HTTPConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        while(true){
            try {
                if (!(( i = fileReader.read()) != -1)) break;
            } catch (IOException e) {
                throw new HTTPConfigurationException(e);
            }
            sb.append((char)i);
        }
        JsonNode conf = null;
        try {
            System.out.println(sb.toString());
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HTTPConfigurationException("Error parsing the configuration file", e);

        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HTTPConfigurationException("Error parsing the configuration file, internal", e);
        }
    }
    public void writeConfiguration(Configuration configuration, String filepath) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("port", configuration.getPort());
        jsonObject.put("webroot", configuration.getWebroot());
        jsonObject.put("darkMode", configuration.isDarkMode());
        jsonObject.put("reverse", configuration.isReverse());
        jsonObject.put("instantStartup", configuration.isInstantStartup());
        jsonObject.put("textMode", configuration.isTextMode());
        jsonObject.put("wahModus" ,configuration.isWahlModus());
        jsonObject.put("bundesland", configuration.getBundesland());
        try {
            FileWriter fileWriter = new FileWriter(filepath);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    /*
    return current loaded config
     */
    public Configuration getCurrentConfiguration(){
        if (myCurrentConfiguration == null){
            throw new HTTPConfigurationException("No Current COnfiguration Set.");
        }
        return myCurrentConfiguration;
    }
}
