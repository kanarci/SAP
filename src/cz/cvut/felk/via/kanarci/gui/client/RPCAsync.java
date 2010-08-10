package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;


public interface RPCAsync {

	void contactSendingServer(ContactRPC newContact, AsyncCallback<String> callback)
	throws IllegalArgumentException;

	void getContactsServer(AsyncCallback<String> callback) throws IllegalArgumentException;

}
