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

    @FXML
    public void tvEdit(InputMethodEvent inputMethodEvent) {
    }

    //This method adds an item to the inventory when the "Add Item" button is clicked.
    @FXML
    public void bAddItem(ActionEvent actionEvent) {
        //check for valid input
        boolean nameValid = iv.checkNameLength(tfName.getText());
        boolean serialValid = true;
        boolean valueValid = iv.checkValue(tfValue.getText());

        //if valid, add items
        if (nameValid && valueValid) {
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


    @FXML
    public void bDeleteItem(ActionEvent actionEvent) {
    }

    @FXML
    public void tvSort(SortEvent<TableView<Item>> tableViewSortEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up table columns
        tcValue.setCellValueFactory(new PropertyValueFactory<Item, String>("value"));
        tcSerial.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        tcName.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
    }

    public void updateTableView() {
        catalog = FXCollections.observableArrayList(userInventory.getCatalog());
        tableView.setItems(catalog);
    }
}
