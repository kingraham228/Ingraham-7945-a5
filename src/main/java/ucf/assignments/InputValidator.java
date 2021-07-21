package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
//This class checks that user input is valid
public class InputValidator {

    //This method checks that value field is a monetary value in US dollars
    public boolean checkValue(String value) {
        //check for length of at least 2 for a valid entry
        if (value.length() < 2) {
            return false;
        } else {
            char[] valueArray = value.toCharArray();

            //check first character for a $
            if (valueArray[0] != '$') {
                return false;
            } else {
                //check that the remaining characters are numbers or a decimal
                int decimalCount = 0;
                int decimalIndex = -1;
                for (int i = 1; i < valueArray.length; i++) {
                    if (!Character.isDigit(valueArray[i])) {
                        if (valueArray[i] == '.') {
                            decimalIndex = i;
                            decimalCount++;
                        } else {
                            return false;
                        }
                    }
                }

                //check number of decimals
                if(decimalCount>1){
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
    }

    //check unique serial

    //This method checks that the serial number is 10 characters with only letters or digits
    public boolean checkFormatSerial(String serial){
        //Check length
        if(serial.length()!=10){
            return false;
        } else{
            //Check for letters and digits
            char [] serialArray = serial.toCharArray();
            for(int i =0; i<serialArray.length; i++){
                if(!Character.isLetterOrDigit(serialArray[i])){
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
