package cz.cvut.felk.via.kanarci.gui.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cz.cvut.felk.via.kanarci.datastore.objects.Contact;
import cz.cvut.felk.via.kanarci.gui.client.ContactSendingService;

public class ContactSendingServiceImpl extends RemoteServiceServlet
implements ContactSendingService {

	@Override
	public String contactSendingServer(Contact contact)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return "Ok";
	}

}
