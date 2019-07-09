package com.example.helloworld.recyclerview;

public class Data {
    private int mdataRank;
    private String mdataInf;
    private int mdataHotValue;

    public void setRank(int i){ mdataRank=i; }
    public void setInf(String i){ mdataInf=i; }
    public void setHotValue(int i){ mdataHotValue=i;}

    public int getRank(){ return mdataRank; }
    public String getInf(){ return mdataInf; }
    public int getHotValue(){ return mdataHotValue; }
}
