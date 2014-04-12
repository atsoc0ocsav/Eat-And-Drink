package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Consulta extends JFrame {

	private static final long serialVersionUID = 4213975954950138529L;
	private JPanel contentPane;

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
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public Consulta() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager
				.getCrossPlatformLookAndFeelClassName());

		setTitle("Eat & Drink Estabelecimentos - Consulta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_Filtros = new JPanel();
		panel_Filtros.setBounds(192, 72, 559, 20);
		contentPane.add(panel_Filtros);
		panel_Filtros.setLayout(new GridLayout(1, 6, 0, 0));

		JLabel lbl_Cidade = new JLabel("CIDADE");
		lbl_Cidade.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Filtros.add(lbl_Cidade);

		JComboBox comboBox_Cidade = new JComboBox();
		panel_Filtros.add(comboBox_Cidade);

		JLabel lbl_Zona = new JLabel("ZONA");
		lbl_Zona.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Filtros.add(lbl_Zona);

		JComboBox comboBox_Zona = new JComboBox();
		panel_Filtros.add(comboBox_Zona);

		JLabel lbl_Tipo = new JLabel("TIPO");
		lbl_Tipo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Filtros.add(lbl_Tipo);

		JComboBox comboBox_Tipo = new JComboBox();
		panel_Filtros.add(comboBox_Tipo);

		JPanel panel_TipoEventos = new JPanel();
		panel_TipoEventos.setBounds(150, 126, 319, 170);
		contentPane.add(panel_TipoEventos);
		panel_TipoEventos.setLayout(new BorderLayout(0, 0));
		panel_TipoEventos.setBorder(BorderFactory
				.createTitledBorder("Tipo de Eventos"));

		JScrollPane scrollPaneEventos = new JScrollPane();
		panel_TipoEventos.add(scrollPaneEventos, BorderLayout.CENTER);

		JPanel panel_InScrollPaneEventos = new JPanel();
		scrollPaneEventos.setViewportView(panel_InScrollPaneEventos);
		panel_InScrollPaneEventos.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_TipoEventos_Sul = new JPanel();
		panel_TipoEventos.add(panel_TipoEventos_Sul, BorderLayout.SOUTH);
		panel_TipoEventos_Sul.setLayout(new GridLayout(0, 2, 0, 0));

		TextField textField_Eventos = new TextField();
		panel_TipoEventos_Sul.add(textField_Eventos);

		JButton button_AddEventos = new JButton("Adicionar");
		panel_TipoEventos_Sul.add(button_AddEventos);

		JPanel panel_TipoPratos = new JPanel();
		panel_TipoPratos.setBounds(472, 126, 319, 170);
		contentPane.add(panel_TipoPratos);
		panel_TipoPratos.setLayout(new BorderLayout(0, 0));
		panel_TipoPratos.setBorder(BorderFactory
				.createTitledBorder("Tipo de Pratos"));

		JScrollPane scrollPanePratos = new JScrollPane();
		panel_TipoPratos.add(scrollPanePratos, BorderLayout.CENTER);

		JPanel panel_InScrollPanelPratos = new JPanel();
		scrollPanePratos.setViewportView(panel_InScrollPanelPratos);
		panel_InScrollPanelPratos.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_TipoPratos_Sul = new JPanel();
		panel_TipoPratos.add(panel_TipoPratos_Sul, BorderLayout.SOUTH);
		panel_TipoPratos_Sul.setLayout(new GridLayout(0, 2, 0, 0));

		TextField textField_Pratos = new TextField();
		panel_TipoPratos_Sul.add(textField_Pratos);

		JButton button_AddPratos = new JButton("Adicionar");
		panel_TipoPratos_Sul.add(button_AddPratos);

		JButton button_AplicarFiltro = new JButton("Aplicar Filtro");
		button_AplicarFiltro.setBounds(32, 312, 91, 22);
		contentPane.add(button_AplicarFiltro);

		JButton button_FiltroTodos = new JButton("Todos");
		button_FiltroTodos.setBounds(137, 312, 80, 22);
		contentPane.add(button_FiltroTodos);

		TextField textField_Avaliacao = new TextField();
		textField_Avaliacao.setBounds(69, 145, 45, 22);
		contentPane.add(textField_Avaliacao);

		TextField textField_Nome = new TextField();
		textField_Nome.setBounds(32, 225, 91, 22);
		contentPane.add(textField_Nome);

		JLabel lbl_EatDrink = DefaultComponentFactory.getInstance()
				.createTitle("Eat and Drink");
		lbl_EatDrink.setBounds(89, 11, 632, 31);
		lbl_EatDrink.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_EatDrink.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lbl_EatDrink);

		JLabel lbl_Avaliacao = new JLabel("AVALIA\u00C7\u00C3O");
		lbl_Avaliacao.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Avaliacao.setBounds(32, 117, 91, 22);
		contentPane.add(lbl_Avaliacao);

		JLabel lbl_MaiorIgual = new JLabel(">=");
		lbl_MaiorIgual.setBounds(42, 145, 26, 22);
		contentPane.add(lbl_MaiorIgual);

		JLabel lbl_Nome = new JLabel("NOME");
		lbl_Nome.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Nome.setBounds(32, 198, 91, 22);
		contentPane.add(lbl_Nome);

	}
}
