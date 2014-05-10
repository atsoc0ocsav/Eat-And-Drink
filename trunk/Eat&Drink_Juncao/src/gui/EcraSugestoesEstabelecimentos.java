package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class EcraSugestoesEstabelecimentos extends JFrame {

	private JPanel contentPane;
	private JTable table;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EcraSugestoesEstabelecimentos frame = new EcraSugestoesEstabelecimentos("user");
//					// frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public EcraSugestoesEstabelecimentos(String NomeUtil, String email) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEatDrink = new JLabel("Eat and Drink");
		lblEatDrink.setBounds(5, 5, 531, 28);
		contentPane.add(lblEatDrink);
		lblEatDrink.setHorizontalAlignment(SwingConstants.CENTER);
		lblEatDrink.setFont(new Font("Tahoma", Font.PLAIN, 23));

		JPanel panel_resultadoSugestoes = new JPanel();
		panel_resultadoSugestoes.setBounds(5, 59, 483, 460);
		panel_resultadoSugestoes.setBorder(BorderFactory
				.createTitledBorder("Sugestões de Estabelecimentos do Utilizador " + NomeUtil));
		contentPane.add(panel_resultadoSugestoes);
		panel_resultadoSugestoes.setLayout(null);
		
		JScrollPane scrollPaneResultadosSugestoes = new JScrollPane();
		scrollPaneResultadosSugestoes.setViewportBorder(new LineBorder(
				new Color(0, 0, 0)));
		scrollPaneResultadosSugestoes.setBounds(10, 30, 463, 388);
		panel_resultadoSugestoes.add(scrollPaneResultadosSugestoes);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Zona", "Avalia\u00E7\u00E3o"
			}
		));
		scrollPaneResultadosSugestoes.setViewportView(table);
		
		JButton btnVerDetalhes = new JButton("Ver Detalhes");
		btnVerDetalhes.setBounds(82, 426, 93, 23);
		panel_resultadoSugestoes.add(btnVerDetalhes);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(311, 426, 89, 23);
		panel_resultadoSugestoes.add(btnSair);

		this.setResizable(false);
		this.setVisible(true);
	}
}
