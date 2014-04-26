package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

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

public class Consulta extends JFrame {

	private static final long serialVersionUID = 4212115798640187383L;
	private JPanel contentPane;
	private Panel panelFiltros;
	private JComboBox comboBoxCidade;
	private JComboBox comboBoxZona;
	private JComboBox comboBoxTipo;
	private JPanel panelTipoEventos;
	private JPanel panel;
	private TextField textFieldEventos;
	private JScrollPane scrollPaneEventos;
	private JPanel panel_InScrollPaneEventos;
	private JPanel panelTipoPratos;
	private JPanel panel_1;
	private TextField textFieldPratos;
	private JScrollPane scrollPanePratos;
	private JPanel panel_InScrollPanelPratos;
	private TextField textField_avaliacao;
	private TextField textField_nome;
	private JPanel panel_resultadoPesquisa;
	private JScrollPane scrollPaneResultadosPesquisa;
	private JPanel panel_resultadosDivisoes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Consulta frame = new Consulta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Consulta() {
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

		JLabel lblCidade = new JLabel("CIDADE");
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCidade.setBounds(0, 0, 87, 23);
		lblCidade.setHorizontalAlignment(SwingConstants.CENTER);
		panelFiltros.add(lblCidade);

		comboBoxCidade = new JComboBox();
		comboBoxCidade.setBounds(87, 0, 100, 23);
		panelFiltros.add(comboBoxCidade);

		JLabel lblZonaFiltro = new JLabel("ZONA");
		lblZonaFiltro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblZonaFiltro.setBounds(0, 48, 87, 23);
		lblZonaFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		panelFiltros.add(lblZonaFiltro);

		comboBoxZona = new JComboBox();
		comboBoxZona.setBounds(87, 48, 100, 23);
		panelFiltros.add(comboBoxZona);

		JLabel lblTipo = new JLabel("TIPO");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTipo.setBounds(0, 96, 87, 23);
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		panelFiltros.add(lblTipo);

		comboBoxTipo = new JComboBox();
		comboBoxTipo.setBounds(87, 96, 100, 23);
		panelFiltros.add(comboBoxTipo);

		panelTipoEventos = new JPanel();
		panelTipoEventos.setBounds(32, 445, 480, 160);
		panelTipoEventos.setBorder(BorderFactory
				.createTitledBorder("Tipo de Eventos"));
		contentPane.add(panelTipoEventos);
		panelTipoEventos.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		panelTipoEventos.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		textFieldEventos = new TextField();
		panel.add(textFieldEventos);

		Button buttonAddEventos = new Button("Adicionar");
		panel.add(buttonAddEventos);

		scrollPaneEventos = new JScrollPane();
		scrollPaneEventos.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		panelTipoEventos.add(scrollPaneEventos, BorderLayout.CENTER);

		panel_InScrollPaneEventos = new JPanel();
		scrollPaneEventos.setViewportView(panel_InScrollPaneEventos);
		panel_InScrollPaneEventos.setLayout(new GridLayout(0, 2, 0, 0));

		panelTipoPratos = new JPanel();
		panelTipoPratos.setBounds(523, 445, 480, 160);
		panelTipoPratos.setBorder(BorderFactory
				.createTitledBorder("Tipo de Pratos"));
		contentPane.add(panelTipoEventos);
		contentPane.add(panelTipoPratos);
		panelTipoPratos.setLayout(new BorderLayout(0, 0));

		panel_1 = new JPanel();
		panelTipoPratos.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		textFieldPratos = new TextField();
		panel_1.add(textFieldPratos);

		Button buttonAddPratos = new Button("Adicionar");
		panel_1.add(buttonAddPratos);

		scrollPanePratos = new JScrollPane();
		scrollPanePratos.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		panelTipoPratos.add(scrollPanePratos, BorderLayout.CENTER);

		panel_InScrollPanelPratos = new JPanel();
		scrollPanePratos.setViewportView(panel_InScrollPanelPratos);
		panel_InScrollPanelPratos.setLayout(new BorderLayout(0, 0));

		Button buttonAplicarFiltro = new Button("Aplicar Filtro");
		buttonAplicarFiltro.setBounds(32, 367, 91, 22);
		contentPane.add(buttonAplicarFiltro);

		Button buttonFiltroTodos = new Button("Todos");
		buttonFiltroTodos.setBounds(141, 367, 60, 22);
		contentPane.add(buttonFiltroTodos);

		textField_avaliacao = new TextField();
		textField_avaliacao.setBounds(163, 261, 38, 22);
		contentPane.add(textField_avaliacao);

		Label label_avaliacao = new Label("AVALIA\u00C7\u00C3O");
		label_avaliacao.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_avaliacao.setBounds(32, 261, 80, 22);
		contentPane.add(label_avaliacao);

		Label label_sinal = new Label(">=");
		label_sinal.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_sinal.setBounds(118, 261, 26, 22);
		contentPane.add(label_sinal);

		textField_nome = new TextField();
		textField_nome.setBounds(121, 314, 80, 22);
		contentPane.add(textField_nome);

		Label label_nome = new Label("NOME");
		label_nome.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_nome.setBounds(60, 314, 62, 22);
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

		panel_resultadosDivisoes = new JPanel();
		panel_resultadosDivisoes.setBounds(10, 17, 701, 31);
		panel_resultadoPesquisa.add(panel_resultadosDivisoes);
		panel_resultadosDivisoes.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblNome = new JLabel("NOME");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		panel_resultadosDivisoes.add(lblNome);

		JLabel lblZona = new JLabel("ZONA");
		lblZona.setHorizontalAlignment(SwingConstants.CENTER);
		panel_resultadosDivisoes.add(lblZona);

		JLabel lblAvaliao = new JLabel("AVALIA\u00C7\u00C3O");
		lblAvaliao.setHorizontalAlignment(SwingConstants.CENTER);
		panel_resultadosDivisoes.add(lblAvaliao);

	}
}
