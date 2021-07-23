package ucf.assignments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    @DisplayName("Parse Test: TSV correct")
    //try a correctly formatted tsv
    void parseFileData() {
        FileManager fm = new FileManager();
        ArrayList<String> testFileData = new ArrayList<>();
        String test1 = "$4.99\t111000SSSS\ttest one\n";
        String test2 = "$789.00\t000222HHHE\ttest two\n";
        testFileData.add(test1);
        testFileData.add(test2);

        ArrayList<Item> fileItems = fm.parseFileData(testFileData,"txt");

        //Check the first name element
        String actual = fileItems.get(0).getName();
        String expected = "test one\n";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Parse Test: TSV correct: Value")
        //try a correctly formatted tsv, value element
    void parseFileData_tsvValue() {
        FileManager fm = new FileManager();
        ArrayList<String> testFileData = new ArrayList<>();
        String test1 = "$4.99\t111000SSSS\ttest one\n";
        String test2 = "$789.00\t000222HHHE\ttest two\n";
        testFileData.add(test1);
        testFileData.add(test2);

        ArrayList<Item> fileItems = fm.parseFileData(testFileData,"txt");

        //Check the second value element
        String actual = fileItems.get(1).getValue();
        String expected = "$789.00";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Parse Test: TSV correct: Serial")
        //try a correctly formatted tsv
    void parseFileData_tsvSerial() {
        FileManager fm = new FileManager();
        ArrayList<String> testFileData = new ArrayList<>();
        String test1 = "$4.99\t111000SSSS\ttest one\n";
        String test2 = "$789.00\t000222HHHE\ttest two\n";
        testFileData.add(test1);
        testFileData.add(test2);

        ArrayList<Item> fileItems = fm.parseFileData(testFileData,"txt");

        //Check the second serial element
        String actual = fileItems.get(1).getSerialNumber();
        String expected = "000222HHHE";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Load file test")
    void loadInventory() {
        FileManager fm = new FileManager();
        String filepath = "myinventory.txt";
        ArrayList<Item> test = fm.loadInventory((Path.of(filepath)),"txt");

        //Set comparison to the first name element that should be read in
        String actual = test.get(0).getName();
        String expected = "item one";
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Test Save TSV list")
        //try saving a tsv list
    void saveInventoryTSV() {
        FileManager fm = new FileManager();
        //create a test inventory
        Inventory test = new Inventory();
        test.addItem("item one","lllwwwiii2","$3444");
        test.addItem("item duo","iii222mmma","$42");
        test.addItem("item three","000222jjj4","$3.99");

        String filePath = "myinventory.txt";

        fm.saveInventory(test.getCatalog(),filePath,"txt");
        //visually check the txt file output
    }

    @Test
    @DisplayName("Test HTML Save")
    //try saving a list to HTML
    void saveInventory_html() {
        FileManager fm = new FileManager();
        //create a test inventory
        Inventory test = new Inventory();
        test.addItem("item one","lllwwwiii2","$3444");
        test.addItem("item two","iii222mmma","$42");
        test.addItem("item three","000222jjj4","$3.99");

        String filePath = "myinventory.html";

        fm.saveInventory(test.getCatalog(),filePath,"html");
        //visually check the txt file output
    }

    @Test
    @DisplayName("Test HTML Parsing: Correct format")
    //try parsing a correctly formatted HTML file
    void testParseFileData_html() {
        FileManager test = new FileManager();
        ArrayList<String> testFileData = new ArrayList<>();
        String test1 = "<td>$3,444.00</td><td>LLLWWWIII2</td><td>item one</td>";
        String test2 = "<td>$42.00</td><td>III222MMMA</td><td>item two</td>";
        testFileData.add(test1);
        testFileData.add(test2);

        ArrayList<Item> fileItems = test.parseFileData(testFileData,"html");

        //Check the second element value
        String actual = fileItems.get(1).getValue();
        String expected = "$42.00";

        assertEquals(expected,actual);
    }
}