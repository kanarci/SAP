package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.List;

import cz.cvut.felk.via.kanarci.gui.shared.*;
import cz.cvut.felk.via.kanarci.datastore.objects.*;
import cz.cvut.felk.via.kanarci.datastore.objects.OrderState;

public interface IDatastoreToRPCConversion {

	public CustomerRPC custToRPC(Customer cust);
	
	public ContactRPC contactToRPC(Contact con);
	
	public GoodsRPC goodsToRPC(Goods goods);

	public OrderStateRPC OrderStateToRPC(Goods goods);

	public AddressRPC addressToRPC(Address adr);

	/* ---------------------------------------------------------- */
	public List<CustomerRPC> custToRPC(List<Customer> cust);
	
	public List<ContactRPC> contactToRPC(List<Contact> con);
	
	public List<GoodsRPC> goodsToRPC(List<Goods> goods);

	public List<OrderStateRPC> OrderStateToRPC(List<OrderState> goods);

	public List<AddressRPC> addressToRPC(List<Address> adr);

	
}
