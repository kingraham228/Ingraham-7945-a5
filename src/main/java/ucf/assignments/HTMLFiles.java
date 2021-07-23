package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
import java.util.ArrayList;


public class HTMLFiles {

    //This method formats output for HTML items
    public String formatHTMLString(String value, String serial, String name){
        return String.format("<tr>\n<td>%s</td><td>%s</td><td>%s</td>\n</tr>\n", value,serial,name);
    }

    //This method formats the beginning header for an HTML table
    public ArrayList<String> formatHTMLHeading(){
        ArrayList<String> htmlHeaders = new ArrayList<>();
        htmlHeaders.add("<!DOCTYPE html>\n");
        htmlHeaders.add("<html>\n");
        htmlHeaders.add("<body>\n");
        htmlHeaders.add("<h2>Inventory</h2>\n");
        htmlHeaders.add("<table>\n");
        //add column headings
        htmlHeaders.add("<tr>\n");
        htmlHeaders.add("<th>Value</th>\n");
        htmlHeaders.add("<th>Serial Number</th>\n");
        htmlHeaders.add("<th>Name</th>\n");
        htmlHeaders.add("</tr>\n");
        return htmlHeaders;
    }

    //This method formats the ending footer for an HTML table
    public ArrayList<String> formatHtmlFooter(){
        ArrayList<String> htmlFooters = new ArrayList<>();
        htmlFooters.add("</table>\n");
        htmlFooters.add("</body>\n");
        htmlFooters.add("</html>");
        return htmlFooters;
    }

    //This method parses existing html files into inventory items
    public ArrayList<Item> parseHTML(ArrayList<String> fileData){
        InputValidator iv = new InputValidator();
        ArrayList<Item> fileItems = new ArrayList<>();

        for (String fileDatum : fileData) {
            String[] pieces = fileDatum.split("</td>");
            if (pieces.length == 3) {
                String value = pieces[0];
                String serial = pieces[1];
                String name = pieces[2];

                //remove <td> tags
                value = value.replaceAll("<td>", "");
                serial = serial.replaceAll("<td>", "");
                name = name.replaceAll("<td>", "");

                //validate input before adding it to the array
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
