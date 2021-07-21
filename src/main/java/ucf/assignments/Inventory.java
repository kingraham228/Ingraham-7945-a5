package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import java.util.ArrayList;

//This class manages items
public class Inventory {
    ArrayList<Item> catalog = new ArrayList<>();


    //add item function
    public void addItem(String name, String serialNumber, String value){
        //call item constructor with string string string
        Item addItem = new Item(name, serialNumber,value);
        //add the item to the list
        catalog.add(addItem);
    }


    //remove item function

    //edit item function

    //sort items function

    //search serial number function

    //search name function

    //get catalog

    public ArrayList<Item> getCatalog() {
        return catalog;
    }
}
