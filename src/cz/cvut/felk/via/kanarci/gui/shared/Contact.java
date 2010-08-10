package cz.cvut.felk.via.kanarci.gui.shared;

import java.io.Serializable;


public class Contact implements Serializable {
	
	public Contact() {
		super();
	}

	private static final long serialVersionUID = -3886050400928774965L;

	private String firstName;

	private String sureName;

	private String phone;

	private String corporationName;

	private String email;

	private String department;

	private Address address;

	public Contact(String firstName, String sureName, String phone,
			String email, Address address) {
		super();
		this.firstName = firstName;
		this.sureName = sureName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.corporationName = null;
		this.department = null;
	}

	public Contact(String firstName, String sureName, String phone,
			String corporationName, String email, String department,
			Address address) {
		super();
		this.firstName = firstName;
		this.sureName = sureName;
		this.phone = phone;
		this.corporationName = corporationName;
		this.email = email;
		this.department = department;
		this.address = address;
	}


	public String getFirstName() {
		return firstName;
	}

	public String getSureName() {
		return sureName;
	}

	public String getPhone() {
		return phone;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public String getEmail() {
		return email;
	}

	public String getDepartment() {
		return department;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Jmeno : " + firstName + " || Prijmeni : " + sureName
				+ " || Telefon : " + phone + " || Email : " + email
				+ " <br>Jmeno spolecnosti : " + corporationName
				+ " || Oddeleni : " + department + "<br> ADRESA : "
				+ address.toString();
	}

}
