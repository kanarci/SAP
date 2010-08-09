package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cz.cvut.felk.via.kanarci.datastore.objects.Contact;

@RemoteServiceRelativePath("contact")
public interface ContactSendingService extends RemoteService{
	String contactSendingServer(Contact contact) throws IllegalArgumentException;
}
