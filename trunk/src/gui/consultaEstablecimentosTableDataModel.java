package gui;

import javax.swing.table.AbstractTableModel;

public class consultaEstablecimentosTableDataModel extends AbstractTableModel {
	private String[] columnNames = { "Nome", "Zona", "Avaliação" };
	private Object[][] data = new Object[0][0];

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	public void changeData(Object[][] data) {
		this.data = data;
	}

}
