package dados;

import java.awt.Image;

import javax.swing.JOptionPane;

public class Fotografias {

	public Fotografias(){
		
	}
	
	public void alterarFotografia(String userID){
		JOptionPane.showMessageDialog(null,"Fotografia do utilizador '" + userID + "' no modulo externo de Fotografias.");
	}
	
	public Image obterFotografia(String userID){
		return null;
	}
}
