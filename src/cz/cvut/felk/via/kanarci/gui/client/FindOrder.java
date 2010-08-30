package cz.cvut.felk.via.kanarci.gui.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import cz.cvut.felk.via.kanarci.gui.client.interfaces.ITab;

public class FindOrder extends Composite implements ITab{
	
	private Grid data = new Grid(1, 9);
	private Date searchDate = new Date();
	private DateBox searchDatePicker = new DateBox(new DatePicker(), searchDate, new DateBox.DefaultFormat(DateTimeFormat.getMediumDateFormat()));
	
	private ListBox goodsCategory = new ListBox();
	private ListBox goods = new ListBox();
	private TextBox countGreater = new TextBox();
	private TextBox countSmaller = new TextBox();
	private TextBox priceGreater = new TextBox();
	private TextBox priceSmaller = new TextBox();
	
	private CheckBox isDate = new CheckBox("Date");
	private CheckBox isCategory = new CheckBox("Category");
	private CheckBox isGoods = new CheckBox("Goods");
	private CheckBox isNoGreater = new CheckBox("Number >=");
	private CheckBox isNoSmaller = new CheckBox("Number <=");
	private CheckBox isPriceGreater = new CheckBox("Price >=");
	private CheckBox isPriceSmaller = new CheckBox("Price <=");
	
	
	public FindOrder() {
		super();
		final VerticalPanel vp = new VerticalPanel();
		final FlexTable flexTable = new FlexTable();
		flexTable.setCellPadding(4);
		Label user = new Label("Jméno Příjmení");
		final Button searchButton = new Button("<b>GO!</b>");
		
		searchDatePicker.setWidth("10em");
		searchDatePicker.setVisible(false);
		goodsCategory.addItem("--------");
		goodsCategory.addItem("Cars");
		goodsCategory.addItem("Others");
		goodsCategory.setVisible(false);
		goods.addItem("--------");
		goods.setVisible(false);
		countGreater.setWidth("3em");
		countGreater.setVisible(false);
		countSmaller.setWidth("3em");
		countSmaller.setVisible(false);
		priceGreater.setWidth("6em");
		priceGreater.setVisible(false);
		priceSmaller.setWidth("6em");
		priceSmaller.setVisible(false);
		
		//handlers	DO NOT EDIT, IT WORKS, trust me :-)
		isDate.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (isDate.getValue()) searchDatePicker.setVisible(true); else searchDatePicker.setVisible(false);
			}
		});
		isCategory.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (isCategory.getValue()) goodsCategory.setVisible(true); else {
					goodsCategory.setVisible(false);
					goods.setVisible(false);
					isGoods.setValue(false);
				}
			}
		});
		isGoods.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (isGoods.getValue()) {
					isCategory.setValue(true);
					goodsCategory.setVisible(true);
					goods.setVisible(true);
				} else {
					goods.setVisible(false);
				}
			}
		});
		isNoGreater.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (isNoGreater.getValue()) countGreater.setVisible(true); else countGreater.setVisible(false);
			}
		});
		isNoSmaller.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (isNoSmaller.getValue()) countSmaller.setVisible(true); else countSmaller.setVisible(false);
			}
		});
		isPriceGreater.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (isPriceGreater.getValue()) priceGreater.setVisible(true); else priceGreater.setVisible(false);
			}
		});
		isPriceSmaller.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (isPriceSmaller.getValue()) priceSmaller.setVisible(true); else priceSmaller.setVisible(false);
			}
		});
		searchDatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				searchDate = searchDatePicker.getValue();
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

		searchButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//TODO: search procedure
				data.setVisible(true);
			}
		});
		
		flexTable.setWidget(0, 0, new HTML("<u>User</u>:"));
		flexTable.setWidget(0, 1, user);
		flexTable.setWidget(1, 0, new HTML("<u>Search</u>:"));
		flexTable.setWidget(2, 1, isDate);
		flexTable.setWidget(3, 1, searchDatePicker);
		flexTable.setWidget(4, 1, isCategory);
		flexTable.setWidget(4, 2, isGoods);
		flexTable.setWidget(5, 1, goodsCategory);
		flexTable.setWidget(5, 2, goods);
		flexTable.setWidget(6, 1, isNoGreater);
		flexTable.setWidget(6, 2, countGreater);
		flexTable.setWidget(6, 3, isNoSmaller);
		flexTable.setWidget(6, 4, countSmaller);
		flexTable.setWidget(7, 1, isPriceGreater);
		flexTable.setWidget(7, 2, priceGreater);
		flexTable.setWidget(7, 3, isPriceSmaller);
		flexTable.setWidget(7, 4, priceSmaller);
		flexTable.setWidget(7, 5, searchButton);
		
		data.setText(0, 0, "timestamp");
		data.setText(0, 1, "Created by");
		data.setText(0, 2, "Order date");
		data.setText(0, 3, "Customer");
		data.setText(0, 4, "Goods");
		data.setText(0, 5, "no.");
		data.setText(0, 6, "Total price");
		data.setText(0, 7, "Address");
		data.setBorderWidth(2);
		data.setCellPadding(4);
		data.setVisible(false);

		vp.add(flexTable);
		vp.add(data);
		initWidget(vp);
	}


	@Override
	public void refreshContent() {
		// TODO Auto-generated method stub
		
	}
}
