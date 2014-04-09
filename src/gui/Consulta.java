package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Consulta extends JFrame {

	private JPanel contentPane;
	private JTextField txtEatDrink;

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

	/**
	 * Create the frame.
	 */
	public Consulta() {
		setTitle("Eat & Drink Estabelecimentos - Consulta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEatDrink = DefaultComponentFactory.getInstance().createTitle(
				"Eat and Drink");
		lblEatDrink.setBounds(119, 11, 632, 31);
		lblEatDrink.setHorizontalAlignment(SwingConstants.CENTER);
		lblEatDrink.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblEatDrink);

		Panel panelFiltros = new Panel();
		panelFiltros.setBounds(192, 72, 559, 20);
		contentPane.add(panelFiltros);
		panelFiltros.setLayout(new GridLayout(1, 6, 0, 0));

		JLabel lblCidade = new JLabel("CIDADE");
		lblCidade.setHorizontalAlignment(SwingConstants.CENTER);
		panelFiltros.add(lblCidade);

		JComboBox comboBoxCidade = new JComboBox();
		panelFiltros.add(comboBoxCidade);

		JLabel lblZona = new JLabel("ZONA");
		lblZona.setHorizontalAlignment(SwingConstants.CENTER);
		panelFiltros.add(lblZona);

		JComboBox comboBoxZona = new JComboBox();
		panelFiltros.add(comboBoxZona);

		JLabel lblTipo = new JLabel("TIPO");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		panelFiltros.add(lblTipo);

		JComboBox comboBoxTipo = new JComboBox();
		panelFiltros.add(comboBoxTipo);

		Panel panelTipoEventos = new Panel();
		panelTipoEventos.setBounds(192, 126, 319, 157);
		contentPane.add(panelTipoEventos);
		panelTipoEventos.setLayout(new BorderLayout(0, 0));

		Panel panel = new Panel();
		panelTipoEventos.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		TextField textFieldEventos = new TextField();
		panel.add(textFieldEventos);

		Button buttonAddEventos = new Button("Adicionar");
		panel.add(buttonAddEventos);

		JScrollPane scrollPaneEventos = new JScrollPane();
		panelTipoEventos.add(scrollPaneEventos, BorderLayout.CENTER);

		JPanel panel_InScrollPaneEventos = new JPanel();
		scrollPaneEventos.setViewportView(panel_InScrollPaneEventos);
		panel_InScrollPaneEventos.setLayout(new GridLayout(0, 2, 0, 0));

		Panel panelTipoPratos = new Panel();
		panelTipoPratos.setBounds(517, 126, 310, 157);
		contentPane.add(panelTipoPratos);
		panelTipoPratos.setLayout(new BorderLayout(0, 0));

		Panel panel_1 = new Panel();
		panelTipoPratos.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		TextField textFieldPratos = new TextField();
		panel_1.add(textFieldPratos);

		Button buttonAddPratos = new Button("Adicionar");
		panel_1.add(buttonAddPratos);

		JScrollPane scrollPanePratos = new JScrollPane();
		panelTipoPratos.add(scrollPanePratos, BorderLayout.CENTER);

		JPanel panel_InScrollPanelPratos = new JPanel();
		scrollPanePratos.setViewportView(panel_InScrollPanelPratos);
		panel_InScrollPanelPratos.setLayout(new GridLayout(1, 0, 0, 0));

		Button buttonAplicarFiltro = new Button("Aplicar Filtro");
		buttonAplicarFiltro.setBounds(32, 312, 91, 22);
		contentPane.add(buttonAplicarFiltro);

		Button buttonFiltroTodos = new Button("Todos");
		buttonFiltroTodos.setBounds(137, 312, 80, 22);
		contentPane.add(buttonFiltroTodos);

		TextField textFieldAvaliacao = new TextField();
		textFieldAvaliacao.setBounds(83, 145, 38, 22);
		contentPane.add(textFieldAvaliacao);

		Label label = new Label("AVALIA\u00C7\u00C3O");
		label.setBounds(65, 117, 80, 22);
		contentPane.add(label);

		Label label_1 = new Label(">=");
		label_1.setBounds(65, 145, 26, 22);
		contentPane.add(label_1);

		TextField textField = new TextField();
		textField.setBounds(65, 225, 80, 22);
		contentPane.add(textField);

		Label label_2 = new Label("NOME");
		label_2.setBounds(65, 197, 62, 22);
		contentPane.add(label_2);

	}
}
