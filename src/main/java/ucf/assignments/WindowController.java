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

import java.math.BigDecimal;
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
    private TableView<Item> tableView;
    @FXML
    public TableColumn<Item, String> tcValue;
    @FXML
    public TableColumn<Item,String> tcSerial;
    @FXML
    public TableColumn<Item,String> tcName;

    private Inventory userInventory = new Inventory();
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

    @FXML
    public void bAddItem(ActionEvent actionEvent) {

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

    public void updateTableView(){
        catalog = FXCollections.observableArrayList(userInventory.getCatalog());
    }
}
