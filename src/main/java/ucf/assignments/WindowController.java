package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
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
    public Button idDeleteButton;
    @FXML
    public Button idEditButton;
    @FXML
    public Button idViewAllButton;
    @FXML
    public Button idAddItemButton;
    @FXML
    private TableView<Item> tableView;

    private final Inventory userInventory = new Inventory();
    private final InputValidator iv = new InputValidator();
    private final DialogManager dm = new DialogManager();
    private final FileManager fm = new FileManager();

    //This method allows the user to open a file and load items into the inventory
    @FXML
    public void mOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Inventory");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Path filePath = Path.of(file.getPath());
            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, file.getName().length());
            ArrayList<Item> fileItems = fm.loadInventory(filePath, fileExtension);
            //send an error if the parser did not add any items
            if (fileItems.size() < 1) {
                dm.reportError("No items loaded. Please check the formatting of the file you are opening.\nSupported formats are TSV and HTML.");
            } else {
                //check for unique serial numbers
                for (Item fileItem : fileItems) {
                    boolean serialUnique = iv.checkUniqueSerial(userInventory.getCatalog(), fileItem.getSerialNumber());
                    if (serialUnique) {
                        userInventory.addItem(fileItem.getName(), fileItem.getSerialNumber(), fileItem.getValue());
                    }
                }
            }
            updateTableView();
        }
    }

    //This method allows the user to save the inventory to a file
    @FXML
    public void mSave() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Inventory");
        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Tab-Separated Value  (*.txt)", "*.txt");
        FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML  (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(txtFilter);
        fileChooser.getExtensionFilters().add(htmlFilter);
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            String filePath = file.getPath();
            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, file.getName().length());
            fm.saveInventory(userInventory.getCatalog(), filePath, fileExtension);
        }
    }

    //This method allows the user to search the inventory by name and display results
    @FXML
    public void mSearchName() {
        String userSearch = dm.getSearchDialog("Enter the item name");
        if (!userSearch.equals("cancel search")) {
            ArrayList<Item> foundItems = userInventory.searchName(userSearch);
            tableView.setItems(FXCollections.observableArrayList(foundItems));

            //Make View All button visible
            idViewAllButton.setVisible(true);

            //Hide inventory buttons in search result view
            idDeleteButton.setVisible(false);
            idEditButton.setVisible(false);
            idAddItemButton.setVisible(false);
            tfValue.setVisible(false);
            tfSerial.setVisible(false);
            tfName.setVisible(false);
        }
    }

    //This method allows the user to search the inventory by serial number and display results
    @FXML
    public void mSearchSerial() {
        String userSearch = dm.getSearchDialog("Enter the item serial number");
        if (!userSearch.equals("cancel search")) {
            ArrayList<Item> foundItems = userInventory.searchSerial(userSearch);
            tableView.setItems(FXCollections.observableArrayList(foundItems));

            //Make View All button visible
            idViewAllButton.setVisible(true);

            //Hide inventory buttons in search result view
            idDeleteButton.setVisible(false);
            idEditButton.setVisible(false);
            idAddItemButton.setVisible(false);
            tfValue.setVisible(false);
            tfSerial.setVisible(false);
            tfName.setVisible(false);
        }
    }

    //This method adds an item to the inventory when the "Add Item" button is clicked.
    @FXML
    public void bAddItem() {
        //check for valid input
        boolean nameValid = iv.checkNameLength(tfName.getText());
        boolean serialValid = iv.checkFormatSerial(tfSerial.getText());
        boolean valueValid = iv.checkValue(tfValue.getText());

        //Check if the serial number already exists
        if (serialValid) {
            if (userInventory.getCatalog().size() > 0) {
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
    public void bDeleteItem() {
        //if the item exists, remove it from the list
        if (!tableView.getSelectionModel().isEmpty()) {
            int index = tableView.getSelectionModel().getSelectedIndex();
            userInventory.removeItem(index);
            updateTableView();
        } else {
            //send an error if there are not items to delete
            dm.reportError("You must select an item before you can delete it.");
        }
    }

    //This method initializes the table view columns.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up table columns
        tcValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        tcSerial.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));

        //hide View All button until needed
        idViewAllButton.setVisible(false);
    }

    //This method updates the items in the tableview.
    public void updateTableView() {
        ObservableList<Item> catalog = FXCollections.observableArrayList(userInventory.getCatalog());
        tableView.setItems(catalog);
    }

    //This method allows a user to edit a table item when the "Edit Item" button is clicked.
    @FXML
    public void bEdit() {
        if (!tableView.getSelectionModel().isEmpty()) {
            Item oldItem = tableView.getSelectionModel().getSelectedItem();
            int index = tableView.getSelectionModel().getSelectedIndex();
            Item editedItem = dm.getEditItemDialog(oldItem.getValue(), oldItem.getSerialNumber(), oldItem.getName());

            //Validate user inputs
            boolean nameValid = iv.checkNameLength(editedItem.getName());
            boolean serialValid = iv.checkFormatSerial(editedItem.getSerialNumber());
            boolean valueValid = iv.checkValue(editedItem.getValue());

            //if valid, replace the old item with the edited item
            if (nameValid && serialValid && valueValid) {
                //call without a serial number pre-duplicate check
                userInventory.editItem(index, editedItem.getName(), "", editedItem.getValue());
                //check for duplicate serial number
                serialValid = iv.checkUniqueSerial(userInventory.getCatalog(), editedItem.getSerialNumber());
                if (!serialValid) {
                    //send a duplicate serial number error and rollback catalog update
                    dm.reportError("Item serial numbers must be unique.");
                    userInventory.editItem(index, oldItem.getName(), oldItem.getSerialNumber(), oldItem.getValue());
                } else {
                    userInventory.editItem(index, editedItem.getName(), editedItem.getSerialNumber(), editedItem.getValue());
                    updateTableView();
                }
            } else {
                //send format item error if inputs were not valid
                dm.reportErrorItem(nameValid, serialValid, valueValid);
            }
        } else {
            //send an error for no item selected
            dm.reportError("An item must be selected before you can edit an item.");
        }
    }

    //This method returns the user to a view of all inventory items when the "View All" button is clicked.
    @FXML
    public void bViewAll() {
        updateTableView();
        //make inventory buttons visible
        idDeleteButton.setVisible(true);
        idEditButton.setVisible(true);
        idAddItemButton.setVisible(true);
        tfValue.setVisible(true);
        tfSerial.setVisible(true);
        tfName.setVisible(true);

        //hide view all button
        idViewAllButton.setVisible(false);
    }
}
