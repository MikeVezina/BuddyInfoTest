import java.io.Serializable;
import java.util.Scanner;

public class BuddyInfo implements Serializable {

    protected static final String GREETING_PREFIX = "Hello from ";

    private static final String DELIM = "$";
    private static final String ESCAPED_DELIM = "\\" + DELIM;
    private static final int NAME_IDX = 0;
    private static final int AGE_IDX = 1;
    private static final int ADDRESS_IDX = 2;
    private static final int PHONE_IDX = 3;
    private static final int PARSED_NUM_FIELDS = 4;

    private String name;
    private String address;
    private String phoneNumber;
    private int age;

    public BuddyInfo(String name, int age, String address, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo(BuddyInfo buddyInfo) {
        if (buddyInfo == null)
            throw new NullPointerException("buddyInfo can not be null");

        this.name = buddyInfo.name;
        this.age = buddyInfo.age;
        this.address = buddyInfo.address;
        this.phoneNumber = buddyInfo.phoneNumber;
    }

    public String getGreeting() {
        return GREETING_PREFIX + getName();
    }


    public int getAge() {
        return age;
    }

    public boolean isOver18() {
        return age > 18;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;

        if (!(other instanceof BuddyInfo))
            return false;

        BuddyInfo otherBuddy = (BuddyInfo) other;

        return this.name.equals(otherBuddy.name) && this.age == otherBuddy.age && this.address.equals(otherBuddy.address) && this.phoneNumber.equals(otherBuddy.phoneNumber);
    }


    @Override
    public String toString() {
        String[] fields = new String[PARSED_NUM_FIELDS];
        fields[NAME_IDX] = this.name;
        fields[AGE_IDX] = String.valueOf(this.age);
        fields[ADDRESS_IDX] = this.address;
        fields[PHONE_IDX] = this.phoneNumber;

        return String.join(DELIM, fields);
    }
}
