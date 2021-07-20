package ucf.assignments;

import java.math.BigDecimal;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
public class Item {
    private String name;
    private String serialNumber;
    private BigDecimal value;

    public Item(String name, String serialNumber, BigDecimal value) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public BigDecimal getValue() {
        return value;
    }
}
