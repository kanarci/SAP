package cz.cvut.felk.via.kanarci.datastore.objects;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable = "true")
public class Invoice_customer extends Invoice {

	private static final long serialVersionUID = 6618288676680999907L;

	public Invoice_customer(Date maturityDate, Order orderInvoice) {
		super(maturityDate, orderInvoice);
	}

}
