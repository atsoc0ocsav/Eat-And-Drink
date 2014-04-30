package gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import javax.swing.table.AbstractTableModel;

import controlo.ctrlDetalhesEstabelecimento;
import dados.Estabelecimento;
import dados.Evento;
import dados.Prato;
import javax.swing.JComboBox;

public class ecraEstabelecimentoDetalhes {

	// GUI Variables
	private JFrame frame;
	private String designacao_estabelecimento = " ";
	private Estabelecimento e;
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

	// Data Variables
	private ctrlDetalhesEstabelecimento controladorDetalherEstabelecimento;
	private MealsTableModel mealsTableModel = new MealsTableModel();
	private EventTableModel eventsTableModel = new EventTableModel();
	private JComboBox comboBox_TipoEvento;

	/**
	 * Launch only this GUI (for debug purpose)
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ecraEstabelecimentoDetalhes(10);
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
			} else {
				controladorDetalherEstabelecimento = new ctrlDetalhesEstabelecimento();
				e = controladorDetalherEstabelecimento
						.consultarDetalhesEstabelecimento(establishmentID);
				designacao_estabelecimento = e.getDesignacao();

				buildGUI();
				addDataToGUI();
			}
			display();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Build GUI (also the entry class for window builder)
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * @wbp.parser.entryPoint
	 */
	private void buildGUI() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		// Basic Setup of the Interface
		UIManager.setLookAndFeel(UIManager
				.getCrossPlatformLookAndFeelClassName());
		frame = new JFrame();
		frame.setTitle("Eat & Drink Estabelecimentos - Estabelecimento \""
				+ designacao_estabelecimento + "\"");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 930, 599);
		contentPane = frame.getContentPane();
		contentPane.setLayout(null);
		frame.setResizable(false);

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
		lbl_EatDrink.setBounds(145, 11, 632, 31);
		lbl_EatDrink.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_EatDrink.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lbl_EatDrink);

		JLabel lbl_Estabelecimento = new JLabel("Estabelecimento");
		lbl_Estabelecimento.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Estabelecimento.setBounds(73, 53, 116, 14);
		contentPane.add(lbl_Estabelecimento);

		JLabel lbl_Tipo = new JLabel("Tipo");
		lbl_Tipo.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Tipo.setBounds(73, 83, 64, 14);
		contentPane.add(lbl_Tipo);

		JLabel lbl_Cidade = new JLabel("Cidade");
		lbl_Cidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Cidade.setBounds(320, 83, 47, 14);
		contentPane.add(lbl_Cidade);

		JLabel lbl_Zona = new JLabel("Zona");
		lbl_Zona.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Zona.setBounds(581, 83, 46, 14);
		contentPane.add(lbl_Zona);

		JLabel lbl_Morada = new JLabel("Morada");
		lbl_Morada.setBounds(73, 113, 55, 14);
		contentPane.add(lbl_Morada);

		JLabel lbl_Horario = new JLabel("Hor\u00E1rio");
		lbl_Horario.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Horario.setBounds(563, 53, 64, 14);
		contentPane.add(lbl_Horario);
	}

	/**
	 * Generates the needed JTextFields on the graphical interface (those in the
	 * main JPanel)
	 */
	private void addTextFields() {
		textField_Estabelecimento = new JTextField();
		textField_Estabelecimento.setEditable(false);
		textField_Estabelecimento.setBounds(177, 50, 390, 20);
		contentPane.add(textField_Estabelecimento);
		textField_Estabelecimento.setColumns(10);

		textField_Tipo = new JTextField();
		textField_Tipo.setEditable(false);
		textField_Tipo.setBounds(109, 80, 190, 20);
		contentPane.add(textField_Tipo);
		textField_Tipo.setColumns(10);

		textField_Cidade = new JTextField();
		textField_Cidade.setEditable(false);
		textField_Cidade.setBounds(377, 80, 190, 20);
		contentPane.add(textField_Cidade);
		textField_Cidade.setColumns(10);

		textField_Zona = new JTextField();
		textField_Zona.setEditable(false);
		textField_Zona.setBounds(637, 80, 190, 20);
		contentPane.add(textField_Zona);
		textField_Zona.setColumns(10);

		textField_Morada = new JTextField();
		textField_Morada.setEditable(false);
		textField_Morada.setBounds(124, 110, 703, 20);
		contentPane.add(textField_Morada);
		textField_Morada.setColumns(10);

		textField_Horario = new JTextField();
		textField_Horario.setEditable(false);
		textField_Horario.setBounds(637, 50, 190, 20);
		contentPane.add(textField_Horario);
		textField_Horario.setColumns(10);
	}

	/**
	 * Generates the needed JButtons on the graphical interface (those in the
	 * main JPanel)
	 */
	private void addButtons() {

		JButton btn_Sair = new JButton("Sair");
		btn_Sair.setBounds(773, 537, 110, 23);
		contentPane.add(btn_Sair);

		btn_Sair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
	}

	/**
	 * Generates the Establishment Evaluation JPanel
	 */
	private void addEvaluationPanel() {
		JPanel panel_Avaliacao = new JPanel();
		panel_Avaliacao.setBounds(10, 156, 259, 81);
		panel_Avaliacao.setBorder(BorderFactory
				.createTitledBorder("Avalia\u00E7\u00E3o"));
		contentPane.add(panel_Avaliacao);
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
		textField_Global.setEditable(false);
		textField_Global.setBounds(94, 16, 49, 20);
		panel_Avaliacao.add(textField_Global);
		textField_Global.setColumns(10);

		textField_ASeguir = new JTextField();
		textField_ASeguir.setEditable(false);
		textField_ASeguir.setBounds(94, 49, 49, 20);
		panel_Avaliacao.add(textField_ASeguir);
		textField_ASeguir.setColumns(10);
	}

	/**
	 * Generates the Events Panel
	 */
	private void addEventPanel() {
		JPanel panel_Eventos = new JPanel();
		panel_Eventos.setBounds(10, 248, 259, 278);
		panel_Eventos.setBorder(BorderFactory.createTitledBorder("Eventos"));
		contentPane.add(panel_Eventos);
		panel_Eventos.setLayout(null);

		JScrollPane scrollPane_Eventos = new JScrollPane();
		scrollPane_Eventos
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_Eventos.setBounds(10, 19, 239, 248);
		panel_Eventos.add(scrollPane_Eventos);

		table_Eventos = new JTable(eventsTableModel);
		scrollPane_Eventos.setViewportView(table_Eventos);

		Evento event = new Evento();
		ArrayList<Evento> eventos = event.select(e.getIdEstabelecimento());
		showEventos(eventos);
	}

	/**
	 * Event JTable data model
	 * 
	 */
	public class EventTableModel extends AbstractTableModel {
		private String[] columnNames = { "Evento" };
		private Object[][] data = new Object[0][0];

		@Override
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public void changeData(Object[][] data) {
			this.data = data;
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
	}

	/**
	 * Generates the Dishes (menus) list
	 */
	private void addDishesPanel() {
		JPanel panel_Pratos = new JPanel();
		panel_Pratos.setBounds(267, 156, 647, 370);
		panel_Pratos.setBorder(BorderFactory.createTitledBorder("Pratos"));
		contentPane.add(panel_Pratos);
		panel_Pratos.setLayout(null);

		// Labels Constructors
		JLabel lbl_Nome = new JLabel("Nome");
		lbl_Nome.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Nome.setBounds(411, 22, 65, 14);
		panel_Pratos.add(lbl_Nome);

		JLabel lbl_Descricao = new JLabel("Descri\u00E7\u00E3o");
		lbl_Descricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Descricao.setBounds(398, 55, 78, 14);
		panel_Pratos.add(lbl_Descricao);

		JLabel lbl_Tipo = new JLabel("Tipo");
		lbl_Tipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Tipo.setBounds(430, 277, 46, 14);
		panel_Pratos.add(lbl_Tipo);

		JLabel lbl_Preco = new JLabel("Pre\u00E7o");
		lbl_Preco.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Preco.setBounds(411, 308, 65, 14);
		panel_Pratos.add(lbl_Preco);

		JLabel lbl_Euro = new JLabel("\u20AC");
		lbl_Euro.setBounds(592, 308, 65, 14);
		panel_Pratos.add(lbl_Euro);

		// Text Fields and Area Constructors
		textField_Nome_Prato = new JTextField();
		textField_Nome_Prato.setBounds(486, 19, 151, 20);
		panel_Pratos.add(textField_Nome_Prato);
		textField_Nome_Prato.setColumns(10);

		textField_Preco = new JTextField();
		textField_Preco.setBounds(486, 305, 96, 20);
		panel_Pratos.add(textField_Preco);
		textField_Preco.setColumns(10);

		TextArea textArea_Descricao = new TextArea("", 20, 10,
				TextArea.SCROLLBARS_VERTICAL_ONLY);
		textArea_Descricao.setBounds(486, 52, 151, 216);
		panel_Pratos.add(textArea_Descricao);

		// Buttons Constructors
		JButton btnFotografias = new JButton("Fotografias");
		btnFotografias.setBounds(133, 336, 110, 23);
		panel_Pratos.add(btnFotografias);

		JButton btn_Comentarios_Prato = new JButton("Coment\u00E1rios");
		btn_Comentarios_Prato.setBounds(13, 336, 110, 23);
		panel_Pratos.add(btn_Comentarios_Prato);

		JButton btn_Adicionar = new JButton("Adicionar");
		btn_Adicionar.setBounds(506, 336, 110, 23);
		panel_Pratos.add(btn_Adicionar);

		comboBox_TipoEvento = new JComboBox();
		comboBox_TipoEvento.setBounds(486, 274, 151, 20);
		panel_Pratos.add(comboBox_TipoEvento);

		// Main JTable Constructors (including JScrollPane)
		JScrollPane scrollPane_Pratos = new JScrollPane();
		scrollPane_Pratos
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_Pratos.setBounds(10, 19, 404, 306);
		panel_Pratos.add(scrollPane_Pratos);

		table_Pratos = new JTable(mealsTableModel);
		table_Pratos.setFillsViewportHeight(true);
		table_Pratos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_Pratos.setViewportView(table_Pratos);

		Prato p = new Prato();
		ArrayList<Prato> pratos = p.select(e.getIdEstabelecimento());
		showPratos(pratos);
	}

	/**
	 * Dishes JTable data model
	 * 
	 */
	public class MealsTableModel extends AbstractTableModel {
		private String[] columnNames = { "Nome", "Pre\u00E7o" };
		private Object[][] data = new Object[0][0];

		@Override
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}

		public void changeData(Object[][] data) {
			this.data = data;
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
	}

	/**
	 * Display method
	 */
	public void display() {
		frame.setVisible(true);
	}

	/**
	 * Adds the establishment data (i.e., the information in establishment
	 * variable) and puts it on the graphic components
	 */
	private void addDataToGUI() {
		textField_Estabelecimento.setText(e.getDesignacao());
		textField_Tipo.setText(e.getTipoDoEstabelecimento());
		textField_Cidade.setText(e.getCidade());
		textField_Zona.setText(e.getNomeZona());
		textField_Morada.setText(e.getMorada());
		textField_Horario.setText(e.getInformacoesHorario());
		textField_Global.setText(e.getRating() + "");
	}

	private void showPratos(ArrayList<Prato> pratos) {
		Object[][] resultado = new Object[pratos.size()][2];

		for (int i = 0; i < pratos.size(); i++) {
			Prato x = pratos.get(i);
			resultado[i][0] = x.getDescricao();
			resultado[i][1] = x.getPreco();
		}

		mealsTableModel.changeData(resultado);
		mealsTableModel.fireTableDataChanged();
	}

	private void showEventos(ArrayList<Evento> eventos) {
		Object[][] resultado = new Object[eventos.size()][2];

		for (int i = 0; i < eventos.size(); i++) {
			Evento x = eventos.get(i);
			resultado[i][0] = x.getDescricao();
			resultado[i][1] = null;
		}

		eventsTableModel.changeData(resultado);
		eventsTableModel.fireTableDataChanged();
	}
}
