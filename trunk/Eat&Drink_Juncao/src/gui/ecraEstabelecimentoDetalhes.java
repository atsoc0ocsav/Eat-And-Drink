package gui;

import interfaceClasses.FotografiasEComentarios;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.table.DefaultTableCellRenderer;

import controlo.ctrlDetalhesEstabelecimento;
import dados.Estabelecimento;
import dados.Evento;
import dados.Prato;
import dados.TipoDePrato;

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
	private JTextField textField_Preco;
	private JTextField textField_Rating;
	private TextArea textArea_Descricao;
	private JTable table_Pratos;
	private JTable table_Eventos;
	private Container contentPane;
	private JComboBox<String> comboBox_TipoEvento;

	// Data Variables
	private ctrlDetalhesEstabelecimento ctrDetalhesEstabelecimento;
	private MealsTableModel mealsTableModel = new MealsTableModel();
	private EventTableModel eventsTableModel = new EventTableModel();
	private ArrayList<TipoDePrato> tiposDePrato = new ArrayList<TipoDePrato>();
	private ArrayList<Prato> pratos = new ArrayList<>();
	private FotografiasEComentarios moduloFotosEComentarios = new FotografiasEComentarios();

	
	/**
	 * Launch only this GUI (for debug purpose)
	 * 
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ecraEstabelecimentoDetalhes(5);
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
		// Debbug Mode
		if (establishmentID == -1) {
			designacao_estabelecimento = "Estabelecimento de Teste";
			buildGUI();
		} else {
			ctrDetalhesEstabelecimento = new ctrlDetalhesEstabelecimento();
			e = ctrDetalhesEstabelecimento
					.consultarDetalhesEstabelecimento(establishmentID);
			designacao_estabelecimento = e.getDesignacao();

			buildGUI();
			addDataToGUI();
		}
		display();
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
	private void buildGUI() {
		// Basic Setup of the Interface
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.out
					.println("Not able to set LookAndFeel for the current OS");
			e.printStackTrace();
		}

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
		addEventPanel();
		addMealsPanel();
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

		JLabel lbl_Rating = new JLabel("Rating");
		lbl_Rating.setBounds(459, 53, 49, 14);
		frame.getContentPane().add(lbl_Rating);
		lbl_Rating.setHorizontalAlignment(SwingConstants.RIGHT);

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
		textField_Estabelecimento.setBounds(177, 50, 272, 20);
		contentPane.add(textField_Estabelecimento);
		textField_Estabelecimento.setColumns(10);

		textField_Rating = new JTextField();
		textField_Rating.setBounds(518, 50, 49, 20);
		frame.getContentPane().add(textField_Rating);
		textField_Rating.setEditable(false);
		textField_Rating.setColumns(10);

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
	 * Generates the Events Panel
	 */
	private void addEventPanel() {
		JPanel panel_Eventos = new JPanel();
		panel_Eventos.setBounds(10, 156, 259, 370);
		panel_Eventos.setBorder(BorderFactory.createTitledBorder("Eventos"));
		contentPane.add(panel_Eventos);
		panel_Eventos.setLayout(null);

		JScrollPane scrollPane_Eventos = new JScrollPane();
		scrollPane_Eventos
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_Eventos.setBounds(10, 19, 239, 340);
		panel_Eventos.add(scrollPane_Eventos);

		table_Eventos = new JTable(eventsTableModel);
		table_Eventos.setEnabled(false);
		scrollPane_Eventos.setViewportView(table_Eventos);

		Evento event = new Evento();
		ArrayList<Evento> eventos = event.select(e.getIdEstabelecimento());
		showEventos(eventos);
	}

	/**
	 * Event JTable data model
	 * 
	 */
	@SuppressWarnings("serial")
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
	}

	/**
	 * Generates the Dishes (menus) list
	 */
	private void addMealsPanel() {
		JPanel panel_Pratos = new JPanel();
		panel_Pratos.setBounds(267, 156, 647, 370);
		panel_Pratos.setBorder(BorderFactory.createTitledBorder("Pratos"));
		contentPane.add(panel_Pratos);
		panel_Pratos.setLayout(null);

		JLabel lbl_Descricao = new JLabel("Descri\u00E7\u00E3o");
		lbl_Descricao.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Descricao.setBounds(398, 19, 78, 14);
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

		textField_Preco = new JTextField();
		textField_Preco.setBounds(486, 305, 96, 20);
		panel_Pratos.add(textField_Preco);
		textField_Preco.setColumns(10);

		textArea_Descricao = new TextArea("", 20, 10,
				TextArea.SCROLLBARS_VERTICAL_ONLY);
		textArea_Descricao.setBounds(486, 19, 151, 249);
		panel_Pratos.add(textArea_Descricao);

		// Buttons Constructors
		JButton btnFotografias = new JButton("Fotografias");
		btnFotografias.setBounds(133, 336, 110, 23);
		panel_Pratos.add(btnFotografias);
		btnFotografias.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				verFotografias();
			}
		});

		JButton btn_Comentarios_Prato = new JButton("Coment\u00E1rios");
		btn_Comentarios_Prato.setBounds(13, 336, 110, 23);
		panel_Pratos.add(btn_Comentarios_Prato);
		btn_Comentarios_Prato.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				verComentarios();
			}
		});

		JButton btn_Adicionar = new JButton("Adicionar Prato");
		btn_Adicionar.setBounds(496, 336, 131, 23);
		panel_Pratos.add(btn_Adicionar);
		btn_Adicionar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				inserePrato();

			}
		});

		JButton btn_RemoverPrato = new JButton("Remover Prato");
		btn_RemoverPrato.setBounds(253, 336, 131, 23);
		panel_Pratos.add(btn_RemoverPrato);
		btn_RemoverPrato.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				removePrato();
			}
		});

		comboBox_TipoEvento = new JComboBox<String>();
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
		pratos = p.select(e.getIdEstabelecimento());
		showPratos();
	}

	/**
	 * Dishes JTable data model
	 * 
	 */
	@SuppressWarnings("serial")
	public class MealsTableModel extends AbstractTableModel {
		private String[] columnNames = { "Descri\u00E7ão", "Pre\u00E7o",
				"Tipo de Prato" };
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
		textField_Rating.setText(e.getRating() + "");

		tiposDePrato = new TipoDePrato().getTiposDePrato();
		if (tiposDePrato.size() > 0)
			for (TipoDePrato tt : tiposDePrato) {
				comboBox_TipoEvento.insertItemAt(tt.getDescricao(),
						comboBox_TipoEvento.getItemCount());
			}
	}

	private void showPratos() {
		Object[][] resultado = new Object[pratos.size()][3];
		ArrayList<TipoDePrato> tiposDePrato = new TipoDePrato()
				.getTiposDePrato();

		for (int i = 0; i < pratos.size(); i++) {
			Prato x = pratos.get(i);
			resultado[i][0] = x.getDescricao();
			resultado[i][1] = String.format("%.2f", x.getPreco()) + "€";

			for (TipoDePrato t : tiposDePrato)
				if (t.getTipoDePrato() == x.getTipoDePrato()) {
					resultado[i][2] = t.getDescricao();
					break;
				}

		}

		mealsTableModel.changeData(resultado);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table_Pratos.getColumnModel().getColumn(1)
				.setCellRenderer(rightRenderer);

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

	private void verComentarios() {
		if (pratos != null) {
			if (table_Pratos.getSelectedRow() != -1) {
				moduloFotosEComentarios.showComentarios(pratos.get(
						table_Pratos.getSelectedRow()).getIdPrato());
			} else {
				JOptionPane
						.showMessageDialog(null, "Nenhum prato seleccionado");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum prato na lista");
		}

	}

	private void verFotografias() {
		if (pratos != null) {
			if (table_Pratos.getSelectedRow() != -1) {
				moduloFotosEComentarios.showFotografias(pratos.get(
						table_Pratos.getSelectedRow()).getIdPrato());
			} else {
				JOptionPane
						.showMessageDialog(null, "Nenhum prato seleccionado");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum prato na lista");
		}

	}

	private void inserePrato() {
		String tipoDePrato = (String) comboBox_TipoEvento.getSelectedItem();

		int tipoDePratoTemp = -1;
		for (TipoDePrato t : tiposDePrato) {
			if (t.getDescricao().equals(tipoDePrato)) {
				tipoDePratoTemp = t.getTipoDePrato();
				break;
			}
		}
		if (tipoDePratoTemp == -1) {
			JOptionPane.showMessageDialog(null, "Tipo de Prato não válido.");
			return;
		}

		String descr = textArea_Descricao.getText();
		if (descr.equals("")) {
			JOptionPane.showMessageDialog(null, "Descrição não válida.");
			return;
		}
		String textoAval = textField_Preco.getText();
		double prec = 0.0;
		if (!textoAval.equals("")) {
			if (textoAval.matches("[0-9]{0,3}|[0-9]{0,3}.[0-9]{0,2}")) {
				prec = Double.parseDouble(textoAval);
				if (prec <= 0) {
					JOptionPane.showMessageDialog(null,
							"O preço tem que ser maior que 0");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"O preço inserido não é válido");
				return;
			}
		} else {
			JOptionPane
					.showMessageDialog(null, "O preço inserido não é válido");
			return;
		}

		Prato p = new Prato(prec, 0, descr, tipoDePratoTemp);

		ctrDetalhesEstabelecimento.insere(e.getIdEstabelecimento(), p);

		pratos = p.select(e.getIdEstabelecimento());
		showPratos();

		textArea_Descricao.setText("");
		textField_Preco.setText("");
		comboBox_TipoEvento.setSelectedIndex(-1);
	}

	/**
	 * Removes a meal from the meals list (presented on a JTable)
	 */
	private void removePrato() {
		if (pratos != null) {
			if (table_Pratos.getSelectedRow() != -1) {
				Prato prato = pratos.get(table_Pratos.getSelectedRow());

				String descricao = prato.getDescricao();
				String preco = String.format("%.2f", prato.getPreco()) + "€";
				String tipoDePrato = tiposDePrato.get(prato.getTipoDePrato())
						.getDescricao();

				int resposta = JOptionPane.showConfirmDialog(null,
						"Adicionou este prato?\nDescrição: "+descricao+"\nTipo de Prato: "+tipoDePrato+"\nPreço: "+preco, "Confirmar Remoção de Prato",
						JOptionPane.YES_NO_OPTION);
				
				if (resposta == JOptionPane.YES_OPTION) {
					ctrDetalhesEstabelecimento.removePrato(prato.getIdPrato());
					
					pratos = new Prato().select(e.getIdEstabelecimento());
					showPratos();
				} else {
					JOptionPane
					.showMessageDialog(null, "Nenhum prato removido!");
				}
			} else {
				JOptionPane
						.showMessageDialog(null, "Nenhum prato seleccionado");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum prato na lista");
		}
	}
}
