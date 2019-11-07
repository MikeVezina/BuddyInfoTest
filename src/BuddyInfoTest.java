import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuddyInfoTest {

    private static final String BUDDY_NAME = "Tester";
    private static final String BUDDY_ADDRESS = "Address";
    private static final String BUDDY_PHONE = "phone";
    private BuddyInfo testBuddyInfo = null;
    private BuddyInfo testOver18BuddyInfo = null;
    private BuddyInfo testUnder18BuddyInfo = null;

    @Before
    public void setUp() {
        testBuddyInfo = new BuddyInfo(BUDDY_NAME, 18, BUDDY_ADDRESS, BUDDY_PHONE);
        testOver18BuddyInfo = new BuddyInfo(BUDDY_NAME, 19, BUDDY_ADDRESS, BUDDY_PHONE);
        testUnder18BuddyInfo = new BuddyInfo(BUDDY_NAME, 17, BUDDY_ADDRESS, BUDDY_PHONE);
    }

    @Test
    public void testToXML()
    {
        String xmlString = testBuddyInfo.toXML();
        assertTrue("Must contain BuddyInfo tag", xmlString.contains("<" + BuddyInfo.BUDDY_TAG + ">" ));
        assertTrue("Must contain BuddyInfo closing tag", xmlString.contains("</" + BuddyInfo.BUDDY_TAG + ">" ));

        assertTrue("Must contain name tag", xmlString.contains("<" + BuddyInfo.NAME_TAG + ">" + testBuddyInfo.getName() + "</" + BuddyInfo.NAME_TAG + ">"));
        assertTrue("Must contain address tag", xmlString.contains("<" + BuddyInfo.ADDRESS_TAG + ">" + testBuddyInfo.getAddress() + "</" + BuddyInfo.ADDRESS_TAG + ">"));
        assertTrue("Must contain phone tag", xmlString.contains("<" + BuddyInfo.PHONE_TAG + ">" + testBuddyInfo.getPhoneNumber() + "</" + BuddyInfo.PHONE_TAG + ">"));
        assertTrue("Must contain age tag", xmlString.contains("<" + BuddyInfo.AGE_TAG + ">" + testBuddyInfo.getAge() + "</" + BuddyInfo.AGE_TAG + ">"));

    }

    @Test
    public void testCopyConstructor() {
        BuddyInfo copyBuddy = new BuddyInfo(testBuddyInfo);
        assertEquals(copyBuddy, testBuddyInfo);
    }

    @Test
    public void testEquals() {
        BuddyInfo sameBuddy = new BuddyInfo(testBuddyInfo.getName(), testBuddyInfo.getAge(), testBuddyInfo.getAddress(), testBuddyInfo.getPhoneNumber());
        BuddyInfo differentAddress = new BuddyInfo(testBuddyInfo.getName(), testBuddyInfo.getAge(), "", testBuddyInfo.getPhoneNumber());
        BuddyInfo differentName = new BuddyInfo("", testBuddyInfo.getAge(), testBuddyInfo.getAddress(), testBuddyInfo.getPhoneNumber());
        BuddyInfo differentPhone = new BuddyInfo(testBuddyInfo.getName(), testBuddyInfo.getAge(), testBuddyInfo.getAddress(), "");

        // Check to make sure our equals method returns true for the same object
        assertEquals(testBuddyInfo, testBuddyInfo);
        assertEquals(testBuddyInfo, sameBuddy);
        assertNotEquals(testBuddyInfo, testOver18BuddyInfo);
        assertNotEquals(testBuddyInfo, differentAddress);
        assertNotEquals(testBuddyInfo, differentName);
        assertNotEquals(testBuddyInfo, differentPhone);
    }

    @Test
    public void testGetName() {
        assertEquals("Buddy Name must be set", testBuddyInfo.getName(), BUDDY_NAME);
    }

    @Test
    public void testSetName() {
        assertEquals("Buddy Name must already be set", testBuddyInfo.getName(), BUDDY_NAME);
        String newName = "tester_new";
        testBuddyInfo.setName(newName);
        assertEquals("Buddy Name must be set to new name", testBuddyInfo.getName(), newName);
    }

    @Test
    public void testGetAddress() {
        assertEquals("Buddy Address must be set", testBuddyInfo.getAddress(), BUDDY_ADDRESS);
    }

    @Test
    public void testSetAddress() {
        assertEquals("Buddy Address must already be set", testBuddyInfo.getAddress(), BUDDY_ADDRESS);
        String newAddress = "tester_new";
        testBuddyInfo.setName(newAddress);
        assertEquals("Buddy Address must be set to new address", testBuddyInfo.getName(), newAddress);
    }

    @Test
    public void testGetPhone() {
        assertEquals("Buddy Phone must be set", testBuddyInfo.getPhoneNumber(), BUDDY_PHONE);
    }

    @Test
    public void testSetPhone() {
        assertEquals("Buddy Phone must already be set", testBuddyInfo.getAddress(), BUDDY_ADDRESS);
        String newPhone = "tester_new";
        testBuddyInfo.setName(newPhone);
        assertEquals("Buddy Phone must be set to new Phone number", testBuddyInfo.getName(), newPhone);
    }

    @Test
    public void testGetAge() {
        assertEquals(testBuddyInfo.getAge(), 18);
    }

    @Test
    public void testSetAge() {
        assertEquals(testBuddyInfo.getAge(), 18);
        int newAge = 50;
        testBuddyInfo.setAge(newAge);
        assertEquals(testBuddyInfo.getAge(), newAge);
    }

    @Test
    public void testIsOver18() {
        // Buddies who are age == 18 are not "over 18"
        assertFalse(testBuddyInfo.isOver18());
        assertFalse(testUnder18BuddyInfo.isOver18());
        assertTrue(testOver18BuddyInfo.isOver18());
    }

    @Test
    public void testGetGreeting() {
        String greetingPrefix = BuddyInfo.GREETING_PREFIX;
        assertEquals(testBuddyInfo.getGreeting(), greetingPrefix + testBuddyInfo.getName());
    }
}