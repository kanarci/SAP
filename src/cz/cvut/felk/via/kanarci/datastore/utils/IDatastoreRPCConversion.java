package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.List;

import cz.cvut.felk.via.kanarci.gui.shared.*;
import cz.cvut.felk.via.kanarci.datastore.objects.*;

public interface IDatastoreRPCConversion {

	public CustomerRPC customerToRPC(Customer cust);
	
	public ContactRPC contactToRPC(Contact con);
	
	public GoodsRPC goodsToRPC(Goods goods);

	public OrderRPC OrderToRPC(Order order);

	public AddressRPC addressToRPC(Address adr);

	public DeliveryMethodRPC deliveryMethodToRPC(DeliveryMethod deliveryMethod);

	public OrderStateRPC orderStateToRPC(OrderState orderState);

	/* ---------------------------------------------------------- */
	
	public List<CustomerRPC> customerToRPC(List<Customer> cust);
	
	public List<ContactRPC> contactToRPC(List<Contact> con);
	
	public List<GoodsRPC> goodsToRPC(List<Goods> goods);

	public List<OrderRPC> OrderToRPC(List<Order> orders);

	/* ---------------------------------------------------------- */
	
	public Customer customerFromRPC(CustomerRPC cust);
	
	public Contact contactFromRPC(ContactRPC con);
	
	public Goods goodsFromRPC(GoodsRPC goods);

	public Order OrderFromRPC(OrderRPC order);

	public Address addressFromRPC(AddressRPC adr);

	public DeliveryMethod deliveryMethodFromRPC(DeliveryMethodRPC deliveryMethod);

	public OrderState orderStateFromRPC(OrderStateRPC orderState);
	
	/* ---------------------------------------------------------- */
	
	public List<Customer> customerFromRPC(List<CustomerRPC> cust);
	
	public List<Contact> contactFromRPC(List<ContactRPC> con);
	
	public List<Goods> goodsFromRPC(List<GoodsRPC> goods);

	public List<Order> OrderFromRPC(List<OrderRPC> orders);
	
	/* ---------------------------------------------------------- */
	
}
