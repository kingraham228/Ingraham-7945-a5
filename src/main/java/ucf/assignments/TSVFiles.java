package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import java.util.ArrayList;

//This class formats data for Tab-separated value(TSV) files
public class TSVFiles {

    //This method formats output for tab-separated values
    public String formatTSV(String value, String serial, String name) {
        return String.format("%s\t%s\t%s%n", value, serial, name);
    }

    //This method parses tab-separated values
    //Note: Testing for this function is integrated in the FileManager parseFileData() testing
    public ArrayList<Item> parseTSV(ArrayList<String> fileData) {
        InputValidator iv = new InputValidator();
        ArrayList<Item> fileItems = new ArrayList<>();
        for (String analyze : fileData) {
            String[] pieces = analyze.split("\t");
            if (pieces.length == 3) {
                String value = pieces[0];
                String serial = pieces[1];
                String name = pieces[2];

                //validate input before adding to the array
                boolean valueValid = iv.checkValue(value);
                boolean serialValid = iv.checkFormatSerial(serial);
                boolean nameValid = iv.checkNameLength(name);
                if (valueValid && serialValid && nameValid) {
                    Item addItem = new Item(name, serial, value);
                    fileItems.add(addItem);
                }
            }
        }
        return fileItems;
    }
}
