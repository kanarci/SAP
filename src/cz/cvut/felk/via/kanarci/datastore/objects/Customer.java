package cz.cvut.felk.via.kanarci.datastore.objects;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
// (detachable="true")
public class Customer extends Person {


	private static final long serialVersionUID = -473531119686226231L;

	@Persistent
	private List<Order> orders;

	@Persistent
	private List<Invoice> invoice;

	public Customer(Contact contact) {
		super(contact);
		invoice = new ArrayList<Invoice>();
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void addOrder(Order var) {
		this.orders.add(var);
	}

	public List<Invoice> getInvoices() {
		return invoice;
	}

	public void addInvoice(Invoice var) {
		this.invoice.add(var);
	}

}
