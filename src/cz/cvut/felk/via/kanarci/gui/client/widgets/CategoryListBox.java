package cz.cvut.felk.via.kanarci.gui.client.widgets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;

import cz.cvut.felk.via.kanarci.gui.client.TreeItemLink;
import cz.cvut.felk.via.kanarci.gui.client.interfaces.ITab;
import cz.cvut.felk.via.kanarci.gui.shared.CategoryRPC;
import cz.cvut.felk.via.kanarci.gui.shared.GoodsRPC;

public class CategoryListBox extends Composite implements ITab {

	private List<CategoryRPC> categories = new ArrayList<CategoryRPC>();
	private List<TreeItemLink> catItemList = new ArrayList<TreeItemLink>();
	ListBox listBox = new ListBox();
	GoodsRPC goods;

	public CategoryListBox() {
		super();

		initWidget(listBox);
	}

	@Override
	public void refreshContent() {
		// TODO Auto-generated method stub

	}

//	public void addItem(String name) {
//		// TODO Auto-generated method stub
//
//	}

	public void setGoods(GoodsRPC goods) {
		this.goods = goods;
	}

	public GoodsRPC getGoods() {
		return goods;
	}

	public CategoryRPC getSelectedItem() {

		// return (CategoryRPC) staticTree.getSelectedItem();

		String html = listBox.getItemText(listBox.getSelectedIndex());
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
			Window.alert("Category cannot be found ");
			return null;
		}

		return categories.get(index);
	}

	public void addItem(CategoryRPC cat) {
		// TODO Auto-generated method stub
		categories.add(cat);
		catItemList.add(new TreeItemLink(categories.indexOf(cat), catTotext(cat)));
		listBox.addItem(catTotext(cat));
		
	}
	public void addItems(List<CategoryRPC> cat){
		categories.clear();
		catItemList.clear();
		listBox.clear();
		for(CategoryRPC c : cat){
			addItem(c);
		}
	}

	public List<String> getCategories() {
		List<String> ret = new ArrayList<String>();
		for(CategoryRPC cat : categories){
			ret.add(cat.getKey());
		}
		return ret;
	}

	public void removeSelectedItem(){
		
		CategoryRPC cat = getSelectedItem();

		int index = 0;
		for(TreeItemLink ti : catItemList){
			if(ti.text.equals(catTotext(cat))){
				break;  
			}
			index++;
		}
		if(index == -1){
			return;
		}
		
		listBox.removeItem(index);
		catItemList.remove(index);
		categories.remove(cat);


		
	}
	
	private String catTotext(CategoryRPC cat) {
		return cat.getName() + " : "
		+ cat.getParameterName() + " - "
		+ cat.getParameterValue();
	}
}
