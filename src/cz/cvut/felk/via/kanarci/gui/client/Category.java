package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class Category extends Composite{

	private ListBox goodsCategory = new ListBox();
	private ListBox goods = new ListBox();
	private TextBox newCategoryName = new TextBox();
	private TextBox newGoodsName = new TextBox();
	private Button addCategoryButton = new Button("<b>+</b>");
	private Button removeCategoryButton = new Button("<b>-</b>");
	private Button addGoodsButton = new Button("<b>+</b>");
	private Button removeGoodsButton = new Button("<b>-</b>");
	
	public Category() {
		super();
		final FlexTable flexTable = new FlexTable();
		
		//goodsCategory.addItem(item)
		
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
		
		initWidget(flexTable);
	}
}
