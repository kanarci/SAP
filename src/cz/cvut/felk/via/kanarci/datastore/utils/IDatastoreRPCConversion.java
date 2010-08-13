package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.List;

import cz.cvut.felk.via.kanarci.gui.shared.*;
import cz.cvut.felk.via.kanarci.datastore.objects.*;
import cz.cvut.felk.via.kanarci.datastore.objects.OrderState;

public interface IDatastoreRPCConversion {

	public CustomerRPC custToRPC(Customer cust);
	
	public ContactRPC contactToRPC(Contact con);
	
	public GoodsRPC goodsToRPC(Goods goods);

	public OrderRPC OrderToRPC(Order order);

	public AddressRPC addressToRPC(Address adr);

	/* ---------------------------------------------------------- */
	
	public List<CustomerRPC> custToRPC(List<Customer> cust);
	
	public List<ContactRPC> contactToRPC(List<Contact> con);
	
	public List<GoodsRPC> goodsToRPC(List<Goods> goods);

	public List<OrderRPC> OrderToRPC(List<Order> orders);

	public List<AddressRPC> addressToRPC(List<Address> adr);

	/* ---------------------------------------------------------- */
	
	public Customer custFromRPC(CustomerRPC cust);
	
	public Contact contactFromRPC(ContactRPC con);
	
	public Goods goodsFromRPC(GoodsRPC goods);

	public Order OrderFromRPC(OrderRPC order);

	public Address addressFromRPC(AddressRPC adr);

	/* ---------------------------------------------------------- */
	
	public List<Customer> custFromRPC(List<CustomerRPC> cust);
	
	public List<Contact> contactFromRPC(List<ContactRPC> con);
	
	public List<Goods> goodsFromRPC(List<GoodsRPC> goods);

	public List<Order> OrderFromRPC(List<OrderRPC> orders);

	public List<Address> addressFromRPC(List<AddressRPC> adr);
}
