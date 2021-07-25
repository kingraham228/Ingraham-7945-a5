package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import java.util.ArrayList;

//This class checks that user input is valid
public class InputValidator {

    //This method checks that value field is a monetary value in US dollars
    public boolean checkValue(String value) {
        //check for length of at least 1 for a valid entry
        if (value.length() < 1) {
            return false;
        } else {
            char[] valueArray = value.toCharArray();
            //check that the remaining characters are numbers or a decimal
            int decimalCount = 0;
            int dollarCount = 0;
            for (char c : valueArray) {
                if (!Character.isDigit(c)) {
                    if (c == '.') {
                        decimalCount++;
                    } else {
                        if (c == '$') {
                            dollarCount++;
                        } else {
                            if (c != ',') {
                                return false;
                            }
                        }
                    }
                }
            }
            //check number of decimals and dollar signs
            return decimalCount <= 1 && dollarCount <= 1;
        }
    }

    //This method checks to see if a serial number already exists in the inventory
    public boolean checkUniqueSerial(ArrayList<Item> catalog, String serial) {
        for (Item item : catalog) {
            if (item.getSerialNumber().equalsIgnoreCase(serial)) {
                return false;
            }
        }
        return true;
    }

    //This method checks that the serial number is 10 characters with only letters or digits
    public boolean checkFormatSerial(String serial) {
        //Check length
        if (serial.length() != 10) {
            return false;
        } else {
            //Check for letters and digits
            char[] serialArray = serial.toCharArray();
            for (char c : serialArray) {
                if (!Character.isLetterOrDigit(c)) {
                    return false;
                }
            }
            return true;
        }
    }

    //This method checks if the inventory name is between 2 and 256 characters
    public boolean checkNameLength(String name) {
        //interpreting "between (inclusive)" as including 2 and 256
        return name.length() >= 2 && name.length() <= 256;
    }
}
