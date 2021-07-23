package ucf.assignments;

import java.util.ArrayList;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
//This class checks that user input is valid
public class InputValidator {

    //This method checks that value field is a monetary value in US dollars
    public boolean checkValue(String value) {
        //check for length of at least 2 for a valid entry
        if (value.length() < 1) {
            return false;
        } else {
            char[] valueArray = value.toCharArray();
                //check that the remaining characters are numbers or a decimal
                int decimalCount = 0;
                int dollarCount = 0;
                int decimalIndex = -1;
                for (int i = 0; i < valueArray.length; i++) {
                    if (!Character.isDigit(valueArray[i])) {
                        if (valueArray[i] == '.') {
                            decimalIndex = i;
                            decimalCount++;
                        } else {
                            if(valueArray[i] == '$'){
                                dollarCount++;
                            }else{
                                if(valueArray[i]==','){
                                    //do nothing. Commas will be pulled out in item construction
                                }else{
                                    return false;
                                }
                            }
                        }
                    }
                }

                //check number of decimals
                if(decimalCount>1||dollarCount>1){
                    return false;
                }else{
                    //check that there are 2 digits after the decimal if there is one
                    if(decimalCount==1){
                        return (valueArray.length - decimalIndex) == 3;
                    }else{
                        return true;
                    }
                }
        }
    }

    //This method checks to see if a serial number already exists in the inventory
    public boolean checkUniqueSerial(ArrayList<Item> catalog, String serial){
        for (Item item : catalog) {
            if (item.getSerialNumber().equalsIgnoreCase(serial)) {
                return false;
            }
        }
        return true;
    }

    //This method checks that the serial number is 10 characters with only letters or digits
    public boolean checkFormatSerial(String serial){
        //Check length
        if(serial.length()!=10){
            return false;
        } else{
            //Check for letters and digits
            char [] serialArray = serial.toCharArray();
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
        //interpreting "between" as not including 2 and 256
        return name.length() >= 3 && name.length() <= 255;
    }
}
