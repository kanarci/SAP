package cz.cvut.felk.via.kanarci.datastore.objects;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
// (detachable="true")
public class Customer extends Person {

	@Persistent
	private int id_customer;

	@Persistent
	private List<Key> orders;

	@Persistent
	private List<Key> invoice;

	public Customer(Contact contact) {
		super(contact);
		invoice = new ArrayList<Key>();
	}

	public int getId_customer() {
		return id_customer;
	}

	public void setId_customer(int idcustomer) {
		id_customer = idcustomer;
	}

	public List<Key> getOrders() {
		return orders;
	}

	public void addOrder(Key var) {
		this.orders.add(var);
	}

	public List<Key> getInvoices() {
		return invoice;
	}

	// public void setInvoices(List<Key> invoice) {
	// this.invoice = invoice;
	// }
	//	
	public void addInvoice(Key var) {
		this.invoice.add(var);
	}

}