package cz.cvut.felk.via.kanarci.gui.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import cz.cvut.felk.via.kanarci.gui.shared.ContactRPC;
import cz.cvut.felk.via.kanarci.gui.shared.CustomerRPC;
import cz.cvut.felk.via.kanarci.gui.shared.DeliveryMethodRPC;
import cz.cvut.felk.via.kanarci.gui.shared.GoodsRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderRPC;
import cz.cvut.felk.via.kanarci.gui.shared.OrderStateRPC;

public class NewOrder extends Composite{

	private NewOrderMessages messages = GWT.create(NewOrderMessages.class);
	
	private Grid data = new Grid(2, 9);
	private Date orderDate = new Date();
	private DateBox orderDatePicker = new DateBox(new DatePicker(), orderDate, new DateBox.DefaultFormat(DateTimeFormat.getMediumDateFormat()));
	
	private ListBox customers = new ListBox();
	private ListBox goods = new ListBox();
	private TextBox onePrice = new TextBox();
	private TextBox count = new TextBox();
	private TextBox priceNoTax = new TextBox();
	private TextBox vat = new TextBox();
	private TextBox priceTax = new TextBox();
	private ArrayList<Double> orderIDs = new ArrayList<Double>();
	private List<OrderRPC> orderList = new ArrayList<OrderRPC>();
	
