package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import javafx.beans.property.SimpleStringProperty;

import java.text.NumberFormat;
import java.util.ArrayList;

//This class provides the object Item and access to its properties.
public class Item {
    private SimpleStringProperty name;
    private SimpleStringProperty serialNumber;
    private SimpleStringProperty value;

    public Item(String name, String serialNumber, String value) {
        serialNumber = serialNumber.toUpperCase();
        value = formatValue(value);
        this.name = new SimpleStringProperty(name);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.value = new SimpleStringProperty(value);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    //This method standardizes the string for value so that it is easy for users to type in
    public String formatValue(String userValue) {
        ArrayList<Character> trim = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < userValue.length(); i++) {
            trim.add(userValue.charAt(i));
        }
        for (Character character : trim) {
            //remove any $ or commas the user may add. Keep decimal point.
            if (!Character.isDigit(character)) {
                if (character == '.') {
                    sb.append(character);
                }
            } else {
                sb.append(character);
            }
        }
        String numbers = sb.toString();
        //format as a money string
        double translate = Double.parseDouble(numbers);
        return NumberFormat.getCurrencyInstance().format(translate);
    }
}
