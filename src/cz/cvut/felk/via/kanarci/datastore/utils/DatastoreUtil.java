package cz.cvut.felk.via.kanarci.datastore.utils;

import java.util.Date;
import java.util.List;

import javax.jdo.JDOUserException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

import cz.cvut.felk.via.kanarci.datastore.objects.*;
import cz.cvut.felk.via.kanarci.datastore.utils.PMF;


public abstract class DatastoreUtil {

	private static <T> void makeObjectPersistent(T o){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(o);
		}finally{
			pm.close();
		}
	}
	
	private static void removeAllPersistentObjects(Class<?> o){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			Query q = pm.newQuery(o);
			q.deletePersistentAll();
		}
		catch(JDOUserException e){
			System.out.println(" error: neni co deletovat");
		}
		finally{
			pm.close();	
		}
	}

	
	/* ------------------------------------------------------------ */
	public static void createAddress(String street, int co, int cp, String city, String zip){
		
		Address adr = new Address(city, street, co, cp, zip);
		makeObjectPersistent(adr);
	}
	
	public static void createCategory(String name, Key supremeCategory){
		
		Category cat = new Category(name, supremeCategory);	
		makeObjectPersistent(cat);
	}
	
	public static void createCategory(String name, Key supremeCategory, String parameterName
			, String parameterValue){
		
		Category cat = new Category(name, supremeCategory, parameterName, parameterValue);
		makeObjectPersistent(cat);
	}
	
	public static void createContact(String firstName, String sureName, String phone
			, String email,	Address address){
		
		Contact con = new Contact(firstName, sureName, phone, email, address);
		makeObjectPersistent(con);
	}
	
	public static void createContact(String firstName, String sureName, String phone,
			String corporationName, String email, String department, Address address){
		
		Contact con = new Contact(firstName, sureName, phone, corporationName, email, department, address);
		makeObjectPersistent(con);
	}	

	public static void createCustomer(Contact contact){
		
		Customer cust = new Customer(contact);
		makeObjectPersistent(cust);
	}
	
	public static void createEmployee( Contact contact){
		
		Employee emp = new Employee(contact);
		makeObjectPersistent(emp);
	}
	
	public static void createEmployee( Contact contact, double salary
			, String bankAccountNumber, Key inTeam){
		
		Employee emp = new Employee(contact, salary, bankAccountNumber, inTeam);
		makeObjectPersistent(emp);
	}
	
	public static void createGoods(String name, Text description, double price
			, int numOfPieces, boolean visiblity, Key supplier, List<Key> category){

		Goods goods = new Goods(name, description, price, numOfPieces, visiblity
				, supplier, category);
		makeObjectPersistent(goods);
	}
	
	public static void createGoods(String name, Text description, double price
			, int numOfPieces, boolean visiblity, Key category){

		Goods goods = new Goods(name, description, price, numOfPieces, visiblity, category);
		makeObjectPersistent(goods);
	}
	
	public static void createInvoiceCustomer(Date maturityDate, Order orderInvoice){
		
		Invoice_customer icust = new Invoice_customer(maturityDate, orderInvoice);
		makeObjectPersistent(icust);
	}

	public static void createInvoiceSupplier(Supplier supplier, Date maturityDate, Order orderInvoice){
		
		Invoice_supplier isup = new Invoice_supplier(supplier, maturityDate, orderInvoice);
		makeObjectPersistent(isup);
	}

	public static void createOrder(String state, Date estimatedDeliveryDate,
			Date deliveryDate, DeliveryMethod deliveryMethod, List<Goods> goodsInOrder,
			Contact deliveryContact, Contact billingContact, Key createdBy){
		
		Order order = new Order(state,estimatedDeliveryDate, deliveryDate, 
				deliveryMethod, goodsInOrder, deliveryContact, billingContact,
				createdBy);
		makeObjectPersistent(order);
	}
	
	public static void createOrder(Date creationDate, DeliveryMethod deliveryMethod,
			Date deliveryDate, Date estimatedDeliveryDate,
			List<Goods> goodsInOrder, Contact deliveryContact,
			Contact billingContact, Key modificatedBy, Key createdBy){
		
		Order order = new Order(creationDate, deliveryMethod,
				deliveryDate, estimatedDeliveryDate, goodsInOrder, deliveryContact,
				billingContact, modificatedBy, createdBy);
		makeObjectPersistent(order);
	}

	public static void createSupplier(String accountNumber, String companyName, Contact contact){
		
		Supplier sup = new Supplier(accountNumber, contact);
		makeObjectPersistent(sup);		
	}
	
	public static void createTeam(String name){
		Team team = new Team(name);
		makeObjectPersistent(team);		
	}
	
	public static void createTeam(String name, List<Employee> employeesInTeam){		
		
		Team team = new Team(name, employeesInTeam);
		makeObjectPersistent(team);	
	}

	
	/* ------------------------------------------------------------ */	
	
	public static void removeAllAddress(){
		removeAllPersistentObjects(Address.class);
	}
	
	public static void removeAllCategories(){
		removeAllPersistentObjects(Category.class);
	}
	
	public static void removeAllContacts(){
		removeAllPersistentObjects(Contact.class);
	}
	
	public static void removeAllCustomers(){
		removeAllPersistentObjects(Customer.class);
	}
	
	public static void removeAllEmployees(){
		removeAllPersistentObjects(Employee.class);
	}
	
	public static void removeAllGoods(){
		removeAllPersistentObjects(Goods.class);
	}
	
	public static void removeAllInvoiceCustomers(){
		removeAllPersistentObjects(Invoice_customer.class);
	}
	
	public static void removeAllInvoiceSuppliers(){
		removeAllPersistentObjects(Invoice_supplier.class);
	}
	
	public static void removeAllOrders(){
		removeAllPersistentObjects(Order.class);
	}
	
	public static void removeAllSuppliers(){
		removeAllPersistentObjects(Supplier.class);
	}
	
	public static void removeAllTeams(){
		removeAllPersistentObjects(Team.class);
	}
	
	/* ------------------------------------------------------------ */
	
	public static void getEmployeeForName(){

		@SuppressWarnings("unused")
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}

