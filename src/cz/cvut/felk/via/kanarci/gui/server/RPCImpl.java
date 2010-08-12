package cz.cvut.felk.via.kanarci.gui.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.objects.Customer;
import cz.cvut.felk.via.kanarci.datastore.utils.DatastoreUtil;
import cz.cvut.felk.via.kanarci.datastore.utils.PMF;
import cz.cvut.felk.via.kanarci.gui.client.RPC;
import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;

public class RPCImpl extends RemoteServiceServlet
implements RPC {

	private static final long serialVersionUID = 4547875787007127536L;
	
	private static final Logger log = Logger.getLogger("DataNucleus.JDO");
	
	@Override
//	public String contactSendingServer(String firstName, String sureName, String phone,
//			String company, String email, String department, String city,
//			String street, int co, int cp, String zip)
//			throws IllegalArgumentException {

	public String contactSendingServer(ContactRPC contact)
			throws IllegalArgumentException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		
		
		Customer cust = new Customer(new cz.cvut.felk.via.kanarci.datastore.objects.Contact(
				contact.getFirstName(), contact.getSureName(), contact.getPhone(), 
				contact.getCorporationName(), contact.getEmail(), contact.getDepartment(), 
				new Address(contact.getAddress().getCity(), 
						contact.getAddress().getStreet(), contact.getAddress().getCo(),
						contact.getAddress().getCp(), contact.getAddress().getZip()))); 
		
        pm.currentTransaction().begin();

		
		try{
			System.out.println("pm.makePersistent(cust);");
			pm.makePersistent(cust);
//			pm.makePersistent(o);
		}
		catch (Exception e) {
			try{
				System.out.println("pm.makePersistent 2 (cust);");
				pm.makePersistent(cust);
			}
			catch (Exception ex) {
				log.log(Level.SEVERE, "Failed in session cleanup", ex);     
			}			
		}
		finally{
	        pm.currentTransaction().commit();
			pm.close();
		}
		
//		XYZZY du = new XYZZY();
//		du.addCustomer(new cz.cvut.felk.via.kanarci.datastore.objects.Contact(
//				contact.getFirstName(), contact.getSureName(), contact.getPhone(), 
//				contact.getCorporationName(), contact.getEmail(), contact.getDepartment(), 
//				new Address(contact.getAddress().getCity(), 
//						contact.getAddress().getStreet(), contact.getAddress().getCo(),
//						contact.getAddress().getCp(), contact.getAddress().getZip())));
////		DatastoreUtil.createCustomer(new cz.cvut.felk.via.kanarci.datastore.objects.Contact(
////				contact.getFirstName(), contact.getSureName(), contact.getPhone(), 
////				contact.getCorporationName(), contact.getEmail(), contact.getDepartment(), 
////				new Address(contact.getAddress().getCity(), 
////						contact.getAddress().getStreet(), contact.getAddress().getCo(),
////						contact.getAddress().getCp(), contact.getAddress().getZip())));
		return "Ok : "+cust.toString();
	}

	@Override
	public String getContactsServer() throws IllegalArgumentException {
		String ret = null;
		try {
			ret = DatastoreUtil.getAllCustomers();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Failed in session cleanup", e); 
		}
		return ret;
	
	}

	@Override
	public String delContactsServer() {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();

		DatastoreUtil.removeAllCustomers();
		
		
		return "Vsichni smazani ";
	}

}
