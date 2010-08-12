package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

import cz.cvut.felk.via.kanarci.datastore.objects.*;
import cz.cvut.felk.via.kanarci.datastore.utils.PMF;

public class DatastoreUtil implements IDatastoreUtil{

	private final Logger log = Logger.getLogger("DataNucleus.JDO");

	// default constructor
	public DatastoreUtil() {
		super();
	}

	private <T> void makeObjectPersistent(T o){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(o);
		}finally{
			pm.close();
		}
	}
	
	private void deleteAllPersistentObjects(Class<?> o){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			Query q = pm.newQuery(o);
			q.deletePersistentAll();
		}
		catch(JDOUserException e){
			System.out.println(" error: nebylo co deletovat");
			log.log(Level.SEVERE, "Failed in all customers delete", e); 
			try{
				Query q = pm.newQuery(o);
				q.deletePersistentAll();
			}
			catch(JDOUserException ex){
				System.out.println(" error: stale neni co deletovat");
			}
		}
		finally{
			pm.close();	
		}
	}

	
	@SuppressWarnings({ "unchecked" })
	private <T> T getUniqueObjectForIdentifier(T o,	String property, String identifier){
		
		T obj = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
  
			Query q = pm.newQuery(o.getClass());
			q.setFilter(property + " == " + identifier);
			q.setUnique(true);
			T qobj = (T) q.execute();
			obj = pm.detachCopy(qobj);
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		return obj;
	}
	
	
	/* ------------------------------------------------------------ */
	public void addAddress(String street, int co, int cp, String city, String zip){
		
		Address adr = new Address(city, street, co, cp, zip);
		makeObjectPersistent(adr);
	}
	
	public void addCategory(String name, Key supremeCategory){
		
		Category cat = new Category(name, supremeCategory);	
		makeObjectPersistent(cat);
	}
	
	public void addCategory(String name){
		
		
	}
	
	public void addCategory(String name, Key supremeCategory, String parameterName
			, String parameterValue){
		
		Category cat = new Category(name, supremeCategory, parameterName, parameterValue);
		makeObjectPersistent(cat);
	}
	
	public void addContact(String firstName, String sureName, String phone
			, String email,	Address address){
		
		Contact con = new Contact(firstName, sureName, phone, email, address);
		makeObjectPersistent(con);
	}
	
	public void addContact(String firstName, String sureName, String phone,
			String corporationName, String email, String department, Address address){
		
		Contact con = new Contact(firstName, sureName, phone, corporationName, email, department, address);
		makeObjectPersistent(con);
	}	

	public void addCustomer(Contact contact){
		
		Customer cust = new Customer(contact);
		makeObjectPersistent(cust);
	}
	
	public void addEmployee( Contact contact){
		
		Employee emp = new Employee(contact);
		makeObjectPersistent(emp);
	}
	
	public void addEmployee( Contact contact, double salary
			, String bankAccountNumber, Key inTeam){
		
		Employee emp = new Employee(contact, salary, bankAccountNumber, inTeam);
		makeObjectPersistent(emp);
	}
	
	public void addGoods(String name, Text description, double price
			, int numOfPieces, boolean visiblity, Key supplier, List<Key> category){

		Goods goods = new Goods(name, description, price, numOfPieces, visiblity
				, supplier, category);
		makeObjectPersistent(goods);
	}
	
	public void addGoods(String name, Text description, double price
			, int numOfPieces, boolean visiblity, Key category){

		Goods goods = new Goods(name, description, price, numOfPieces, visiblity, category);
		makeObjectPersistent(goods);
	}
	
	public void addInvoiceCustomer(Date maturityDate, Order orderInvoice){
		
		Invoice_customer icust = new Invoice_customer(maturityDate, orderInvoice);
		makeObjectPersistent(icust);
	}

	public void addInvoiceSupplier(Supplier supplier, Date maturityDate, Order orderInvoice){
		
		Invoice_supplier isup = new Invoice_supplier(supplier, maturityDate, orderInvoice);
		makeObjectPersistent(isup);
	}

	public void addOrder(String state, Date estimatedDeliveryDate,
			Date deliveryDate, DeliveryMethod deliveryMethod, List<Goods> goodsInOrder,
			Contact deliveryContact, Contact billingContact, Key adddBy){
		
		Order order = new Order(state,estimatedDeliveryDate, deliveryDate, 
				deliveryMethod, goodsInOrder, deliveryContact, billingContact,
				adddBy);
		makeObjectPersistent(order);
	}
	
	public void addOrder(Date creationDate, DeliveryMethod deliveryMethod,
			Date deliveryDate, Date estimatedDeliveryDate,
			List<Goods> goodsInOrder, Contact deliveryContact,
			Contact billingContact, Key modificatedBy, Key adddBy){
		
		Order order = new Order(creationDate, deliveryMethod,
				deliveryDate, estimatedDeliveryDate, goodsInOrder, deliveryContact,
				billingContact, modificatedBy, adddBy);
		makeObjectPersistent(order);
	}

	public void addSupplier(String accountNumber, String companyName, Contact contact){
		
		Supplier sup = new Supplier(accountNumber, contact);
		makeObjectPersistent(sup);		
	}
	
	public void addTeam(String name){
		Team team = new Team(name);
		makeObjectPersistent(team);		
	}
	
	public void addTeam(String name, List<Employee> employeesInTeam){		
		
		Team team = new Team(name, employeesInTeam);
		makeObjectPersistent(team);	
	}

	
	/* ------------------------------------------------------------ */	
	
	public void deleteAllAddress(){
		deleteAllPersistentObjects(Address.class);
	}
	
	public void deleteAllCategories(){
		deleteAllPersistentObjects(Category.class);
	}
	
	public void deleteAllContacts(){
		deleteAllPersistentObjects(Contact.class);
	}
	
	public void deleteAllCustomers(){
		deleteAllPersistentObjects(Customer.class);
	}
	
	public void deleteAllEmployees(){
		deleteAllPersistentObjects(Employee.class);
	}
	
	public void deleteAllGoods(){
		deleteAllPersistentObjects(Goods.class);
	}
	
	public void deleteAllInvoiceCustomers(){
		deleteAllPersistentObjects(Invoice_customer.class);
	}
	
	public void deleteAllInvoiceSuppliers(){
		deleteAllPersistentObjects(Invoice_supplier.class);
	}
	
	public void deleteAllOrders(){
		deleteAllPersistentObjects(Order.class);
	}
	
	public void deleteAllSuppliers(){
		deleteAllPersistentObjects(Supplier.class);
	}
	
	public void deleteAllTeams(){
		deleteAllPersistentObjects(Team.class);
	}
	
	/* ------------------------------------------------------------ */
	
	public Employee getEmployeeForFirstName(String firstName){

		String property = "firstName";
		Employee emp = getUniqueObjectForIdentifier(new Employee(null), property, firstName);
		return emp;
		
//		return getUniqueObjectForIdentifier(Employee, property, identifier);
		
//		Employee e = null;
//		PersistenceManager pm = PMF.get().getPersistenceManager();
//		
//		Transaction tx = pm.currentTransaction();
//		try {
//      tx.begin();
//  
//      Query q = pm.newQuery(Employee.class);
//      q.setFilter("firstName == fnParam");
//      q.declareParameters("String fnParam");
//      q.setUnique(true);
//      Employee emp = (Employee)q.execute(firstName);
//      e = pm.detachCopy(emp);
//      tx.commit();
//    } finally {
//      if (tx.isActive()) {
//        tx.rollback();
//      }
//    }
//    return e;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getAllCustomers(){
		String ret = "";
		
		PersistenceManager pm = PMF.get().getPersistenceManager();

		Query q = pm.newQuery(Customer.class);
		List<Customer> cus = (List<Customer>) q.execute();
		

		int i = 1;
		for(Customer c : cus){
			ret = ret.concat(i + " " + c.toString()+ "\n");
			i++;
		}
		pm.close();
		return ret;
	}

	@Override
	public String getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllContacts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllGoods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllInvoices_customer(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllInvoices_supplier(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllOrders(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllSuppliers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllTeams() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
}

