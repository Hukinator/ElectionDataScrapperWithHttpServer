package com.hukinator.ElectionDataScraper;

public class Party {
    String name;
    double previousResult;
    double currentResult;

    public Party(String name, double previousResult, double currentResult){
        this.name = name;
        this.previousResult = previousResult;
        this.currentResult = currentResult;
    }
    public Party(String name, double previousResult){
        this.name = name;
        this.previousResult = previousResult;
    }

    public double getCurrentResult() {
        return currentResult;
    }

    public void setCurrentResult(double currentResult) {
        this.currentResult = currentResult;
    }
    public double getChange(){
        return (currentResult / previousResult);
    }

    public String getName() {
        return name;
    }
}
