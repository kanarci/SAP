package cz.cvut.felk.via.kanarci.gui.shared;

import java.io.Serializable;




public class ContactRPC implements Serializable {
	
	private static final long serialVersionUID = -3886050400928774965L;

	private String Key;
	
	private String firstName;

	private String sureName;

	private String phone;

	private String corporationName;

	private String email;

	private String department;

	private AddressRPC address;
	
//	private String accountNumber;
//	
//	private String hire;

	/**
	 * Create new Customer Contact
	 * @param firstName
	 * @param sureName
	 * @param phone
	 * @param email
	 * @param address
	 */
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
//		this.accountNumber = null;
//		this.hire = null;
	}

	/**
	 * Create new Customer in Corporation
	 * @param firstName
	 * @param sureName
	 * @param phone
	 * @param corporationName
	 * @param email
	 * @param department
	 * @param address
//	 */
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
//		this.accountNumber = null;
//		this.hire = null;
	}

	/**
	 * Create new Employee
	 * @param firstName
	 * @param sureName
	 * @param phone
	 * @param corporationName
	 * @param email
	 * @param department
	 * @param address
	 * @param accountNumber
	 * @param hire
	 */
	
	//TODO
	/**
	 * Test only constructor !!!
	 */
	public ContactRPC() {
		super();
	}

	public ContactRPC(String key, String firstName, String sureName,
			String phone, String corporationName, String email,
			String department, AddressRPC address) {
		super();
		Key = key;
		this.firstName = firstName;
		this.sureName = sureName;
		this.phone = phone;
		this.corporationName = corporationName;
		this.email = email;
		this.department = department;
		this.address = address;
	}

	public String getKey() {
		return Key;
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

//	public String getAccountNumber() {
//		return accountNumber;
//	}
//
//	public String getHire() {
//		return hire;
//	}

	@Override
	public String toString() {
		return "Jmeno : " + firstName + " || Prijmeni : " + sureName
				+ " || Telefon : " + phone + " || Email : " + email
				+ " <br>Jmeno spolecnosti : " + corporationName
				+ " || Oddeleni : " + department + " || AccountNumber : "
				+ "\nADRESA : "	+ address.toString();
	}

}
