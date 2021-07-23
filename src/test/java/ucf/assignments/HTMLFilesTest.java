package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HTMLFilesTest {

    @Test
    @DisplayName("Parse HTML Proper Format Test")
        //Try a properly formatted file
    void parseHTML() {
        HTMLFiles test = new HTMLFiles();
        ArrayList<String> testFileData = new ArrayList<>();
        String test1 = "<td>$3,444.00</td><td>LLLWWWIII2</td><td>item one</td>";
        String test2 = "<td>$42.00</td><td>III222MMMA</td><td>item two</td>";
        testFileData.add(test1);
        testFileData.add(test2);

        ArrayList<Item> fileItems = test.parseHTML(testFileData);

        //Check the first name element
        String actual = fileItems.get(0).getName();
        String expected = "item one";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Parse HTML Proper Format Test: serial two")
        //Try a properly formatted file with a check of second element serial
    void parseHTML_serial() {
        HTMLFiles test = new HTMLFiles();
        ArrayList<String> testFileData = new ArrayList<>();
        String test1 = "<td>$3,444.00</td><td>LLLWWWIII2</td><td>item one</td>";
        String test2 = "<td>$42.00</td><td>III222MMMA</td><td>item two</td>";
        testFileData.add(test1);
        testFileData.add(test2);

        ArrayList<Item> fileItems = test.parseHTML(testFileData);

        //Check the second item serial
        String actual = fileItems.get(1).getSerialNumber();
        String expected = "III222MMMA";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Parse HTML Proper Format Test: value two")
        //Try a properly formatted file with a check of second element value
    void parseHTML_value() {
        HTMLFiles test = new HTMLFiles();
        ArrayList<String> testFileData = new ArrayList<>();
        String test1 = "<td>$3,444.00</td><td>LLLWWWIII2</td><td>item one</td>";
        String test2 = "<td>$42.00</td><td>III222MMMA</td><td>item two</td>";
        testFileData.add(test1);
        testFileData.add(test2);

        ArrayList<Item> fileItems = test.parseHTML(testFileData);

        //Check the second item serial
        String actual = fileItems.get(1).getValue();
        String expected = "$42.00";

        assertEquals(expected, actual);
    }
}