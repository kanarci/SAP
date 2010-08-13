package cz.cvut.felk.via.kanarci.gui.shared;

import java.io.Serializable;
import java.util.List;

public class GoodsRPC implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8133921748739994928L;

	private String key;
	
	private String name;

	private String description;
	
	private double price;

	private int numOfPieces;
	
	private boolean visiblity;
	
	private double DPH;
	
	private String supplier;
	
	private List<String> category;

	public GoodsRPC(String key, String name, String description, double price,
			int numOfPieces, boolean visiblity, double dPH, String supplier,
			List<String> category) {
		super();
		this.key = key;
		this.name = name;
		this.description = description;
		this.price = price;
		this.numOfPieces = numOfPieces;
		this.visiblity = visiblity;
		DPH = dPH;
		this.supplier = supplier;
		this.category = category;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
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

	public boolean getVisiblity() {
		return visiblity;
	}

	public void setVisiblity(boolean visiblity) {
		this.visiblity = visiblity;
	}

	public double getDPH() {
		return DPH;
	}

	public void setDPH(double dPH) {
		DPH = dPH;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	
}
