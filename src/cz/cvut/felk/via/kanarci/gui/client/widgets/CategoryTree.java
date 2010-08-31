package cz.cvut.felk.via.kanarci.gui.client.widgets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

import cz.cvut.felk.via.kanarci.gui.client.RPC;
import cz.cvut.felk.via.kanarci.gui.client.RPCAsync;
import cz.cvut.felk.via.kanarci.gui.client.TreeItemLink;
import cz.cvut.felk.via.kanarci.gui.client.interfaces.ITab;
import cz.cvut.felk.via.kanarci.gui.shared.CategoryRPC;

public class CategoryTree extends Composite implements ITab {

	final RPCAsync rpc = GWT.create(RPC.class);

	private List<CategoryRPC> categories = new ArrayList<CategoryRPC>();
	private List<TreeItemLink> catItemList = new ArrayList<TreeItemLink>();
	public final Tree categoryTree = new Tree();

	public CategoryTree() {
		super();
		// TODO Auto-generated constructor stub
		initWidget(categoryTree);
	}

	@Override
	public void refreshContent() {

		rpc.getAllCategoriesServer(new AsyncCallback<List<CategoryRPC>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("fail: " + caught.toString());
			}

			@Override
			public void onSuccess(List<CategoryRPC> result) {
				categories = result;
				categoryTree.removeItems();
				catItemList.clear();
				for (CategoryRPC cat : categories) {
					TreeItem ti = new TreeItem(cat.getName() + " : "
							+ cat.getParameterName() + " - "
							+ cat.getParameterValue());
					// catTree.put(ti.getHTML(), categories.indexOf(cat));
					catItemList.add(new TreeItemLink(categories.indexOf(cat),
							ti.getHTML()));
					categoryTree.addItem(ti);
				}
			}
		});
	}

	public void removeSelectedCategory() {

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
				refreshContent();

			}
		});

	}

	public CategoryRPC getSelectedItem() {

		String html = categoryTree.getSelectedItem().getHTML();
		int index = -1;
		int i = -1;
		for (TreeItemLink ti : catItemList) {
			i++;
			if (ti.text.equals(html)) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			Window.alert("Category cannot be deleted ");
			return null;
		}

		System.out.println("returnet cat is "+ categories.get(index).getKey());
		return categories.get(index);
	}
}
