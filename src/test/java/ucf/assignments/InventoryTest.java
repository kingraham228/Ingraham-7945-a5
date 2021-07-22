package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryTest {

    @Test
    @DisplayName("Add Item Test")
        //Try adding an item
    void addItem() {
        Inventory test = new Inventory();

        String name = "Test widget.";
        String serialNumber = "DEG674WBS9";
        String value = "$4.99";

        test.addItem(name,serialNumber,value);

        String actual = test.getCatalog().get(0).getName();
        String expected = "Test widget.";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Remove Item Test")
    //Try removing an item
    void removeItem() {
        Inventory test = new Inventory();

        //add item 1
        String name = "Test widget.";
        String serialNumber = "DEG674WBS9";
        String value = "$4.99";
        test.addItem(name,serialNumber,value);

        //add item 2
        String name2 = "Widget 2.";
        String serialNumber2 = "DE0074WBS9";
        String value2 = "$785";
        test.addItem(name2,serialNumber2,value2);

        //remove the first item
        test.removeItem(0);

        //check that second item is now the first item.
        String actual = test.getCatalog().get(0).getName();
        String expected = "Widget 2.";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Edit Item Test")
    //Try editing an existing item
    void editItem() {
        Inventory test = new Inventory();

        //add item 1
        String name = "Test widget.";
        String serialNumber = "DEG674WBS9";
        String value = "$4.99";
        test.addItem(name,serialNumber,value);

        //set edited values
        String name2 = "Widget 2.";
        String serialNumber2 = "ZE0074WBS9";
        String value2 = "$785";

        test.editItem(0,name2,serialNumber2,value2);

        //test that the item has changed
        String actual = test.getCatalog().get(0).getName();
        String expected = "Widget 2.";

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Search by Name: exact match")
    //try an exact match search
    void searchName() {
        Inventory test = new Inventory();

        //add item 1
        String name = "Test widget.";
        String serialNumber = "DEG674WBS9";
        String value = "$4.99";
        test.addItem(name,serialNumber,value);

        //add item 2
        String name2 = "Widget 2.";
        String serialNumber2 = "DE0074WBS9";
        String value2 = "$785";
        test.addItem(name2,serialNumber2,value2);

        String search = "Test widget.";

        ArrayList<Item> results = test.searchName(search);

        //Check expected array size
        int actualSize = results.size();
        int expectedSize = 1;

        assertEquals(expectedSize, actualSize);

        //Check that it is the correct item
        String actual = results.get(0).getName();
        String expected = "Test widget.";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Search by Name: partial match")
        //try a partial match search
    void searchName_partial() {
        Inventory test = new Inventory();

        //add item 1
        String name = "Test widget.";
        String serialNumber = "DEG674WBS9";
        String value = "$4.99";
        test.addItem(name,serialNumber,value);

        //add item 2
        String name2 = "Widget 2.";
        String serialNumber2 = "DE0074WBS9";
        String value2 = "$785";
        test.addItem(name2,serialNumber2,value2);

        String search = "widget";

        ArrayList<Item> results = test.searchName(search);

        //Check expected array size
        int actualSize = results.size();
        int expectedSize = 2;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @DisplayName("Search by Name: no match")
        //try a no match search
    void searchName_noMatch() {
        Inventory test = new Inventory();

        //add item 1
        String name = "Test widget.";
        String serialNumber = "DEG674WBS9";
        String value = "$4.99";
        test.addItem(name,serialNumber,value);

        //add item 2
        String name2 = "Widget 2.";
        String serialNumber2 = "DE0074WBS9";
        String value2 = "$785";
        test.addItem(name2,serialNumber2,value2);

        String search = "shady";

        ArrayList<Item> results = test.searchName(search);

        //Check no result
        String actual = results.get(0).getName();
        String expected = "No search results found.";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Search by Serial: Exact match")
    //Try an exact match
    void searchSerial() {
        Inventory test = new Inventory();

        //add item 1
        String name = "Test widget.";
        String serialNumber = "DEG674WBS9";
        String value = "$4.99";
        test.addItem(name,serialNumber,value);

        //add item 2
        String name2 = "Widget 2.";
        String serialNumber2 = "B7S9981140";
        String value2 = "$785";
        test.addItem(name2,serialNumber2,value2);

        String search = "B7S9981140";

        ArrayList<Item> results = test.searchSerial(search);

        //Check expected array size
        int actualSize = results.size();
        int expectedSize = 1;

        assertEquals(expectedSize, actualSize);

        //Check that it is the correct item
        String actual = results.get(0).getSerialNumber();
        String expected = "B7S9981140";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Search by Serial: Partial match")
        //Try a partial match
    void searchSerial_partial() {
        Inventory test = new Inventory();

        //add item 1
        String name = "Test widget.";
        String serialNumber = "DEG674WBS9";
        String value = "$4.99";
        test.addItem(name,serialNumber,value);

        //add item 2
        String name2 = "Widget 2.";
        String serialNumber2 = "B7S9981140";
        String value2 = "$785";
        test.addItem(name2,serialNumber2,value2);

        String search = "7";

        ArrayList<Item> results = test.searchSerial(search);

        //Check expected array size
        int actualSize = results.size();
        int expectedSize = 2;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @DisplayName("Search by Serial: No match")
        //Try a no match
    void searchSerial_noMatch() {
        Inventory test = new Inventory();

        //add item 1
        String name = "Test widget.";
        String serialNumber = "DEG674WBS9";
        String value = "$4.99";
        test.addItem(name,serialNumber,value);

        //add item 2
        String name2 = "Widget 2.";
        String serialNumber2 = "B7S9981140";
        String value2 = "$785";
        test.addItem(name2,serialNumber2,value2);

        String search = "0000000000";

        ArrayList<Item> results = test.searchSerial(search);

        //Check no result
        String actual = results.get(0).getSerialNumber();
        String expected = "NO SEARCH RESULTS FOUND.";

        assertEquals(expected,actual);
    }
}