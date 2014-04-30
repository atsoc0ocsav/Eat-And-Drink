package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import controlo.ctrlConsultarEstabelecimentos;
import dados.Cidade;
import dados.Estabelecimento;
import dados.TipoDeEstablecimento;
import dados.TipoDeEvento;
import dados.TipoDePrato;
import dados.Zona;

public class ecraConsultarEstabelecimentos extends JFrame {

	private static final long serialVersionUID = 4212115798640187383L;
	private JPanel contentPane;
	private JPanel panelFiltros;
	private JComboBox<String> comboBoxCidades;
	private JComboBox<String> comboBoxZonas;
	private JComboBox<String> comboBoxTiposDeEstablecimento;
	private JComboBox<String> comboBoxTiposDePrato;
	private JComboBox<String> comboBoxTiposDeEvento;
	private JPanel panelTipoEventos;
	private JPanel panel_TipoEventosBotoes;
	private JScrollPane scrollPaneEventos;
	private JPanel panel_InScrollPaneEventos;
	private JPanel panelTipoPratos;
	private JPanel panel_TipoPratosBotoes;
	private JScrollPane scrollPanePratos;
	private JPanel panel_InScrollPanelPratos;
	private TextField textField_avaliacao;
	private TextField textField_nome;
	private JPanel panel_resultadoPesquisa;
	private JScrollPane scrollPaneResultadosPesquisa;
	private Button buttonFiltroTodos;
	private Button buttonAplicarFiltro;

	private consultaEstablecimentosTableDataModel modeloTabelaConsulta = new consultaEstablecimentosTableDataModel();
	private JTable table;

	private ctrlConsultarEstabelecimentos ctrConsulta;

	private JList<String> listTipoDeEventos;
	private ListaDeTiposDataModel modeloListaDeEventos = new ListaDeTiposDataModel();
	private JList<String> listTipoDePratos;
	private ListaDeTiposDataModel modeloListaDePratos = new ListaDeTiposDataModel();

	private ArrayList<Cidade> cidades;
	private ArrayList<Zona> zonas;
	private ArrayList<TipoDeEstablecimento> tiposdeEstablecimento;
	private ArrayList<TipoDeEvento> tiposDeEvento;
	private ArrayList<TipoDePrato> tiposDePrato;

	private ArrayList<Estabelecimento> estabelecimentos;

	private Button buttonAddPratos;
	private Button buttonRemoverPratos;
	private Button buttonAddEventos;
	private Button buttonRemoveEventos;
	private JButton buttonVerDetalhes;

	public ecraConsultarEstabelecimentos() {
		ctrConsulta = new ctrlConsultarEstabelecimentos();

		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.out
					.println("Not able to set LookAndFeel for the current OS");
			e.printStackTrace();
		}

