package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlo.CtrlDetalhesEstabelecimento;

public class DetalhesEstabelecimento extends JFrame {

	private static final long serialVersionUID = -4354922814029833393L;
	private CtrlDetalhesEstabelecimento establishmentDetails;
	private JPanel contentPane;

	/**
	 * Launch the application (for debug purpose)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new DetalhesEstabelecimento(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Open Establishment Details
	 * 
	 * @param establishment
	 *            ID
	 */
	public DetalhesEstabelecimento(int establishmentID) {
		
		initialize();
		setVisible(true);
	}

	/**
	 * Build GUI
	 */
	private void initialize() {
		setTitle("Eat & Drink Estabelecimentos - Estabelecimento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

}
