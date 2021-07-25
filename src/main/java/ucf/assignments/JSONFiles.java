package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import java.util.ArrayList;

//This class formats data to and from JSON files
public class JSONFiles {

    //This method formats output for JSON items
    public String formatJSONString(String value, String serial, String name) {
        return String.format("\t\t{\"value\" : \"%s\", \"serial\" : \"%s\", \"name\" : \"%s\" }", value, serial, name);
    }

    //This method formats the beginning header for a JSON file
    public String formatJSONHeader() {
        return String.format("{%n\t\"items\" : [%n");
    }

    //This method formats the ending footer for a JSON file
    public String formatJSONFooter() {
        return String.format("\t]%n}");
    }

    //This method parses existing json files into inventory items
    public ArrayList<Item> parseJSON(ArrayList<String> fileData) {
        InputValidator iv = new InputValidator();
        ArrayList<Item> fileItems = new ArrayList<>();

        for (String fileDatum : fileData) {
            String[] pieces = fileDatum.split("\",");
            if (pieces.length == 3) {
                String value = pieces[0];
                String serial = pieces[1];
                String name = pieces[2];

                //remove json formatting for value
                StringBuilder sbValue = new StringBuilder();
                char[] valueTrim = value.toCharArray();
                for (char c : valueTrim) {
                    char decimal = '.';
                    char comma = ',';
                    if (Character.isDigit(c) || decimal == c || comma == c) {
                        sbValue.append(c);
                    }
                }
                value = sbValue.toString();

                //remove json formatting for serial number
                serial = serial.replaceAll("serial", "");
                StringBuilder sbSerial = new StringBuilder();
                char[] serialTrim = serial.toCharArray();
                for (char c : serialTrim) {
                    if (Character.isLetterOrDigit(c)) {
                        sbSerial.append(c);
                    }
                }
                serial = sbSerial.toString();

                //remove json formatting for name
                name = name.replaceAll(" \"name\" : \"", "");
                name = name.replaceAll("\" },", "");
                name = name.replaceAll("\" }", "");

                //validate before adding item to the array
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
