package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class FileManager {
    HTMLFiles html = new HTMLFiles();
    TSVFiles tsv = new TSVFiles();

    //This method saves the inventory to a file
    public void saveInventory(ArrayList<Item> inventory, String filePath, String fileExtension) {
        try (Formatter output = new Formatter(filePath)) {
            //set file headers if needed
            if(fileExtension.equalsIgnoreCase("html")){
                ArrayList<String> headers = html.formatHTMLHeading();
                for (String header : headers) {
                    output.format(header);
                }
            }
            //print each item in the inventory to the file
            for (Item inventoryItem : inventory) {
                String name = inventoryItem.getName();
                String serial = inventoryItem.getSerialNumber();
                String value = inventoryItem.getValue();

                //TSV
                if (fileExtension.equalsIgnoreCase("txt")) {
                    String item = tsv.formatTSV(value, serial, name);
                    output.format(item);
                }else{
                    //HTML
                    if(fileExtension.equalsIgnoreCase("html")){
                        String item = html.formatHTMLString(value,serial,name);
                        output.format(item);
                    }
                }
            }

            //set file footers if needed
            if(fileExtension.equalsIgnoreCase("html")){
                ArrayList<String> footers = html.formatHtmlFooter();
                for (String footer : footers) {
                    output.format(footer);
                }
            }
        } catch (SecurityException | FileNotFoundException | FormatterClosedException e) {
            e.printStackTrace();
        }
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
        return parseFileData(fileData, fileExtension);
    }

    //This method parses file data into an arraylist of inventory items based on file extension
    public ArrayList<Item> parseFileData(ArrayList<String> fileData, String fileExtension) {
        ArrayList<Item> fileItems = new ArrayList<>();
        //parse according to file extension
        if (fileExtension.equalsIgnoreCase("txt")) {
            return tsv.parseTSV(fileData);
        }
        if(fileExtension.equalsIgnoreCase("html")){
            return html.parseHTML(fileData);
        }
        return fileItems;
    }

}
