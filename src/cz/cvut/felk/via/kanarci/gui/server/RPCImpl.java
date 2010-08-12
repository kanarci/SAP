package cz.cvut.felk.via.kanarci.gui.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.objects.Contact;
import cz.cvut.felk.via.kanarci.datastore.utils.DUF;
import cz.cvut.felk.via.kanarci.gui.client.RPC;
import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;

public class RPCImpl extends RemoteServiceServlet
implements RPC {

	private static final long serialVersionUID = 4547875787007127536L;
	
	private static final Logger log = Logger.getLogger("DataNucleus.JDO");
	
	@Override
	public String contactSendingServer(ContactRPC contact)
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
	public String getContactsServer() throws IllegalArgumentException {
		String ret = null;
		try {
			ret = DUF.get().getAllCustomers();
		} catch (Exception e) {
			log.log(Level.SEVERE, "Failed in session cleanup", e); 
		}
		return ret;
	
	}

	@Override
	public String delContactsServer() {
		
		DUF.get().deleteAllCustomers();
			
		return "Vsichni smazani ";
	}

}
