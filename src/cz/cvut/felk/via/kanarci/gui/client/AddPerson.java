package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;

//import cz.cvut.felk.via.kanarci.datastore.objects.Address;
import cz.cvut.felk.via.kanarci.gui.shared.AddressRPC;
import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;



public class AddPerson extends Composite{

	private RadioButton customerRB = new RadioButton("person", "customer");
	private RadioButton employeeRB = new RadioButton("person", "employee");
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
	private Label accountNumber = new Label("Account Number:");
	private Label hire = new Label("Hire:");
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
	private TextBox accountNumberBox = new TextBox();
	private TextBox hireBox = new TextBox();
	private Button create = new Button("Create");
	
	private final RPCAsync contactSendingService = GWT.create(RPC.class);
	
	public AddPerson() {
		super();
		final FlexTable flexTable = new FlexTable();
		final HorizontalPanel hp = new HorizontalPanel();
		
		create.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				AddressRPC address = new AddressRPC(cityBox.getValue(), streetBox.getValue(), Integer.parseInt(houseNumberBox1.getValue()), Integer.parseInt(houseNumberBox2.getValue()), zipBox.getValue());
				if (employeeRB.getValue()) {
					// TODO
					ContactRPC newContact = new ContactRPC(firstNameBox.getValue(), surenameBox.getValue(), phoneBox.getValue(), companyBox.getValue(), emailBox.getValue(), departmentBox.getValue(), address);
				}
				ContactRPC newContact = new ContactRPC(firstNameBox.getValue(), surenameBox.getValue(), phoneBox.getValue(), companyBox.getValue(), emailBox.getValue(), departmentBox.getValue(), address);
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
		customerRB.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (customerRB.getValue()) {
					accountNumber.setVisible(false);
					accountNumberBox.setVisible(false);
					hire.setVisible(false);
					hireBox.setVisible(false);
				}
			}
		});
		employeeRB.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (employeeRB.getValue()) {
					accountNumber.setVisible(true);
					accountNumberBox.setVisible(true);
					hire.setVisible(true);
					hireBox.setVisible(true);
				}
			}
		});
		
		customerRB.setValue(true);
		customerRB.setFocus(true);
		
		houseNumberBox1.setWidth("6em");
		houseNumberBox2.setWidth("6em");
		
		hp.add(houseNumberBox1);
		hp.add(houseNumberBox2);
		
		accountNumber.setVisible(false);
		accountNumberBox.setVisible(false);
		hire.setVisible(false);
		hireBox.setVisible(false);
		customerRB.setValue(true);
		
		flexTable.setWidget(0, 0, customerRB);
		flexTable.setWidget(0, 1, employeeRB);
		flexTable.setWidget(1, 0, firstName);
		flexTable.setWidget(1, 1, firstNameBox);
		flexTable.setWidget(2, 0, surename);
		flexTable.setWidget(2, 1, surenameBox);
		flexTable.setWidget(3, 0, company);
		flexTable.setWidget(3, 1, companyBox);
		flexTable.setWidget(4, 0, department);
		flexTable.setWidget(4, 1, departmentBox);
		flexTable.setWidget(5, 0, phone);
		flexTable.setWidget(5, 1, phoneBox);
		flexTable.setWidget(6, 0, email);
		flexTable.setWidget(6, 1, emailBox);
		flexTable.setWidget(7, 0, city);
		flexTable.setWidget(7, 1, cityBox);
		flexTable.setWidget(8, 0, street);
		flexTable.setWidget(8, 1, streetBox);
		flexTable.setWidget(9, 0, houseNumber);
		flexTable.setWidget(9, 1, hp);
		flexTable.setWidget(10, 0, zip);
		flexTable.setWidget(10, 1, zipBox);
		flexTable.setWidget(11, 0, accountNumber);
		flexTable.setWidget(11, 1, accountNumberBox);
		flexTable.setWidget(12, 0, hire);
		flexTable.setWidget(12, 1, hireBox);
		flexTable.setWidget(13, 0, create);
		
		initWidget(flexTable);
	}
}