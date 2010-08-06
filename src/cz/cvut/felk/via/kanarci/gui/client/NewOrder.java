package cz.cvut.felk.via.kanarci.gui.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
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

public class NewOrder extends Composite{

	private NewOrderMessages messages = GWT.create(NewOrderMessages.class);
	
	private Grid data = new Grid(2, 9);
	private Date orderDate = new Date();
	private DateBox orderDatePicker = new DateBox(new DatePicker(), orderDate, new DateBox.DefaultFormat(DateTimeFormat.getMediumDateFormat()));
	
	private ListBox goodsCategory = new ListBox();
	private ListBox goods = new ListBox();
	private TextBox onePrice = new TextBox();
	private TextBox count = new TextBox();
	private TextBox priceNOTax = new TextBox();
	private TextBox vat = new TextBox();
	private TextBox priceTax = new TextBox();
	private ArrayList<Double> orderIDs = new ArrayList<Double>();
	
	public NewOrder() {
		super();
		final FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(4);
		Label user = new Label("Jméno Příjmení");
		final Button plusButton = new Button("<b>+</b>");
		final Button save = new Button("Save changes"); 
		
		orderDatePicker.setWidth("10em");
		goodsCategory.addItem("--------");
		goodsCategory.addItem("Cars");
		goodsCategory.addItem("Others");
		goods.addItem("--------");
		onePrice.setWidth("6em");
		onePrice.setReadOnly(true);
		count.setWidth("2em");
		priceNOTax.setWidth("6em");
		priceNOTax.setReadOnly(true);
		vat.setWidth("3em");
		vat.setReadOnly(true);
		priceTax.setWidth("6em");
		priceTax.setReadOnly(true);
		//folloving will be read from DB
		onePrice.setText("from DB");
		priceNOTax.setText("calculated");
		vat.setText("20%");
		priceTax.setText("calculated");
		
		data.setText(0, 0, "Order date");
		data.setText(0, 1, "Category");
		data.setText(0, 2, "Goods");
		data.setText(0, 3, "Price");
		data.setText(0, 4, "no.");
		data.setText(0, 5, "excl. VAT");
		data.setText(0, 6, "VAT");
		data.setText(0, 7, "Total price");
		data.setWidget(1, 0, orderDatePicker);
		data.setWidget(1, 1, goodsCategory);
		data.setWidget(1, 2, goods);
		data.setWidget(1, 3, onePrice);
		data.setWidget(1, 4, count);
		data.setWidget(1, 5, priceNOTax);
		data.setWidget(1, 6, vat);
		data.setWidget(1, 7, priceTax);
		data.setWidget(1, 8, plusButton);
		data.setBorderWidth(2);
		data.setCellPadding(4);
		
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
				hash = 17 * hash + goodsCategory.getSelectedIndex();
				hash = 17 * hash + goods.getSelectedIndex();
				hash = 17 * hash + (onePrice.getValue() != null ? onePrice.getValue().hashCode() : 0);
				hash = 17 * hash + (count.getValue() != null ? count.getValue().hashCode() : 0);
				hash = 17 * hash + (priceNOTax.getValue() != null ? priceNOTax.getValue().hashCode() : 0);
				hash = 17 * hash + (vat.getValue() != null ? vat.getValue().hashCode() : 0);
				hash = 17 * hash + (priceTax.getValue() != null ? priceTax.getValue().hashCode() : 0);
				final double finalHash = hash;
				if (orderIDs.contains(finalHash)){
					Window.alert(messages.duplicityOrder(orderIDs.indexOf(finalHash)+3));
					//Window.alert("You are making duplicity order. Save our trees and make it as a single order.\n" +
					//		"Duplicity found on line: "+ orderIDs.indexOf(finalHash)+3);
					return;
				}
				orderIDs.add(finalHash);
				data.insertRow(row);
				data.setText(row, 0, DateTimeFormat.getMediumDateFormat().format(orderDate));
				data.setText(row, 1, goodsCategory.getItemText(goodsCategory.getSelectedIndex()));
				data.setText(row, 2, goods.getItemText(goods.getSelectedIndex()));
				data.setText(row, 3, onePrice.getText());
				data.setText(row, 4, count.getText());
				data.setText(row, 5, priceNOTax.getText());
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
		
		goodsCategory.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				if (goodsCategory.getItemText(goodsCategory.getSelectedIndex()).equals("Cars")) {
					goods.clear();
					goods.addItem("Opel");
					goods.addItem("Škoda");
				}
				else if (goodsCategory.getItemText(goodsCategory.getSelectedIndex()).equals("Others")) {
					goods.clear();
					goods.addItem("something other");
				}
				else {
					goods.clear();
					goods.addItem("--------");
				}
			}
		});
		
		save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("This will save your orders to DB  (will means in future, not yet)");
			}
		});
		
		flexTable.setWidget(0, 0, new HTML("<u>User</u>:"));
		flexTable.setWidget(0, 1, user);
		flexTable.setWidget(1, 0, new HTML("<u>Order</u>:"));
		flexTable.setWidget(2, 1, data);
		flexTable.setWidget(3, 1, save);

		initWidget(flexTable);
	}
	/*
	void dataFeed(){
		data.insertRow(data.getRowCount());
		data.setText(1, 0, "29.2.2009");
		data.setText(1, 1, "bla blablabla");
		data.setText(1, 2, "1");
		data.setText(1, 3, "5 328,45");
		data.insertRow(data.getRowCount());
		data.setText(2, 0, "30.2.2010");
		data.setText(2, 1, "blabla blabla");
		data.setText(2, 2, "2");
		data.setText(2, 3, "3 284,55");
		data.insertRow(data.getRowCount());
		data.setText(3, 0, "31.2.2010");
		data.setText(3, 1, "blablabla bla");
		data.setText(3, 2, "3");
		data.setText(3, 3, "2 845,53");
	}
	void dataFeed(String date, String label, int count, double price, double tax){
		data.insertRow(data.getRowCount());
		data.setText(data.getRowCount()-1, 0, date);
		data.setText(data.getRowCount()-1, 1, label);
		data.setText(data.getRowCount()-1, 2, Integer.toString(count));
		data.setText(data.getRowCount()-1, 3, Double.toString(price));
		data.setText(data.getRowCount()-1, 4, Double.toString(price * count));
		data.setText(data.getRowCount()-1, 5, Double.toString(tax));
		data.setText(data.getRowCount()-1, 6, Double.toString(price * count * tax / 100 + price * count));
	}*/
}
