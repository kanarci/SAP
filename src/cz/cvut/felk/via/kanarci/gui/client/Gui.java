package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
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
		final AddPerson addPerson = new AddPerson();
		final Category cathegory = new Category();
		final Goods goods = new Goods();
		final Menu menu = new Menu();
		
		tabs.add(newOrder, constants.new_order());
		tabs.add(findOrder, constants.find());
	    tabs.add(new HTML("smazání"), constants.erase());
	    tabs.add(new HTML("uzavření"), constants.finalizee());
	    tabs.add(addPerson, constants.add_person());
	    tabs.add(cathegory, constants.category());
	    tabs.add(goods, constants.goods());
	    tabs.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
//				Window.alert(event.getSelectedItem().toString());
				switch (event.getSelectedItem()) {
				case 0:
					newOrder.refreshContent();
					break;
				case 1:
					findOrder.refreshContent();
					break;
				case 4:
					addPerson.refreshContent();
					break;
				case 5:
					cathegory.refreshContent();
					break;
				case 6:
					goods.refreshContent();
					break;
				default:
					break;
				}
				
			}
		});

	    dock.addNorth(new Label("logo"), 5);
	    dock.addWest(menu, 10);
	    dock.add(tabs);
	    rp.add(dock);
//	    rp.setSize("1200px", "600px");
	}
	
	
}
