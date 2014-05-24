package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlo.GestorReservaBilhete;
import dados.Evento;

public class ReservaBilhete extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDia;
	private JTextField textFieldHora;
	private JComboBox comboBoxEvento;
	private JComboBox comboBoxLugar;
	private GestorReservaBilhete ctrReservaBilhete;
	
	private ArrayList<Evento> eventoOferecido;

	public ReservaBilhete() {
		ctrReservaBilhete = new GestorReservaBilhete();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelTitulo = new JPanel();
		contentPane.add(panelTitulo, BorderLayout.NORTH);
		
		JLabel lblReservaDeBilhetes = new JLabel("Reserva de Bilhetes");
		lblReservaDeBilhetes.setFont(new Font("Tahoma", Font.PLAIN, 26));
		panelTitulo.add(lblReservaDeBilhetes);
		
		JPanel panelInterno = new JPanel();
		contentPane.add(panelInterno, BorderLayout.CENTER);
		panelInterno.setLayout(null);
		
		comboBoxEvento = new JComboBox();
		comboBoxEvento.setBounds(122, 44, 232, 20);
		panelInterno.add(comboBoxEvento);
		
		comboBoxLugar = new JComboBox();
		comboBoxLugar.setBounds(182, 212, 111, 23);
		panelInterno.add(comboBoxLugar);
		
		JButton buttonConfirme = new JButton("Confirme");
		buttonConfirme.setBounds(97, 312, 89, 23);
		panelInterno.add(buttonConfirme);
		
		JButton buttonCancele = new JButton("Cancele");
		buttonCancele.setBounds(248, 312, 89, 23);
		panelInterno.add(buttonCancele);
		
		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEvento.setBounds(47, 45, 89, 14);
		panelInterno.add(lblEvento);
		
		JLabel lblLugar = new JLabel("Escolha o Lugar:");
		lblLugar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLugar.setBounds(47, 215, 125, 17);
		panelInterno.add(lblLugar);
		
		textFieldDia = new JTextField();
		textFieldDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldDia.setBounds(83, 117, 90, 23);
		panelInterno.add(textFieldDia);
		textFieldDia.setColumns(10);
		
		textFieldHora = new JTextField();
		textFieldHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldHora.setBounds(251, 117, 63, 23);
		panelInterno.add(textFieldHora);
		textFieldHora.setColumns(10);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDia.setBounds(49, 116, 46, 24);
		panelInterno.add(lblDia);
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHora.setBounds(208, 119, 46, 19);
		panelInterno.add(lblHora);
		
		preencheComboBox();
		
		this.setResizable(false);
		this.setVisible(true);
	}

	private void preencheComboBox() {
		this.eventoOferecido = ctrReservaBilhete.getEvento();

		comboBoxEvento.insertItemAt(" ", 0);
		for (Evento evento : eventoOferecido) {			
			comboBoxEvento.insertItemAt(evento.getDescricao(),
					comboBoxEvento.getItemCount());			
		}
		
	}
}