	public NewOrder() {
		super();
		final FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(4);
		Label user = new Label("Generic Seller");
		final Button plusButton = new Button("<b>+</b>");
		final Button save = new Button("Save changes");
		final RPCAsync rpc = GWT.create(RPC.class);
		
		rpc.getAllCustomersServer(new AsyncCallback<List<CustomerRPC>>() {
			@Override
			public void onSuccess(List<CustomerRPC> result) {
				for (CustomerRPC cust : result) {
					customers.addItem(cust.getContactInfo().getSureName() + ", " + cust.getContactInfo().getFirstName());
				}
			}
			@Override
			public void onFailure(Throwable caught) {
				customers.addItem("--------");
			}
		});
		
		orderDatePicker.setWidth("10em");
		goods.addItem("phone 1");
		goods.addItem("phone 2");
		goods.addItem("phone 3");
		onePrice.setText("5000");
		onePrice.setWidth("6em");
		onePrice.setReadOnly(true);
		count.setText("1");
		count.setWidth("2em");
		priceNoTax.setWidth("6em");
		priceNoTax.setReadOnly(true);
		vat.setWidth("3em");
		vat.setReadOnly(true);
		priceTax.setWidth("6em");
		priceTax.setReadOnly(true);
		//folloving will be read from DB
		vat.setText("20");
		
		data.setText(0, 0, "Order date");
		data.setText(0, 1, "Customer");
		data.setText(0, 2, "Goods");
		data.setText(0, 3, "Price");
		data.setText(0, 4, "no.");
		data.setText(0, 5, "excl. VAT");
		data.setText(0, 6, "VAT [%]");
		data.setText(0, 7, "Total price");
		data.setWidget(1, 0, orderDatePicker);
		data.setWidget(1, 1, customers);
		data.setWidget(1, 2, goods);
		data.setWidget(1, 3, onePrice);
		data.setWidget(1, 4, count);
		data.setWidget(1, 5, priceNoTax);
		data.setWidget(1, 6, vat);
		data.setWidget(1, 7, priceTax);
		data.setWidget(1, 8, plusButton);
		data.setBorderWidth(2);
		data.setCellPadding(4);
		calc();
		
		orderDatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				orderDate = orderDatePicker.getValue();
			}
		});
		
		plusButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int row = data.getRowCount();
				double hash = 5;
				hash = 17 * hash + (orderDatePicker.getValue() != null ? orderDatePicker.getValue().hashCode() : 0);
				hash = 17 * hash + customers.getSelectedIndex();
				hash = 17 * hash + goods.getSelectedIndex();
				hash = 17 * hash + (onePrice.getValue() != null ? onePrice.getValue().hashCode() : 0);
				hash = 17 * hash + (count.getValue() != null ? count.getValue().hashCode() : 0);
				hash = 17 * hash + (priceNoTax.getValue() != null ? priceNoTax.getValue().hashCode() : 0);
				hash = 17 * hash + (vat.getValue() != null ? vat.getValue().hashCode() : 0);
				hash = 17 * hash + (priceTax.getValue() != null ? priceTax.getValue().hashCode() : 0);
				final double finalHash = hash;
				if (orderIDs.contains(finalHash)){
					Window.alert(messages.duplicityOrder(orderIDs.indexOf(finalHash)+3));
					return;
				}
				List<String> s = new ArrayList<String>();
				s.add("cat");
				List<GoodsRPC> g = new ArrayList<GoodsRPC>();
				g.add(new GoodsRPC("666", goods.getValue(goods.getSelectedIndex()), "description",
						Double.parseDouble(onePrice.getValue()), Integer.parseInt(count.getValue()),
						true, Double.parseDouble(vat.getValue()), "666", s));
				final OrderRPC order = new OrderRPC(OrderStateRPC.OPEN, new Date(), new Date(), new Date(),
						new Date(), new Date(), orderDate, DeliveryMethodRPC.PPL, g, new ContactRPC(),
						new ContactRPC(), "Generic Seller", "Generic Seller"); 
				orderList.add(order);
				
				orderIDs.add(finalHash);
				data.insertRow(row);
				data.setText(row, 0, DateTimeFormat.getMediumDateFormat().format(orderDate));
				data.setText(row, 1, customers.getItemText(customers.getSelectedIndex()));
				data.setText(row, 2, goods.getItemText(goods.getSelectedIndex()));
				data.setText(row, 3, onePrice.getText());
				data.setText(row, 4, count.getText());
				data.setText(row, 5, priceNoTax.getText());
				data.setText(row, 6, vat.getText());
				data.setText(row, 7, priceTax.getText());
				data.setWidget(row, 8, new Button("<b>-</b>", new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						int removeIndex = orderIDs.indexOf(finalHash);
						orderIDs.remove(removeIndex);
						data.removeRow(removeIndex+2);
					}
				}));
			}
		});
		/*
		customers.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if (customers.getItemText(customers.getSelectedIndex()).equals("Cars")) {
					goods.clear();
					goods.addItem("Opel");
					goods.addItem("Škoda");
				}
				else if (customers.getItemText(customers.getSelectedIndex()).equals("Others")) {
					goods.clear();
					goods.addItem("something other");
				}
				else {
					goods.clear();
					goods.addItem("--------");
				}
			}
		});*/
		goods.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if (goods.getItemText(goods.getSelectedIndex()).equals("phone 1")) {
					onePrice.setText("5000");
					calc();
				}
				else if (goods.getItemText(goods.getSelectedIndex()).equals("phone 2")) {
					onePrice.setText("6000");
					calc();
				}
				else if (goods.getItemText(goods.getSelectedIndex()).equals("phone 3")) {
					onePrice.setText("5");
					calc();
				}
			}
		});
		count.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				calc();
			}
		});
		save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("This will save your orders to DB  (will means in future, not yet)");
				//TODO: save to DB
				rpc.addNewOrders(orderList, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						Window.alert("Ok");
					}
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("fail: "+caught.toString());
					}
				});
			}
		});
		
		flexTable.setWidget(0, 0, new HTML("<u>User</u>:"));
		flexTable.setWidget(0, 1, user);
		flexTable.setWidget(1, 0, new HTML("<u>Order</u>:"));
		flexTable.setWidget(2, 1, data);
		flexTable.setWidget(3, 1, save);

		initWidget(flexTable);
	}
	void calc(){
		if (Integer.parseInt(count.getText()) > 0) {
			priceNoTax.setText(Integer.toString(Integer.parseInt(count.getText())*Integer.parseInt(onePrice.getText())));
			priceTax.setText(Double.toString(Integer.parseInt(priceNoTax.getText())*(1 + Double.parseDouble(vat.getText())/100)));
		} else {
			priceNoTax.setText("------");
			priceTax.setText("------");
		}
	}
}
