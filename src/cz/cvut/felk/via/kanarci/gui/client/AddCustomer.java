package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.datastore.objects.Contact;

public class AddCustomer extends Composite{

	private Label firstName = new Label("First name:");
	private Label surename = new Label("Surname:");
	private Label company = new Label("Company:");
	private Label department = new Label("Department:");
	private Label phone = new Label("Phone:");
	private Label email = new Label("E-mail:");
	private Label city = new Label("City:");
	private Label street = new Label("Street:");
	private Label houseNumber = new Label("House number:");
	private Label zip = new Label("ZIP code:");
	private TextBox firstNameBox = new TextBox();
	private TextBox surenameBox = new TextBox();
	private TextBox companyBox = new TextBox();
	private TextBox departmentBox = new TextBox();
	private TextBox phoneBox = new TextBox();
	private TextBox emailBox = new TextBox();
	private TextBox cityBox = new TextBox();
	private TextBox streetBox = new TextBox();
	private TextBox houseNumberBox1 = new TextBox();
	private TextBox houseNumberBox2 = new TextBox();
	private TextBox zipBox = new TextBox();
	private Button create = new Button("Create");
	
	private final ContactSendingServiceAsync contactSendingService = GWT.create(ContactSendingService.class);
	
	public AddCustomer() {
		super();
		final FlexTable flexTable = new FlexTable();
		final HorizontalPanel hp = new HorizontalPanel();
		
		create.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Address address = new Address(cityBox.getValue(), streetBox.getValue(), Integer.parseInt(houseNumberBox1.getValue()), Integer.parseInt(houseNumberBox2.getValue()), zipBox.getValue());
				Contact newContact = new Contact(firstNameBox.getValue(), surenameBox.getValue(), phoneBox.getValue(), companyBox.getValue(), emailBox.getValue(), departmentBox.getValue(), address);
				create.setEnabled(false);
				contactSendingService.contactSendingServer(newContact, new AsyncCallback<String>() {
					@Override
					public void onSuccess(String result) {
						Window.alert(result);
						create.setEnabled(true);
					}
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Communication failed"+caught.getMessage());
						create.setEnabled(true);
					}
				});
			}
		});
		
		houseNumberBox1.setWidth("6em");
		houseNumberBox2.setWidth("6em");
		
		hp.add(houseNumberBox1);
		hp.add(houseNumberBox2);
		
		flexTable.setWidget(0, 0, firstName);
		flexTable.setWidget(0, 1, firstNameBox);
		flexTable.setWidget(1, 0, surename);
		flexTable.setWidget(1, 1, surenameBox);
		flexTable.setWidget(2, 0, company);
		flexTable.setWidget(2, 1, companyBox);
		flexTable.setWidget(3, 0, department);
		flexTable.setWidget(3, 1, departmentBox);
		flexTable.setWidget(4, 0, phone);
		flexTable.setWidget(4, 1, phoneBox);
		flexTable.setWidget(5, 0, email);
		flexTable.setWidget(5, 1, emailBox);
		flexTable.setWidget(6, 0, city);
		flexTable.setWidget(6, 1, cityBox);
		flexTable.setWidget(7, 0, street);
		flexTable.setWidget(7, 1, streetBox);
		flexTable.setWidget(8, 0, houseNumber);
		flexTable.setWidget(8, 1, hp);
		flexTable.setWidget(9, 0, zip);
		flexTable.setWidget(9, 1, zipBox);
		flexTable.setWidget(10, 0, create);
		
		initWidget(flexTable);
	}
}