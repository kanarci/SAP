package cz.cvut.felk.via.kanarci.gui.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cz.cvut.felk.via.kanarci.gui.client.interfaces.ITab;
import cz.cvut.felk.via.kanarci.gui.shared.CategoryRPC;

public class Goods extends Composite implements ITab{

	private ListBox goodsCategory = new ListBox();
	private ListBox goods = new ListBox();
	private TextBox newCategoryName = new TextBox();
	private TextBox newGoodsName = new TextBox();
	private Button addCategoryButton = new Button("<b>+</b>");
	private Button removeCategoryButton = new Button("<b>-</b>");
	private Button addGoodsButton = new Button("<b>+</b>");
	private Button removeGoodsButton = new Button("<b>-</b>");
	final RPCAsync rpc = GWT.create(RPC.class);
	final FlexTable flexTable = new FlexTable();
	
	public Goods() {
		super();


		//goodsCategory.addItem(item)

		
		initWidget(createGoodsPanel());
		
		refreshCategory();	
	}

	
	private Widget createGoodsPanel(){
		
		// Add FlexTable elements
		flexTable.setWidget(0, 0, new HTML("<u>Category</u>:"));
		flexTable.setWidget(1, 0, goodsCategory);
		flexTable.setWidget(1, 1, newCategoryName);
		flexTable.setWidget(1, 2, addCategoryButton);
		flexTable.setWidget(1, 3, removeCategoryButton);
		flexTable.setWidget(2, 0, new HTML("<u>Goods</u>:"));
		flexTable.setWidget(3, 0, goods);
		flexTable.setWidget(3, 1, newGoodsName);
		flexTable.setWidget(3, 2, addGoodsButton);
		flexTable.setWidget(3, 3, removeGoodsButton);		
		
		
		
		return flexTable;
	}
	
	
	private void refreshCategory() {
		rpc.getAllCategoriesServer(new AsyncCallback<List<CategoryRPC>>() {

			@Override
			public void onFailure(Throwable caught) {
				goodsCategory.clear();
				goodsCategory.addItem("--------");
			}

			@Override
			public void onSuccess(List<CategoryRPC> result) {
				goodsCategory.clear();
				for (CategoryRPC cat : result) {
					goodsCategory.addItem(cat.getName() + "-" + cat.getParameterName() +" ("+ cat.getParameterValue()+")");
				}
				
			}
		});	
		
	}



	@Override
	public void refreshContent() {
		refreshCategory();
		
	}
}
