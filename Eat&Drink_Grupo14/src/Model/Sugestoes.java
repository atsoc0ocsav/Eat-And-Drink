package Model;

import javax.swing.JOptionPane;

public class Sugestoes {

	public Sugestoes(){
		
	}
	
	public void mostrarSugestoes(String userID){
		JOptionPane.showMessageDialog(null,"Sugest�es do utilizador '" + userID + "' no modulo externo de Sugest�es.");
	}
	
}
