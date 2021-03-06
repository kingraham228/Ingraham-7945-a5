package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//This class tests the input validation functions for name, value, and serial number
class InputValidatorTest {

    @Test
    @DisplayName("Name length test: less than 2")
        //try a name that is too short
    void checkNameLength_lesstwo() {
        InputValidator test = new InputValidator();

        String name = "i";

        boolean actual = test.checkNameLength(name);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Name length test: 2")
        //try an edge case
    void checkNameLength_two() {
        InputValidator test = new InputValidator();

        String name = "it";

        boolean actual = test.checkNameLength(name);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Name length test: empty")
        //try an empty name
    void checkNameLength_empty() {
        InputValidator test = new InputValidator();

        String name = "";

        boolean actual = test.checkNameLength(name);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Name length test: very long")
        //try a name that is too long
    void checkNameLength_verylong() {
        InputValidator test = new InputValidator();

        String name = """
                True, I talk of dreams,
                Which are the children of an idle brain,
                Begot of nothing but vain fantasy,
                Which is as thin of substance as the air
                And more inconstant than the wind, who woos
                Even now the frozen bosom of the north,
                And, being anger'd, puffs away from thence,
                Turning his face to the dew-dropping south.""";

        boolean actual = test.checkNameLength(name);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Name length test: mid")
        //try a mid-length name
    void checkNameLength_mid() {
        InputValidator test = new InputValidator();

        String name = "Learning Management System";

        boolean actual = test.checkNameLength(name);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Value Test: formatted entry")
        //try a valid entry
    void checkValue_formatted() {
        InputValidator test = new InputValidator();

        String value = "$4.99";

        boolean actual = test.checkValue(value);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Value Test: missing $")
        //try without the $
    void checkValue_missing$() {
        InputValidator test = new InputValidator();

        String value = "4.99";

        boolean actual = test.checkValue(value);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Value Test: letters")
        //try with letters
    void checkValue_letters() {
        InputValidator test = new InputValidator();

        String value = "adgse";

        boolean actual = test.checkValue(value);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Value Test: special characters")
        //try with special characters
    void checkValue_specialCharacters() {
        InputValidator test = new InputValidator();

        String value = "8.6+";

        boolean actual = test.checkValue(value);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Value Test: whole amount")
        //try an amount without a decimal
    void checkValue_whole() {
        InputValidator test = new InputValidator();

        String value = "$499";

        boolean actual = test.checkValue(value);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Value Test: too many ..")
        //try with many decimals
    void checkValue_decimals() {
        InputValidator test = new InputValidator();

        String value = "$4.99.23";

        boolean actual = test.checkValue(value);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Value Test: too many digits after decimal")
        //try with many digits after the decimal
    void checkValue_decimalDigitsAfter() {
        InputValidator test = new InputValidator();

        String value = "$4.991";

        boolean actual = test.checkValue(value);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Value Test: too few digits after decimal")
        //try with too few digits after the decimal
    void checkValue_decimalFewDigitsAfter() {
        InputValidator test = new InputValidator();

        String value = "$4.9";

        boolean actual = test.checkValue(value);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Value Test: commas")
        //try with commas
    void checkValue_commas() {
        InputValidator test = new InputValidator();

        String value = "5,486";

        boolean actual = test.checkValue(value);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Value Test: lots of commas")
        //try with lots of commas
    void checkValue_lotscommas() {
        InputValidator test = new InputValidator();

        String value = "5,000,000,486";

        boolean actual = test.checkValue(value);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Check Serial Number: Valid")
        //try a valid serial number
    void checkFormatSerial() {
        InputValidator test = new InputValidator();

        String serial = "KA807DBK72";

        boolean actual = test.checkFormatSerial(serial);

        assertTrue(actual);
    }

    @Test
    @DisplayName("Check Serial Number: too short")
        //try a short serial number
    void checkFormatSerial_short() {
        InputValidator test = new InputValidator();

        String serial = "KA80BK72";

        boolean actual = test.checkFormatSerial(serial);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Check Serial Number: too long")
        //try a long serial number
    void checkFormatSerial_long() {
        InputValidator test = new InputValidator();

        String serial = "KA8KAI920285DKLJGAE0BK72";

        boolean actual = test.checkFormatSerial(serial);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Check Serial Number: special characters")
        //try a serial number with special characters
    void checkFormatSerial_special() {
        InputValidator test = new InputValidator();

        String serial = "92D84G9_s@";

        boolean actual = test.checkFormatSerial(serial);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Check Serial Number: hyphens")
        //try a serial number with special characters
    void checkFormatSerial_hyphen() {
        InputValidator test = new InputValidator();

        String serial = "SK8-SKE-25";

        boolean actual = test.checkFormatSerial(serial);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Check Unique Serial: Unique")
        //try a serial number that doesn't yet exist
    void checkUniqueSerial() {
        InputValidator test = new InputValidator();
        Inventory testIV = new Inventory();
        testIV.addItem("Test widget", "AAA000AAA0", "$2.99");

        String serial = "BBB000BBB0";

        boolean actual = test.checkUniqueSerial(testIV.getCatalog(), serial);

        assertTrue(actual);

    }

    @Test
    @DisplayName("Check Unique Serial: Repeat")
        //try a serial number that already is in the catalog
    void checkUniqueSerial_match() {
        InputValidator test = new InputValidator();
        Inventory testIV = new Inventory();
        testIV.addItem("Test widget", "AAA000AAA0", "$2.99");

        String serial = "AAA000AAA0";

        boolean actual = test.checkUniqueSerial(testIV.getCatalog(), serial);

        assertFalse(actual);

    }
}