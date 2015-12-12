package com.example.sandman.xml_reader;

import java.util.ArrayList;

/**
 * Created by Sandman on 11/16/2014.
 */
public class Planet {
    ArrayList<Satellite> sats;
    String pName;
    String pSize;
    String pMoons;
    String pDist;
    public Planet(String name, String size, String moons, String distance){
        sats = new ArrayList<Satellite>();
        pName = name;
        pSize = size;
        pMoons = moons;
        pDist = distance;
    }
    public void addSat(String name, String size){
        sats.add(new Satellite(name, size));
    }
    public String toString(){
        if(pName.equals("Jupiter"))
            return sats.get(2).toString();
        else
            return "";
    }
    class Satellite{
        String satName;
        String satSize;
        public Satellite(String name, String size){
            satName = name; satSize = size;
        }
        public String toString(){
            String temp = "Name: " + satName + ", Size: " + satSize;
            return temp;
        }
    }
}
