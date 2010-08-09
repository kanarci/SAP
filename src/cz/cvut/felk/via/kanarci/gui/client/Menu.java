package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;

public class Menu extends Composite{

	    Command cmd = new Command() {
	      public void execute() {
	        Window.alert("You selected a menu item!");
	      }
	    };

	    // Make some sub-menus that we will cascade from the top menu.
	    public Menu() {
			super();
		MenuBar menu = new MenuBar(true);
		menu.addSeparator();
		//menu.addItem("Add customer", cmd);
		menu.addSeparator();
	    menu.addItem("Edit profile", cmd);
	    menu.addItem("Customize", cmd);
	    menu.addItem("Log out", cmd);
	    menu.addSeparator();

	    initWidget(menu);	    
	  }
	}
