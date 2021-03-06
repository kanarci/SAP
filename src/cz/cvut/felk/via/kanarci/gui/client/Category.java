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
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import cz.cvut.felk.via.kanarci.gui.client.interfaces.ITab;
import cz.cvut.felk.via.kanarci.gui.client.widgets.CategoryTree;
import cz.cvut.felk.via.kanarci.gui.shared.CategoryRPC;

public class Category extends Composite implements ITab{
	
	final HorizontalPanel hPanel = new HorizontalPanel();
	final RPCAsync rpc = GWT.create(RPC.class);
	final TextBox catName = new TextBox();
	final TextBox name = new TextBox();
	final TextBox value = new TextBox();
	final CategoryTree staticTree = new CategoryTree();
	final Button refreshCat = new Button("<b>Refresh</b>");
	final Button deleteCat = new Button("<b>Delete</b>");
	
//	private List<CategoryRPC> categories = new ArrayList<CategoryRPC>();
//	private List<TreeItemLink> catTree = new ArrayList<TreeItemLink>();

	
	/**
	 * Initialize this example.
	 */
	public Category() {
		// Add the disclosure panels to a panel
		
		DecoratorPanel oldCategoryDecorator = new DecoratorPanel();
		DecoratorPanel newCategoryDecorator = new DecoratorPanel();
		
		oldCategoryDecorator.setWidget(createCategoryTree());
		newCategoryDecorator.setWidget(createCategoryEdit());
		
		Grid grid = new Grid(2, 2);
		grid.setCellPadding(2);
		grid.getRowFormatter().setVerticalAlign(1, HasVerticalAlignment.ALIGN_TOP);
		
		grid.setHTML(0, 0, "<b>Kategorie</b>");
		grid.setWidget(1, 0, oldCategoryDecorator);
		grid.setHTML(0, 1, "<b>Edit</b>");
		grid.setWidget(1, 1, newCategoryDecorator);
		
		DockPanel dPanel = new DockPanel();
		dPanel.add(grid, DockPanel.CENTER);
		
		initWidget(dPanel);

	}

	private Widget createCategoryEdit() {
		// Create a table to layout the form options
		FlexTable layout = new FlexTable();
		layout.setCellSpacing(6);
		layout.setWidth("250px");
		FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		// Add a title to the form
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,	HasHorizontalAlignment.ALIGN_LEFT);
		cellFormatter.setHorizontalAlignment(1, 0,	HasHorizontalAlignment.ALIGN_CENTER);

		// Add some standard form options
		layout.setHTML(0, 0, "<br s><b>Category name:</b></br>");
		catName.setWidth("200px");
		layout.setWidget(1, 1, catName);

		// Create some advanced options
		Grid advancedOptions = new Grid(2, 2);
		advancedOptions.setCellSpacing(6);
		advancedOptions.setHTML(0, 0, "<b>Name</b>");
		advancedOptions.setWidget(0, 1, name);
		advancedOptions.setHTML(1, 0, "<b>Value</b>");
		advancedOptions.setWidget(1, 1, value);

		// Add advanced options to form in a disclosure panel
		DisclosurePanel advancedDisclosure = new DisclosurePanel("Parameter options");
		advancedDisclosure.setAnimationEnabled(true);
		advancedDisclosure.setContent(advancedOptions);
		layout.setWidget(2, 0, advancedDisclosure);
		cellFormatter.setColSpan(2, 0, 2);

		// Add create category button
		Button addCat = new Button("<b>Add new</b>");
		addCat.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(catName.getText() == null || catName.getText().equals("")){
					Window.alert("fail: text/cat name null");
					return;
				}
				CategoryRPC cat = new CategoryRPC(catName.getText(), null, name.getText(), value.getText());
				
