package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

//This class formats data to and from JSON files
public class JSONFiles {

    //This method formats output for JSON items
    public String formatJSONString(String value, String serial, String name){
        return String.format("\t\t{\"value\" : \"%s\", \"serial\" : \"%s\", \"name\" : \"%s\" }",value,serial,name);
    }

    //This method formats the beginning header for a JSON file
    public String formatJSONHeader(){
        return String.format("{%n\t\"items\" : [%n");
    }

    //This method formats the ending footer for a JSON file
    public String formatJSONFooter(){
        return String.format("\t]%n}");
    }
}
