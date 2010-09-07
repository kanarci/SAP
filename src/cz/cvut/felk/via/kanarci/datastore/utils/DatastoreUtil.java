package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.jdo.identity.ObjectIdentity;

import org.datanucleus.jpa.Persistable;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

import cz.cvut.felk.via.kanarci.datastore.objects.*;
import cz.cvut.felk.via.kanarci.datastore.utils.PMF;

public class DatastoreUtil implements IDatastoreUtil {

	private final Logger log = Logger.getLogger("DataNucleus.JDO");

	/**
	 * Default constructor
	 */
	DatastoreUtil() {
		super();
	}

	private <T> void makeObjectPersistent(T o) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(o);
			System.out.println("Object " + o.getClass().getSimpleName()
					+ " saved in datastore " + o.toString());
		} finally {
			pm.close();
		}
	}

	private <T> Key makeObjectPersistentRetKey(T o) {

		Key ret = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(o);
			System.out.println("Object " + o.getClass().getSimpleName()
					+ " saved in datastore");
			// TODO
			// System.out.println("ObjectId is " + pm.getObjectId(o));
			ObjectIdentity oi = (ObjectIdentity) pm.getObjectId(o);
			ret = (Key) oi.getKey();
		} finally {
			pm.close();
		}
		return ret;
	}

	private void deleteAllPersistentObjects(Class<?> o) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			Query q = pm.newQuery(o);
			q.deletePersistentAll();
			System.out.println("All " + o.getClass().getSimpleName()
					+ " objects wiped out from datastore");
		} catch (JDOUserException e) {
			System.out.println(" error: nebylo co deletovat");
			log.log(Level.SEVERE, "Failed in all customers delete", e);
			try {
				Query q = pm.newQuery(o);
				q.deletePersistentAll();
			} catch (JDOUserException ex) {
				System.out.println(" error: stale neni co deletovat");
			}
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	private <T> void deletePersistentObject(T o, Key key) {
		System.out.println("Deleting object " + o.getClass().getSimpleName()
				+ " with key " + KeyFactory.keyToString(key));
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			T obj = (T) pm.getObjectById(o.getClass(), key);
			pm.deletePersistent(obj);
			System.out.println(" Object wiped out from datastore");
		} catch (JDOUserException ex) {
			System.out.println(" Object was not deleted from datastore");
		} finally {
			pm.close();
		}

	}

	@SuppressWarnings( { "unchecked" })
	private <T> List<T> getAllObjects(Class<T> o) {

		List<T> ret;

		PersistenceManager pm = PMF.get().getPersistenceManager();

		pm.getFetchPlan().setMaxFetchDepth(4);

		try {
			Query q = pm.newQuery(o);
			List<T> qret = (List<T>) q.execute();
			ret = new ArrayList<T>(pm.detachCopyAll(qret));
		} finally {
			pm.close();
		}
		return ret;
	}

	@SuppressWarnings( { "unchecked" })
	private <T> List<T> getAllObjectsOrderedBy(Class<T> o, String orderValue) {

		List<T> ret;

		StringTokenizer st = new StringTokenizer(orderValue);
		String orderString = "";
		while (st.hasMoreElements()) {
			orderString = orderString.concat(st.nextElement().toString()
					+ " asc, ");
		}
		orderString = orderString.substring(0, orderString.length() - 2);
		System.out.println("order string : " + orderString);

		PersistenceManager pm = PMF.get().getPersistenceManager();

		pm.getFetchPlan().setMaxFetchDepth(4);

		try {
			Query q = pm.newQuery(o);
			q.setOrdering(orderString);
			// q.setOrdering(sortValue+" desc");
			List<T> qret = (List<T>) q.execute();
			ret = new ArrayList<T>(pm.detachCopyAll(qret));
		} finally {
			pm.close();
		}
		return ret;
	}

	private <T> T getObjectForKey(Class<T> o, Key key) {

		if (key == null) {
			System.out.println("getObjectForKey(class<T> o,key) - key was null");
			return null;
		}

		PersistenceManager pm = PMF.get().getPersistenceManager();

		pm.getFetchPlan().setMaxFetchDepth(4);

		T obj;
		try {
			obj = (T) pm.getObjectById(o, key);
			obj = pm.detachCopy(obj);
		} finally {
			pm.close();
		}

		return obj;
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> getObjectsForKey(Class<T> o, List<Key> objectsInCategory) {

		List<T> ret = new ArrayList<T>();

		PersistenceManager pm = PMF.get().getPersistenceManager();

		pm.getFetchPlan().setMaxFetchDepth(4);

		try {
			Query query = pm.newQuery(o, ":p.contains(key)");
			ret = (List<T>) query.execute(objectsInCategory);

			ret = new ArrayList<T>(pm.detachCopyAll(ret));
		} finally {
			pm.close();
		}

		return ret;
	}

	/* ------------------------------------------------------------ */

	public void addAddress(String street, int co, int cp, String city,
			String zip) {

		Address adr = new Address(city, street, co, cp, zip);
		makeObjectPersistent(adr);
	}

	@Override
	public void addCategory(Category cat) {
		makeObjectPersistent(cat);
	}

	@Override
	public void addCategory(String name) {
		Category cat = new Category(name, KeyFactory.createKey("Category",
				"Root"));
		makeObjectPersistent(cat);
	}

	@Override
	public void addCategory(String name, Key supremeCategory) {

		Category cat = new Category(name, supremeCategory);
		makeObjectPersistent(cat);
	}

	@Override
	public void addCategory(String name, Key supremeCategory,
			String parameterName, String parameterValue) {

		Category cat = new Category(name, supremeCategory, parameterName,
				parameterValue);
		makeObjectPersistent(cat);
	}

	@Override
	public void addContact(String firstName, String sureName, String phone,
			String email, Address address) {

		Contact con = new Contact(firstName, sureName, phone, email, address);
		makeObjectPersistent(con);
	}

	@Override
	public void addContact(String firstName, String sureName, String phone,
			String corporationName, String email, String department,
			Address address) {

		Contact con = new Contact(firstName, sureName, phone, corporationName,
				email, department, address);
		makeObjectPersistent(con);
	}

	@Override
	public void addContact(Contact con) {
		makeObjectPersistent(con);
	}

	@Override
	public void addCustomer(Customer cust) {
		makeObjectPersistent(cust);
	}

	@Override
	public void addCustomer(Contact contact) {
		Customer cust = new Customer(contact);
		makeObjectPersistent(cust);
	}

	@Override
	public void addEmployee(Employee emp) {
		makeObjectPersistent(emp);
	}

	@Override
	public void addEmployee(Contact contact) {
		Employee emp = new Employee(contact);
		makeObjectPersistent(emp);
	}

	@Override
	public void addEmployee(Contact contact, double salary,
			String bankAccountNumber) {
		Employee emp = new Employee(contact, salary, bankAccountNumber);
		makeObjectPersistent(emp);
	}

	@Override
	public void addEmployee(Contact contact, double salary,
			String bankAccountNumber, Key inTeam) {
		Employee emp = new Employee(contact, salary, bankAccountNumber, inTeam);
		makeObjectPersistent(emp);
	}

	@Override
	public void addGoods(Goods goods) {

		Key goodsKey = null;

		goodsKey = makeObjectPersistentRetKey(goods);

		PersistenceManager pm = PMF.get().getPersistenceManager();

		int i = 1;
		for (Key k : goods.getCategory()) {
			System.out.println(" Adding to " + i + " category with key " + k);
			Category cat = (Category) pm.getObjectById(Category.class, k);
			cat.addGoodsToCategory(goodsKey);
		}

		pm.close();

	}

	@Override
	public void addGoods(String name) {
		Goods goods = new Goods(name);
		makeObjectPersistent(goods);
	}

	@Override
	public void addGoods(String name, Text description, double price,
			int numOfPieces, boolean visiblity, Key supplier, List<Key> category) {

		Goods goods = new Goods(name, description, price, numOfPieces,
				visiblity, supplier, category);
		makeObjectPersistent(goods);
	}

	@Override
	public void addGoods(String name, Text description, double price,
			int numOfPieces, boolean visiblity, Key category) {

		Goods goods = new Goods(name, description, price, numOfPieces,
				visiblity, category);
		makeObjectPersistent(goods);
	}

	@Override
	public void addInvoiceCustomer(Invoice_customer invc) {
		makeObjectPersistent(invc);
	}

	@Override
	public void addInvoiceCustomer(Date maturityDate, Order orderInvoice) {

		Invoice_customer icust = new Invoice_customer(maturityDate,
				orderInvoice);
		makeObjectPersistent(icust);
	}

	@Override
	public void addInvoiceSupplier(Invoice_supplier invs) {
		makeObjectPersistent(invs);
	}

	@Override
	public void addInvoiceSupplier(Supplier supplier, Date maturityDate,
			Order orderInvoice) {

		Invoice_supplier isup = new Invoice_supplier(supplier, maturityDate,
				orderInvoice);
		makeObjectPersistent(isup);
	}

	@Override
	public void addOrder(Order order) {
		makeObjectPersistent(order);
	}

	@Override
	public void addOrder(String state, Date estimatedDeliveryDate,
			Date deliveryDate, DeliveryMethod deliveryMethod,
			List<Key> goodsInOrder, Contact deliveryContact,
			Contact billingContact, Key addedBy) {

		Order order = new Order(state, estimatedDeliveryDate, deliveryDate,
				deliveryMethod, goodsInOrder, deliveryContact, billingContact,
				addedBy);
		makeObjectPersistent(order);
	}

	@Override
	public void addOrder(Date creationDate, DeliveryMethod deliveryMethod,
			Date deliveryDate, Date estimatedDeliveryDate,
			List<Key> goodsInOrder, Contact deliveryContact,
			Contact billingContact, Key modificatedBy, Key adddBy) {

		Order order = new Order(creationDate, deliveryMethod, deliveryDate,
				estimatedDeliveryDate, goodsInOrder, deliveryContact,
				billingContact, modificatedBy, adddBy);
		makeObjectPersistent(order);
	}

	@Override
	public void addSupplier(String accountNumber, String companyName,
			Contact contact) {

		Supplier sup = new Supplier(accountNumber, contact);
		makeObjectPersistent(sup);
	}

	@Override
	public void addSupplier(Supplier sup) {
		makeObjectPersistent(sup);
	}

	@Override
	public void addTeam(String name) {
		Team team = new Team(name);
		makeObjectPersistent(team);
	}

	@Override
	public void addTeam(String name, List<Employee> employeesInTeam) {

		Team team = new Team(name, employeesInTeam);
		makeObjectPersistent(team);
	}

	@Override
	public void addTeam(Team team) {
		makeObjectPersistent(team);
	}

	/* ------------------------------------------------------------ */

	@Override
	public void deleteAllAddress() {
		deleteAllPersistentObjects(Address.class);
	}

	@Override
	public void deleteAllCategories() {
		deleteAllPersistentObjects(Category.class);
	}

	@Override
	public void deleteAllContacts() {
		deleteAllPersistentObjects(Contact.class);
	}

	@Override
	public void deleteAllCustomers() {
		deleteAllPersistentObjects(Customer.class);
	}

	@Override
	public void deleteAllEmployees() {
		deleteAllPersistentObjects(Employee.class);
	}

	@Override
	public void deleteAllGoods() {
		deleteAllPersistentObjects(Goods.class);
	}

	@Override
	public void deleteAllInvoiceCustomers() {
		deleteAllPersistentObjects(Invoice_customer.class);
	}

	@Override
	public void deleteAllInvoiceSuppliers() {
		deleteAllPersistentObjects(Invoice_supplier.class);
	}

	@Override
	public void deleteAllOrders() {
		deleteAllPersistentObjects(Order.class);
	}

	@Override
	public void deleteAllSuppliers() {
		deleteAllPersistentObjects(Supplier.class);
	}

	@Override
	public void deleteAllTeams() {
		deleteAllPersistentObjects(Team.class);
	}

	/* ------------------------------------------------------------ */

	@Override
	public List<Customer> getAllCustomers() {
		return getAllObjects(Customer.class);
	}

	@Override
	public List<Category> getAllCategories() {
		// return getAllObjects(Category.class);
		return getAllObjectsOrderedBy(Category.class,
				"name parameterName parameterValue");

	}

	@Override
	public List<Contact> getAllContacts() {
		return getAllObjects(Contact.class);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return getAllObjects(Employee.class);
	}

	@Override
	public List<Goods> getAllGoods() {
		return getAllObjects(Goods.class);
	}

	@Override
	public List<Goods> getAllGoods(Category cat) {
		// System.out.println("getAllGoods(cat) - Encoded key "+cat.getKey() );
		Category updCat = getObjectForKey(Category.class, cat.getKey());
		if (updCat == null || updCat.getGoodsInCategory().isEmpty()) {
			System.out
					.println("getAllGoods(cat) - updCat was null or empty category ");
			return new ArrayList<Goods>();
		}
		return getObjectsForKey(Goods.class, updCat.getGoodsInCategory());
	}

	@Override
	public List<Invoice_customer> getAllInvoices_customer(Key key) {
		return getAllObjects(Invoice_customer.class);
	}

	@Override
	public List<Invoice_supplier> getAllInvoices_supplier(Key key) {
		return getAllObjects(Invoice_supplier.class);
	}

	@Override
	public List<Order> getAllOrders() {
		return getAllObjects(Order.class);
	}

	@Override
	public List<Order> getAllOrders(Key key) {
		return getAllObjects(Order.class);
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		return getAllObjects(Supplier.class);
	}

	@Override
	public List<Team> getAllTeams() {
		return getAllObjects(Team.class);
	}

	/* ------------------------------------------------------------ */

	@Override
	public void deleteAddress(Address adr) {
		deletePersistentObject(adr, adr.getKey());

	}

	@Override
	public void deleteCategory(Category cat) {
		deletePersistentObject(cat, cat.getKey());
	}

	@Override
	public void deleteContact(Contact con) {
		deletePersistentObject(con, con.getKey());

	}

	@Override
	public void deleteCustomer(Customer cust) {
		deletePersistentObject(cust, cust.getKey());

	}

	@Override
	public void deleteEmployee(Employee emp) {
		deletePersistentObject(emp, emp.getKey());

	}

	@Override
	public void deleteGoods(Goods goods) {
		deletePersistentObject(goods, goods.getKey());

	}

	@Override
	public void deleteInvoiceCustomer(Invoice_customer invc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteInvoiceSupplier(Invoice_supplier invs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOrder(Order order) {
		deletePersistentObject(order, order.getKey());

	}

	@Override
	public void deleteSupplier(Supplier supplier) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTeam(Team team) {
		deletePersistentObject(team, team.getKey());

	}

	@Override
	public void updateAddress(Address adr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCategory(Category cat) {
		// deletePersistentObject(cat, cat.getKey());
		makeObjectPersistent(cat);
	}

	@Override
	public void updateContact(Contact con) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCustomer(Customer cust) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEmployee(Employee emp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGoods(Goods goods) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateInvoiceCustomer(Invoice_customer invc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateInvoiceSupplier(Invoice_supplier invs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTeam(Team team) {
		// TODO Auto-generated method stub

	}

}
