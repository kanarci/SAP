package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.i18n.client.Messages;

public interface NewOrderMessages extends Messages{

	@DefaultMessage("You are making duplicity order. Save our trees and make it as a single order.\n" +
					"Duplicity found on line: {0,int}")
	String duplicityOrder(int line);

}
