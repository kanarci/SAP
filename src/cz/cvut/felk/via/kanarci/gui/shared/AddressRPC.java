package cz.cvut.felk.via.kanarci.gui.shared;

import java.io.Serializable;

public class AddressRPC implements Serializable {

	private static final long serialVersionUID = -6287201767070877999L;

	private String street;

	private int co;

	private int cp;

	private String city;

	private String zip;

	public AddressRPC() {
		super();
	}

	public AddressRPC(String city, String street, int co, int cp, String zip) {
		super();
		this.street = street;
		this.co = co;
		this.cp = cp;
		this.city = city;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public int getCo() {
		return co;
	}

	public int getCp() {
		return cp;
	}

	public String getCity() {
		return city;
	}

	public String getZip() {
		return zip;
	}

	@Override
	public String toString() {
		return "City : " + city + " || Street : " + street + " " + co + "/"
				+ cp + " || ZIP : " + zip;
	}
}
