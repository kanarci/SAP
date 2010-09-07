package cz.cvut.felk.via.kanarci.gui.client.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;

import cz.cvut.felk.via.kanarci.gui.client.TreeItemLink;
import cz.cvut.felk.via.kanarci.gui.client.interfaces.ITab;
import cz.cvut.felk.via.kanarci.gui.shared.CategoryRPC;
import cz.cvut.felk.via.kanarci.gui.shared.GoodsRPC;

public class CategoryListBox extends Composite implements ITab {

	Map<String, CategoryRPC> categories = new HashMap<String, CategoryRPC>();

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

	public void setGoods(GoodsRPC goods) {
		this.goods = goods;
	}

	public GoodsRPC getGoods() {
		return goods;
	}

	public CategoryRPC getSelectedItem() {
		String html = listBox.getValue(listBox.getSelectedIndex());
		return categories.get(html);
	}

	public void addItem(CategoryRPC cat) {

		categories.put(catTotext(cat), cat);
		listBox.addItem(catTotext(cat));

	}

	public void addItems(List<CategoryRPC> cat) {
		categories.clear();
		listBox.clear();
		for (CategoryRPC c : cat) {
			addItem(c);
		}
	}

	public List<String> getCategories() {
		List<String> ret = new ArrayList<String>();

		for (CategoryRPC cat : categories.values()) {
			ret.add(cat.getKey());
		}
		return ret;
	}

	public void removeSelectedItem() {

		CategoryRPC cat = getSelectedItem();
		listBox.removeItem(listBox.getSelectedIndex());
		categories.remove(catTotext(cat));

	}

	private String catTotext(CategoryRPC cat) {
		return cat.getName() + " : " + cat.getParameterName() + " - "
				+ cat.getParameterValue();

	}
}
