package ucf.assignments;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
public class FileManager {

    //This method saves the inventory to a file
    public void saveInventory(ArrayList<Item> inventory, String filePath, String fileExtension){
        try(Formatter output = new Formatter(filePath)){
            //print each item in the inventory to the file
            for (Item inventoryItem : inventory) {
                String name = inventoryItem.getName();
                String serial = inventoryItem.getSerialNumber();
                String value = inventoryItem.getValue();
                String item = "File save error.";

                if(fileExtension.equalsIgnoreCase("txt")){
                    item = formatTSV(value,serial,name);
                }
                output.format(item);
            }
        } catch (SecurityException | FileNotFoundException | FormatterClosedException e) {
            e.printStackTrace();
        }

    }

    public String formatTSV(String value, String serial, String name){
        return String.format("%s\t%s\t%s%n", value,serial,name);
    }

}
