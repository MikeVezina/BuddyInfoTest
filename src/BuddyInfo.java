
public class BuddyInfo {

	private String name;
	private String address;
	private String phoneNumber;
	
	public BuddyInfo(String name, String address, String phoneNumber)
	{
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
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

		return this.name.equals(otherBuddy.name) && this.address.equals(otherBuddy.address) && this.phoneNumber.equals(otherBuddy.phoneNumber);
	}

	@Override
	public String toString()
	{
		return "[Name: " + name + ", Phone: " + phoneNumber + ", Address: " + address + "]";
	}

}
