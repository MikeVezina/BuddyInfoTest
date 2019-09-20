import java.util.*;

public class AddressBook {
	private List<BuddyInfo> buddyInfoList;
	
	public AddressBook()
	{
		buddyInfoList = new ArrayList<>();
	}
	
	public void addBuddy(BuddyInfo buddyInfo)
	{
		if(buddyInfo == null || this.buddyInfoList.contains(buddyInfo))
			return;
		
		this.buddyInfoList.add(buddyInfo);
	}
	
	public void removeBuddy(BuddyInfo buddyInfo)
	{
		if(buddyInfo == null || !this.buddyInfoList.contains(buddyInfo))
			return;
		
		this.buddyInfoList.remove(buddyInfo);
	}
	
	private void thisIsANewMethodForThisBranch()
	{
		System.out.println("Hello new branch!");
	}
	
	public static void main(String[] args)
	{
		// This is a test for the GitHub editor
		AddressBook addressBook = new AddressBook();
		BuddyInfo buddyInfo = new BuddyInfo("Test Buddy", "Test Address", "Test Phone");
		addressBook.addBuddy(buddyInfo);
		addressBook.removeBuddy(buddyInfo);
		
		addressBook.thisIsANewMethodForThisBranch();
	}
}
