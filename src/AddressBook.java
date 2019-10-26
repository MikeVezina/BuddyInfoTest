import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
     *
     * @return True if the file was saved successfully, false otherwise.
     */
    public boolean saveToFile() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(ADDRESS_BOOK_OUTPUT_FILE));
            out.write(this.toString());
            out.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
