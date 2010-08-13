package cz.cvut.felk.via.kanarci.datastore.objects;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable (detachable="true")
public class Contact {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String firstName;

	@Persistent
	private String sureName;

	@Persistent
	private String phone;

	@Persistent
	private String corporationName;

	@Persistent
	private String email;

	@Persistent
	private String department;

	@Persistent(serialized = "true", defaultFetchGroup = "true")
	private Address address;

	public Contact(String firstName, String sureName, String phone, String email,
			Address address) {
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
			String corporationName, String email, String department, Address address) {
		super();
		this.firstName = firstName;
		this.sureName = sureName;
		this.phone = phone;
		this.corporationName = corporationName;
		this.email = email;
		this.department = department;
		this.address = address;
	}

	public Contact(Key key, String firstName, String sureName, String phone,
			String corporationName, String email, String department,
			Address address) {
		super();
		this.key = key;
		this.firstName = firstName;
		this.sureName = sureName;
		this.phone = phone;
		this.corporationName = corporationName;
		this.email = email;
		this.department = department;
		this.address = address;
	}

	public Key getKey() {
		return key;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSureName() {
		return sureName;
	}

	public void setSureName(String sureName) {
		this.sureName = sureName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
    @Override
    public String toString(){
		return "Jmeno : " + firstName + " || Prijmeni : " + sureName + " || Telefon : " + phone 
		+ " || Email : " + email + " <br>Jmeno spolecnosti : " + corporationName + " || Oddeleni : " + department +
		"<br> ADRESA : " + address.toString() ;
    }

}
