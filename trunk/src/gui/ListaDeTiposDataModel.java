package gui;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class ListaDeTiposDataModel extends AbstractListModel<String> {
	private ArrayList<String> tipos = new ArrayList<String>();
	
	@Override
	public String getElementAt(int arg0) {
		return tipos.get(arg0);
	}

	@Override
	public int getSize() {
		return tipos.size();
	}
	
	public void add(String x){
		int s = tipos.size();
		tipos.add(x);
		fireIntervalAdded(this, s, s);
	}
	
	public void remove(int index){
		tipos.remove(index);
		fireIntervalAdded(this, index, index);
	}
	
	public boolean exists(String s){
		for (String i : tipos) {
			if(i.equals(s))
				return true;
		}
		return false;
	}
}
