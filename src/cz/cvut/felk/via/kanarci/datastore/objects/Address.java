package cz.cvut.felk.via.kanarci.datastore.objects;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Address {

	 @PrimaryKey
	 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	 private Key key;

	@Persistent
	private String street;

	@Persistent
	private int co;

	@Persistent
	private int cp;

	@Persistent
	private String city;

	@Persistent
	private int zip;

	public Address() {
		super();
	}

	public Address(String street, int co, int cp, String city, int zip) {
		super();
		this.street = street;
		this.co = co;
		this.cp = cp;
		this.city = city;
		this.zip = zip;
	}

	// public Key getKey() {
	// return key;
	// }

	public Key getKey() {
		return key;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getCo() {
		return co;
	}

	public void setCo(int co) {
		this.co = co;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "City : " + city + " || Street : " + street + " " + co + "/"
				+ cp + " || ZIP : " + zip;
	}
}