				// Add category to DB
				rpc.addNewCategory(cat,  new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						refreshCategoryTree();
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
		
		Button editOK = new Button("<b>OK</b>");
		editOK.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				CategoryRPC cat = updateSelectedItem(getSelectedItem());
				System.out.println(" Edit ok clicked");
				rpc.updateCategory(cat, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("fail: "+caught.toString());						
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
//						Window.alert("Edit successfull");
						refreshCategoryTree();
					}
				});
			}
		});
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hPanel.add(editOK);
		hPanel.add(addCat);
//		layout.setWidget(3, 1, hPanel);
//		cellFormatter.setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_RIGHT);

		// Wrap the contents in a DecoratorPanel
		Grid editGrid = new Grid(2,1);
		editGrid.setWidget(0, 0, layout);
		editGrid.setWidget(1, 0, hPanel);
		editGrid.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		return editGrid;

	}

	protected CategoryRPC updateSelectedItem(CategoryRPC selectedItem) {
		selectedItem.setName(catName.getText());
		
		if(name.getText() == null){
			selectedItem.setParameterName("");	
		}
		else{
			selectedItem.setParameterName(name.getText());
		}
		
		if(value.getText() == null){
			selectedItem.setParameterValue("");
		}
		else{
			selectedItem.setParameterValue(value.getText());
		}
		
		return selectedItem;
	}

	private Widget createCategoryTree() {
		staticTree.categoryTree.addSelectionHandler(new SelectionHandler<TreeItem>() {
			
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				// TODO Auto-generated method stub
				CategoryRPC cat = getSelectedItem();
				catName.setText(cat.getName());
				name.setText(cat.getParameterName());
				value.setText(cat.getParameterValue());
			}
		});
		staticTree.categoryTree.setAnimationEnabled(true);

//		refreshCategoryTree();
		
		refreshCat.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				refreshCategoryTree();
			}
		});
		
		deleteCat.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				removeSelectedCategory();
				
			}
		});
		
		// Wrap the contents in a ScrollPanel and put into Grid
		ScrollPanel staticTreeWrapper = new ScrollPanel(staticTree);
		staticTreeWrapper.setSize("300px", "300px");
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(deleteCat);
		hPanel.add(refreshCat);
		
		Grid grid = new Grid(2, 1);
		grid.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		grid.setWidget(0, 0, staticTreeWrapper);
		grid.setWidget(1, 0, hPanel);
		
		return grid;

	}

	protected void refreshCategoryTree() {
		
		staticTree.refreshContent();
//		rpc.getAllCategoriesServer(new AsyncCallback<List<CategoryRPC>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("fail: "+caught.toString());				
//			}
//
//			@Override
//			public void onSuccess(List<CategoryRPC> result) {
//				categories = result;
//				staticTree.removeItems();
//				catTree.clear();
//				for(CategoryRPC cat : categories){				
//					TreeItem ti = new TreeItem(cat.getName() + " : " + cat.getParameterName() + " - " + cat.getParameterValue());
////					catTree.put(ti.getHTML(), categories.indexOf(cat));
//					catTree.add(new TreeItemLink(categories.indexOf(cat), ti.getHTML()));
//					staticTree.addItem(ti);
//				}
//			}
//		});
	}

	@Override
	public void refreshContent() {
		refreshCategoryTree();
		
	}

	protected void removeSelectedCategory() {
		
		CategoryRPC cat = getSelectedItem();
				
		rpc.delCategory(cat, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Category was NOT deleted ");	
				
			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				Window.alert("Category deleted ");
				refreshCategoryTree();
				
			}
		});
		
	}
	
	private CategoryRPC getSelectedItem(){
		
		return staticTree.getSelectedItem();
////		return (CategoryRPC) staticTree.getSelectedItem();
//		
//		
//		String html = staticTree.getSelectedItem().getHTML();
//		int index = -1;
//		int i = -1;
//		for(TreeItemLink ti : catTree){
//			i++;
//			if(ti.text.equals(html)){
//				index = i;
//				break;
//			}
//		}
//			
//		if(index == -1){
//			Window.alert("Category cannot be deleted ");	
//			return null;
//		}
//		
//		return categories.get(index);	
	}
	
	
	
	

}
