package cz.cvut.felk.via.kanarci.gui.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class OrderRPC implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6326984437548944723L;

	private String key;
		
	private OrderStateRPC orderState;
	
	private Date creationDate;
	
	private Date closeDate;
	
	private Date modificationDate;

	private Date courierShipmentDate;

	private Date estimatedDeliveryDate;
	
	private Date deliveryDate;
	
	private DeliveryMethod deliveryMethod;
	
	private List<GoodsRPC> goodsInOrder;
	
	private ContactRPC deliveryContact;
	
	private ContactRPC billingContact;

	private String modificatedBy;
	
	private String createdBy;

	public OrderRPC(String key, OrderStateRPC orderState, Date creationDate,
			Date closeDate, Date modificationDate, Date courierShipmentDate,
			Date estimatedDeliveryDate, Date deliveryDate,
			DeliveryMethod deliveryMethod, List<GoodsRPC> goodsInOrder,
			ContactRPC deliveryContact, ContactRPC billingContact,
			String modificatedBy, String createdBy) {
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
	
	public OrderRPC(){
		super();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public OrderStateRPC getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderStateRPC orderState) {
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

	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public List<GoodsRPC> getGoodsInOrder() {
		return goodsInOrder;
	}

	public void setGoodsInOrder(List<GoodsRPC> goodsInOrder) {
		this.goodsInOrder = goodsInOrder;
	}

	public ContactRPC getDeliveryContact() {
		return deliveryContact;
	}

	public void setDeliveryContact(ContactRPC deliveryContact) {
		this.deliveryContact = deliveryContact;
	}

	public ContactRPC getBillingContact() {
		return billingContact;
	}

	public void setBillingContact(ContactRPC billingContact) {
		this.billingContact = billingContact;
	}

	public String getModificatedBy() {
		return modificatedBy;
	}

	public void setModificatedBy(String modificatedBy) {
		this.modificatedBy = modificatedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	

	
	
}
