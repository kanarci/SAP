package cz.cvut.felk.via.kanarci.datastore.objects;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Supplier implements Serializable{

	private static final long serialVersionUID = -6996667178762282000L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String accountNumber;

	@Persistent
	private Contact contact;

	public Supplier(String accountNumber, Contact contact) {
		super();
		this.accountNumber = accountNumber;
		this.contact = contact;
	}

	public Key getKey() {
		return key;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
