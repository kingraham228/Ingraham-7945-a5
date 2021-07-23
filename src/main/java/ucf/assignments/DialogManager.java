package ucf.assignments;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Optional;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

//This class creates dialog boxes.
public class DialogManager {

    //This method creates an error alert when item input is not valid.
    public void reportErrorItem(boolean name, boolean serial, boolean value) {
        String nameError = "Inventory items must have a name between 2 and 256 characters.\n\n";
        String serialError = """
                Inventory items must have a unique serial number in the format of XXXXXXXXXX where X can be a letter or digit.
                Serial numbers must be 10 characters long.
                                
                """;
        String valueError = "Inventory items must have a monetary value in US dollars.\n\n";
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

    //This method creates a general error alert
    public void reportError(String error){
        Alert generalError = new Alert(Alert.AlertType.ERROR);
        generalError.setContentText(error);
        generalError.show();
    }

    public String getSearchDialog(String instructions){
        TextInputDialog search = new TextInputDialog(instructions);
        search.setHeaderText("Search Item Inventory");
        Optional<String> userSearch = search.showAndWait();
        return userSearch.orElse("cancel search");

    }

    //This method creates a dialog box for editing an item
    public Item getEditItemDialog(String oldValue, String oldSerial, String oldName){
        Dialog<ArrayList<String>> dialog = new Dialog<>();
        dialog.setTitle("Edit Item");

        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType,ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));


        TextField textFValue = new TextField();
        TextField textFSerial = new TextField();
        TextField textFName = new TextField();

        //Set textfields with existing item data
        textFValue.setText(oldValue);
        textFSerial.setText(oldSerial);
        textFName.setText(oldName);

        gridPane.add(new Label("Item Value:"),0,0);
        gridPane.add(textFValue, 1, 0);
        gridPane.add(new Label("Item Serial Number:"),0,1);
        gridPane.add(textFSerial,1,1);
        gridPane.add(new Label("Item Name:"),0,2);
        gridPane.add(textFName,1,2);

        dialog.getDialogPane().setContent(gridPane);

        //Set result array
        ArrayList<String> editedArray = new ArrayList<>();

        dialog.setResultConverter(dialogButton ->{
            if (dialogButton == loginButtonType) {
                editedArray.add(textFValue.getText());
                editedArray.add(textFSerial.getText());
                editedArray.add(textFName.getText());
                return editedArray;
            }
            return null;
        });

        Optional<ArrayList<String>> result = dialog.showAndWait();

        if(result.isPresent()){
            return new Item(editedArray.get(2),editedArray.get(1),editedArray.get(0));
        }else{
            return new Item(oldName,oldSerial,oldValue);
        }
    }
}
