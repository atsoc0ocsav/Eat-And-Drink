package interfaceClasses;

import javax.swing.JOptionPane;

public class FotografiasEComentarios implements InterfaceFotoEComment_Establecimentos{

	@Override
	public void showFotografias(int idPrato) {
		JOptionPane.showMessageDialog(null,
				"Por implementar a chamada ao módulo de Fotografias e Comentário\n Mostrar fotografias do prato idPrato="
						+idPrato);
	}

	@Override
	public void showComentarios(int idPrato) {
		JOptionPane.showMessageDialog(null,
				"Por implementar a chamada ao módulo de Fotografias e Comentário\n Mostrar comentário do prato idPrato="
						+ idPrato);
	}

}
