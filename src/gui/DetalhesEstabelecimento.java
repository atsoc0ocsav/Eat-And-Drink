package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import controlo.CtrlDetalhesEstabelecimento;
import dados.Estabelecimento;
import javax.swing.JTextField;

public class DetalhesEstabelecimento extends JFrame {

	private static final long serialVersionUID = -4354922814029833393L;
	private CtrlDetalhesEstabelecimento controladorDetalherEstabelecimento;
	private Estabelecimento estabelecimento;
	private JPanel contentPane;
	private JTextField textField_Estabelecimento;
	private JTextField textField_Tipo;
	private JTextField textField_Cidade;
	private JTextField textField_Zona;
	private JTextField textField_Morada;
	private JTextField textField_Horario;

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
		try {
			controladorDetalherEstabelecimento = new CtrlDetalhesEstabelecimento(
					establishmentID);
			estabelecimento = controladorDetalherEstabelecimento
					.consultarDetalhes();

			// TODO Display Method ;P
			buildGUI();
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Build GUI
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	private void buildGUI() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		// Basic Setup of the Interface
		UIManager.setLookAndFeel(UIManager
				.getCrossPlatformLookAndFeelClassName());
		setTitle("Eat & Drink Estabelecimentos - Estabelecimento \""+estabelecimento.getDesignacao()+"\"");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Labels Constructors
		JLabel lbl_EatDrink = new JLabel("Eat and Drink");
		lbl_EatDrink.setBounds(89, 11, 632, 31);
		lbl_EatDrink.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_EatDrink.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lbl_EatDrink);
		
		JLabel lbl_Estabelecimento = new JLabel("ESTABELECIMENTO");
		lbl_Estabelecimento.setBounds(185, 53, 101, 14);
		contentPane.add(lbl_Estabelecimento);
		
		JLabel lbl_Tipo = new JLabel("TIPO");
		lbl_Tipo.setBounds(205, 78, 46, 14);
		contentPane.add(lbl_Tipo);
		
		JLabel lbl_Cidade = new JLabel("CIDADE");
		lbl_Cidade.setBounds(191, 119, 46, 14);
		contentPane.add(lbl_Cidade);
		
		JLabel lbl_Zona = new JLabel("ZONA");
		lbl_Zona.setBounds(191, 185, 46, 14);
		contentPane.add(lbl_Zona);
		
		JLabel lbl_Morada = new JLabel("MORADA");
		lbl_Morada.setBounds(106, 236, 46, 14);
		contentPane.add(lbl_Morada);
		
		JLabel lbl_Horario = new JLabel("HOR\u00C1RIO");
		lbl_Horario.setBounds(414, 236, 64, 14);
		contentPane.add(lbl_Horario);
		
		textField_Estabelecimento = new JTextField();
		textField_Estabelecimento.setEditable(false);
		textField_Estabelecimento.setBounds(314, 50, 86, 20);
		contentPane.add(textField_Estabelecimento);
		textField_Estabelecimento.setColumns(10);
		
		textField_Tipo = new JTextField();
		textField_Tipo.setEditable(false);
		textField_Tipo.setBounds(261, 78, 86, 20);
		contentPane.add(textField_Tipo);
		textField_Tipo.setColumns(10);
		
		textField_Cidade = new JTextField();
		textField_Cidade.setEditable(false);
		textField_Cidade.setBounds(247, 116, 86, 20);
		contentPane.add(textField_Cidade);
		textField_Cidade.setColumns(10);
		
		textField_Zona = new JTextField();
		textField_Zona.setEditable(false);
		textField_Zona.setBounds(247, 182, 86, 20);
		contentPane.add(textField_Zona);
		textField_Zona.setColumns(10);
		
		textField_Morada = new JTextField();
		textField_Morada.setEditable(false);
		textField_Morada.setBounds(185, 233, 86, 20);
		contentPane.add(textField_Morada);
		textField_Morada.setColumns(10);
		
		textField_Horario = new JTextField();
		textField_Horario.setEditable(false);
		textField_Horario.setBounds(496, 233, 86, 20);
		contentPane.add(textField_Horario);
		textField_Horario.setColumns(10);

	}
}
