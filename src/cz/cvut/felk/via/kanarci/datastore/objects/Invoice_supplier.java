package cz.cvut.felk.via.kanarci.datastore.objects;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Invoice_supplier extends Invoice {

	@Persistent
	private Supplier supplier;

	public Invoice_supplier(Supplier supplier, Date maturityDate,
			Order orderInvoice) {
		super(maturityDate, orderInvoice);
		this.supplier = supplier;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier val) {
		this.supplier = val;
	}

}
