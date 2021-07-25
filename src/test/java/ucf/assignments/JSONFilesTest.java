package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//This class tests parsing data from JSON files
class JSONFilesTest {

    @Test
    @DisplayName("Parse JSON: First Element check, name")
        //try parsing a properly formatted file
    void parseJSON() {
        JSONFiles test = new JSONFiles();
        ArrayList<String> testFileData = new ArrayList<>();
        String test1 = "{\"value\" : \"$3,444.00\", \"serial\" : \"LLLWWWIII2\", \"name\" : \"item one\" },";
        String test2 = "{\"value\" : \"$42.00\", \"serial\" : \"III222MMMA\", \"name\" : \"item two\" }";
        testFileData.add(test1);
        testFileData.add(test2);

        ArrayList<Item> fileItems = test.parseJSON(testFileData);

        //Check the first name element
        String actual = fileItems.get(0).getName();
        String expected = "item one";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Parse JSON: second Element check")
        //try parsing a properly formatted file
    void parseJSON_2serial() {
        JSONFiles test = new JSONFiles();
        ArrayList<String> testFileData = new ArrayList<>();
        String test1 = "{\"value\" : \"$3,444.00\", \"serial\" : \"LLLWWWIII2\", \"name\" : \"item one\" },";
        String test2 = "{\"value\" : \"$42.00\", \"serial\" : \"III222MMMA\", \"name\" : \"item two\" }";
        testFileData.add(test1);
        testFileData.add(test2);

        ArrayList<Item> fileItems = test.parseJSON(testFileData);

        //Check the second element serial
        String actual = fileItems.get(1).getSerialNumber();
        String expected = "III222MMMA";

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Parse JSON: second Element check, value")
        //try parsing a properly formatted file
    void parseJSON_2value() {
        JSONFiles test = new JSONFiles();
        ArrayList<String> testFileData = new ArrayList<>();
        String test1 = "{\"value\" : \"$3,444.00\", \"serial\" : \"LLLWWWIII2\", \"name\" : \"item one\" },";
        String test2 = "{\"value\" : \"$42.00\", \"serial\" : \"III222MMMA\", \"name\" : \"item two\" }";
        testFileData.add(test1);
        testFileData.add(test2);

        ArrayList<Item> fileItems = test.parseJSON(testFileData);

        //Check the second element value
        String actual = fileItems.get(1).getValue();
        String expected = "$42.00";

        assertEquals(expected, actual);
    }
}