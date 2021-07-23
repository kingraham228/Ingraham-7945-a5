package ucf.assignments;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
public class FileManager {

    //This method saves the inventory to a file
    public void saveInventory(ArrayList<Item> inventory, String filePath, String fileExtension) {
        try (Formatter output = new Formatter(filePath)) {
            //print each item in the inventory to the file
            for (Item inventoryItem : inventory) {
                String name = inventoryItem.getName();
                String serial = inventoryItem.getSerialNumber();
                String value = inventoryItem.getValue();

                if (fileExtension.equalsIgnoreCase("txt")) {
                    String item = formatTSV(value, serial, name);
                    output.format(item);
                }
            }
        } catch (SecurityException | FileNotFoundException | FormatterClosedException e) {
            e.printStackTrace();
        }

    }

    //This method formats output for tab-separated values
    public String formatTSV(String value, String serial, String name) {
        return String.format("%s\t%s\t%s%n", value, serial, name);
    }

    //This method opens inventory lists from a file
    public ArrayList<Item> loadInventory(Path filename, String fileExtension) {
        ArrayList<String> fileData = new ArrayList<>();
        try (Scanner input = new Scanner(filename)) {
            while (input.hasNext()) {
                String nextString = input.nextLine();
                fileData.add(nextString);
            }
        } catch (IOException | NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        }
        //return appropriately parsed data
        return parseFileData(fileData,fileExtension);
    }

    //This method parses file data into an arraylist of inventory items
    public ArrayList<Item> parseFileData(ArrayList<String> fileData, String fileExtension){
        InputValidator iv = new InputValidator();
        ArrayList<Item> fileItems = new ArrayList<>();
        //parse according to file extension
        //for TSV files
        if(fileExtension.equalsIgnoreCase("txt")){
            for(String analyze: fileData){
                String [] pieces = analyze.split("\t");
                if(pieces.length==3){
                    String value = pieces[0];
                    String serial = pieces[1];
                    String name = pieces[2];

                    //validate input before adding to the array
                    boolean valueValid =iv.checkValue(value);
                    boolean serialValid = iv.checkFormatSerial(serial);
                    boolean nameValid = iv.checkNameLength(name);
                    if(valueValid&&serialValid&&nameValid){
                        Item addItem = new Item(name,serial,value);
                        fileItems.add(addItem);
                    }
                }
            }
        }
        return fileItems;
    }

}
