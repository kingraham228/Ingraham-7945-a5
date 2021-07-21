package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
//This class checks that user input is valid
public class InputValidator {

    //format dollars

    //check unique serial

    //format serial

    //This method checks if the inventory name is between 2 and 256 characters
    public boolean checkNameLength(String name){
        //interpreting "between" as not including 2 and 256
        return name.length() >= 3 && name.length() <= 255;
    }
}
