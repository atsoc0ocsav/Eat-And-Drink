package dados;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Botao extends AbstractCellEditor implements TableCellRenderer, TableCellEditor{  
	
	private JTable table;  
	private JButton renderButton;  
	private String text;  

	public Botao(JTable table, int column){  
		super();  
		this.table = table;  
		renderButton = new JButton();   
		renderButton = new JButton();
		TableColumnModel columnModel = table.getColumnModel();  
		columnModel.getColumn(column).setCellRenderer(this);  
		//columnModel.getColumn(column).setCellEditor(this);  
	}  
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){ 
		if(value.toString().equals("Não")){
			ImageIcon img = new ImageIcon("remove.png");
			renderButton.setIcon(img);
		}else if(value.toString().equals("Sim")){
			ImageIcon img = new ImageIcon("add.png");
			renderButton.setIcon(img);
		}else if(value.toString().equals("Info")){
			ImageIcon img = new ImageIcon("info.png");
			renderButton.setIcon(img);
		}else{
			renderButton.setIcon(null);
		}
		renderButton.setBorderPainted(false); 
		renderButton.setContentAreaFilled(false); 
		renderButton.setFocusPainted(false); 
		renderButton.setOpaque(false);  
        return renderButton; 
	}    
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){ 
		text = (value == null) ? "" : value.toString();  
        renderButton.setText(value.toString());  
        return renderButton; 
	}    

	public Object getCellEditorValue(){  
		return text;  
	}  
}  

