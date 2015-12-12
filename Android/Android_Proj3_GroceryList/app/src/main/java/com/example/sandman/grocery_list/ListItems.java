package com.example.sandman.grocery_list;

/**
 * Created by Sandman on 10/7/2014.
 */
public class ListItems{
    private String itemName;
    ListItems(){
        itemName = "";
    }
    ListItems(String s){
        itemName = s;
    }
    @Override
    public String toString(){
        return itemName;
    }
}
