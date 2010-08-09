package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cz.cvut.felk.via.kanarci.datastore.objects.Contact;

public interface ContactSendingServiceAsync {

	void contactSendingServer(Contact contact, AsyncCallback<String> callback)
	throws IllegalArgumentException;

}
