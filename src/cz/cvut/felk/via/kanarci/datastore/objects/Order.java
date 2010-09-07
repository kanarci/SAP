package cz.cvut.felk.via.kanarci.datastore.objects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;



@PersistenceCapable(detachable = "true")
public class Order implements Serializable{

	private static final long serialVersionUID = -2185072106944143762L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private OrderState orderState;

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
	private DeliveryMethod deliveryMethod;

	@Persistent
	private List<Key> goodsInOrder;

	@Persistent
	private Contact deliveryContact;

	@Persistent
	private Contact billingContact;

	@Persistent
	private Key modificatedBy;

	@Persistent
	private Key createdBy;

	public Order(Date creationDate, DeliveryMethod deliveryMethod,
			Date deliveryDate, Date estimatedDeliveryDate,
			List<Key> goodsInOrder, Contact deliveryContact,
			Contact billingContact, Key modificatedBy, Key createdBy) {
		super();
		this.creationDate = new Date();
		this.deliveryMethod = deliveryMethod;
		this.deliveryDate = deliveryDate;
		this.estimatedDeliveryDate = estimatedDeliveryDate;
		this.goodsInOrder = goodsInOrder;
		this.deliveryContact = deliveryContact;
		this.billingContact = billingContact;
		this.modificationDate = new Date();
		this.closeDate = null;
		this.modificatedBy = modificatedBy;
		this.orderState = OrderState.OPEN;
		this.createdBy = createdBy;
		this.courierShipmentDate = null;
	}

	public Order(String state, Date estimatedDeliveryDate, Date deliveryDate,
			DeliveryMethod deliveryMethod, List<Key> goodsInOrder,
			Contact deliveryContact, Contact billingContact, Key createdBy) {
		super();
		this.orderState = OrderState.OPEN;
		this.creationDate = new Date();
		this.closeDate = null;
		this.modificationDate = new Date();
		this.estimatedDeliveryDate = estimatedDeliveryDate;
		this.deliveryDate = deliveryDate;
		this.deliveryMethod = deliveryMethod;
		this.goodsInOrder = goodsInOrder;
		this.deliveryContact = deliveryContact;
		this.billingContact = billingContact;
		this.modificatedBy = createdBy;
		this.createdBy = createdBy;
		this.courierShipmentDate = null;
	}
	
	

	public Order(Key key, OrderState orderState, Date creationDate,
			Date closeDate, Date modificationDate, Date courierShipmentDate,
			Date estimatedDeliveryDate, Date deliveryDate,
			DeliveryMethod deliveryMethod, List<Key> goodsInOrder,
			Contact deliveryContact, Contact billingContact, Key modificatedBy,
			Key createdBy) {
		super();
		this.key = key;
		this.orderState = orderState;
		this.creationDate = creationDate;
		this.closeDate = closeDate;
		this.modificationDate = modificationDate;
		this.courierShipmentDate = courierShipmentDate;
		this.estimatedDeliveryDate = estimatedDeliveryDate;
		this.deliveryDate = deliveryDate;
		this.deliveryMethod = deliveryMethod;
		this.goodsInOrder = goodsInOrder;
		this.deliveryContact = deliveryContact;
		this.billingContact = billingContact;
		this.modificatedBy = modificatedBy;
		this.createdBy = createdBy;
	}

	public Key getKey() {
		return key;
	}

	public OrderState getState() {
		return orderState;
	}

	public void setState(OrderState orderState) {
		this.orderState = orderState;
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

	public List<Key> getGoodsInOrder() {
		return goodsInOrder;
	}

	public void setGoodsInOrder(List<Key> goodsInOrder) {
		this.goodsInOrder = goodsInOrder;
	}

	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
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

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public Contact getDeliveryContact() {
		return deliveryContact;
	}

	public void setDeliveryContact(Contact deliveryContact) {
		this.deliveryContact = deliveryContact;
	}

	public Contact getBillingContact() {
		return billingContact;
	}

	public void setBillingContact(Contact billingContact) {
		this.billingContact = billingContact;
	}

	@SuppressWarnings("unused")
	private void modificated() {
		this.modificationDate = new Date();
	}

}
