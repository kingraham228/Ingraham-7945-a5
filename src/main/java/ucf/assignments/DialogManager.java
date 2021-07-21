package ucf.assignments;

import javafx.scene.control.Alert;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

//This class creates dialog boxes.
public class DialogManager {

    //This method creates an error alert when item input is not valid.
    public void reportErrorItem(boolean name, boolean serial, boolean value) {
        String nameError = "Inventory items must have a name between 2 and 256 characters.\n";
        String serialError = "Inventory items must have a unique serial number in the format" +
                " of XXXXXXXXXX where X can be a letter or digit.\n";
        String valueError = """
                Inventory items must have a monetary value in US dollars in the format $0.00
                Example: $4.99 or $364
                """;

        StringBuilder sb = new StringBuilder();

        //add appropriate error text to the string builder
        if(!name){
            sb.append(nameError);
        }
        if(!serial){
            sb.append(serialError);
        }
        if(!value){
            sb.append(valueError);
        }

        //Create an error alert with the appropriate text
        Alert itemError = new Alert(Alert.AlertType.ERROR);
        itemError.setContentText(sb.toString());
        itemError.show();
    }
}
