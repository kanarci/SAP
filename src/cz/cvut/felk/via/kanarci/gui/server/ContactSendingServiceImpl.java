package cz.cvut.felk.via.kanarci.gui.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cz.cvut.felk.via.kanarci.gui.shared.Contact;
import cz.cvut.felk.via.kanarci.gui.client.ContactSendingService;

public class ContactSendingServiceImpl extends RemoteServiceServlet
implements ContactSendingService {

	private static final long serialVersionUID = 4547875787007127536L;

	@Override
	public String contactSendingServer(Contact contact)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return "Ok";
	}

}
