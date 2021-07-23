package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    @DisplayName("Number Formatting: add $")
    //try without a dollar sign
    void formatValue() {
        String value = "2.99";
        Item test = new Item("test item","000sssuuu8",value);
        String actual = test.getValue();
        String expected = "$2.99";
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Number Formatting: with $")
        //try with a dollar sign
    void formatValue_with$() {
        String value = "$2.99";
        Item test = new Item("test item","000sssuuu8",value);
        String actual = test.getValue();
        String expected = "$2.99";
        assertEquals(expected,actual);
    }
}