package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;


public interface RPCAsync {

	void addCustomerServer(ContactRPC newContact, AsyncCallback<String> callback)
	throws IllegalArgumentException;

	void addEmployeeServer(ContactRPC contact, AsyncCallback<String> callback);
	
	void getContactsServer(AsyncCallback<String> callback) throws IllegalArgumentException;

	void delContactsServer(AsyncCallback<String> callback);

}
