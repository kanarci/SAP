package cz.cvut.felk.via.kanarci.datastore.objects;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Order {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String state;

	@Persistent
	private Date creationDate;

	@Persistent
	private Date closeDate;

	@Persistent
	private Date modificationDate;

	@Persistent
	private Date courierShipmentDate;

	@Persistent
	private Date estimatedDeliveryDate;

	@Persistent
	private Date deliveryDate;

	@Persistent
	private String deliveryMethod;

	@Persistent
	private List<Goods> goodsInOrder;

	@Persistent
	private Contact deliveryContact;

	@Persistent
	private Contact billingContact;

	@Persistent
	private Key modificatedBy;

	@Persistent
	private Key createdBy;

	// TODO delivery, estimatedDelivery, courierShipment Dates
	public Order(Date creationDate, String deliveryMethod,
			List<Goods> goodsInOrder, Contact deliveryAddress,
			Contact billingAddress, Key modificatedBy, Key createdBy) {
		super();
		this.creationDate = creationDate;
		this.deliveryMethod = deliveryMethod;
		this.goodsInOrder = goodsInOrder;
		this.deliveryContact = deliveryAddress;
		this.billingContact = billingAddress;
		this.modificationDate = new Date();
		this.closeDate = null;
		this.modificatedBy = modificatedBy;
		this.state = "untouched";
		this.createdBy = createdBy;
	}

	public Key getKey() {
		return key;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public List<Goods> getGoodsInOrder() {
		return goodsInOrder;
	}

	public void setGoodsInOrder(List<Goods> goodsInOrder) {
		this.goodsInOrder = goodsInOrder;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public Contact getDeliveryAddress() {
		return deliveryContact;
	}

	public void setDeliveryAddress(Contact deliveryAddress) {
		this.deliveryContact = deliveryAddress;
	}

	public Contact getBillingAddress() {
		return billingContact;
	}

	public void setBillingAddress(Contact billingAddress) {
		this.billingContact = billingAddress;
	}

	public Key getCodificatedBy() {
		return modificatedBy;
	}

	public void setCodificatedBy(Key modificatedBy) {
		this.modificatedBy = modificatedBy;
		this.modificationDate = new Date();
	}

	public Key getCreatedBy() {
		return createdBy;
	}

	public Date getCourierShipmentDate() {
		return courierShipmentDate;
	}

	public void setCourierShipmentDate(Date courierShipmentDate) {
		this.courierShipmentDate = courierShipmentDate;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Key getModificatedBy() {
		return modificatedBy;
	}

	public void setModificatedBy(Key modificatedBy) {
		this.modificatedBy = modificatedBy;
	}

	@SuppressWarnings("unused")
	private void modificated(){
		this.modificationDate = new Date();
	}
	
}
