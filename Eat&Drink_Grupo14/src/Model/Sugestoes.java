package Model;

import javax.swing.JOptionPane;

public class Sugestoes {

	public Sugestoes(){
		
	}
	
	public void mostrarSugestoes(String userID){
		JOptionPane.showMessageDialog(null,"Sugestões do utilizador '" + userID + "' no modulo externo de Sugestões.");
	}
	
}
