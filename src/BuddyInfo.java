
public class BuddyInfo {

	protected static final String GREETING_PREFIX = "Hello from ";
	private String name;
	private String address;
	private String phoneNumber;
	private int age;
	
	public BuddyInfo(String name, int age, String address, String phoneNumber)
	{
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public BuddyInfo(BuddyInfo buddyInfo)
	{
		if(buddyInfo == null)
			throw new NullPointerException("buddyInfo can not be null");

		this.name = buddyInfo.name;
		this.age = buddyInfo.age;
		this.address = buddyInfo.address;
		this.phoneNumber = buddyInfo.phoneNumber;
	}

	public String getGreeting()
	{
		return GREETING_PREFIX + getName();
	}


	public int getAge() {
		return age;
	}

	public boolean isOver18()
	{
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
	public boolean equals(Object other)
	{
		if(this == other)
			return true;

		if(!(other instanceof BuddyInfo))
			return false;

		BuddyInfo otherBuddy = (BuddyInfo) other;

		return this.name.equals(otherBuddy.name) && this.age == otherBuddy.age && this.address.equals(otherBuddy.address) && this.phoneNumber.equals(otherBuddy.phoneNumber);
	}

	@Override
	public String toString()
	{
		return "[Name: " + name + ", Phone: " + phoneNumber + ", Address: " + address + "]";
	}

}
