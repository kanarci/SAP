package cz.cvut.felk.via.kanarci.gui.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

import cz.cvut.felk.via.kanarci.gui.client.interfaces.ITab;
import cz.cvut.felk.via.kanarci.gui.client.widgets.CategoryListBox;
import cz.cvut.felk.via.kanarci.gui.client.widgets.CategoryTree;
import cz.cvut.felk.via.kanarci.gui.client.widgets.GoodsList;
import cz.cvut.felk.via.kanarci.gui.shared.CategoryRPC;
import cz.cvut.felk.via.kanarci.gui.shared.Constants;
import cz.cvut.felk.via.kanarci.gui.shared.GoodsRPC;

public class Goods extends Composite implements ITab {

	// private ListBox goodsCategory = new ListBox();
	private TextBox goodsName = new TextBox();
	private TextBox goodsDescription = new TextBox();
	private TextBox goodsPrice = new TextBox();
	private TextBox goodsCount = new TextBox();
	private ListBox goodsVAT = new ListBox();
	private CategoryListBox goodsCategories = new CategoryListBox();
	private Button goodsAddCategory = new Button("<b>+</b>");
	private Button goodsRemoveCategory = new Button("<b>-</b>");
	private Button goodsAdd = new Button("<b>Add</b>");
	private Button goodsOK = new Button("<b>OK</b>");

	final RPCAsync rpc = GWT.create(RPC.class);
	final FlexTable flexTable = new FlexTable();

	final GoodsList gList = new GoodsList();

	final CategoryTree categoryTree = new CategoryTree();

	// private List<CategoryRPC> categoriesGoods = new ArrayList<CategoryRPC>();
	// private List<TreeItemLink> categoriesGoodsItems = new
	// ArrayList<TreeItemLink>();

	public Goods() {
		super();

		DockPanel dPanel = new DockPanel();
		dPanel.add(createGoodsPanel(), DockPanel.NORTH);
		initWidget(dPanel);
		// initWidget(createGoodsPanel());

		// refreshContent();
	}

	private Widget createGoodsPanel() {

		Grid grid = new Grid(3, 1);
		grid.setWidget(0, 0, new HTML("<b>Nové Zboží</b>"));
		grid.setWidget(1, 0, createGoods());
		grid.setWidget(2, 0, viewGoods());
		grid.getRowFormatter().setVerticalAlign(1,
				HasVerticalAlignment.ALIGN_TOP);
		grid.getRowFormatter().setVerticalAlign(2,
				HasVerticalAlignment.ALIGN_TOP);

		return grid;
	}

	private Widget viewGoods() {

		DecoratorPanel dec = new DecoratorPanel();
		Grid grid = new Grid(2, 2);
		grid.setWidget(0, 0, createCategoryTree());
		grid.setWidget(0, 1, createGoodsInCategoryList());

		dec.add(grid);
		return dec;
	}

	private Widget createGoodsInCategoryList() {
		return this.gList;
	}

	private Widget createCategoryTree() {
		categoryTree.categoryTree
				.addSelectionHandler(new SelectionHandler<TreeItem>() {

					@Override
					public void onSelection(SelectionEvent<TreeItem> event) {
						// TODO Auto-generated method stub
						CategoryRPC cat = getSelectedItem();
						gList.setSelectedCategory(cat);
						gList.refreshContent();
					}
				});
		categoryTree.categoryTree.setAnimationEnabled(true);
		// refreshCategoryTree();

		ScrollPanel staticTreeWrapper = new ScrollPanel(categoryTree);
		staticTreeWrapper.setSize("300px", "300px");

		return staticTreeWrapper;
	}

