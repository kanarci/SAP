package cz.cvut.felk.via.kanarci.gui.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.objects.Contact;
import cz.cvut.felk.via.kanarci.datastore.objects.Customer;
import cz.cvut.felk.via.kanarci.datastore.utils.DRPCC;
import cz.cvut.felk.via.kanarci.datastore.utils.DUF;
import cz.cvut.felk.via.kanarci.gui.client.RPC;
import cz.cvut.felk.via.kanarci.gui.shared.CategoryRPC;
import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;
import cz.cvut.felk.via.kanarci.gui.shared.CustomerRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderRPC;

public class RPCImpl extends RemoteServiceServlet
implements RPC {

	private static final long serialVersionUID = 4547875787007127536L;
	
	private static final Logger log = Logger.getLogger("DataNucleus.JDO");
	
	@Override
	public String addCustomerServer(ContactRPC contact)
			throws IllegalArgumentException {

		Contact custContact = new Contact(contact.getFirstName(), contact.getSureName(),
				contact.getPhone(), contact.getCorporationName(), contact.getEmail(), 
				contact.getDepartment(), new Address(contact.getAddress().getCity(), 
						contact.getAddress().getStreet(), contact.getAddress().getCo(),
						contact.getAddress().getCp(), contact.getAddress().getZip())); 
		
		DUF.get().addCustomer(custContact);
			
		return "Ok : "+custContact.toString();
		//replace with following & make this method void
		//DUF.get().addCustomer(DRPCC.get().contactFromRPC(contact));
	}
	
	@Override
	public String addEmployeeServer(ContactRPC contact, String accountNumber,
			Double hire) throws IllegalArgumentException {
		Contact custContact = new Contact(contact.getFirstName(), contact.getSureName(),
				contact.getPhone(), contact.getCorporationName(), contact.getEmail(), 
				contact.getDepartment(), new Address(contact.getAddress().getCity(), 
						contact.getAddress().getStreet(), contact.getAddress().getCo(),
						contact.getAddress().getCp(), contact.getAddress().getZip())); 
		
		DUF.get().addEmployee(custContact, hire, accountNumber);
		return "Ok: "+custContact.toString()+" hire: "+hire+" account number: "+accountNumber;		
		//replace with following & make this method void
		//DUF.get().addEmployee(DRPCC.get().contactFromRPC(contact), hire, accountNumber);
	}
	
	@Override
	public String getContactsServer() throws IllegalArgumentException {
		List<Customer> retCust = new ArrayList<Customer>();
		String retStr = "";
		try {
			retCust = DUF.get().getAllCustomers();
			for(Customer cus : retCust){
				retStr = retStr.concat(cus.toString()+"\n");
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Failed in session cleanup", e); 
		}
		return retStr;
	
	}

	@Override
	public String delContactsServer() {
		
		DUF.get().deleteAllCustomers();
			
		return "Vsichni smazani ";
	}

	@Override
	public List<CustomerRPC> getAllCustomersServer() {
		return DRPCC.get().customerToRPC(DUF.get().getAllCustomers());
	}

	@Override
	public List<OrderRPC> getAllOrdersServer() {
		return DRPCC.get().orderToRPC(DUF.get().getAllOrders());
	}

	@Override
	public void addNewOrders(List<OrderRPC> orders)
			throws IllegalArgumentException {
		DRPCC.get().orderFromRPC(orders);
	}

	@Override
	public List<CategoryRPC> getAllCategoriesServer() {
		return DRPCC.get().categoryToRPC(DUF.get().getAllCategories());
	}

	@Override
	public void addNewCategory(CategoryRPC cat) {
		DUF.get().addCategory(DRPCC.get().categoryFromRPC(cat));
		System.out.println("Category added");
	}

	@Override
	public void delCategory(CategoryRPC cat) {
		System.out.println("RPC del category");
		DUF.get().deleteCategory(DRPCC.get().categoryFromRPC(cat));
		
	}

}
