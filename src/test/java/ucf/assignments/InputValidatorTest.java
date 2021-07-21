package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Kate Ingraham
 */
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @Test
    @DisplayName("Name length test: less than 3")
    //try a name that is too short
    void checkNameLength_lessthree() {
        InputValidator test = new InputValidator();

        String name = "it";

        boolean actual = test.checkNameLength(name);

        assertFalse(actual);
    }

    @Test
    @DisplayName("Name length test: 3")
    //try an edge case
    void checkNameLength_three() {
        InputValidator test = new InputValidator();

        String name = "dog";

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
}