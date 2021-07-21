package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}