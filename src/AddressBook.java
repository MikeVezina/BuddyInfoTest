import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AddressBook extends DefaultListModel<BuddyInfo> implements Serializable {
    public static final String ADDRESS_BOOK_OUTPUT_FILE = "Address_Book.txt";
    public static final String ADDRESS_BOOK_XML_FILE = "Address_Book.xml";
    public static final String ADDRESS_BOOK_TAG = "addressbook";

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
    public void export() {


        try {
            this.exportToXMLFile();
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

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append("<" + ADDRESS_BOOK_TAG + ">");

        for (int i = 0; i < this.size(); i++) {
            stringBuilder.append(this.get(i).toXML());
        }

        stringBuilder.append("</" + ADDRESS_BOOK_TAG + ">");

        return stringBuilder.toString();
    }

    public void exportToXMLFile() throws IOException {
        FileWriter fileOutputStream = new FileWriter(ADDRESS_BOOK_XML_FILE);
        fileOutputStream.write(toXML());
        fileOutputStream.close();
    }

    public static AddressBook importFromXMLFile() throws IOException, SAXException, ParserConfigurationException {
        File addressBookFile = new File(ADDRESS_BOOK_XML_FILE);

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser s = spf.newSAXParser();

        AddressBook currentAddressBook = new AddressBook();

        DefaultHandler dh = new DefaultHandler() {

            private Map<String, String> buddyInfoPropertyMap = new HashMap<>();

            private String currentKey;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                System.out.println("Start: " + qName);
                currentKey = qName;
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (qName.equals(BuddyInfo.BUDDY_TAG)) {
                    String name = buddyInfoPropertyMap.getOrDefault(BuddyInfo.NAME_TAG, "");
                    String address = buddyInfoPropertyMap.getOrDefault(BuddyInfo.ADDRESS_TAG, "");
                    String phone = buddyInfoPropertyMap.getOrDefault(BuddyInfo.PHONE_TAG, "");
                    int age = Integer.parseInt(buddyInfoPropertyMap.getOrDefault(BuddyInfo.AGE_TAG, "0"));

                    currentAddressBook.addBuddy(new BuddyInfo(name, age, address, phone));
                }
                System.out.println("end: " + qName);
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String chars = new String(ch, start, length);
                buddyInfoPropertyMap.put(currentKey, chars);
                System.out.println("Chars: " + chars + " - " + currentKey);
            }
        };
        s.parse(addressBookFile, dh);

        return currentAddressBook;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof AddressBook))
            return false;

        AddressBook addressBook = (AddressBook) o;

        if (this.size() != addressBook.size())
            return false;

        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(addressBook.get(i)))
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
