package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
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

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ecraConsultarEstabelecimentos extends JFrame {

	private static final long serialVersionUID = 4212115798640187383L;
	private JPanel contentPane;
	private Panel panelFiltros;
	private JComboBox comboBoxCidades;
	private JComboBox comboBoxZonas;
	private JComboBox comboBoxTiposDeEstablecimento;
	private JComboBox comboBoxTiposDePrato;
	private JComboBox comboBoxTiposDeEvento;
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
	private JPanel panel_resultadosDivisoes;
	private Button buttonFiltroTodos;
	private Button buttonAplicarFiltro;
	private consultaEstablecimentosTableDataModel modeloTabelaConsulta = new consultaEstablecimentosTableDataModel();

	private ctrlConsultarEstabelecimentos ctrConsulta;

	private JList listTipoDeEventos;
	private ListaDeTiposDataModel modeloListaDeEventos = new ListaDeTiposDataModel();
	private JList listTipoDePratos;
	private ListaDeTiposDataModel modeloListaDePratos = new ListaDeTiposDataModel();

	private ArrayList<Cidade> cidades;
	private ArrayList<Zona> zonas;
	private ArrayList<TipoDeEstablecimento> tiposdeEstablecimento;
	private ArrayList<TipoDeEvento> tiposDeEvento;
	private ArrayList<TipoDePrato> tiposDePrato;
	private JTable table;

	public ecraConsultarEstabelecimentos(ctrlConsultarEstabelecimentos consulta) {

		setTitle("Eat & Drink Estabelecimentos - Consulta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 35, 1050, 674);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEatDrink = DefaultComponentFactory.getInstance().createTitle(
				"Eat and Drink");
		lblEatDrink.setBounds(226, 11, 632, 31);
		lblEatDrink.setHorizontalAlignment(SwingConstants.CENTER);
		lblEatDrink.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblEatDrink);

		panelFiltros = new Panel();
		panelFiltros.setBounds(10, 95, 197, 136);
		contentPane.add(panelFiltros);
		panelFiltros.setLayout(null);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCidade.setBounds(10, 0, 87, 23);
		lblCidade.setHorizontalAlignment(SwingConstants.CENTER);
		panelFiltros.add(lblCidade);

		comboBoxCidades = new JComboBox();
		comboBoxCidades.setBounds(87, 0, 100, 23);
		panelFiltros.add(comboBoxCidades);

		JLabel lblZonaFiltro = new JLabel("Zona");
		lblZonaFiltro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblZonaFiltro.setBounds(10, 48, 87, 23);
		lblZonaFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		panelFiltros.add(lblZonaFiltro);

		comboBoxZonas = new JComboBox();
		comboBoxZonas.setBounds(87, 48, 100, 23);
		panelFiltros.add(comboBoxZonas);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipo.setBounds(10, 96, 87, 23);
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		panelFiltros.add(lblTipo);

		comboBoxTiposDeEstablecimento = new JComboBox();
		comboBoxTiposDeEstablecimento.setBounds(87, 96, 100, 23);
		panelFiltros.add(comboBoxTiposDeEstablecimento);

		panelTipoEventos = new JPanel();
		panelTipoEventos.setBounds(32, 445, 480, 160);
		panelTipoEventos.setBorder(BorderFactory
				.createTitledBorder("Tipo de Eventos"));
		contentPane.add(panelTipoEventos);
		panelTipoEventos.setLayout(new BorderLayout(0, 0));

		panel_TipoEventosBotoes = new JPanel();
		panelTipoEventos.add(panel_TipoEventosBotoes, BorderLayout.SOUTH);
		panel_TipoEventosBotoes.setLayout(new GridLayout(0, 3, 0, 0));

		comboBoxTiposDeEvento = new JComboBox();
		comboBoxTiposDeEvento.setToolTipText("Tipo de Eventos");
		panel_TipoEventosBotoes.add(comboBoxTiposDeEvento);

		Button buttonAddEventos = new Button("Adicionar");
		panel_TipoEventosBotoes.add(buttonAddEventos);

		Button buttonRemoveEventos = new Button("Remover");
		panel_TipoEventosBotoes.add(buttonRemoveEventos);

		scrollPaneEventos = new JScrollPane();
		scrollPaneEventos.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		panelTipoEventos.add(scrollPaneEventos, BorderLayout.CENTER);

		panel_InScrollPaneEventos = new JPanel();
		scrollPaneEventos.setViewportView(panel_InScrollPaneEventos);
		panel_InScrollPaneEventos.setLayout(new BorderLayout(0, 0));

		listTipoDeEventos = new JList(modeloListaDeEventos);
		listTipoDeEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_InScrollPaneEventos.add(listTipoDeEventos);

		panelTipoPratos = new JPanel();
		panelTipoPratos.setBounds(523, 445, 480, 160);
		panelTipoPratos.setBorder(BorderFactory
				.createTitledBorder("Tipo de Pratos"));
		contentPane.add(panelTipoEventos);
		contentPane.add(panelTipoPratos);
		panelTipoPratos.setLayout(new BorderLayout(0, 0));

		panel_TipoPratosBotoes = new JPanel();
		panelTipoPratos.add(panel_TipoPratosBotoes, BorderLayout.SOUTH);
		panel_TipoPratosBotoes.setLayout(new GridLayout(0, 3, 0, 0));

		comboBoxTiposDePrato = new JComboBox();
		panel_TipoPratosBotoes.add(comboBoxTiposDePrato);

		Button buttonAddPratos = new Button("Adicionar");
		panel_TipoPratosBotoes.add(buttonAddPratos);

		Button buttonRemoverPratos = new Button("Remover");
		panel_TipoPratosBotoes.add(buttonRemoverPratos);

		scrollPanePratos = new JScrollPane();
		scrollPanePratos.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		panelTipoPratos.add(scrollPanePratos, BorderLayout.CENTER);

		panel_InScrollPanelPratos = new JPanel();
		scrollPanePratos.setViewportView(panel_InScrollPanelPratos);
		panel_InScrollPanelPratos.setLayout(new BorderLayout(0, 0));

		listTipoDePratos = new JList(modeloListaDePratos);
		listTipoDePratos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_InScrollPanelPratos.add(listTipoDePratos, BorderLayout.CENTER);

		buttonAplicarFiltro = new Button("Aplicar Filtro");
		buttonAplicarFiltro.setBounds(32, 367, 91, 22);
		contentPane.add(buttonAplicarFiltro);

		buttonFiltroTodos = new Button("Todos");
		buttonFiltroTodos.setBounds(141, 367, 60, 22);
		contentPane.add(buttonFiltroTodos);

		textField_avaliacao = new TextField();
		textField_avaliacao.setBounds(163, 261, 38, 22);
		contentPane.add(textField_avaliacao);

		Label label_avaliacao = new Label("Avaliação");
		label_avaliacao.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_avaliacao.setBounds(70, 261, 60, 22);
		contentPane.add(label_avaliacao);

		Label label_sinal = new Label(">=");
		label_sinal.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_sinal.setBounds(131, 261, 26, 22);
		contentPane.add(label_sinal);

		textField_nome = new TextField();
		textField_nome.setBounds(121, 314, 80, 22);
		contentPane.add(textField_nome);

		Label label_nome = new Label("Nome");
		label_nome.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_nome.setBounds(81, 314, 62, 22);
		contentPane.add(label_nome);

		panel_resultadoPesquisa = new JPanel();
		panel_resultadoPesquisa.setBounds(207, 45, 827, 394);
		panel_resultadoPesquisa.setBorder(BorderFactory
				.createTitledBorder("Resultados da Pesquisa"));
		contentPane.add(panel_resultadoPesquisa);
		panel_resultadoPesquisa.setLayout(null);

		JButton btnVerDetalhes = new JButton("Ver Detalhes");
		btnVerDetalhes.setBounds(714, 200, 108, 23);
		panel_resultadoPesquisa.add(btnVerDetalhes);

		scrollPaneResultadosPesquisa = new JScrollPane();
		scrollPaneResultadosPesquisa.setViewportBorder(new LineBorder(
				new Color(0, 0, 0)));
		scrollPaneResultadosPesquisa.setBounds(10, 44, 701, 339);
		panel_resultadoPesquisa.add(scrollPaneResultadosPesquisa);

		table = new JTable(modeloTabelaConsulta);
		scrollPaneResultadosPesquisa.setViewportView(table);

		panel_resultadosDivisoes = new JPanel();
		panel_resultadosDivisoes.setBounds(10, 17, 701, 31);
		panel_resultadoPesquisa.add(panel_resultadosDivisoes);
		panel_resultadosDivisoes.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		panel_resultadosDivisoes.add(lblNome);

		JLabel lblZona = new JLabel("Zona");
		lblZona.setHorizontalAlignment(SwingConstants.CENTER);
		panel_resultadosDivisoes.add(lblZona);

		JLabel lblAvaliao = new JLabel("Avaliação");
		lblAvaliao.setHorizontalAlignment(SwingConstants.CENTER);
		panel_resultadosDivisoes.add(lblAvaliao);

		this.ctrConsulta = consulta;

		addListeners();

		fillComboBoxesWithData();

		this.setResizable(false);
		this.setVisible(true);

	}

	private void fillComboBoxesWithData() {
		this.cidades = ctrConsulta.getCidades();

		for (Cidade cidade : cidades) {
			comboBoxCidades.insertItemAt(cidade.getName(),
					comboBoxCidades.getItemCount());
		}

		this.zonas = ctrConsulta.getZonas();

		for (Zona zona : zonas) {
			comboBoxZonas.insertItemAt(zona.getDesignacao(),
					comboBoxZonas.getItemCount());
		}

		this.tiposdeEstablecimento = ctrConsulta.getTiposDeEstablecimento();

		for (TipoDeEstablecimento tipoEsblecimento : tiposdeEstablecimento) {
			comboBoxTiposDeEstablecimento.insertItemAt(
					tipoEsblecimento.getTipoDeEstablecimento(),
					comboBoxTiposDeEstablecimento.getItemCount());
		}

		this.tiposDeEvento = ctrConsulta.getTiposDeEventos();

		for (TipoDeEvento tipoDeEvento : tiposDeEvento) {
			comboBoxTiposDeEvento.insertItemAt(tipoDeEvento.getDescricao(),
					comboBoxTiposDeEvento.getItemCount());
		}

		this.tiposDePrato = ctrConsulta.getTiposDePratos();

		for (TipoDePrato tipoDePrato : tiposDePrato) {
			comboBoxTiposDePrato.insertItemAt(tipoDePrato.getDescricao(),
					comboBoxTiposDePrato.getItemCount());
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

	}

	private void consultarEstabelecimentos(boolean todos) {
		ArrayList<Estabelecimento> estabelecimentos = null;

		if (todos) {
			estabelecimentos = ctrConsulta.consultarEstabelecimentos(null,
					null, null, 0.0, null, null, null);
		} else {
			String cidade = cidades.get(comboBoxCidades.getSelectedIndex())
					.getName();
			String zona = zonas.get(comboBoxZonas.getSelectedIndex())
					.getDesignacao();
			String tipoDeEstablecimento = tiposdeEstablecimento.get(
					comboBoxTiposDeEstablecimento.getSelectedIndex())
					.getTipoDeEstablecimento();
			double aval = Double.parseDouble(textField_avaliacao.getText());
			String eventos = juntarEventos();
			String pratos = juntarpratos();
			String nome = textField_nome.getText();
			estabelecimentos = ctrConsulta.consultarEstabelecimentos(cidade,
					zona, tipoDeEstablecimento, aval, pratos, eventos, nome);
		}

		showConsultResult(estabelecimentos);
	}

	private String juntarpratos() {
		String pratos = "";

		for (int i = 0; i < modeloListaDePratos.getSize(); i++) {
			pratos += modeloListaDePratos.getElementAt(i);
			if (i + 1 < modeloListaDePratos.getSize())
				pratos += " ";
		}

		return pratos;
	}

	private String juntarEventos() {
		String eventos = "";

		for (int i = 0; i < modeloListaDeEventos.getSize(); i++) {
			eventos += modeloListaDeEventos.getElementAt(i);
			if (i + 1 < modeloListaDeEventos.getSize())
				eventos += " ";
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
