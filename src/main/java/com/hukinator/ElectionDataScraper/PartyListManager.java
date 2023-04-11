package com.hukinator.ElectionDataScraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.hukinator.httpServer.config.Configuration;
import com.hukinator.httpServer.config.ConfigurationManager;
import com.hukinator.httpServer.config.HTTPConfigurationException;
import com.hukinator.httpServer.config.util.Json;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PartyListManager {

    private static PartyListManager myPartyListManager;
    private static PartyList myCurrentPartyList;

    private static Party myCurrentParty;

    private PartyListManager(){};

    public static PartyListManager getInstance() {
        if (myPartyListManager == null)
            myPartyListManager = new PartyListManager();
        return myPartyListManager;

    }
    public void loadPartyList(String filePath) {
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
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HTTPConfigurationException("Error parsing the configuration file", e);

        }
        try {
            myCurrentPartyList = Json.fromJson(conf, PartyList.class);
        } catch (JsonProcessingException e) {
            throw new HTTPConfigurationException("Error parsing the configuration file, internal", e);
        }
    }
    //TODO add working objects to the object and add writer.
    public void writePartyList(PartyList partyList, String filepath){
        JSONObject jsonObject = new JSONObject();

    }
}
