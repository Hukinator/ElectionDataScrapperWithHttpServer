package com.hukinator.ElectionDataScraper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;

public class tweetParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(tweetParser.class);

    public tweet[] parseTweets(String response) throws IOException {
        int _byte;
        boolean tweetIdReady = false;
        boolean historicTweetIdReady = false;
        boolean tweetTextReady = false;
        boolean tweetCreatedAtReady = false;
        tweet[] tweets = new tweet[10];
        for(int i = 0; i < tweets.length; i++) {
            tweets[i] = new tweet();
        }
        StringBuilder builder = new StringBuilder();
        StringReader stringReader = new StringReader(response);
        int counter = 0;
        _byte = stringReader.read();
        if (_byte == '{'){
                _byte = stringReader.read();
                if (_byte == '"'){
                    _byte = stringReader.read();
                    while (_byte != '"') {
                        builder.append((char) _byte);
                        _byte = stringReader.read();
                    }
                    LOGGER.info(builder.toString());
                    if (builder.toString().equals("data")){
                        _byte = stringReader.read();
                        _byte = stringReader.read();
                        if (_byte == '[') {
                            _byte = stringReader.read();
                            _byte = stringReader.read();
                            if (_byte == '"') {
                                while (!(tweetIdReady && tweetCreatedAtReady && tweetTextReady && historicTweetIdReady)) {
                                    LOGGER.info("switch case: " + builder.toString());
                                    builder.delete(0, builder.length());
                                    LOGGER.info("before switch case loop: " + ((char) _byte));
                                    while ((_byte = stringReader.read()) != '"') {
                                        LOGGER.info(" " + (char)_byte);
                                        builder.append((char) _byte);
                                    }
                                    LOGGER.info("now doing: " + builder.toString() + "at tweet" + counter);
                                    switch (builder.toString()) {
                                        case "id":
                                            _byte = stringReader.read();
                                            _byte = stringReader.read();
                                            _byte = stringReader.read();
                                            builder.delete(0, builder.length());
                                            while (_byte != '"') {
                                                builder.append((char) _byte);
                                                _byte = stringReader.read();
                                            }
                                            try {
                                                LOGGER.info("should be id: " + builder.toString());
                                                tweets[counter].setTweetID(builder.toString());
                                            } catch (NumberFormatException e) {
                                                throw new RuntimeException(e);
                                            }
                                            tweetIdReady = true;
                                            LOGGER.info("tweetID" + counter + " " + tweets[counter].getTweetID() + " " + tweetIdReady);
                                        case "edit_history_tweet_ids":
                                            //don't need to use this right now, but should work
                                    /*
                                            _byte = stringReader.read();
                                            _byte = stringReader.read();
                                            builder.delete(0,builder.length());
                                            if (_byte == '[') {
                                                _byte = stringReader.read();
                                                while(_byte != ']') {
                                                    if (_byte == '"') {
                                                        _byte = stringReader.read();
                                                        while (_byte != '"'){
                                                            builder.append((char) _byte);
                                                            _byte = stringReader.read();
                                                        }
                                                        tweets[counter].add_history_tweet_id(Integer.parseInt(builder.toString()));
                                                    }
                                                }
                                            }

                                     */
                                            while (_byte != ']') {
                                                _byte = stringReader.read();
                                            }
                                            _byte = stringReader.read();
                                            historicTweetIdReady = true;
                                            LOGGER.info("historic tweetIDs: " + counter + " " + historicTweetIdReady);
                                        case "text":
                                            _byte = stringReader.read();
                                            _byte = stringReader.read();
                                            _byte = stringReader.read();
                                            builder.delete(0, builder.length());
                                            while (_byte != '"') {
                                                if (_byte == 92) {
                                                    builder.append((char) _byte);
                                                    _byte = stringReader.read();
                                                    builder.append((char) _byte);
                                                    _byte = stringReader.read();
                                                }
                                                builder.append((char) _byte);
                                                _byte = stringReader.read();
                                            }
                                            //TODO clean up the unnecessary links
                                            tweets[counter].setText(builder.toString());
                                            tweetTextReady = true;
                                            LOGGER.info("tweeTtext " + counter + " " + tweets[counter].getText() + " " + tweetTextReady);
                                        case "created_at":
                                            _byte = stringReader.read();
                                            _byte = stringReader.read();
                                            builder.delete(0, builder.length());
                                            while (_byte != '"') {
                                                builder.append((char) _byte);
                                                _byte = stringReader.read();
                                            }
                                            tweets[counter].setText(builder.toString());
                                            tweetCreatedAtReady = true;
                                            LOGGER.info("tweet created at: " + counter + " " + tweets[counter].getCreated_at() + " " + tweetCreatedAtReady);
                                    }
                                    if(tweetIdReady && tweetCreatedAtReady && tweetTextReady && historicTweetIdReady) {
                                        tweetIdReady = false;
                                        tweetCreatedAtReady = false;
                                        tweetTextReady = false;
                                        historicTweetIdReady = false;
                                        counter++;
                                    }
                                }
                            }
                        }
                    } else {
                        throw new tweetParsingException("Format Error in Response");
                    }
                }
            }


        return tweets;
    }

}
