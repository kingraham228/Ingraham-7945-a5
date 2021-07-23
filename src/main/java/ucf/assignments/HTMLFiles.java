package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
import java.util.ArrayList;


public class HTMLFiles {

    //This method formats output for HTML items
    public String formatHTMLString(String value, String serial, String name){
        return String.format("<tr>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n</tr>\n", value,serial,name);
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
}
