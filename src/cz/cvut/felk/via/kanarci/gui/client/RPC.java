package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;



@RemoteServiceRelativePath("contact")
public interface RPC extends RemoteService{
	String contactSendingServer(ContactRPC contact) throws IllegalArgumentException;
	String getCustomersServer() throws IllegalArgumentException;
	String delContactsServer() ;
}
