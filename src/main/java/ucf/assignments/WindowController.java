package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class WindowController implements Initializable {

    @FXML
    public TextField tfValue;
    @FXML
    public TextField tfSerial;
    @FXML
    public TextField tfName;
    @FXML
    public TableColumn<Item, String> tcValue;
    @FXML
    public TableColumn<Item, String> tcSerial;
    @FXML
    public TableColumn<Item, String> tcName;
    @FXML
    private TableView<Item> tableView;
    private Inventory userInventory = new Inventory();
    private InputValidator iv = new InputValidator();
    private DialogManager dm = new DialogManager();
    private ObservableList<Item> catalog;

    @FXML
    public void mOpen(ActionEvent actionEvent) {
    }

    @FXML
    public void mSave(ActionEvent actionEvent) {
    }

    @FXML
    public void mSearchName(ActionEvent actionEvent) {
    }

    @FXML
    public void mSearchSerial(ActionEvent actionEvent) {
    }


    //This method adds an item to the inventory when the "Add Item" button is clicked.
    @FXML
    public void bAddItem(ActionEvent actionEvent) {
        //check for valid input
        boolean nameValid = iv.checkNameLength(tfName.getText());
        boolean serialValid = iv.checkFormatSerial(tfSerial.getText());
        boolean valueValid = iv.checkValue(tfValue.getText());

        //Check if the serial number already exists
        if(serialValid){
            if(userInventory.getCatalog().size()>0){
                serialValid = iv.checkUniqueSerial(userInventory.getCatalog(), tfSerial.getText());
            }
        }

        //if valid, add items
        if (nameValid && valueValid && serialValid) {
            userInventory.addItem(tfName.getText(), tfSerial.getText(), tfValue.getText());
            updateTableView();

            //clear text fields
            tfName.clear();
            tfSerial.clear();
            tfValue.clear();
        } else {
            //if not valid, send an error
            dm.reportErrorItem(nameValid, serialValid, valueValid);
        }

    }

    //This method removes an item from the table when the "Delete Item" button is clicked
    @FXML
    public void bDeleteItem(ActionEvent actionEvent) {
       //if the item exists, remove it from the list
        if(userInventory.getCatalog().size()>0){
           int index = tableView.getSelectionModel().getSelectedIndex();
           userInventory.removeItem(index);
           updateTableView();
       }else{
            //send an error if there are not items to delete
            dm.reportErrorEmpty();
        }

    }

    @FXML
    public void tvSort(SortEvent<TableView<Item>> tableViewSortEvent) {
    }
    //This method initializes the table view columns.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up table columns
        tcValue.setCellValueFactory(new PropertyValueFactory<Item, String>("value"));
        tcSerial.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        tcName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
    }

    //This method updates teh items in the tableview.
    public void updateTableView() {
        catalog = FXCollections.observableArrayList(userInventory.getCatalog());
        tableView.setItems(catalog);
    }

    //This method allows a user to edit a table item when the "Edit Item" button is clicked.
    public void bEdit(ActionEvent actionEvent) {
    }
}
