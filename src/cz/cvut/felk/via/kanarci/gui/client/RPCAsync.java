package cz.cvut.felk.via.kanarci.gui.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;
import cz.cvut.felk.via.kanarci.gui.shared.CustomerRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderRPC;


public interface RPCAsync {

	void addCustomerServer(ContactRPC newContact, AsyncCallback<String> callback)
	throws IllegalArgumentException;

	void addEmployeeServer(ContactRPC contact, AsyncCallback<String> callback);
	
	void getContactsServer(AsyncCallback<String> callback) throws IllegalArgumentException;

	void delContactsServer(AsyncCallback<String> callback);

	void getAllCustomersServer(AsyncCallback<List<CustomerRPC>> callback);

	void addNewOrder(OrderRPC order, AsyncCallback<Void> callback);

	void getAllOrdersServer(AsyncCallback<List<OrderRPC>> callback);

}
