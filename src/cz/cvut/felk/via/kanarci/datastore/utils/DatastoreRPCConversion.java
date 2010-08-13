package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.objects.Contact;
import cz.cvut.felk.via.kanarci.datastore.objects.Customer;
import cz.cvut.felk.via.kanarci.datastore.objects.DeliveryMethod;
import cz.cvut.felk.via.kanarci.datastore.objects.Goods;
import cz.cvut.felk.via.kanarci.datastore.objects.Order;
import cz.cvut.felk.via.kanarci.datastore.objects.OrderState;
import cz.cvut.felk.via.kanarci.gui.shared.AddressRPC;
import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;
import cz.cvut.felk.via.kanarci.gui.shared.CustomerRPC;
import cz.cvut.felk.via.kanarci.gui.shared.DeliveryMethodRPC;
import cz.cvut.felk.via.kanarci.gui.shared.GoodsRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderStateRPC;

public class DatastoreRPCConversion implements IDatastoreRPCConversion {

	public DatastoreRPCConversion() {
		super();
	}

	/* ------------------------------------------------------------ */

	private List<String> keyToString(List<Key> category) {
		List<String> ret = new ArrayList<String>();
		for(Key k : category){
			ret.add(KeyFactory.keyToString(k));
		}
		return ret;
	}	
	
	private List<Key> stringToKey(List<String> category) {
		List<Key> ret = new ArrayList<Key>();
		for(String s : category){
			ret.add(KeyFactory.stringToKey(s));
		}
		return ret;
	}
	
	/* ------------------------------------------------------------ */
	
	@Override
	public OrderRPC OrderToRPC(Order order) {
		return new OrderRPC(KeyFactory.keyToString(order.getKey()), 
				orderStateToRPC(order.getOrderState()), order.getCreationDate(), order.getCloseDate(), 
				order.getModificationDate(), order.getCourierShipmentDate(), 
				order.getEstimatedDeliveryDate(), order.getDeliveryDate(), 
				deliveryMethodToRPC(order.getDeliveryMethod()), goodsToRPC(order.getGoodsInOrder()), 
				contactToRPC(order.getDeliveryContact()), 
				contactToRPC(order.getBillingContact()), KeyFactory.keyToString(order.getModificatedBy()), 
				KeyFactory.keyToString(order.getCreatedBy()));
	}
	

	@Override
	public AddressRPC addressToRPC(Address adr) {
		return new AddressRPC(KeyFactory.keyToString(adr.getKey()),adr.getCity(), 
				adr.getStreet(), adr.getCo(), adr.getCp(), adr.getZip());
	}

	@Override
	public ContactRPC contactToRPC(Contact con) {
		AddressRPC adr = new AddressRPC(KeyFactory.keyToString(con.getAddress().getKey()),
				con.getAddress().getCity(), con.getAddress().getStreet(), 
				con.getAddress().getCo(), con.getAddress().getCp(), con.getAddress().getZip());
		return new ContactRPC(KeyFactory.keyToString(con.getKey()),con.getFirstName(), con.getSureName(),
				con.getPhone(), con.getCorporationName(), con.getEmail(), 
				con.getDepartment(), adr);
	}

	@Override
	public CustomerRPC customerToRPC(Customer cust) {
		return new CustomerRPC(KeyFactory.keyToString(cust.getKey()),
				contactToRPC(cust.getContact()), OrderToRPC(cust.getOrders()));
	}

	@Override
	public GoodsRPC goodsToRPC(Goods goods) {
		return new GoodsRPC(KeyFactory.keyToString(goods.getKey()), goods.getName(), 
				goods.getDescription().toString(), goods.getPrice(), goods.getNumOfPieces(), 
				goods.getVisiblity(), goods.getDPH(), KeyFactory.keyToString(goods.getSupplier()), 
				keyToString(goods.getCategory()));
	}

	public DeliveryMethodRPC deliveryMethodToRPC(DeliveryMethod deliveryMethod) {
		return DeliveryMethodRPC.valueOf(deliveryMethod.name());
	}

	public OrderStateRPC orderStateToRPC(OrderState orderState) {
		return OrderStateRPC.valueOf(orderState.name());
	}
	
	/* ------------------------------------------------------------ */
	
	@Override
	public List<OrderRPC> OrderToRPC(List<Order> orders) {
		List<OrderRPC> ret = new ArrayList<OrderRPC>();
		for(Order o : orders){
			ret.add(OrderToRPC(o));
		}
		return ret;
	}

