package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.List;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.objects.Contact;
import cz.cvut.felk.via.kanarci.datastore.objects.Customer;
import cz.cvut.felk.via.kanarci.datastore.objects.Goods;
import cz.cvut.felk.via.kanarci.datastore.objects.OrderState;
import cz.cvut.felk.via.kanarci.gui.shared.AddressRPC;
import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;
import cz.cvut.felk.via.kanarci.gui.shared.CustomerRPC;
import cz.cvut.felk.via.kanarci.gui.shared.GoodsRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderStateRPC;

public class DatastoreToRPCConversion implements IDatastoreToRPCConversion {

	public DatastoreToRPCConversion() {
		super();
	}

	@Override
	public OrderStateRPC OrderStateToRPC(Goods goods) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerRPC custToRPC(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GoodsRPC goodsToRPC(Goods goods) {
		// TODO Auto-generated method stub
		return null;
	}

	/* ------------------------------------------------------------ */
	
	@Override
	public List<OrderStateRPC> OrderStateToRPC(List<OrderState> goods) {
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

}
