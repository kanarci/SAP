package cz.cvut.felk.via.kanarci.datastore.objects;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Invoice_customer extends Invoice {

	public Invoice_customer(Date maturityDate, Order orderInvoice) {
		super(maturityDate, orderInvoice);
	}

}
