package cz.cvut.felk.via.kanarci.datastore.objects;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class Invoice implements Serializable{


	private static final long serialVersionUID = 5244203137059684625L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	/*
	 * datum splatnosti
	 */
	@Persistent
	private Date maturityDate;

	/*
	 * datum splaceni
	 */
	@Persistent
	private Date repaymentDate;

	@Persistent
	private String state;

	@Persistent
	private Order orderInvoice;

	public Invoice(Date maturityDate, Order orderInvoice) {
		super();
		this.maturityDate = maturityDate;
		this.orderInvoice = orderInvoice;
		this.repaymentDate = null;
		this.state = "untouched";
	}

	public Key getKey() {
		return key;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public Date getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Order getorderInvoice() {
		return orderInvoice;
	}

	public void setorderInvoice(Order orderInvoice) {
		this.orderInvoice = orderInvoice;
	}

}
