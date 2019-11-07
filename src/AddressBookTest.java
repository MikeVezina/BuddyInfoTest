import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AddressBookTest {

    private AddressBook emptyAddressBook = null;
    private AddressBook oneBuddyAddressBook = null;
    private AddressBook twoBuddyAddressBook = null;

    private BuddyInfo firstBuddy = new BuddyInfo("Buddy1", 18, "", "");
    private BuddyInfo secondBuddy = new BuddyInfo("Buddy2", 19, "", "");


    @Before
    public void setUp() {
        emptyAddressBook = new AddressBook();

        oneBuddyAddressBook = new AddressBook();
        oneBuddyAddressBook.addBuddy(firstBuddy);

        twoBuddyAddressBook = new AddressBook();
        twoBuddyAddressBook.addBuddy(firstBuddy);
        twoBuddyAddressBook.addBuddy(secondBuddy);
    }

    @Test
    public void testSize()
    {
        assertEquals(emptyAddressBook.size(), 0);
        assertEquals(oneBuddyAddressBook.size(), 1);
        assertEquals(twoBuddyAddressBook.size(), 2);
    }

    @Test
    public void testExportAndImport() throws IOException, ClassNotFoundException {
        // Test Export and Import for each address book
        emptyAddressBook.export();
        testImportAddressBook(emptyAddressBook);

        oneBuddyAddressBook.export();
        testImportAddressBook(oneBuddyAddressBook);

        twoBuddyAddressBook.export();
        testImportAddressBook(twoBuddyAddressBook);
    }

    private void testImportAddressBook(AddressBook addressBook) throws IOException, ClassNotFoundException {
        AddressBook importedBook = AddressBook.importAddressBook();
        assertEquals(addressBook, importedBook);
    }


    @Test
    public void testClear()
    {
        emptyAddressBook.clear();
        oneBuddyAddressBook.clear();
        twoBuddyAddressBook.clear();

        assertEquals(emptyAddressBook.size(), 0);
        assertEquals(oneBuddyAddressBook.size(), 0);
        assertEquals(twoBuddyAddressBook.size(), 0);
    }


}