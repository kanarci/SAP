package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.List;

import cz.cvut.felk.via.kanarci.gui.shared.*;
import cz.cvut.felk.via.kanarci.datastore.objects.*;

public interface IDatastoreRPCConversion {

	public CustomerRPC customerToRPC(Customer cust);
	
	public ContactRPC contactToRPC(Contact con);
	
	public GoodsRPC goodsToRPC(Goods goods);

	public OrderRPC orderToRPC(Order order);

	public AddressRPC addressToRPC(Address adr);

	public DeliveryMethodRPC deliveryMethodToRPC(DeliveryMethod deliveryMethod);

	public OrderStateRPC orderStateToRPC(OrderState orderState);
	
	public CategoryRPC categoryToRPC(Category cat);	

	/* ---------------------------------------------------------- */
	
	public List<CustomerRPC> customerToRPC(List<Customer> cust);
	
	public List<ContactRPC> contactToRPC(List<Contact> con);
	
	public List<GoodsRPC> goodsToRPC(List<Goods> goods);

	public List<OrderRPC> orderToRPC(List<Order> orders);
	
	public List<CategoryRPC> categoryToRPC(List<Category> cat);

	/* ---------------------------------------------------------- */
	
	public Customer customerFromRPC(CustomerRPC cust);
	
	public Contact contactFromRPC(ContactRPC con);
	
	public Goods goodsFromRPC(GoodsRPC goods);

	public Order orderFromRPC(OrderRPC order);

	public Address addressFromRPC(AddressRPC adr);

	public DeliveryMethod deliveryMethodFromRPC(DeliveryMethodRPC deliveryMethod);

	public OrderState orderStateFromRPC(OrderStateRPC orderState);
	
	public Category categoryFromRPC(CategoryRPC cat);
	
	/* ---------------------------------------------------------- */
	
	public List<Customer> customerFromRPC(List<CustomerRPC> cust);
	
	public List<Contact> contactFromRPC(List<ContactRPC> con);
	
	public List<Goods> goodsFromRPC(List<GoodsRPC> goods);

	public List<Order> orderFromRPC(List<OrderRPC> orders);
	
	public List<Category> categoryFromRPC(List<CategoryRPC> cat);
	
	/* ---------------------------------------------------------- */
	
}