	private Widget createGoods() {

		DecoratorPanel dec = new DecoratorPanel();

		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(goodsPrice);
		hPanel.add(new HTML(",- Kč"));

		flexTable.setWidget(0, 0, new HTML("Name"));
		flexTable.setWidget(0, 1, new HTML("Description"));
		flexTable.setWidget(0, 2, new HTML("Price"));
		flexTable.setWidget(0, 3, new HTML("Count"));
		flexTable.setWidget(0, 4, new HTML("VAT"));
		flexTable.setWidget(0, 5, new HTML("Category"));

		flexTable.setWidget(1, 0, goodsName);
		flexTable.setWidget(1, 1, goodsDescription);
		flexTable.setWidget(1, 2, hPanel);
		flexTable.setWidget(1, 3, goodsCount);
		flexTable.setWidget(1, 4, goodsVAT);
		flexTable.setWidget(1, 5, goodsCategoryWidget());
		flexTable.setWidget(1, 6, goodsEditAddWidget());

		goodsPrice.setWidth("50px");
		goodsCount.setWidth("50px");

		// fill VAT values
		for (String s : Constants.getVAT()) {
			goodsVAT.addItem(s);
		}

		dec.add(flexTable);

		return dec;
	}

	private Widget goodsEditAddWidget() {

		goodsAdd.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				double price = 0;
				int numOfPieces = 0;
				double DPH = 0;
				try {
					price = Double.parseDouble(goodsPrice.getText());
					numOfPieces = Integer.parseInt(goodsCount.getText());
					DPH = Double.parseDouble(goodsVAT.getItemText(goodsVAT
							.getSelectedIndex()));
				} catch (NumberFormatException e) {
					// e.printStackTrace();
					Window.alert(" Number conversion error :");
					return;
				}

				GoodsRPC goods = new GoodsRPC(goodsName.getText(),
						goodsDescription.getText(), price, numOfPieces, DPH,
						goodsCategories.getCategories());
				rpc.addNewGoods(goods, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(" Goods was not saved ... :");

					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						refreshContent();
					}
				});

			}
		});

		goodsOK.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		});

		Grid grid = new Grid(2, 1);
		grid.setWidget(0, 0, goodsAdd);
		grid.setWidget(1, 0, goodsOK);

		goodsAdd.setWidth("50px");
		goodsOK.setWidth("50px");

		return grid;
	}

	private Widget goodsCategoryWidget() {
		this.goodsAddCategory.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CategoryRPC cat = getSelectedItem();

				goodsCategories.addItem(cat);

			}
		});
		this.goodsRemoveCategory.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				goodsCategories.removeSelectedItem();

			}
		});

		goodsAddCategory.setWidth("30px");
		goodsRemoveCategory.setWidth("30px");

		HorizontalPanel hPanel = new HorizontalPanel();
		Grid grid = new Grid(2, 1);
		grid.setWidget(0, 0, goodsAddCategory);
		grid.setWidget(1, 0, goodsRemoveCategory);

		hPanel.add(goodsCategories);
		hPanel.setCellVerticalAlignment(goodsCategories,
				HasVerticalAlignment.ALIGN_MIDDLE);

		hPanel.add(grid);

		return hPanel;
	}

	@Override
	public void refreshContent() {

		refreshCategoryTree();
		gList.refreshContent();
		goodsCategories.addItems(categoryTree.getCategoryForKey(goodsCategories
				.getCategories()));

	}

	private void refreshCategoryTree() {

		categoryTree.refreshContent();
		// rpc.getAllCategoriesServer(new AsyncCallback<List<CategoryRPC>>() {
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// Window.alert("fail: " + caught.toString());
		// }
		//
		// @Override
		// public void onSuccess(List<CategoryRPC> result) {
		// categories = result;
		// categoryTree.removeItems();
		// catTree.clear();
		// for (CategoryRPC cat : categories) {
		// TreeItem ti = new TreeItem(cat.getName() + " : "
		// + cat.getParameterName() + " - "
		// + cat.getParameterValue());
		// // catTree.put(ti.getHTML(), categories.indexOf(cat));
		// catTree.add(new TreeItemLink(categories.indexOf(cat), ti
		// .getHTML()));
		// categoryTree.addItem(ti);
		// }
		// }
		// });
	}

	private CategoryRPC getSelectedItem() {

		return categoryTree.getSelectedItem();
		// String html = categoryTree.getSelectedItem().getHTML();
		// int index = -1;
		// int i = -1;
		// for (TreeItemLink ti : catTree) {
		// i++;
		// if (ti.text.equals(html)) {
		// index = i;
		// break;
		// }
		// }
		//
		// if (index == -1) {
		// Window.alert("Category cannot be found ");
		// return null;
		// }
		//
		// return categories.get(index);
	}
}
