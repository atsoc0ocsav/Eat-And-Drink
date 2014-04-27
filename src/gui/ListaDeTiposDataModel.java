package gui;

import java.util.ArrayList;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class ListaDeTiposDataModel implements ListModel<String> {
	private ArrayList<String> dados = new ArrayList<String>();
	
	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getElementAt(int index) {
		return dados.get(index);
	}

	@Override
	public int getSize() {
		return dados.size();
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void add(String s){
		dados.add(s);
	}
	
	public void remove(int index){
		dados.remove(index);
	}

}
