package com.hukinator.httpServer.config;

public class Configuration {

    private int port= 8080;
    private String webroot = "";

    private boolean darkMode;

    private boolean reverse;

    private boolean instantStartup;

    private boolean textMode;

    private boolean wahlModus;

    private String bundesland;
    //standard config when none exists
    public Configuration(){
        port = 8080;
        webroot = "";
        darkMode = false;
        reverse = false;
        instantStartup = false;
        textMode = true;
        wahlModus = false;
        bundesland = "";
    }
    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public boolean isInstantStartup() {
        return instantStartup;
    }

    public void setInstantStartup(boolean instantStartup) {
        this.instantStartup = instantStartup;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebroot() {
        return webroot;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }

    public boolean isTextMode() {
        return textMode;
    }

    public void setTextMode(boolean textMode) {
        this.textMode = textMode;
    }

    public boolean isWahlModus() {
        return wahlModus;
    }

    public void setWahlModus(boolean wahlModus) {
        this.wahlModus = wahlModus;
    }

    public String getBundesland() {
        return bundesland;
    }

    public void setBundesland(String bundesland) {
        this.bundesland = bundesland;
    }
}
