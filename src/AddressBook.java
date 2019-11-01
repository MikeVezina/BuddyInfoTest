import javax.swing.*;
import java.io.*;
import java.util.Enumeration;

public class AddressBook extends DefaultListModel<BuddyInfo> {
    public static final String ADDRESS_BOOK_OUTPUT_FILE = "Address_Book.txt";

    /**
     * Adds a buddy to the list model.
     *
     * @param buddyInfo The BuddyInfo object to add. NO DUPLICATES!
     */
    public void addBuddy(BuddyInfo buddyInfo) {
        if (buddyInfo == null || super.contains(buddyInfo))
            return;

        super.addElement(buddyInfo);
    }


    /**
     * Removes the specified buddyInfo
     *
     * @param buddyInfo The object to remove.
     */
    public void removeBuddy(BuddyInfo buddyInfo) {
        if (buddyInfo == null || !super.contains(buddyInfo))
            return;

        super.removeElement(buddyInfo);
    }

    public int size() {
        return super.size();
    }


    public void clear() {
        super.clear();
    }

    /**
     * Saves the address book contents to a pre-defined file name.
     */
    public void export()
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(ADDRESS_BOOK_OUTPUT_FILE));
            Enumeration<BuddyInfo> buddyEnum = super.elements();

            while(buddyEnum.hasMoreElements())
            {
                BuddyInfo next = buddyEnum.nextElement();
                out.write(next.toString());
                out.write("\n");
            }

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static AddressBook importAddressBook()
    {
        AddressBook addressBook = new AddressBook();

        try {
            BufferedReader in = new BufferedReader(new FileReader(ADDRESS_BOOK_OUTPUT_FILE));

            // Map each line to a BuddyInfo object and add it to the address book
            in.lines().map(BuddyInfo::importBuddy).forEach(addressBook::addBuddy);

            in.close();

            return addressBook;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
