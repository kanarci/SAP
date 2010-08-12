package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

import cz.cvut.felk.via.kanarci.datastore.objects.*;


public interface IDatastoreUtil {

	public void addAddress(String street, int co, int cp, String city, String zip);
	
	public void addCategory(String name);
	
	public void addCategory(String name, Key supremeCategory);
	
	public void addCategory(String name, Key supremeCategory, String parameterName
			, String parameterValue);
	
	public void addContact(String firstName, String sureName, String phone
			, String email,	Address address);
	
	public void addContact(String firstName, String sureName, String phone,
			String corporationName, String email, String department, Address address);

	public void addCustomer(Contact contact);
	
	public void addEmployee( Contact contact);

	public void addEmployee( Contact contact, double salary
			, String bankAccountNumber);
	
	public void addEmployee( Contact contact, double salary
			, String bankAccountNumber, Key inTeam);

	public void addGoods(String name);
	
	public void addGoods(String name, Text description, double price
			, int numOfPieces, boolean visiblity, Key supplier, List<Key> category);
	
	public void addGoods(String name, Text description, double price
			, int numOfPieces, boolean visiblity, Key category);
	
	public void addInvoiceCustomer(Date maturityDate, Order orderInvoice);

	public void addInvoiceSupplier(Supplier supplier, Date maturityDate, Order orderInvoice);

	public void addOrder(String state, Date estimatedDeliveryDate,
			Date deliveryDate, DeliveryMethod deliveryMethod, List<Goods> goodsInOrder,
			Contact deliveryContact, Contact billingContact, Key adddBy);
	
	public void addOrder(Date creationDate, DeliveryMethod deliveryMethod,
			Date deliveryDate, Date estimatedDeliveryDate,
			List<Goods> goodsInOrder, Contact deliveryContact,
			Contact billingContact, Key modificatedBy, Key adddBy);

	public void addSupplier(String accountNumber, String companyName, Contact contact);
	
	public void addTeam(String name);
	
	public void addTeam(String name, List<Employee> employeesInTeam);

	
	/* ------------------------------------------------------------ */	
	
	public void deleteAllAddress();
	
	public void deleteAllCategories();
	
	public void deleteAllContacts();
	
	public void deleteAllCustomers();
	
	public void deleteAllEmployees();

	public void deleteAllGoods();
	
	public void deleteAllInvoiceCustomers();
	
	public void deleteAllInvoiceSuppliers();
	
	public void deleteAllOrders();
	
	public void deleteAllSuppliers();
	
	public void deleteAllTeams();
	
	/* ------------------------------------------------------------ */
	
//	public String getAllAddress();
	
	public List<Category> getAllCategories();

	public List<Contact> getAllContacts();
	
	public List<Customer> getAllCustomers();
	
	public List<Employee> getAllEmployees();
	
	public List<Goods> getAllGoods();
	
	public List<Invoice_customer> getAllInvoices_customer(Key key);
	
	public List<Invoice_supplier> getAllInvoices_supplier(Key key);
	
	public List<Order> getAllOrders();

	/**
	 * 
	 * @param key User key
	 * @return User's orders
	 */
	public List<Order> getAllOrders(Key key);
	
	public List<Supplier> getAllSuppliers();
	
	public List<Team> getAllTeams();
	
	
	/* ------------------------------------------------------------ */
	
}
