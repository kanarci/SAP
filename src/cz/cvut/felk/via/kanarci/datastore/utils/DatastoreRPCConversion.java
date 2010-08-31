package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.objects.Category;
import cz.cvut.felk.via.kanarci.datastore.objects.Contact;
import cz.cvut.felk.via.kanarci.datastore.objects.Customer;
import cz.cvut.felk.via.kanarci.datastore.objects.DeliveryMethod;
import cz.cvut.felk.via.kanarci.datastore.objects.Goods;
import cz.cvut.felk.via.kanarci.datastore.objects.Order;
import cz.cvut.felk.via.kanarci.datastore.objects.OrderState;
import cz.cvut.felk.via.kanarci.gui.shared.AddressRPC;
import cz.cvut.felk.via.kanarci.gui.shared.CategoryRPC;
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

	private List<String> keyToString(List<Key> keyList) {
		List<String> ret = new ArrayList<String>();
		for (Key k : keyList) {
			ret.add(keyToString(k));
		}
		return ret;
	}

	private List<Key> stringToKey(List<String> stringList) {
		List<Key> ret = new ArrayList<Key>();
		for (String s : stringList) {
			ret.add(stringToKey(s));
		}
		return ret;
	}

	private String keyToString(Key key) {
		if (key == null) {
			return "";
		} else {
			return KeyFactory.keyToString(key);
		}
	}

	private Key stringToKey(String string) {
		if (string == null || string.equals("")) {
//			System.out.println("stringToKey - key was null od empy !!! ");
			return null;
		} else {
//			System.out.println("stringToKey - key "+string);
			return KeyFactory.stringToKey(string);
		}
	}

	/* ------------------------------------------------------------ */

	@Override
	public OrderRPC orderToRPC(Order order) {
		return new OrderRPC(keyToString(order.getKey()), orderStateToRPC(order
				.getOrderState()), order.getCreationDate(), order
				.getCloseDate(), order.getModificationDate(), order
				.getCourierShipmentDate(), order.getEstimatedDeliveryDate(),
				order.getDeliveryDate(), deliveryMethodToRPC(order
						.getDeliveryMethod()), goodsToRPC(order
						.getGoodsInOrder()), contactToRPC(order
						.getDeliveryContact()), contactToRPC(order
						.getBillingContact()), keyToString(order
						.getModificatedBy()), keyToString(order.getCreatedBy()));
	}

	@Override
	public AddressRPC addressToRPC(Address adr) {
		return new AddressRPC(adr.getCity(), adr.getStreet(), adr.getCo(), adr
				.getCp(), adr.getZip());
	}

	@Override
	public ContactRPC contactToRPC(Contact con) {
		AddressRPC adr = new AddressRPC(con.getAddress().getCity(), con
				.getAddress().getStreet(), con.getAddress().getCo(), con
				.getAddress().getCp(), con.getAddress().getZip());
		return new ContactRPC(keyToString(con.getKey()), con.getFirstName(),
				con.getSureName(), con.getPhone(), con.getCorporationName(),
				con.getEmail(), con.getDepartment(), adr);
	}

	@Override
	public CustomerRPC customerToRPC(Customer cust) {
		return new CustomerRPC(keyToString(cust.getKey()), contactToRPC(cust
				.getContact()), orderToRPC(cust.getOrders()));
	}

	@Override
	public GoodsRPC goodsToRPC(Goods goods) {
		return new GoodsRPC(keyToString(goods.getKey()), goods.getName(), goods
				.getDescription().toString(), goods.getPrice(), goods
				.getNumOfPieces(), goods.getVisiblity(), goods.getDPH(),
				keyToString(goods.getSupplier()), keyToString(goods
						.getCategory()));
	}

	public DeliveryMethodRPC deliveryMethodToRPC(DeliveryMethod deliveryMethod) {
		return DeliveryMethodRPC.valueOf(deliveryMethod.name());
	}

	public OrderStateRPC orderStateToRPC(OrderState orderState) {
		return OrderStateRPC.valueOf(orderState.name());
	}

	@Override
	public CategoryRPC categoryToRPC(Category cat) {
		return new CategoryRPC(keyToString(cat.getKey()), cat.getName(),
				keyToString(cat.getSupremeCategory()), cat.getParameterName(),
				cat.getParameterValue());
	}

	/* ------------------------------------------------------------ */

	@Override
	public List<OrderRPC> orderToRPC(List<Order> orders) {
		List<OrderRPC> ret = new ArrayList<OrderRPC>();
		for (Order o : orders) {
			ret.add(orderToRPC(o));
		}
		return ret;
	}

	@Override
	public List<ContactRPC> contactToRPC(List<Contact> con) {
		List<ContactRPC> ret = new ArrayList<ContactRPC>();
		for (Contact c : con) {
			ret.add(contactToRPC(c));
		}
		return ret;
	}

	@Override
	public List<CustomerRPC> customerToRPC(List<Customer> cust) {
		List<CustomerRPC> ret = new ArrayList<CustomerRPC>();
		for (Customer c : cust) {
			ret.add(customerToRPC(c));
		}
		return ret;
	}

	@Override
	public List<GoodsRPC> goodsToRPC(List<Goods> goods) {
		List<GoodsRPC> ret = new ArrayList<GoodsRPC>();
		if (goods != null) {
			for (Goods g : goods) {

				ret.add(goodsToRPC(g));
			}
		}
		return ret;
	}

	@Override
	public List<CategoryRPC> categoryToRPC(List<Category> cat) {
		List<CategoryRPC> ret = new ArrayList<CategoryRPC>();
		if (cat != null) {
			for (Category c : cat) {
				ret.add(categoryToRPC(c));
			}
		}
		return ret;
	}

	/* ------------------------------------------------------------ */

	@Override
	public Order orderFromRPC(OrderRPC order) {
		return new Order(stringToKey(order.getKey()), orderStateFromRPC(order
				.getOrderState()), order.getCreationDate(), order
				.getCloseDate(), order.getModificationDate(), order
				.getCourierShipmentDate(), order.getEstimatedDeliveryDate(),
				order.getDeliveryDate(), deliveryMethodFromRPC(order
						.getDeliveryMethod()), goodsFromRPC(order
						.getGoodsInOrder()), contactFromRPC(order
						.getDeliveryContact()), contactFromRPC(order
						.getBillingContact()), stringToKey(order
						.getModificatedBy()), stringToKey(order.getCreatedBy()));
	}

	@Override
	public Address addressFromRPC(AddressRPC adr) {
		return new Address(adr.getStreet(), adr.getCo(), adr.getCp(), adr
				.getCity(), adr.getZip());
	}

	@Override
	public Contact contactFromRPC(ContactRPC con) {
		return new Contact(stringToKey(con.getKey()), con.getFirstName(), con
				.getSureName(), con.getPhone(), con.getCorporationName(), con
				.getEmail(), con.getDepartment(), addressFromRPC(con
				.getAddress()));
	}

	@Override
	public Customer customerFromRPC(CustomerRPC cust) {
		return new Customer(stringToKey(cust.getKey()), contactFromRPC(cust
				.getContactInfo()));
	}

	@Override
	public Goods goodsFromRPC(GoodsRPC goods) {
		return new Goods(stringToKey(goods.getKey()), goods.getName(),
				new Text(goods.getDescription()), goods.getPrice(), goods
						.getNumOfPieces(), goods.getVisiblity(),
				goods.getDPH(), stringToKey(goods.getSupplier()),
				stringToKey(goods.getCategory()));
	}

	public DeliveryMethod deliveryMethodFromRPC(DeliveryMethodRPC deliveryMethod) {
		return DeliveryMethod.valueOf(deliveryMethod.name());
	}

	public OrderState orderStateFromRPC(OrderStateRPC orderState) {
		return OrderState.valueOf(orderState.name());
	}

	@Override
	public Category categoryFromRPC(CategoryRPC cat) {
		if (cat.getGoodsInCategory() == null || cat.getGoodsInCategory().isEmpty()) {
//			System.out.println("DRPCC0 - cat key "+cat.getKey());
			return new Category(stringToKey(cat.getKey()), cat.getName(),
					stringToKey(cat.getSupremeCategory()), cat
							.getParameterName(), cat.getParameterValue());
		} else {
//			System.out.println("DRPCC1 - cat key "+cat.getKey());
			return new Category(stringToKey(cat.getKey()), cat.getName(),
					stringToKey(cat.getSupremeCategory()), stringToKey(cat
							.getGoodsInCategory()), cat.getParameterName(), cat
							.getParameterValue());
		}
	}

	/* ------------------------------------------------------------ */

	@Override
	public List<Order> orderFromRPC(List<OrderRPC> orders) {
		List<Order> ret = new ArrayList<Order>();
		for (OrderRPC o : orders) {
			ret.add(orderFromRPC(o));
		}
		return ret;
	}

	@Override
	public List<Contact> contactFromRPC(List<ContactRPC> con) {
		List<Contact> ret = new ArrayList<Contact>();
		for (ContactRPC c : con) {
			ret.add(contactFromRPC(c));
		}
		return ret;
	}

	@Override
	public List<Customer> customerFromRPC(List<CustomerRPC> cust) {
		List<Customer> ret = new ArrayList<Customer>();
		for (CustomerRPC c : cust) {
			ret.add(customerFromRPC(c));
		}
		return ret;
	}

	@Override
	public List<Goods> goodsFromRPC(List<GoodsRPC> goods) {
		List<Goods> ret = new ArrayList<Goods>();
		for (GoodsRPC g : goods) {
			ret.add(goodsFromRPC(g));
		}
		return ret;
	}

	@Override
	public List<Category> categoryFromRPC(List<CategoryRPC> cat) {
		List<Category> ret = new ArrayList<Category>();
		for (CategoryRPC c : cat) {
			ret.add(categoryFromRPC(c));
		}
		return ret;
	}
	/* ------------------------------------------------------------- */
}
