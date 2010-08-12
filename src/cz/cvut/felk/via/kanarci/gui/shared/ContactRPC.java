package cz.cvut.felk.via.kanarci.gui.shared;

import java.io.Serializable;




public class ContactRPC implements Serializable {
	
	public ContactRPC() {
		super();
	}

	private static final long serialVersionUID = -3886050400928774965L;

	private String firstName;

	private String sureName;

	private String phone;

	private String corporationName;

	private String email;

	private String department;

	private AddressRPC address;
	
	private String accountNumber;
	
	private String hire;

	public ContactRPC(String firstName, String sureName, String phone,
			String email, AddressRPC address) {
		super();
		this.firstName = firstName;
		this.sureName = sureName;
		this.phone = phone;
		this.corporationName = null;
		this.email = email;
		this.department = null;
		this.address = address;
		this.accountNumber = null;
		this.hire = null;
	}

	public ContactRPC(String firstName, String sureName, String phone,
			String corporationName, String email, String department,
			AddressRPC address) {
		super();
		this.firstName = firstName;
		this.sureName = sureName;
		this.phone = phone;
		this.corporationName = corporationName;
		this.email = email;
		this.department = department;
		this.address = address;
		this.accountNumber = null;
		this.hire = null;
	}


	public ContactRPC(String firstName, String sureName, String phone,
			String corporationName, String email, String department,
			AddressRPC address, String accountNumber, String hire) {
		super();
		this.firstName = firstName;
		this.sureName = sureName;
		this.phone = phone;
		this.corporationName = corporationName;
		this.email = email;
		this.department = department;
		this.address = address;
		this.accountNumber = accountNumber;
		this.hire = hire;
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

	public AddressRPC getAddress() {
		return address;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getHire() {
		return hire;
	}

	@Override
	public String toString() {
		return "Jmeno : " + firstName + " || Prijmeni : " + sureName
				+ " || Telefon : " + phone + " || Email : " + email
				+ " <br>Jmeno spolecnosti : " + corporationName
				+ " || Oddeleni : " + department + " || AccountNumber : "
				+ accountNumber + " || Hire : " + hire + "<br> ADRESA : "
				+ address.toString();
	}

}
