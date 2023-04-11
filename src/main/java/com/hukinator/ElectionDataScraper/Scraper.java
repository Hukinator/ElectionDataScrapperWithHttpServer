package com.hukinator.ElectionDataScraper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Sample code to demonstrate the use of the v2 User Tweet timeline endpoint
 * */
public class Scraper {
    private final static Logger LOGGER = LoggerFactory.getLogger(Scraper.class);

    // To set your environment variables in your terminal run the following line:
    // export 'BEARER_TOKEN'='<your_bearer_token>'

    public static void main(String args[]) throws IOException, URISyntaxException {
        final String bearerToken = ("AAAAAAAAAAAAAAAAAAAAAL99kAEAAAAAQHTx21UWE3qMSeRP7wRca4Ztxmo%3DwzzscCFK0o8aZkr3KQFxx2zCQ2t0vg7L1dDBbxJcVKBnTt0vbR");
        if (null != bearerToken) {
            // Replace with user ID below
            /*
            String response = getTweets("1001374075", bearerToken);
            LOGGER.info("is this actually working? "+ response);

             */
            String test = "{\"data\":[{\"edit_history_tweet_ids\":[\"1615359311412359174\"],\"text\":\"Die Deutsche Bahn hat zuletzt an einigen Bahnhöfen ihre Fahrkartenautomaten abgebaut. Es gibt Forderungen, sie wieder zu installieren.\nhttps://t.co/KTxKRnjz3j\",\"id\":\"1615359311412359174\",\"created_at\":\"2023-01-17T14:44:06.000Z\"},{\"edit_history_tweet_ids\":[\"1615346642143830024\"],\"text\":\"In Niedersachsen werden aktuell rund 6.000 ehrenamtliche Schöffen gesucht. Bis Ende Februar können sich Interessierte über die Kommunen bewerben.\nhttps://t.co/7VL3LIL4TX https://t.co/NvHjF8A4o1\",\"id\":\"1615346642143830024\",\"created_at\":\"2023-01-17T13:53:45.000Z\"},{\"edit_history_tweet_ids\":[\"1615310590104182785\"],\"text\":\"Aufgepasst: Neuschnee und Glätte sorgen insbesondere im Oberharz für schwierige Straßenverhältnisse. https://t.co/xfpw0Qh5lo https://t.co/D2xLvlfouB\",\"id\":\"1615310590104182785\",\"created_at\":\"2023-01-17T11:30:30.000Z\"},{\"edit_history_tweet_ids\":[\"1615273123875635200\"],\"text\":\"Boris Pistorius aus Niedersachsen soll neuer Bundesverteidigungsminister werden. Das erfuhr das ARD-Hauptstadtstudio. Vorgängerin Christine Lambrecht hatte am Montag um ihre Entlassung gebeten. #Pistorius #Bundesverteidigungsminister #Lambrecht  \nhttps://t.co/ITo49Di0yL https://t.co/QprfwlbWL0\",\"id\":\"1615273123875635200\",\"created_at\":\"2023-01-17T09:01:37.000Z\"},{\"edit_history_tweet_ids\":[\"1615265291449110529\"],\"text\":\"\"In Niedersachsen soll keine Sporthalle schließen und keine Sportlerin oder kein Sportler kalt duschen müssen\", sagt Sport- und Innenminister Boris Pistorius (SPD). https://t.co/g6K9T3qmWI https://t.co/DkMLxPGJxo\",\"id\":\"1615265291449110529\",\"created_at\":\"2023-01-17T08:30:30.000Z\"},{\"edit_history_tweet_ids\":[\"1615259913751511040\"],\"text\":\"RT @NDRinfo: Heute soll bekanntgegeben werden, wer Verteidigungsministerin #Lambrecht im Amt nachfolgt. Das teilte Arbeitsminister Heil in…\",\"id\":\"1615259913751511040\",\"created_at\":\"2023-01-17T08:09:08.000Z\"},{\"edit_history_tweet_ids\":[\"1615259728073957377\"],\"text\":\"Nach der Geldautomatensprengung in #Torfhaus und der anschließenden Geiselnahme ordnet die Staatsanwaltschaft die drei Verdächtigen einer Bande zu, die aus 300 bis 500 Personen bestehen soll. https://t.co/W5AYgmagcb\",\"id\":\"1615259728073957377\",\"created_at\":\"2023-01-17T08:08:23.000Z\"},{\"edit_history_tweet_ids\":[\"1615259324019875841\"],\"text\":\"Die @DB_Bahn hat zuletzt an einigen Bahnhöfen ihre Fahrkartenautomaten abgebaut. Daran gibt es vielfach Kritik. Die Serviceeinschränkung sei \"nicht vermittelbar\". #Bahn #Niedersachsen  https://t.co/S42w0qZgBG\",\"id\":\"1615259324019875841\",\"created_at\":\"2023-01-17T08:06:47.000Z\"},{\"edit_history_tweet_ids\":[\"1615259152925835264\"],\"text\":\"In #Niedersachsen werden aktuell rund 6.000 ehrenamtliche Schöffen gesucht. Bis Ende Februar können sich Interessierte über die Kommunen bewerben. Dabei gelten bestimmte Voraussetzungen. https://t.co/PBhfiHaegJ\",\"id\":\"1615259152925835264\",\"created_at\":\"2023-01-17T08:06:06.000Z\"},{\"edit_history_tweet_ids\":[\"1615235092342837251\"],\"text\":\"Guten Morgen Niedersachsen! \uD83D\uDE07\n\nMehr Bilder des Tages gibt es hier: https://t.co/MoN7IQfYOU https://t.co/awXP6Jav27\",\"id\":\"1615235092342837251\",\"created_at\":\"2023-01-17T06:30:30.000Z\"}],\"meta\":{\"result_count\":10,\"newest_id\":\"1615359311412359174\",\"oldest_id\":\"1615235092342837251\",\"next_token\":\"7140dibdnow9c7btw450qvrexh103r5r7rzzs6o7squk1\"}}";

            tweetParser tweetParser = new tweetParser();
            tweet[] tweetarray = tweetParser.parseTweets(test);
            //String response = getTweetsByID("1599325924431798272", bearerToken);
            int i = 0;
            while (i < tweetarray.length) {
                LOGGER.info("tweet number " + i + "id: " + tweetarray[i].getTweetID() +  "text: " + tweetarray[i].getText() + "created at: " + tweetarray[i].getCreated_at());
                i++;
            }
            System.out.println(test);

        } else {
            System.out.println("There was a problem getting your bearer token. Please make sure you set the BEARER_TOKEN environment variable");
        }
    }

