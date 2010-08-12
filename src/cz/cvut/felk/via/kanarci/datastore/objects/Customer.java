package cz.cvut.felk.via.kanarci.datastore.objects;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
 (detachable="true")
public class Customer extends Person {


	private static final long serialVersionUID = 4963844968764606571L;

	@Persistent
	private List<Key> orders;

	@Persistent
	private List<Key> invoice;

	public Customer(Contact contact) {
		super(contact);
		invoice = new ArrayList<Key>();
	}

	public List<Key> getOrders() {
		return orders;
	}

	public void addOrder(Key var){
		this.orders.add(var);
	}

	public List<Key> getInvoices() {
		return invoice;
	}

//	public void setInvoices(List<Key> invoice) {
//		this.invoice = invoice;
//	}
//	
	public void addInvoice(Key var){
		this.invoice.add(var);
	}
	


}
