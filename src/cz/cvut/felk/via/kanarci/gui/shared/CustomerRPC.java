package cz.cvut.felk.via.kanarci.gui.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cz.cvut.felk.via.kanarci.gui.shared.OrderRPC;
import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;

public class CustomerRPC implements Serializable{


	private static final long serialVersionUID = -6440441293020632856L;

	private String key;
	
	private ContactRPC contactInfo;

	private List<OrderRPC> orders;

	public CustomerRPC(String key, ContactRPC contactInfo, List<OrderRPC> orders) {
		super();
		this.key = key;
		this.contactInfo = contactInfo;
		this.orders = orders;
	}
	

	public CustomerRPC(ContactRPC contactInfo) {
		super();
		this.contactInfo = contactInfo;
		orders = new ArrayList<OrderRPC>();
	}


	public CustomerRPC() {
		super();
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
