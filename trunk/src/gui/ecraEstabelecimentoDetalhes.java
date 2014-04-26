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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import controlo.CtrlDetalhesEstabelecimento;
import dados.Estabelecimento;

@SuppressWarnings("serial")
public class ecraEstabelecimentoDetalhes extends JFrame {

	private String designacao_estabelecimento = "";
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
	private JTable table_Pratos;
	private JTable table_Eventos;
	private Container contentPane;

	/**
	 * Launch only this GUI (for debug purpose)
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ecraEstabelecimentoDetalhes(-1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Open Establishment Details
	 * 
	 * @param establishmentID
	 */
	public ecraEstabelecimentoDetalhes(int establishmentID) {
		try {
			// Debbug Mode
			if (establishmentID == -1) {
				designacao_estabelecimento = "Estabelecimento de Teste";
				buildGUI();
				setVisible(true);
			} else {

				// controladorDetalherEstabelecimento = new
				// ctrlDetalhesEstabelecimento(
				// establishmentID);
				// estabelecimento = controladorDetalherEstabelecimento
				// .consultarDetalhes();
				designacao_estabelecimento = estabelecimento.getDesignacao();
				// TODO Display Method ;P
				buildGUI();

			}
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
				+ designacao_estabelecimento + "\"");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 523);
		contentPane = getContentPane();
		contentPane.setLayout(null);
		setResizable(false);

