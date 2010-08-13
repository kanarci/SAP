package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.objects.Contact;
import cz.cvut.felk.via.kanarci.datastore.objects.Customer;
import cz.cvut.felk.via.kanarci.datastore.objects.Goods;
import cz.cvut.felk.via.kanarci.datastore.objects.Order;
import cz.cvut.felk.via.kanarci.datastore.objects.OrderState;
import cz.cvut.felk.via.kanarci.gui.shared.AddressRPC;
import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;
import cz.cvut.felk.via.kanarci.gui.shared.CustomerRPC;
import cz.cvut.felk.via.kanarci.gui.shared.GoodsRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderStateRPC;

public class DatastoreRPCConversion implements IDatastoreRPCConversion {

	public DatastoreRPCConversion() {
		super();
	}

	/* ------------------------------------------------------------ */
	
	@Override
	public OrderRPC OrderToRPC(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressRPC addressToRPC(Address adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactRPC contactToRPC(Contact con) {
		AddressRPC adr = new AddressRPC(con.getAddress().getCity(), 
				con.getAddress().getStreet(), con.getAddress().getCo(),
				con.getAddress().getCp(), con.getAddress().getZip());
		return new ContactRPC(con.getFirstName(), con.getSureName(),
				con.getPhone(), con.getCorporationName(), con.getEmail(), 
				con.getDepartment(), adr);
	}

	@Override
	public CustomerRPC custToRPC(Customer cust) {
		return new CustomerRPC(KeyFactory.keyToString(cust.getKey()),
				contactToRPC(cust.getContact()), OrderToRPC(cust.getOrders()));
	}

	@Override
	public GoodsRPC goodsToRPC(Goods goods) {
		// TODO Auto-generated method stub
		return null;
	}

	/* ------------------------------------------------------------ */
	
	@Override
	public List<OrderRPC> OrderToRPC(List<Order> orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddressRPC> addressToRPC(List<Address> adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContactRPC> contactToRPC(List<Contact> con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerRPC> custToRPC(List<Customer> cust) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<GoodsRPC> goodsToRPC(List<Goods> goods) {
		// TODO Auto-generated method stub
		return null;
	}

	/* ------------------------------------------------------------ */
	
	@Override
	public Order OrderFromRPC(OrderRPC order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address addressFromRPC(AddressRPC adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact contactFromRPC(ContactRPC con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer custFromRPC(CustomerRPC cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Goods goodsFromRPC(GoodsRPC goods) {
		// TODO Auto-generated method stub
		return null;
	}

	/* ------------------------------------------------------------ */
	
	@Override
	public List<Order> OrderFromRPC(List<OrderRPC> orderss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> addressFromRPC(List<AddressRPC> adr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> contactFromRPC(List<ContactRPC> con) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> custFromRPC(List<CustomerRPC> cust) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Goods> goodsFromRPC(List<GoodsRPC> goods) {
		// TODO Auto-generated method stub
		return null;
	}

}
