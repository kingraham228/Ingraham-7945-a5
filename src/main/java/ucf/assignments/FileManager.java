package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

//This class manages saving and loading files
public class FileManager {
    HTMLFiles html = new HTMLFiles();
    TSVFiles tsv = new TSVFiles();
    JSONFiles json = new JSONFiles();

    //This method saves the inventory to a file
    public void saveInventory(ArrayList<Item> inventory, String filePath, String fileExtension) {
        try (Formatter output = new Formatter(filePath)) {
            //set file headers if needed
            if (fileExtension.equalsIgnoreCase("html")) {
                ArrayList<String> headers = html.formatHTMLHeading();
                for (String header : headers) {
                    output.format(header);
                }
            }
            if(fileExtension.equalsIgnoreCase("json")){
                output.format(json.formatJSONHeader());
            }

            //print each item in the inventory to the file
            for (int i=0; i< inventory.size(); i++) {
                String name = inventory.get(i).getName();
                String serial = inventory.get(i).getSerialNumber();
                String value = inventory.get(i).getValue();

                //TSV
                if (fileExtension.equalsIgnoreCase("txt")) {
                    String item = tsv.formatTSV(value, serial, name);
                    output.format(item);
                } else {
                    //HTML
                    if (fileExtension.equalsIgnoreCase("html")) {
                        String item = html.formatHTMLString(value, serial, name);
                        output.format(item);
                    } else{
                        //JSON
                        if(fileExtension.equalsIgnoreCase("json")){
                            //account for commas between all but the last item
                            String item = json.formatJSONString(value,serial,name);
                            if(i<(inventory.size())-1){
                                output.format(item+",\n");
                            }else{
                                output.format(item+"\n");
                            }
                        }
                    }
                }
            }
            //set file footers if needed
            if (fileExtension.equalsIgnoreCase("html")) {
                ArrayList<String> footers = html.formatHtmlFooter();
                for (String footer : footers) {
                    output.format(footer);
                }
            }
            if(fileExtension.equalsIgnoreCase("json")){
                output.format(json.formatJSONFooter());
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
        if (fileExtension.equalsIgnoreCase("html")) {
            return html.parseHTML(fileData);
        }
        if (fileExtension.equalsIgnoreCase("json")){
            return json.parseJSON(fileData);
        }
        return fileItems;
    }
}
