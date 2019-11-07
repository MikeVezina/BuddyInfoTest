import javax.swing.*;
import java.io.*;
import java.util.Enumeration;

public class AddressBook extends DefaultListModel<BuddyInfo> implements Serializable {
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
            FileOutputStream fileOutputStream = new FileOutputStream(ADDRESS_BOOK_OUTPUT_FILE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static AddressBook importAddressBook() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(ADDRESS_BOOK_OUTPUT_FILE);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (AddressBook) objectInputStream.readObject();
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this)
            return true;

        if(!(o instanceof AddressBook))
            return false;

        AddressBook addressBook = (AddressBook) o;

        if(this.size() != addressBook.size())
            return false;

        for(int i = 0; i < this.size(); i++)
        {
            if(!this.get(i).equals(addressBook.get(i)))
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
