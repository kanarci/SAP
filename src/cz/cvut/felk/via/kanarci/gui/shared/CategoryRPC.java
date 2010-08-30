package cz.cvut.felk.via.kanarci.gui.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryRPC implements Serializable{

	private static final long serialVersionUID = 6449461820231166652L;

	private String key;

	private String name;

	private String supremeCategory;

	private List<String> goodsInCategory;

	private String parameterName;

	private String parameterValue;

	
	public CategoryRPC() {
		super();
	}

	public CategoryRPC(String name, String supremeCategory) {
		super();
		this.name = name;
		this.supremeCategory = supremeCategory;
		this.goodsInCategory = new ArrayList<String>();
		this.parameterName = null;
		this.parameterValue = null;
	}

	public CategoryRPC(String name, String supremeCategory, String parameterName,
			String parameterValue) {
		super();
		this.name = name;
//		System.out.println(supremeCategory.toString());
		this.supremeCategory = supremeCategory;
		this.parameterName = parameterName;
		this.parameterValue = parameterValue;
		this.goodsInCategory = new ArrayList<String>();
	}
	
	public CategoryRPC(String key, String name, String supremeCategory, String parameterName,
			String parameterValue) {
		super();
		this.key = key;
		this.name = name;
//		System.out.println(supremeCategory.toString());
		this.supremeCategory = supremeCategory;
		this.parameterName = parameterName;
		this.parameterValue = parameterValue;
		this.goodsInCategory = new ArrayList<String>();
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupremeCategory() {
		return supremeCategory;
	}

	public void setSupremeCategory(String supremeCategory) {
		this.supremeCategory = supremeCategory;
	}

	public List<String> getGoodsInCategory() {
		return goodsInCategory;
	}

	public void addGoodsToCategory(String goods) {
		this.goodsInCategory.add(goods);
	}

	public void addGoodsToCategory(List<String> goods) {
		this.goodsInCategory.addAll(goods);
	}

	public void removeGoodsFromCategory(String goods) {
		this.goodsInCategory.remove(goods);
	}

	public void removeGoodsFromCategory(List<String> goods) {
		this.goodsInCategory.remove(goods);
	}

	public String[] getBothParametersInField() {
		String[] val = new String[2];
		val[1] = parameterName;
		val[2] = parameterValue;
		return val;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}


}
