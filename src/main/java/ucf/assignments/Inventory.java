package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class Inventory {
    ArrayList<Item> catalog = new ArrayList<>();
    //Create input validator


    //add item function
    public void addItem(String name, String serialNumber, String value){
        //examine input fields for validity
            //if valid
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
