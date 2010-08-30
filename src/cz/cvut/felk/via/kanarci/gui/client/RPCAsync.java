package cz.cvut.felk.via.kanarci.gui.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cz.cvut.felk.via.kanarci.gui.shared.CategoryRPC;
import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;
import cz.cvut.felk.via.kanarci.gui.shared.CustomerRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderRPC;


public interface RPCAsync {

	void addCustomerServer(ContactRPC newContact, AsyncCallback<String> callback)
	throws IllegalArgumentException;

	void addEmployeeServer(ContactRPC contact, String accounNumber,
			Double hire, AsyncCallback<String> callback);
	
	void getContactsServer(AsyncCallback<String> callback) throws IllegalArgumentException;

	void delContactsServer(AsyncCallback<String> callback);

	void getAllCustomersServer(AsyncCallback<List<CustomerRPC>> callback);

	void getAllOrdersServer(AsyncCallback<List<OrderRPC>> callback);

	void addNewOrders(List<OrderRPC> orders, AsyncCallback<Void> callback);

	void getAllCategoriesServer(AsyncCallback<List<CategoryRPC>> asyncCallback);

	void addNewCategory(CategoryRPC cat, AsyncCallback<Void> asyncCallback);

	void delCategory(CategoryRPC cat, AsyncCallback<Void> callback);

}
