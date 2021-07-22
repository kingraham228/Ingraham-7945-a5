package ucf.assignments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    @DisplayName("Test Save TSV list")
    //try saving a tsv list
    void saveInventoryTSV() {
        FileManager fm = new FileManager();
        //create a test inventory
        Inventory test = new Inventory();
        test.addItem("item one","lllwwwiii2","$3444");
        test.addItem("item two","iii222mmma","$42");
        test.addItem("item three","000222jjj4","$3.99");

        String filePath = "myinventory.txt";

        fm.saveInventoryTSV(test.getCatalog(),filePath);
        //visually check the txt file output
    }
}