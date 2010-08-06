package cz.cvut.felk.via.kanarci.datastore.objects;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Employee extends Person {

	@Persistent
	private double salary;

	@Persistent
	private String bankAccountNumber;

	@Persistent
	private Key inTeam;

	@Persistent
	private List<Key> createdOrders;

	public Employee(Contact contact) {
		super(contact);
	}

	public Employee(Contact contact, double salary, String bankAccountNumber,
			Key inTeam) {
		super(contact);
		this.salary = salary;
		this.bankAccountNumber = bankAccountNumber;
		this.inTeam = inTeam;
	}

	public double getsalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public Key getInTeam() {
		return inTeam;
	}

	public void setInTeam(Key inTeam) {
		this.inTeam = inTeam;
	}

	public List<Key> getCreatedOrders() {
		return createdOrders;
	}

	public void addCreatedOrder(Key order) {
		createdOrders.add(order);
	}

	public void removeCreatedOrder(Key order) {
		createdOrders.remove(order);
	}

}