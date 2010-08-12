package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;

public class Menu extends Composite{

	    Command cmd = new Command() {
	      public void execute() {
	        Window.alert("You selected a menu item!");
	      }
	    };
	    private Command showCustomers = new Command() {			
			@Override
			public void execute() {
				final RPCAsync getContactServer = GWT.create(RPC.class);
				getContactServer.getCustomersServer(new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						Window.alert("Ok: "+result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});				
			}
		};
		
	    private Command deleteCustomers = new Command() {			
			@Override
			public void execute() {
				final RPCAsync delContactServer = GWT.create(RPC.class);
				delContactServer.delContactsServer(new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						Window.alert("Ok: "+result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});				
			}
		};

	    // Make some sub-menus that we will cascade from the top menu.
	    public Menu() {
			super();
		MenuBar menu = new MenuBar(true);
		menu.addSeparator();
		menu.addItem("Show customers", showCustomers);
		menu.addItem("Delete customers", deleteCustomers);
		menu.addSeparator();
	    menu.addItem("Edit profile", cmd);
	    menu.addItem("Customize", cmd);
	    menu.addItem("Log out", cmd);
	    menu.addSeparator();

	    initWidget(menu);	    
	  }
	}
