package cz.cvut.felk.via.kanarci.gui.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;
import cz.cvut.felk.via.kanarci.gui.shared.CustomerRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderRPC;



@RemoteServiceRelativePath("contact")
public interface RPC extends RemoteService{
	String addCustomerServer(ContactRPC contact) throws IllegalArgumentException;
	String addEmployeeServer(ContactRPC contact) throws IllegalArgumentException;
	String getContactsServer();
	String delContactsServer();
	List<CustomerRPC> getAllCustomersServer();
	List<OrderRPC> getAllOrdersServer();
	void addNewOrder(OrderRPC order) throws IllegalArgumentException;
}
