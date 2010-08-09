package cz.cvut.felk.via.kanarci.datastore.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable
public class Category implements Serializable{


	private static final long serialVersionUID = 6449461820231166652L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private Key supremeCategory;

	@Persistent
	private List<Key> goodsInCategory;

	@Persistent
	private String parameterName;

	@Persistent
	private String parameterValue;

	public Category(String name, Key supremeCategory) {
		super();
		this.name = name;
		this.supremeCategory = supremeCategory;
		this.goodsInCategory = new ArrayList<Key>();
		this.parameterName = null;
		this.parameterValue = null;
	}

	public Category(String name, Key supremeCategory, String parameterName,
			String parameterValue) {
		super();
		this.name = name;
		System.out.println(supremeCategory.toString());
		this.supremeCategory = supremeCategory;
		this.parameterName = parameterName;
		this.parameterValue = parameterValue;
		this.goodsInCategory = new ArrayList<Key>();
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

	public Key getSupremeCategory() {
		return supremeCategory;
	}

	public void setSupremeCategory(Key supremeCategory) {
		this.supremeCategory = supremeCategory;
	}

	public List<Key> getGoodsInCategory() {
		return goodsInCategory;
	}

	public void addGoodsToCategory(Key goods) {
		this.goodsInCategory.add(goods);
	}

	public void addGoodsToCategory(List<Key> goods) {
		this.goodsInCategory.addAll(goods);
	}

	public void removeGoodsFromCategory(Key goods) {
		this.goodsInCategory.remove(goods);
	}

	public void removeGoodsFromCategory(List<Key> goods) {
		this.goodsInCategory.remove(goods);
	}

	public String[] getBothParametersInField() {
		String[] val = new String[2];
		val[1] = parameterName;
		val[2] = parameterValue;
		return val;
	}

	public void setParametrName(String parameterName) {
		this.parameterName = parameterName;
	}

	public void setParametrValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	@Override
	public String toString() {
		String ret = " Kategorie : " + name + " || +"
				+ KeyFactory.keyToString(key) + "+ || ";
		if (this.supremeCategory == null) {
			ret = ret.concat("No supreme key || ");
		} else {
			if (KeyFactory.keyToString(supremeCategory).equals(
					"aglub19hcHBfaWRyEgsSCENhdGVnb3J5IgRSb290DA")) {
				ret = ret.concat("Supreme key ROOT || ");
			} else {
				ret = ret.concat("Supreme key +"
						+ KeyFactory.keyToString(supremeCategory) + "+|| ");
			}
		}
		ret = ret.concat("<br> Parametr : " + parameterName + " -- "
				+ parameterValue);
		return ret;
	}

}