		setTitle("Eat & Drink Estabelecimentos - Consulta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 35, 930, 707);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEatDrink = DefaultComponentFactory.getInstance().createTitle(
				"Eat and Drink");
		lblEatDrink.setBounds(262, 11, 400, 31);
		lblEatDrink.setHorizontalAlignment(SwingConstants.CENTER);
		lblEatDrink.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblEatDrink);

		panelFiltros = new JPanel();
		panelFiltros.setBounds(10, 42, 904, 265);
		panelFiltros.setBorder(BorderFactory
				.createTitledBorder("Filtros de Pesquisa"));
		contentPane.add(panelFiltros);
		panelFiltros.setLayout(null);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCidade.setBounds(10, 21, 79, 23);
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFiltros.add(lblCidade);

		comboBoxCidades = new JComboBox<String>();
		comboBoxCidades.setBounds(99, 21, 135, 23);
		panelFiltros.add(comboBoxCidades);

		JLabel lblZonaFiltro = new JLabel("Zona");
		lblZonaFiltro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblZonaFiltro.setBounds(10, 61, 79, 23);
		lblZonaFiltro.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFiltros.add(lblZonaFiltro);

		comboBoxZonas = new JComboBox<String>();
		comboBoxZonas.setBounds(99, 61, 135, 23);
		panelFiltros.add(comboBoxZonas);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipo.setBounds(10, 101, 79, 23);
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		panelFiltros.add(lblTipo);

		comboBoxTiposDeEstablecimento = new JComboBox<String>();
		comboBoxTiposDeEstablecimento.setBounds(99, 101, 135, 23);
		panelFiltros.add(comboBoxTiposDeEstablecimento);

		JLabel label_avaliacao = new JLabel("Avalia\u00E7\u00E3o >=");
		label_avaliacao.setHorizontalAlignment(SwingConstants.RIGHT);
		label_avaliacao.setBounds(10, 181, 79, 22);
		panelFiltros.add(label_avaliacao);
		label_avaliacao.setFont(new Font("Tahoma", Font.BOLD, 11));

		textField_avaliacao = new TextField();
		textField_avaliacao.setBounds(99, 181, 45, 22);
		panelFiltros.add(textField_avaliacao);
		textField_avaliacao.setText("00.0");

		textField_nome = new TextField();
		textField_nome.setBounds(99, 141, 135, 22);
		panelFiltros.add(textField_nome);

		JLabel label_nome = new JLabel("Nome");
		label_nome.setHorizontalAlignment(SwingConstants.RIGHT);
		label_nome.setBounds(10, 141, 79, 22);
		panelFiltros.add(label_nome);
		label_nome.setFont(new Font("Tahoma", Font.BOLD, 11));

		panelTipoEventos = new JPanel();
		panelTipoEventos.setBounds(244, 15, 326, 240);
		panelFiltros.add(panelTipoEventos);
		panelTipoEventos.setBorder(BorderFactory
				.createTitledBorder("Tipo de Eventos"));
		panelTipoEventos.setLayout(new BorderLayout(0, 0));

		panel_TipoEventosBotoes = new JPanel();
		panelTipoEventos.add(panel_TipoEventosBotoes, BorderLayout.SOUTH);
		panel_TipoEventosBotoes.setLayout(new GridLayout(0, 3, 0, 0));

		comboBoxTiposDeEvento = new JComboBox<String>();
		comboBoxTiposDeEvento.setToolTipText("Tipo de Eventos");
		panel_TipoEventosBotoes.add(comboBoxTiposDeEvento);

		buttonAddEventos = new Button("Adicionar");
		buttonAddEventos.setEnabled(false);
		panel_TipoEventosBotoes.add(buttonAddEventos);

		buttonRemoveEventos = new Button("Remover");
		buttonRemoveEventos.setEnabled(false);
		panel_TipoEventosBotoes.add(buttonRemoveEventos);

		scrollPaneEventos = new JScrollPane();
		scrollPaneEventos.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		panelTipoEventos.add(scrollPaneEventos, BorderLayout.CENTER);

		panel_InScrollPaneEventos = new JPanel();
		scrollPaneEventos.setViewportView(panel_InScrollPaneEventos);
		panel_InScrollPaneEventos.setLayout(new BorderLayout(0, 0));

		listTipoDeEventos = new JList<String>(modeloListaDeEventos);
		listTipoDeEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_InScrollPaneEventos.add(listTipoDeEventos);

		buttonAplicarFiltro = new Button("Aplicar Filtro");
		buttonAplicarFiltro.setBounds(41, 222, 91, 22);
		panelFiltros.add(buttonAplicarFiltro);

		buttonFiltroTodos = new Button("Todos");
		buttonFiltroTodos.setBounds(154, 222, 60, 22);
		panelFiltros.add(buttonFiltroTodos);

		panelTipoPratos = new JPanel();
		panelTipoPratos.setBounds(568, 15, 326, 240);
		panelFiltros.add(panelTipoPratos);
		panelTipoPratos.setBorder(BorderFactory
				.createTitledBorder("Tipo de Pratos"));
		panelTipoPratos.setLayout(new BorderLayout(0, 0));

		panel_TipoPratosBotoes = new JPanel();
		panelTipoPratos.add(panel_TipoPratosBotoes, BorderLayout.SOUTH);
		panel_TipoPratosBotoes.setLayout(new GridLayout(0, 3, 0, 0));

		comboBoxTiposDePrato = new JComboBox<String>();
		panel_TipoPratosBotoes.add(comboBoxTiposDePrato);

		buttonAddPratos = new Button("Adicionar");
		buttonAddPratos.setEnabled(false);
		panel_TipoPratosBotoes.add(buttonAddPratos);

		buttonRemoverPratos = new Button("Remover");
		buttonRemoverPratos.setEnabled(false);
		panel_TipoPratosBotoes.add(buttonRemoverPratos);

		scrollPanePratos = new JScrollPane();
		scrollPanePratos.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		panelTipoPratos.add(scrollPanePratos, BorderLayout.CENTER);

		panel_InScrollPanelPratos = new JPanel();
		scrollPanePratos.setViewportView(panel_InScrollPanelPratos);
		panel_InScrollPanelPratos.setLayout(new BorderLayout(0, 0));

		listTipoDePratos = new JList<String>(modeloListaDePratos);
		listTipoDePratos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_InScrollPanelPratos.add(listTipoDePratos, BorderLayout.CENTER);

		panel_resultadoPesquisa = new JPanel();
		panel_resultadoPesquisa.setBounds(10, 310, 904, 358);
		panel_resultadoPesquisa.setBorder(BorderFactory
				.createTitledBorder("Resultados da Pesquisa"));
		contentPane.add(panel_resultadoPesquisa);
		panel_resultadoPesquisa.setLayout(null);

		buttonVerDetalhes = new JButton("Ver Detalhes");
		buttonVerDetalhes.setBounds(764, 324, 108, 23);
		panel_resultadoPesquisa.add(buttonVerDetalhes);

		scrollPaneResultadosPesquisa = new JScrollPane();
		scrollPaneResultadosPesquisa.setViewportBorder(new LineBorder(
				new Color(0, 0, 0)));
		scrollPaneResultadosPesquisa.setBounds(10, 17, 884, 300);
		panel_resultadoPesquisa.add(scrollPaneResultadosPesquisa);

		table = new JTable(modeloTabelaConsulta);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneResultadosPesquisa.setViewportView(table);

		addListeners();

		fillComboBoxesWithData();

		this.setResizable(false);
		this.setVisible(true);

	}

	private void fillComboBoxesWithData() {
		this.cidades = ctrConsulta.getCidades();

		comboBoxCidades.insertItemAt(" ", 0);
		for (Cidade cidade : cidades) {
			comboBoxCidades.insertItemAt(cidade.getName(),
					comboBoxCidades.getItemCount());
		}

		this.zonas = ctrConsulta.getZonas();

		comboBoxZonas.insertItemAt(" ", 0);
		for (Zona zona : zonas) {
			comboBoxZonas.insertItemAt(zona.getDesignacao(),
					comboBoxZonas.getItemCount());
		}

		this.tiposdeEstablecimento = ctrConsulta.getTiposDeEstablecimento();

		comboBoxTiposDeEstablecimento.insertItemAt(" ", 0);
		for (TipoDeEstablecimento tipoEsblecimento : tiposdeEstablecimento) {
			comboBoxTiposDeEstablecimento.insertItemAt(
					tipoEsblecimento.getTipoDeEstablecimento(),
					comboBoxTiposDeEstablecimento.getItemCount());
		}

		this.tiposDeEvento = ctrConsulta.getTiposDeEventos();

		if (tiposDeEvento.size() > 0) {
			for (TipoDeEvento tipoDeEvento : tiposDeEvento) {
				comboBoxTiposDeEvento.insertItemAt(tipoDeEvento.getDescricao(),
						comboBoxTiposDeEvento.getItemCount());
			}
			buttonAddEventos.setEnabled(true);
		}

		this.tiposDePrato = ctrConsulta.getTiposDePratos();

		if (tiposDePrato.size() > 0) {
			for (TipoDePrato tipoDePrato : tiposDePrato) {
				comboBoxTiposDePrato.insertItemAt(tipoDePrato.getDescricao(),
						comboBoxTiposDePrato.getItemCount());
			}
			buttonAddPratos.setEnabled(true);
		}
	}

	private void addListeners() {

		buttonFiltroTodos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				consultarEstabelecimentos(true);
			}
		});

		buttonAplicarFiltro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				consultarEstabelecimentos(false);
			}
		});

		buttonAddEventos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				adicionarEvento();
			}
		});

		buttonRemoveEventos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removerEvento();
			}
		});

		buttonAddPratos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adicionarPrato();
			}
		});

		buttonRemoverPratos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removerPrato();
			}
		});

		comboBoxCidades.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				corresponderZonasACidade();
			}
		});

		buttonVerDetalhes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				verDetalhes();
			}
		});

	}

	private void verDetalhes() {
		if (estabelecimentos != null) {
			if (table.getSelectedRow() != -1) {
				new ecraEstabelecimentoDetalhes(estabelecimentos.get(
						table.getSelectedRow()).getIdEstabelecimento());
			}
		}
	}

	private void corresponderZonasACidade() {
		comboBoxZonas.removeAllItems();
		comboBoxZonas.insertItemAt(" ", 0);
		if (comboBoxCidades.getSelectedIndex() > 0) {
			String sel = (String) comboBoxCidades.getSelectedItem();
			for (Zona zona : zonas) {
				String cidadeZona = zona.getCidade();
				if (cidadeZona.equals(sel))
					comboBoxZonas.insertItemAt(zona.getDesignacao(),
							comboBoxZonas.getItemCount());
			}
		} else {
			for (Zona zona : zonas) {
				comboBoxZonas.insertItemAt(zona.getDesignacao(),
						comboBoxZonas.getItemCount());
			}
		}
	}

	private void removerPrato() {
		int i = listTipoDePratos.getSelectedIndex();
		if (i != -1) {
			comboBoxTiposDePrato.insertItemAt(
					listTipoDePratos.getSelectedValue(),
					comboBoxTiposDePrato.getItemCount());
			modeloListaDePratos.remove(i);
			if (modeloListaDePratos.getSize() == 0) {
				buttonRemoverPratos.setEnabled(false);
			}
		}

	}

	private void adicionarPrato() {
		int i = comboBoxTiposDePrato.getSelectedIndex();
		if (i != -1) {
			modeloListaDePratos.add((String) comboBoxTiposDePrato
					.getSelectedItem());
			comboBoxTiposDePrato.removeItemAt(i);;
			buttonRemoverPratos.setEnabled(true);
		}
	}

	private void removerEvento() {
		int i = listTipoDeEventos.getSelectedIndex();
		if (i != -1) {
			comboBoxTiposDeEvento.insertItemAt(
					listTipoDePratos.getSelectedValue(),
					comboBoxTiposDeEvento.getItemCount());
			modeloListaDeEventos.remove(i);
			if (modeloListaDeEventos.getSize() == 0) {
				buttonRemoveEventos.setEnabled(false);
			}
		}
	}

	private void adicionarEvento() {
		int i = comboBoxTiposDeEvento.getSelectedIndex();
		if (i != -1) {
			modeloListaDeEventos.add((String) comboBoxTiposDeEvento
					.getSelectedItem());
			comboBoxTiposDeEvento.removeItemAt(i);
			buttonRemoveEventos.setEnabled(true);
		}
	}

	private void consultarEstabelecimentos(boolean todos) {
		ArrayList<Estabelecimento> estabelecimentos = null;

		if (todos) {
			estabelecimentos = ctrConsulta.consultarEstabelecimentos("", "",
					"", 0.0, "", "", "");
			this.estabelecimentos = estabelecimentos;
			showConsultResult(estabelecimentos);

		} else {
			if (validaAval()) {
				String cidade = "";
				if (comboBoxCidades.getSelectedIndex() > 0) {
					cidade = (String) comboBoxCidades.getSelectedItem();
				}
				String zona = "";
				if (comboBoxZonas.getSelectedIndex() > 0) {
					zona = (String) comboBoxZonas.getSelectedItem();
				}

				String tipoDeEstablecimento = "";
				if (comboBoxTiposDeEstablecimento.getSelectedIndex() > 0) {
					tipoDeEstablecimento = (String) comboBoxTiposDeEstablecimento
							.getSelectedItem();
				}

				double aval = 0.0;
				if (!textField_avaliacao.getText().equals("")) {
					aval = Double.parseDouble(textField_avaliacao.getText());
				}
				String eventos = juntarEventos();
				String pratos = juntarpratos();
				String nome = textField_nome.getText();
				estabelecimentos = ctrConsulta.consultarEstabelecimentos(
						cidade, zona, tipoDeEstablecimento, aval, pratos,
						eventos, nome);

				this.estabelecimentos = estabelecimentos;
				showConsultResult(estabelecimentos);
			} else {
				JOptionPane
						.showMessageDialog(
								this,
								"O campo avaliação só pode estar preenchido com números de 0 a 100 com uma casa decimal opcional");
			}
		}
	}

	private boolean validaAval() {
		String textoAval = textField_avaliacao.getText();
		if (!textoAval.equals("")) {
			if (textoAval.matches("[0-9]{0,3}|[0-9]{0,3}.[0-9]{0,2}")) {
				Double aval = Double.parseDouble(textoAval);
				if (aval <= 100 && aval >= 0)
					return true;
				else
					return false;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	private String juntarpratos() {
		String pratos = "";

		for (int i = 0; i < modeloListaDePratos.getSize(); i++) {
			pratos += modeloListaDePratos.getElementAt(i);
			if (i + 1 < modeloListaDePratos.getSize())
				pratos += ";";
		}

		return pratos;
	}

	private String juntarEventos() {
		String eventos = "";

		for (int i = 0; i < modeloListaDeEventos.getSize(); i++) {
			eventos += modeloListaDeEventos.getElementAt(i);
			if (i + 1 < modeloListaDeEventos.getSize())
				eventos += ";";
		}
		return eventos;
	}

	private void showConsultResult(ArrayList<Estabelecimento> estabelecimentos) {
		Object[][] resultado = new Object[estabelecimentos.size()][3];

		for (int i = 0; i < estabelecimentos.size(); i++) {
			Estabelecimento x = estabelecimentos.get(i);
			resultado[i][0] = x.getDesignacao();
			resultado[i][1] = idZoneToDesignacao(x.getIdZona());
			resultado[i][2] = x.getRating();
		}

		modeloTabelaConsulta.changeData(resultado);
		modeloTabelaConsulta.fireTableDataChanged();
	}

	private Object idZoneToDesignacao(int idZona) {
		for (Zona zona : zonas) {
			if (zona.getIdZona() == idZona) {
				return zona.getDesignacao();
			}
		}
		return "-";
	}
}
