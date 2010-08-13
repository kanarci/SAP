package cz.cvut.felk.via.kanarci.gui.shared;

import java.io.Serializable;
import java.util.List;


public class CustomerRPC implements Serializable{
	
	private String key;
	
	private ContactRPC contactInfo;

	private List<OrderRPC> orders;

	public CustomerRPC(String key, ContactRPC contactInfo, List<OrderRPC> orders) {
		super();
		this.key = key;
		this.contactInfo = contactInfo;
		this.orders = orders;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ContactRPC getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactRPC contactInfo) {
		this.contactInfo = contactInfo;
	}

	public List<OrderRPC> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderRPC> orders) {
		this.orders = orders;
	}
	
	
	
	
}
