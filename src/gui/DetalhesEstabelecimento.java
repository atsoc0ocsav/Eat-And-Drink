package gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controlo.CtrlDetalhesEstabelecimento;
import dados.Estabelecimento;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class DetalhesEstabelecimento extends JFrame {

	private static final long serialVersionUID = -4354922814029833393L;
	private CtrlDetalhesEstabelecimento controladorDetalherEstabelecimento;
	private Estabelecimento estabelecimento;
	private JTextField textField_Estabelecimento;
	private JTextField textField_Tipo;
	private JTextField textField_Cidade;
	private JTextField textField_Zona;
	private JTextField textField_Morada;
	private JTextField textField_Horario;
	private JTextField textField_Nome_Prato;
	private JTextField textField_Preco;
	private JTextField textField_Global;
	private JTextField textField_ASeguir;
	private JTable table;

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
		setTitle("Eat & Drink Estabelecimentos - Estabelecimento \""
				+ estabelecimento.getDesignacao() + "\"");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 523);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		setResizable(false);

		// Labels Constructors
		JLabel lbl_EatDrink = new JLabel("Eat and Drink");
		lbl_EatDrink.setBounds(89, 11, 632, 31);
		lbl_EatDrink.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_EatDrink.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lbl_EatDrink);

		JLabel lbl_Estabelecimento = new JLabel("ESTABELECIMENTOS");
		lbl_Estabelecimento.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Estabelecimento.setBounds(73, 53, 116, 14);
		contentPane.add(lbl_Estabelecimento);

		JLabel lbl_Tipo = new JLabel("TIPO");
		lbl_Tipo.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Tipo.setBounds(73, 83, 64, 14);
		contentPane.add(lbl_Tipo);

		JLabel lbl_Cidade = new JLabel("CIDADE");
		lbl_Cidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Cidade.setBounds(232, 83, 47, 14);
		contentPane.add(lbl_Cidade);

		JLabel lbl_Zona = new JLabel("ZONA");
		lbl_Zona.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Zona.setBounds(402, 83, 46, 14);
		contentPane.add(lbl_Zona);

		JLabel lbl_Morada = new JLabel("MORADA");
		lbl_Morada.setBounds(73, 113, 55, 14);
		contentPane.add(lbl_Morada);

		JLabel lbl_Horario = new JLabel("HOR\u00C1RIO");
		lbl_Horario.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Horario.setBounds(561, 83, 64, 14);
		contentPane.add(lbl_Horario);

		// Text Fields Constructors
		textField_Estabelecimento = new JTextField();
		textField_Estabelecimento.setEditable(false);
		textField_Estabelecimento.setBounds(199, 50, 522, 20);
		contentPane.add(textField_Estabelecimento);
		textField_Estabelecimento.setColumns(10);

		textField_Tipo = new JTextField();
		textField_Tipo.setEditable(false);
		textField_Tipo.setBounds(114, 80, 86, 20);
		contentPane.add(textField_Tipo);
		textField_Tipo.setColumns(10);

		textField_Cidade = new JTextField();
		textField_Cidade.setEditable(false);
		textField_Cidade.setBounds(289, 80, 86, 20);
		contentPane.add(textField_Cidade);
		textField_Cidade.setColumns(10);

		textField_Zona = new JTextField();
		textField_Zona.setEditable(false);
		textField_Zona.setBounds(458, 80, 86, 20);
		contentPane.add(textField_Zona);
		textField_Zona.setColumns(10);

		textField_Morada = new JTextField();
		textField_Morada.setEditable(false);
		textField_Morada.setBounds(139, 110, 582, 20);
		contentPane.add(textField_Morada);
		textField_Morada.setColumns(10);

		textField_Horario = new JTextField();
		textField_Horario.setEditable(false);
		textField_Horario.setBounds(635, 80, 86, 20);
		contentPane.add(textField_Horario);
		textField_Horario.setColumns(10);

		JButton btn_AplicarFiltro = new JButton("Aplicar Filtro");
		btn_AplicarFiltro.setBounds(49, 450, 110, 23);
		contentPane.add(btn_AplicarFiltro);

		// Buttons Constructors
		JButton btn_Sair = new JButton("Sair");
		btn_Sair.setBounds(645, 450, 110, 23);
		contentPane.add(btn_Sair);

		// Menus Panel
		JPanel panel_Pratos = new JPanel();
		panel_Pratos.setBounds(204, 156, 596, 272);
		panel_Pratos.setBorder(BorderFactory.createTitledBorder("Pratos"));
		contentPane.add(panel_Pratos);
		panel_Pratos.setLayout(null);

		JButton btnFotografias = new JButton("Fotografias");
		btnFotografias.setBounds(134, 238, 110, 23);
		panel_Pratos.add(btnFotografias);

		JButton btn_Comentarios_Prato = new JButton("Coment\u00E1rios");
		btn_Comentarios_Prato.setBounds(19, 238, 110, 23);
		panel_Pratos.add(btn_Comentarios_Prato);

		JButton btn_Adicionar = new JButton("Adicionar");
		btn_Adicionar.setBounds(441, 238, 110, 23);
		panel_Pratos.add(btn_Adicionar);

		JLabel lbl_Nome = new JLabel("NOME");
		lbl_Nome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Nome.setBounds(349, 22, 74, 14);
		panel_Pratos.add(lbl_Nome);

		JLabel lbl_Descricao = new JLabel("DESCRI\u00C7\u00C3O");
		lbl_Descricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Descricao.setBounds(349, 55, 74, 14);
		panel_Pratos.add(lbl_Descricao);

		JLabel lbl_Preco = new JLabel("PRE\u00C7O");
		lbl_Preco.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Preco.setBounds(349, 208, 74, 14);
		panel_Pratos.add(lbl_Preco);

		JLabel lbl_Euro = new JLabel("\u20AC");
		lbl_Euro.setBounds(509, 208, 65, 14);
		panel_Pratos.add(lbl_Euro);

		textField_Nome_Prato = new JTextField();
		textField_Nome_Prato.setBounds(429, 19, 157, 20);
		panel_Pratos.add(textField_Nome_Prato);
		textField_Nome_Prato.setColumns(10);

		textField_Preco = new JTextField();
		textField_Preco.setBounds(429, 205, 70, 20);
		panel_Pratos.add(textField_Preco);
		textField_Preco.setColumns(10);

		TextArea textArea_Descricao = new TextArea("", 20, 10,
				TextArea.SCROLLBARS_VERTICAL_ONLY);
		textArea_Descricao.setBounds(429, 52, 157, 140);
		panel_Pratos.add(textArea_Descricao);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"xzcx", null, "zxcxz", null},
				{null, "zxczxc", null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table.setBounds(19, 22, 327, 204);
		panel_Pratos.add(table);

		// Evaluation Panel
		JPanel panel_Avaliacao = new JPanel();
		panel_Avaliacao.setBounds(10, 156, 190, 81);
		panel_Avaliacao.setBorder(BorderFactory
				.createTitledBorder("Avalia\u00E7\u00E3o"));
		getContentPane().add(panel_Avaliacao);
		panel_Avaliacao.setLayout(null);

		JLabel lbl_Global = new JLabel("GLOBAL");
		lbl_Global.setBounds(35, 22, 49, 14);
		panel_Avaliacao.add(lbl_Global);
		lbl_Global.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lbl_ASeguir = new JLabel("A SEGUIR");
		lbl_ASeguir.setBounds(35, 55, 58, 14);
		panel_Avaliacao.add(lbl_ASeguir);
		lbl_ASeguir.setHorizontalAlignment(SwingConstants.LEFT);

		textField_Global = new JTextField();
		textField_Global.setBounds(94, 19, 42, 20);
		panel_Avaliacao.add(textField_Global);
		textField_Global.setColumns(10);

		textField_ASeguir = new JTextField();
		textField_ASeguir.setBounds(94, 52, 42, 20);
		panel_Avaliacao.add(textField_ASeguir);
		textField_ASeguir.setColumns(10);

		// Month Panel
		JPanel panel_Mes = new JPanel();
		panel_Mes.setBounds(10, 248, 190, 180);
		panel_Mes.setBorder(BorderFactory.createTitledBorder("M\u00EAs"));
		getContentPane().add(panel_Mes);
		panel_Mes.setLayout(null);
		
				JLabel lbl_Mes = new JLabel("M\u00CAS");
				lbl_Mes.setBounds(35, 22, 64, 14);
				panel_Mes.add(lbl_Mes);
				
						JComboBox comboBox_Mes = new JComboBox();
						comboBox_Mes.setBounds(96, 19, 84, 20);
						panel_Mes.add(comboBox_Mes);

	}
}
