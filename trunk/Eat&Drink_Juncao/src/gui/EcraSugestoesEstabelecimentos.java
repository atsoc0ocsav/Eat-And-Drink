package gui;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlo.ctrlConsultarEstabelecimentos;
import dados.Estabelecimento;
import dados.Zona;

public class EcraSugestoesEstabelecimentos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ctrlConsultarEstabelecimentos ctrlConsulta;
	private ArrayList<Zona> zonas;
	private ArrayList<Estabelecimento>  estabelecimentos;
	private consultaEstablecimentosTableDataModel modeloTabelaConsulta = new consultaEstablecimentosTableDataModel();


	/**
	 * Create the frame.
	 */
	public EcraSugestoesEstabelecimentos(String NomeUtil, String email, ctrlConsultarEstabelecimentos ctrlConsulta) {
		
		this.ctrlConsulta = ctrlConsulta;
		this.zonas = ctrlConsulta.getZonas();
				
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		table = new JTable(modeloTabelaConsulta);
		scrollPaneResultadosSugestoes.setViewportView(table);
		
		JButton btnVerDetalhes = new JButton("Ver Detalhes");
		btnVerDetalhes.setBounds(82, 426, 93, 23);
		panel_resultadoSugestoes.add(btnVerDetalhes);
		
		btnVerDetalhes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showDetalhesEstabelecimento();
			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(311, 426, 89, 23);
		btnSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_resultadoSugestoes.add(btnSair);
		
		armazenaSugestoes(email, ctrlConsulta);
		
		this.setResizable(false);
		this.setVisible(true);
	}

	private void armazenaSugestoes(String email, ctrlConsultarEstabelecimentos ctrlConsulta) {
		
		ArrayList<Estabelecimento> array = ctrlConsulta.consultaSugestoes(email);	
		
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i).toString());
		}
		
		estabelecimentos = array;
		
		preencheTabela(array);		
	}


	private void preencheTabela(ArrayList<Estabelecimento> array) {
		
		Object[][] resultado = new Object[array.size()][3];

		for (int i = 0; i < array.size(); i++) {
			Estabelecimento x = array.get(i);
			resultado[i][0] = x.getDesignacao();
			resultado[i][1] = idZoneToDesignacao(x.getIdZona());
			resultado[i][2] = x.getRating();
			
			modeloTabelaConsulta.changeData(resultado);
			modeloTabelaConsulta.fireTableDataChanged();
		}
		
	}
	
	private Object idZoneToDesignacao(int idZona) {
		for (Zona zona : zonas) {
			if (zona.getIdZona() == idZona) {
				return zona.getDesignacao();
			}
		}
		return "-";
	}
	
	private void showDetalhesEstabelecimento() {
		int selected = table.getSelectedRow();
		int id = estabelecimentos.get(selected).getIdEstabelecimento();
		new ecraEstabelecimentoDetalhes(id);
	}
	
}
