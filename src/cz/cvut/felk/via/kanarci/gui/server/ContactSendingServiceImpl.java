package cz.cvut.felk.via.kanarci.gui.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.utils.DatastoreUtil;
import cz.cvut.felk.via.kanarci.gui.shared.Contact;
import cz.cvut.felk.via.kanarci.gui.client.ContactSendingService;

public class ContactSendingServiceImpl extends RemoteServiceServlet
implements ContactSendingService {

	private static final long serialVersionUID = 4547875787007127536L;

	@Override
	public String contactSendingServer(Contact contact)
			throws IllegalArgumentException {
		DatastoreUtil.createCustomer(new cz.cvut.felk.via.kanarci.datastore.objects.Contact(
				contact.getFirstName(), contact.getSureName(), contact.getPhone(), 
				contact.getCorporationName(), contact.getEmail(), contact.getDepartment(), 
				new Address(contact.getAddress().getCity(), 
						contact.getAddress().getStreet(), contact.getAddress().getCo(),
						contact.getAddress().getCp(), contact.getAddress().getZip())));
		return "Ok";
	}

}
