package interfaceClasses;

import javax.swing.JOptionPane;

public class FotografiasEComentarios implements InterfaceFotoEComment_Establecimentos{

	@Override
	public void showFotografias(int idPrato) {
		JOptionPane.showMessageDialog(null,
				"Por implementar a chamada ao m�dulo de Fotografias e Coment�rio\n Mostrar fotografias do prato idPrato="
						+idPrato);
	}

	@Override
	public void showComentarios(int idPrato) {
		JOptionPane.showMessageDialog(null,
				"Por implementar a chamada ao m�dulo de Fotografias e Coment�rio\n Mostrar coment�rio do prato idPrato="
						+ idPrato);
	}

}
