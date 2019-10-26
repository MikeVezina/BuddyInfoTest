import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame implements ListSelectionListener {

    private JMenuBar menuBar;

    // AddressBook JMenu and Corresponding Items
    private JMenu addressBookMenu;
    private JMenuItem createAddressBookMenuItem;
    private JMenuItem saveAddressBookMenuItem;

    // BuddyInfo JMenu and Corresponding Items
    private JMenu buddyInfoMenu;
    private JMenuItem createBuddyInfoMenuItem;
    private JMenuItem editBuddyInfoMenuItem;
    private JMenuItem removeBuddyInfoMenuItem;
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

        buddyInfoMenu = new JMenu("Buddy Info");
        createBuddyInfoMenuItem = new JMenuItem("Create");
        editBuddyInfoMenuItem = new JMenuItem("Edit Selected Buddy");
        removeBuddyInfoMenuItem = new JMenuItem("Remove Selected Buddy");

        // Add any necessary action handlers
        createAddressBookMenuItem.addActionListener(this::createAddressBook);
        saveAddressBookMenuItem.addActionListener(this::saveAddressBook);
        createBuddyInfoMenuItem.addActionListener(this::createBuddyInfo);
        editBuddyInfoMenuItem.addActionListener(this::editBuddyInfo);
        removeBuddyInfoMenuItem.addActionListener(this::removeBuddyInfo);

        // Add address book menu items
        addressBookMenu.add(createAddressBookMenuItem);
        addressBookMenu.add(saveAddressBookMenuItem);

        // Add buddy info menu items
        buddyInfoMenu.add(createBuddyInfoMenuItem);
        buddyInfoMenu.add(editBuddyInfoMenuItem);
        buddyInfoMenu.add(removeBuddyInfoMenuItem);

        // Add menus to the menu bar
        menuBar.add(addressBookMenu);
        menuBar.add(buddyInfoMenu);

        // Disable some of the menu items
        saveAddressBookMenuItem.setEnabled(false);
        createBuddyInfoMenuItem.setEnabled(false);
        editBuddyInfoMenuItem.setEnabled(false);
        removeBuddyInfoMenuItem.setEnabled(false);

        currentBuddyInfoList.addListSelectionListener(this);

        // Only one item should be able to be selected at a time.
        currentBuddyInfoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.getContentPane().add(menuBar);
        this.getContentPane().add(currentBuddyInfoList);

    }

    /**
     * Removes the selected buddy info object.
     *
     * @param actionEvent Unused.
     */
    private void removeBuddyInfo(ActionEvent actionEvent) {
        int selectedBuddyIndex = currentBuddyInfoList.getSelectedIndex();
        currentAddressBook.remove(selectedBuddyIndex);
    }

    /**
     * Utilizes the Dialogs to edit the existing buddy info
     *
     * @param actionEvent Unused.
     */
    private void editBuddyInfo(ActionEvent actionEvent) {
        int selectedBuddyIndex = currentBuddyInfoList.getSelectedIndex();
        BuddyInfo selectedBuddy = currentAddressBook.get(selectedBuddyIndex);

        BuddyInfo modifiedBuddy = requestBuddyInfoDialog(selectedBuddy.getName(), selectedBuddy.getPhoneNumber(), selectedBuddy.getAddress());

        if (modifiedBuddy == null)
            return;

        selectedBuddy.setName(modifiedBuddy.getName());
        selectedBuddy.setAddress(modifiedBuddy.getAddress());
        selectedBuddy.setPhoneNumber(modifiedBuddy.getPhoneNumber());
    }

    /**
     * Requests Buddy Information dialogs (Name / Address / Phone Number), using any default dialog inputs and returns
     * a buddy info object containing the obtained inputs or null if input is cancelled.
     */
    private BuddyInfo requestBuddyInfoDialog(String initialName, String initialPhone, String initialAddress) {

        String buddyNameInput = requestNonEmptyInput("Buddy Name: ", initialName);

        if (buddyNameInput == null)
            return null;

        String buddyAddressInput = requestNonEmptyInput("Please enter the buddy address: ", initialAddress);

        if (buddyAddressInput == null)
            return null;

        String buddyPhoneInput = requestNonEmptyInput("Please enter the buddy phone number: ", initialPhone);

        if (buddyPhoneInput == null)
            return null;

        return new BuddyInfo(buddyNameInput, 18, buddyAddressInput, buddyPhoneInput);
    }

    /**
     * Requests input from JOptionPane.showInputDialog(message) until input is cancelled or not empty. Initializes
     * the input dialog with the defaultText
     *
     * @return The non-empty input, or Null if the user cancelled input
     */
    private String requestNonEmptyInput(String message, String defaultText) {
        String response = "";

        // Null Response values should break the loop, since they result from the user clicking cancel
        while (response != null && response.isEmpty())
            response = JOptionPane.showInputDialog(message, defaultText);

        return response;
    }

    /**
     * Creates a new Buddy info.
     *
     * @param actionEvent Not used.
     */
    private void createBuddyInfo(ActionEvent actionEvent) {

        BuddyInfo inputBuddy = requestBuddyInfoDialog();
        if (inputBuddy != null)
            currentAddressBook.addBuddy(inputBuddy);
    }

    private BuddyInfo requestBuddyInfoDialog() {
        return requestBuddyInfoDialog("", "", "");
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
        createBuddyInfoMenuItem.setEnabled(true);
    }


    public static void main(String[] args) {
        new MainFrame();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Only process this event once.
        if (e.getValueIsAdjusting())
            return;

        int selectedIndex = currentBuddyInfoList.getSelectedIndex();

        // If a buddy info gets removed (or de-selected), we should disable the edit/remove buttons.
        if (selectedIndex == -1) {
            editBuddyInfoMenuItem.setEnabled(false);
            removeBuddyInfoMenuItem.setEnabled(false);
        } else {
            editBuddyInfoMenuItem.setEnabled(true);
            removeBuddyInfoMenuItem.setEnabled(true);
        }

    }
}
