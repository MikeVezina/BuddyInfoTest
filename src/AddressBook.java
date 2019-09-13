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
	
	public static void main(String[] args)
	{
		System.out.println("Address Book Test!");
	}
}
