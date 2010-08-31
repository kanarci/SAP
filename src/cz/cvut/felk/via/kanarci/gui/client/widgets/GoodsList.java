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
import cz.cvut.felk.via.kanarci.gui.shared.GoodsRPC;

public class GoodsList extends Composite implements ITab {

	final RPCAsync rpc = GWT.create(RPC.class);
	private List<GoodsRPC> goods = new ArrayList<GoodsRPC>();
	private List<TreeItemLink> goodsList = new ArrayList<TreeItemLink>();
	private CategoryRPC selectedCategory = new CategoryRPC();
	private Tree goodsTree = new Tree();

	public GoodsList() {
		super();
		goodsTree.setSize("400px", "400px");

		initWidget(goodsTree);

	}

	@Override
	public void refreshContent() {

		if(selectedCategory == null || selectedCategory.getKey() == null){
			return;
		}
		
		System.out.println(" GoodsList - cat key "+selectedCategory.getKey());
		
		rpc.getAllGoodsServer(selectedCategory,
				new AsyncCallback<List<GoodsRPC>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("fail: " + caught.toString());
					}

					@Override
					public void onSuccess(List<GoodsRPC> result) {
						goods = result;
						goodsTree.removeItems();
						goodsList.clear();
						for (GoodsRPC g : goods) {
							TreeItem ti = new TreeItem(g.getName());
							goodsList.add(new TreeItemLink(goods.indexOf(g), ti
									.getHTML()));
							goodsTree.addItem(ti);
						}
					}
				});

	}

	@SuppressWarnings("unused")
	private GoodsRPC getSelectedItem() {

		String html = goodsTree.getSelectedItem().getHTML();
		int index = -1;
		int i = -1;
		for (TreeItemLink ti : goodsList) {
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

		return goods.get(index);
	}

	public CategoryRPC getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(CategoryRPC selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public List<GoodsRPC> getGoods() {
		return goods;
	}

}