    /*
     * This method calls the v2 User Tweet timeline endpoint by user ID
     * */
    private static String getTweets(String userId, String bearerToken) throws IOException, URISyntaxException {
        String tweetResponse = null;

        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        URIBuilder uriBuilder = new URIBuilder(String.format("https://api.twitter.com/2/users/%s/tweets", userId));
        ArrayList<NameValuePair> queryParameters;
        queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair("tweet.fields", "created_at"));
        uriBuilder.addParameters(queryParameters);

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
        httpGet.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (null != entity) {
            tweetResponse = EntityUtils.toString(entity, "UTF-8");
        }
        return tweetResponse;
    }
    private static String getUsers(String usernames, String bearerToken) throws IOException, URISyntaxException {
        String userResponse = null;

        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/users/by");
        ArrayList<NameValuePair> queryParameters;
        queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair("usernames", usernames));
        queryParameters.add(new BasicNameValuePair("user.fields", "created_at,description,pinned_tweet_id"));
        uriBuilder.addParameters(queryParameters);

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
        httpGet.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (null != entity) {
            userResponse = EntityUtils.toString(entity, "UTF-8");
        }
        return userResponse;
    }
    private static String getTweetsByID(String ids, String bearerToken) throws IOException, URISyntaxException {
        String tweetResponse = null;

        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setCookieSpec(CookieSpecs.STANDARD).build())
                .build();

        URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets");
        ArrayList<NameValuePair> queryParameters;
        queryParameters = new ArrayList<>();
        queryParameters.add(new BasicNameValuePair("ids", ids));
        queryParameters.add(new BasicNameValuePair("tweet.fields", "created_at"));
        uriBuilder.addParameters(queryParameters);

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
        httpGet.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (null != entity) {
            tweetResponse = EntityUtils.toString(entity, "UTF-8");
        }
        return tweetResponse;
    }
}

