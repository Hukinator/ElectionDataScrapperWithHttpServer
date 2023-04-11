package com.hukinator.ElectionDataScraper;

public class tweet {
    String tweetID;
    String[] edit_history_tweet_ids;
    int tweetEditCounter;
    String text;
    String created_at;
    public tweet(String tweetID, String [] edit_history_tweet_ids, String text, String created_at){
        this.tweetID = tweetID;
        this.edit_history_tweet_ids = edit_history_tweet_ids;
        this.text = text;
        this.created_at = created_at;
    }
    public tweet() {
        this.tweetID = "";
        this.text = "";
        this.created_at = "";

    }

    public String getTweetID() {
        return tweetID;
    }

    public String[] getEdit_history_tweet_ids() {
        return edit_history_tweet_ids;
    }

    public String getText() {
        return text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setTweetID(String tweetID) {
        this.tweetID = tweetID;
    }

    public void setEdit_history_tweet_ids(String[] edit_history_tweet_ids) {
        this.edit_history_tweet_ids = edit_history_tweet_ids;
    }
    public void add_history_tweet_id(String tweetID){

        edit_history_tweet_ids[tweetEditCounter] = tweetID;
        tweetEditCounter++;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
