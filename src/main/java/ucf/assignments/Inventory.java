package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import java.util.ArrayList;

//This class manages items
public class Inventory {
    ArrayList<Item> catalog = new ArrayList<>();


    //This method adds an item to the catalog array list
    public void addItem(String name, String serialNumber, String value){
        Item addItem = new Item(name, serialNumber,value);
        catalog.add(addItem);
    }


    //This method removes an item from the catalog array list
    public void removeItem(int index){
        catalog.remove(index);
    }

    //This method edits an existing item in the catalog.
    public void editItem(int index, String name, String serialNumber, String value){
        Item editedItem =  new Item(name, serialNumber, value);
        catalog.set(index,editedItem);
    }

    //This method searches the catalog by serial number
    public ArrayList<Item> searchSerial(String serial){
        ArrayList<Item> foundItems = new ArrayList<>();
        for(Item item: catalog){
            String checkSerial = item.getSerialNumber();
            //check for exact match
            if(checkSerial.equalsIgnoreCase(serial)){
                foundItems.add(item);
            }else{
                //check for partial match
                serial = serial.toUpperCase();
                if(checkSerial.contains(serial)){
                    foundItems.add(item);
                }
            }
        }
        //Add an empty item if nothing is found
        if(foundItems.size()<1){
            Item noResults = new Item("","No search results found.","0");
            foundItems.add(noResults);
        }
        return foundItems;
    }

    //This method searches the catalog by name
    public ArrayList<Item> searchName(String name){
        ArrayList<Item> foundItems = new ArrayList<>();
        //Search each catalog element
        for (Item item : catalog) {
            String checkCatName = item.getName();
            //check for exact match
            if (checkCatName.equalsIgnoreCase(name)) {
                foundItems.add(item);
            }else{
                //check for partial match
                checkCatName = checkCatName.toLowerCase();
                name = name.toLowerCase();
                if(checkCatName.contains(name)){
                    foundItems.add(item);
                }
            }
        }
        //Add an empty item if nothing is found
        if(foundItems.size()<1){
            Item noResults = new Item("","No search results found.","0");
            foundItems.add(noResults);
        }
        return foundItems;
    }

    //get catalog

    public ArrayList<Item> getCatalog() {
        return catalog;
    }
}