		// Builds the remaining of the GUI
		addLabels();
		addTextFields();
		addButtons();
		addEvaluationPanel();
		addEventPanel();
		addDishesPanel();
	}

	/**
	 * Generates the needed JLabels on the graphical interface (those in the
	 * main JPanel)
	 */
	private void addLabels() {
		JLabel lbl_EatDrink = new JLabel("Eat and Drink");
		lbl_EatDrink.setBounds(89, 11, 632, 31);
		lbl_EatDrink.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_EatDrink.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lbl_EatDrink);

		JLabel lbl_Estabelecimento = new JLabel("Estabelecimentos");
		lbl_Estabelecimento.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Estabelecimento.setBounds(73, 53, 116, 14);
		contentPane.add(lbl_Estabelecimento);

		JLabel lbl_Tipo = new JLabel("Tipo");
		lbl_Tipo.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Tipo.setBounds(73, 83, 64, 14);
		contentPane.add(lbl_Tipo);

		JLabel lbl_Cidade = new JLabel("Cidade");
		lbl_Cidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Cidade.setBounds(227, 83, 47, 14);
		contentPane.add(lbl_Cidade);

		JLabel lbl_Zona = new JLabel("Zona");
		lbl_Zona.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Zona.setBounds(397, 83, 46, 14);
		contentPane.add(lbl_Zona);

		JLabel lbl_Morada = new JLabel("Morada");
		lbl_Morada.setBounds(73, 113, 55, 14);
		contentPane.add(lbl_Morada);

		JLabel lbl_Horario = new JLabel("Hor\u00E1rio");
		lbl_Horario.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Horario.setBounds(556, 83, 64, 14);
		contentPane.add(lbl_Horario);
	}

	/**
	 * Generates the needed JTextFields on the graphical interface (those in the
	 * main JPanel)
	 */
	private void addTextFields() {
		textField_Estabelecimento = new JTextField();
		textField_Estabelecimento.setEditable(false);
		textField_Estabelecimento.setBounds(177, 50, 544, 20);
		contentPane.add(textField_Estabelecimento);
		textField_Estabelecimento.setColumns(10);

		textField_Tipo = new JTextField();
		textField_Tipo.setEditable(false);
		textField_Tipo.setBounds(109, 80, 91, 20);
		contentPane.add(textField_Tipo);
		textField_Tipo.setColumns(10);

		textField_Cidade = new JTextField();
		textField_Cidade.setEditable(false);
		textField_Cidade.setBounds(284, 80, 91, 20);
		contentPane.add(textField_Cidade);
		textField_Cidade.setColumns(10);

		textField_Zona = new JTextField();
		textField_Zona.setEditable(false);
		textField_Zona.setBounds(453, 80, 91, 20);
		contentPane.add(textField_Zona);
		textField_Zona.setColumns(10);

		textField_Morada = new JTextField();
		textField_Morada.setEditable(false);
		textField_Morada.setBounds(124, 110, 597, 20);
		contentPane.add(textField_Morada);
		textField_Morada.setColumns(10);

		textField_Horario = new JTextField();
		textField_Horario.setEditable(false);
		textField_Horario.setBounds(630, 80, 91, 20);
		contentPane.add(textField_Horario);
		textField_Horario.setColumns(10);
	}

	/**
	 * Generates the needed JButtons on the graphical interface (those in the
	 * main JPanel)
	 */
	private void addButtons() {
		JButton btn_AplicarFiltro = new JButton("Aplicar Filtro");
		btn_AplicarFiltro.setBounds(49, 461, 110, 23);
		contentPane.add(btn_AplicarFiltro);

		JButton btn_Sair = new JButton("Sair");
		btn_Sair.setBounds(645, 461, 110, 23);
		contentPane.add(btn_Sair);
	}

	/**
	 * Generates the Establishment Evaluation JPanel
	 */
	private void addEvaluationPanel() {
		JPanel panel_Avaliacao = new JPanel();
		panel_Avaliacao.setBounds(10, 156, 190, 81);
		panel_Avaliacao.setBorder(BorderFactory
				.createTitledBorder("Avalia\u00E7\u00E3o"));
		getContentPane().add(panel_Avaliacao);
		panel_Avaliacao.setLayout(null);

		JLabel lbl_Global = new JLabel("Global");
		lbl_Global.setBounds(35, 19, 49, 14);
		panel_Avaliacao.add(lbl_Global);
		lbl_Global.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lbl_ASeguir = new JLabel("A Seguir");
		lbl_ASeguir.setBounds(35, 52, 58, 14);
		panel_Avaliacao.add(lbl_ASeguir);
		lbl_ASeguir.setHorizontalAlignment(SwingConstants.LEFT);

		textField_Global = new JTextField();
		textField_Global.setBounds(94, 16, 49, 20);
		panel_Avaliacao.add(textField_Global);
		textField_Global.setColumns(10);

		textField_ASeguir = new JTextField();
		textField_ASeguir.setBounds(94, 49, 49, 20);
		panel_Avaliacao.add(textField_ASeguir);
		textField_ASeguir.setColumns(10);
	}

	/**
	 * Generates the Events Panel
	 */
	private void addEventPanel() {
		JPanel panel_Eventos = new JPanel();
		panel_Eventos.setBounds(10, 248, 190, 202);
		panel_Eventos.setBorder(BorderFactory.createTitledBorder("Eventos"));
		getContentPane().add(panel_Eventos);
		panel_Eventos.setLayout(null);

		JScrollPane scrollPane_Eventos = new JScrollPane();
		scrollPane_Eventos
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_Eventos.setBounds(10, 19, 170, 172);
		panel_Eventos.add(scrollPane_Eventos);

		table_Eventos = new JTable();
		table_Eventos.setModel(new DefaultTableModel(new Object[][] {
				{ "asdsa", null }, { null, "asdsa" }, { "asdsa", null }, },
				new String[] { "New column", "New column" }));
		table_Eventos.getColumnModel().getColumn(1).setResizable(false);
		scrollPane_Eventos.setViewportView(table_Eventos);
	}

	/**
	 * Generates the Dishes (menus) list
	 */
	private void addDishesPanel() {
		JPanel panel_Pratos = new JPanel();
		panel_Pratos.setBounds(204, 156, 596, 294);
		panel_Pratos.setBorder(BorderFactory.createTitledBorder("Pratos"));
		contentPane.add(panel_Pratos);
		panel_Pratos.setLayout(null);

		// Labels Constructors
		JLabel lbl_Nome = new JLabel("Nome");
		lbl_Nome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Nome.setBounds(349, 22, 65, 14);
		panel_Pratos.add(lbl_Nome);

		JLabel lbl_Descricao = new JLabel("Descri\u00E7\u00E3o");
		lbl_Descricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Descricao.setBounds(349, 55, 65, 14);
		panel_Pratos.add(lbl_Descricao);

		JLabel lbl_Preco = new JLabel("Pre\u00E7o");
		lbl_Preco.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Preco.setBounds(349, 232, 65, 14);
		panel_Pratos.add(lbl_Preco);

		JLabel lbl_Euro = new JLabel("\u20AC");
		lbl_Euro.setBounds(509, 232, 65, 14);
		panel_Pratos.add(lbl_Euro);

		// Text Fields and Area Constructors
		textField_Nome_Prato = new JTextField();
		textField_Nome_Prato.setBounds(418, 19, 168, 20);
		panel_Pratos.add(textField_Nome_Prato);
		textField_Nome_Prato.setColumns(10);

		textField_Preco = new JTextField();
		textField_Preco.setBounds(418, 229, 81, 20);
		panel_Pratos.add(textField_Preco);
		textField_Preco.setColumns(10);

		TextArea textArea_Descricao = new TextArea("", 20, 10,
				TextArea.SCROLLBARS_VERTICAL_ONLY);
		textArea_Descricao.setBounds(418, 52, 168, 165);
		panel_Pratos.add(textArea_Descricao);

		// Buttons Constructors
		JButton btnFotografias = new JButton("Fotografias");
		btnFotografias.setBounds(134, 260, 110, 23);
		panel_Pratos.add(btnFotografias);

		JButton btn_Comentarios_Prato = new JButton("Coment\u00E1rios");
		btn_Comentarios_Prato.setBounds(10, 260, 110, 23);
		panel_Pratos.add(btn_Comentarios_Prato);

		JButton btn_Adicionar = new JButton("Adicionar");
		btn_Adicionar.setBounds(441, 260, 110, 23);
		panel_Pratos.add(btn_Adicionar);

		// Main JTable Constructors (including JScrollPane)
		JScrollPane scrollPane_Pratos = new JScrollPane();
		scrollPane_Pratos
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_Pratos.setBounds(10, 19, 343, 230);
		panel_Pratos.add(scrollPane_Pratos);

		table_Pratos = new JTable();
		table_Pratos.setEnabled(false);
		table_Pratos.setFillsViewportHeight(true);
		table_Pratos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_Pratos.setViewportView(table_Pratos);
		table_Pratos.setModel(new DefaultTableModel(new Object[][] {
				{ "xzcx", null, "zxcxz", null },
				{ null, "zxczxc", null, null }, }, new String[] { "New column",
				"New column", "New column", "New column" }));
	}

	/**
	 * Display method
	 */
	public void display() {
		setVisible(true);
	}
}
