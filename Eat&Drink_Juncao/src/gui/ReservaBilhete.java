package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import dados.ReservaDeBilhetes;

public class ReservaBilhete extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDia;
	private JTextField textFieldHora;
	private JComboBox comboBoxEvento;
	private JComboBox comboBoxLugar;
	private JButton buttonConfirme;
	private JButton buttonCancele;

	private GestorReservaBilhete ctrReservaBilhete;
	private ArrayList<Evento> eventoOferecido;
	private ArrayList<ReservaDeBilhetes> bilhetes;

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

		buttonConfirme = new JButton("Confirme");
		buttonConfirme.setBounds(97, 312, 89, 23);
		panelInterno.add(buttonConfirme);

		buttonCancele = new JButton("Cancele");
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
		textFieldDia.setEditable(false);

		textFieldHora = new JTextField();
		textFieldHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldHora.setBounds(251, 117, 63, 23);
		panelInterno.add(textFieldHora);
		textFieldHora.setColumns(10);
		textFieldHora.setEditable(false);

		JLabel lblDia = new JLabel("Dia");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDia.setBounds(49, 116, 46, 24);
		panelInterno.add(lblDia);

		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHora.setBounds(208, 119, 46, 19);
		panelInterno.add(lblHora);

		preencheComboBoxEventos();
		addListener();

		this.setResizable(false);
		this.setVisible(true);
	}

	private void addListener() {
		comboBoxEvento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				montarHoraEData();
				corresponderLugaresAEvento();

			}
		});

		buttonConfirme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				confirmarBilhete();

			}

		});

		buttonCancele.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				desmarcarBilhete();

			}

		});

	}

	private void desmarcarBilhete() {
		if (comboBoxEvento.getSelectedItem() != null
				&& comboBoxLugar.getSelectedItem() != null) {
			String evento = (String) comboBoxEvento.getSelectedItem();
			if (!comboBoxLugar.getSelectedItem().equals("")) {
				int lugar = (int) comboBoxLugar.getSelectedItem();
				int idEvento = -1;
				for (int i = 0; i < eventoOferecido.size(); i++) {
					if (eventoOferecido.get(i).getDescricao().equals(evento))
						idEvento = eventoOferecido.get(i).getIdEvento();
				}
				if (idEvento != -1)
					ctrReservaBilhete
							.confirmarBilhete(idEvento, lugar, "Livre");

				// .. else
				// .. diz algo ???
			}
		}
	}

	private void confirmarBilhete() {
		if (comboBoxEvento.getSelectedItem() != null
				&& comboBoxLugar.getSelectedItem() != null) {
			String evento = (String) comboBoxEvento.getSelectedItem();
			if (!comboBoxLugar.getSelectedItem().equals("")) {
				int lugar = (int) comboBoxLugar.getSelectedItem();
				int idEvento = -1;
				for (int i = 0; i < eventoOferecido.size(); i++) {
					if (eventoOferecido.get(i).getDescricao().equals(evento))
						idEvento = eventoOferecido.get(i).getIdEvento();
				}
				if (idEvento != -1)
					ctrReservaBilhete.confirmarBilhete(idEvento, lugar,
							"Reservado");

				// .. else
				// .. diz algo ???
			}
		}
	}

	private void montarHoraEData() {
		textFieldDia.setText("");
		textFieldHora.setText("");
		String evento = (String) comboBoxEvento.getSelectedItem();
		for (int i = 0; i < eventoOferecido.size(); i++) {
			if (eventoOferecido.get(i).getDescricao().equals(evento)) {
				textFieldDia.setText(eventoOferecido.get(i).getData());
				textFieldHora.setText(eventoOferecido.get(i).getHora());
			}
		}
	}

	private void corresponderLugaresAEvento() {
		if (!comboBoxEvento.getSelectedItem().equals("")) {
			comboBoxLugar.removeAllItems();
			String evento = (String) comboBoxEvento.getSelectedItem();
			int id = -1;
			for (int i = 0; i < eventoOferecido.size(); i++) {
				if (eventoOferecido.get(i).getDescricao().equals(evento))
					id = eventoOferecido.get(i).getIdEvento();
			}
			if (id != -1)
				this.bilhetes = ctrReservaBilhete.getLugares(id);

			comboBoxLugar.insertItemAt("", 0);
			for (ReservaDeBilhetes bilhete : bilhetes) {
				comboBoxLugar.insertItemAt(bilhete.getNumeroLugar(),
						comboBoxLugar.getItemCount());
			}
		}

	}

	private void preencheComboBoxEventos() {
		this.eventoOferecido = ctrReservaBilhete.getEvento();

		comboBoxEvento.insertItemAt("", 0);
		for (Evento evento : eventoOferecido) {
			comboBoxEvento.insertItemAt(evento.getDescricao(),
					comboBoxEvento.getItemCount());
		}

	}
}
