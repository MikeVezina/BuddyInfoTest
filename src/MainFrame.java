import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private JMenuBar menuBar;

    // AddressBook JMenu and Corresponding Items
    private JMenu addressBookMenu;
    private JMenuItem createAddressBookMenuItem;
    private JMenuItem saveAddressBookMenuItem;
    private JMenuItem displayAddressBookMenuItem;

    // BuddyInfo JMenu and Corresponding Items
    private JMenu buddyInfoMenu;
    private JMenuItem createBuddyInfoMenuItem;
    private JList currentBuddyInfoList;

    private AddressBook currentAddressBook;

    public MainFrame() {
        this.setTitle("Address Book Application");
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 300));
        setResizable(false);

        initializeComponents();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        currentBuddyInfoList = new JList();

        // Create the menu bar
        menuBar = new JMenuBar();
        menuBar.setMaximumSize(new Dimension(this.getPreferredSize().width, 20));

        addressBookMenu = new JMenu("Address Book");
        createAddressBookMenuItem = new JMenuItem("Create");
        saveAddressBookMenuItem = new JMenuItem("Save");
        displayAddressBookMenuItem = new JMenuItem("Display");

        buddyInfoMenu = new JMenu("Buddy Info");
        createBuddyInfoMenuItem = new JMenuItem("Create");

        // Add any necessary action handlers
        createAddressBookMenuItem.addActionListener(this::createAddressBook);
        saveAddressBookMenuItem.addActionListener(this::saveAddressBook);
        displayAddressBookMenuItem.addActionListener(this::displayAddressBook);
        createBuddyInfoMenuItem.addActionListener(this::createBuddyInfo);

        // Add address book menu items
        addressBookMenu.add(createAddressBookMenuItem);
        addressBookMenu.add(saveAddressBookMenuItem);
        addressBookMenu.add(displayAddressBookMenuItem);

        // Add buddy info menu items
        buddyInfoMenu.add(createBuddyInfoMenuItem);

        // Add menus to the menu bar
        menuBar.add(addressBookMenu);
        menuBar.add(buddyInfoMenu);

        // Disable some of the menu items
        saveAddressBookMenuItem.setEnabled(false);
        displayAddressBookMenuItem.setEnabled(false);
        createBuddyInfoMenuItem.setEnabled(false);

        this.getContentPane().add(menuBar);
        this.getContentPane().add(currentBuddyInfoList);

    }

    /**
     * Requests input from JOptionPane.showInputDialog(message) until input is cancelled or not empty.
     *
     * @return The non-empty input, or Null if the user cancelled input
     */
    private String requestNonEmptyInput(String message) {
        String response = "";

        // Null Response values should break the loop, since they result from the user clicking cancel
        while (response != null && response.isEmpty())
            response = JOptionPane.showInputDialog(message);

        return response;
    }

    /**
     * Creates a new Buddy info.
     *
     * @param actionEvent Not used.
     */
    private void createBuddyInfo(ActionEvent actionEvent) {

        String buddyNameInput = requestNonEmptyInput("Please enter the buddy name: ");

        if (buddyNameInput == null)
            return;

        String buddyAddressInput = requestNonEmptyInput("Please enter the buddy address: ");

        if (buddyAddressInput == null)
            return;

        String buddyPhoneInput = requestNonEmptyInput("Please enter the buddy phone number: ");

        if (buddyPhoneInput == null)
            return;

        BuddyInfo buddyInfo = new BuddyInfo(buddyNameInput, buddyAddressInput, buddyPhoneInput);
        currentAddressBook.addBuddy(buddyInfo);
    }


    /**
     * Displays the address book
     *
     * @param actionEvent Not used.
     */
    private void displayAddressBook(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(this, "No need to manually invoke displaying of contents. Contents are automatically updated with listeners :)");
    }

    /**
     * Saves the current address book.
     *
     * @param actionEvent Not used.
     */
    private void saveAddressBook(ActionEvent actionEvent) {
        if (currentAddressBook == null)
            return;

        // Save the address book to file
        if (currentAddressBook.saveToFile())
            JOptionPane.showMessageDialog(this, "The Address Book has been saved to file: " + AddressBook.ADDRESS_BOOK_OUTPUT_FILE);
        else
            JOptionPane.showMessageDialog(this, "Failed to save address book to file: " + AddressBook.ADDRESS_BOOK_OUTPUT_FILE);
    }

    /**
     * Creates a new address book.
     *
     * @param actionEvent Not used.
     */
    private void createAddressBook(ActionEvent actionEvent) {
        currentAddressBook = new AddressBook();
        currentBuddyInfoList.setModel(currentAddressBook);

        saveAddressBookMenuItem.setEnabled(true);
        displayAddressBookMenuItem.setEnabled(true);
        createBuddyInfoMenuItem.setEnabled(true);
    }


    public static void main(String[] args) {
        new MainFrame();
    }

}
