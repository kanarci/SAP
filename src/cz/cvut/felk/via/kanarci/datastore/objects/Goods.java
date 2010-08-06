package cz.cvut.felk.via.kanarci.datastore.objects;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class Goods {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private Text description;

	@Persistent
	private double price;

	@Persistent
	private int numOfPieces;

	@Persistent
	private boolean visiblity;

	@Persistent
	private double DPH;

	@Persistent
	private Key supplier;

	@Persistent
	private List<Key> category;

	// public Goods(String name, Text description, double price, int
	// numOfPieces,
	// Key supplier, List<Key> category) {
	// super();
	// this.name = name;
	// this.description = description;
	// this.price = price;
	// this.numOfPieces = numOfPieces;
	// this.supplier = supplier;
	// this.category = category;
	// this.visiblity = Boolean.TRUE;
	// }

	public Goods(String name, Text description, double price, int numOfPieces,
			boolean visiblity, Key supplier, List<Key> category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.numOfPieces = numOfPieces;
		this.visiblity = visiblity;
		this.supplier = supplier;
		this.category = category;
		this.DPH = (double) 1000000;
	}
	
	public Goods(String name, Text description, double price, int numOfPieces,
			boolean visiblity, Key category) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.numOfPieces = numOfPieces;
		this.visiblity = visiblity;
//		this.supplier = supplier;
		this.category = new ArrayList<Key>();
		this.category.add(category);
		this.DPH = (double) 1000000;
	}

	public Key getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Text getDescription() {
		return description;
	}

	public void setDescription(Text description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumOfPieces() {
		return numOfPieces;
	}

	public void setNumOfPieces(int numOfPieces) {
		this.numOfPieces = numOfPieces;
	}

	public boolean isVisiblity() {
		return visiblity;
	}

	public void setVisiblity(boolean visiblity) {
		this.visiblity = visiblity;
	}

	public Key getSupplier() {
		return supplier;
	}

	public void setSupplier(Key supplier) {
		this.supplier = supplier;
	}

	public List<Key> getCategory() {
		return category;
	}

	public void setCategory(List<Key> category) {
		this.category = category;
	}

	public void addCategory(Key category) {
		this.category.add(category);
	}

	public double getDPH() {
		return DPH;
	}

	@Override
	public String toString(){
		String ret = " Zbozi : " + this.name + " || key " + key.toString()
		+ "<br> Popisek: " + this.description.toString();
		
		return ret;
	}
	
}
