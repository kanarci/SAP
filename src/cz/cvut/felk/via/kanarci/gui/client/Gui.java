package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gui implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	private GuiConstants constants = GWT.create(GuiConstants.class);

	private DockLayoutPanel dock = new DockLayoutPanel(Unit.EM);
	private TabLayoutPanel tabs = new TabLayoutPanel(1.5, Unit.EM);
	
	public void onModuleLoad() {
		final RootLayoutPanel rp = RootLayoutPanel.get();
		final NewOrder newOrder = new NewOrder();
		final FindOrder findOrder = new FindOrder();
		final AddPerson AddPerson = new AddPerson();
		final Category cathegory = new Category();
		final Menu menu = new Menu();

		tabs.add(newOrder, constants.new_order());
		tabs.add(findOrder, constants.find());
	    tabs.add(new HTML("smazání"), constants.erase());
	    tabs.add(new HTML("uzavření"), constants.finalizee());
	    tabs.add(AddPerson, constants.add_person());
	    tabs.add(cathegory, constants.category());

	    dock.addNorth(new Label("logo"), 5);
	    dock.addWest(menu, 10);
	    dock.add(tabs);
	    rp.add(dock);
	    rp.setSize("1200px", "600px");
	}
}
