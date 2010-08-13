package cz.cvut.felk.via.kanarci.gui.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.objects.Contact;
import cz.cvut.felk.via.kanarci.datastore.objects.Customer;
import cz.cvut.felk.via.kanarci.datastore.utils.DUF;
import cz.cvut.felk.via.kanarci.gui.client.RPC;
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
	}
	
	@Override
	public String addEmployeeServer(ContactRPC contact)
			throws IllegalArgumentException {
		//TODO: not completly implemented
		Contact custContact = new Contact(contact.getFirstName(), contact.getSureName(),
				contact.getPhone(), contact.getCorporationName(), contact.getEmail(), 
				contact.getDepartment(), new Address(contact.getAddress().getCity(), 
						contact.getAddress().getStreet(), contact.getAddress().getCo(),
						contact.getAddress().getCp(), contact.getAddress().getZip())); 
		
		DUF.get().addEmployee(custContact);
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderRPC> getAllOrdersServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewOrders(List<OrderRPC> orders)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