	@Override
	public List<ContactRPC> contactToRPC(List<Contact> con) {
		List<ContactRPC> ret = new ArrayList<ContactRPC>();
		for(Contact c : con){
			ret.add(contactToRPC(c));
		}
		return ret;
	}

	@Override
	public List<CustomerRPC> customerToRPC(List<Customer> cust) {
		List<CustomerRPC> ret = new ArrayList<CustomerRPC>();
		for(Customer c : cust){
			ret.add(customerToRPC(c));
		}
		return ret;
	}
	
	@Override
	public List<GoodsRPC> goodsToRPC(List<Goods> goods) {
		List<GoodsRPC> ret = new ArrayList<GoodsRPC>();
		for(Goods g : goods){
			ret.add(goodsToRPC(g));
		}
		return ret;
	}

	/* ------------------------------------------------------------ */
	
	@Override
	public Order OrderFromRPC(OrderRPC order) {
		return new Order(KeyFactory.stringToKey(order.getKey()), orderStateFromRPC(order.getOrderState()), 
				order.getCreationDate(), order.getCloseDate(),order.getModificationDate(), 
				order.getCourierShipmentDate(),	order.getEstimatedDeliveryDate(), 
				order.getDeliveryDate(), deliveryMethodFromRPC(order.getDeliveryMethod()), 
				goodsFromRPC(order.getGoodsInOrder()), contactFromRPC(order.getDeliveryContact()), 
				contactFromRPC(order.getBillingContact()), KeyFactory.stringToKey(order.getModificatedBy()),
				KeyFactory.stringToKey(order.getCreatedBy()));
	}

	@Override
	public Address addressFromRPC(AddressRPC adr) {
		return new Address(KeyFactory.stringToKey(adr.getKey()), adr.getStreet(), adr.getCo(),
				adr.getCp(), adr.getCity(), adr.getZip());
	}

	@Override
	public Contact contactFromRPC(ContactRPC con) {
		return new Contact(KeyFactory.stringToKey(con.getKey()), con.getFirstName(), con.getSureName(), 
				con.getPhone(), con.getCorporationName(), con.getEmail(), con.getDepartment(), 
				addressFromRPC(con.getAddress()));
	}

	@Override
	public Customer customerFromRPC(CustomerRPC cust) {
		return new Customer(KeyFactory.stringToKey(cust.getKey()), contactFromRPC(cust.getContactInfo()));
	}

	@Override
	public Goods goodsFromRPC(GoodsRPC goods) {
		return new Goods(KeyFactory.stringToKey(goods.getKey()), goods.getName(),
				new Text(goods.getDescription()), goods.getPrice(), goods.getNumOfPieces(), 
				goods.getVisiblity(), goods.getDPH(), KeyFactory.stringToKey(goods.getSupplier()),
				stringToKey(goods.getCategory()));		
	}

	public DeliveryMethod deliveryMethodFromRPC(DeliveryMethodRPC deliveryMethod) {
		return DeliveryMethod.valueOf(deliveryMethod.name());
	}

	public OrderState orderStateFromRPC(OrderStateRPC orderState) {
		return OrderState.valueOf(orderState.name());
	}
	/* ------------------------------------------------------------ */

	@Override
	public List<Order> OrderFromRPC(List<OrderRPC> orders) {
		List<Order> ret = new ArrayList<Order>();
		for(OrderRPC o : orders){
			ret.add(OrderFromRPC(o));
		}
		return ret;
	}

	@Override
	public List<Contact> contactFromRPC(List<ContactRPC> con) {
		List<Contact> ret = new ArrayList<Contact>();
		for(ContactRPC c : con){
			ret.add(contactFromRPC(c));
		}
		return ret;
	}

	@Override
	public List<Customer> customerFromRPC(List<CustomerRPC> cust) {
		List<Customer> ret = new ArrayList<Customer>();
		for(CustomerRPC c : cust){
			ret.add(customerFromRPC(c));
		}
		return ret;
	}
	
	@Override
	public List<Goods> goodsFromRPC(List<GoodsRPC> goods) {
		List<Goods> ret = new ArrayList<Goods>();
		for(GoodsRPC g : goods){
			ret.add(goodsFromRPC(g));
		}
		return ret;
	}

	/* ------------------------------------------------------------- */
}
