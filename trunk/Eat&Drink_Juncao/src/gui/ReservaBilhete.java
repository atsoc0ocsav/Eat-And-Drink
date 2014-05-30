package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlo.GestorReservaBilhete;
import controlo.GestorReservaBilhete.ConcorrencyLevel;
import dados.Evento;
import dados.ReservaDeBilhetes;

@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class ReservaBilhete extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDia;
	private JTextField textFieldHora;
	private JComboBox comboBoxEvento;
	private JComboBox comboBoxLugar;
	private JButton buttonConfirme;
	private JButton buttonCancel;

	private GestorReservaBilhete ctrReservaBilhete;
	private ArrayList<Evento> eventoOferecido;
	private ArrayList<ReservaDeBilhetes> bilhetes;
	private JTextArea textMensagemConc;

	public ReservaBilhete(ConcorrencyLevel concurrenceLevel) {
		ctrReservaBilhete = new GestorReservaBilhete(concurrenceLevel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 463, 479);
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

		buttonConfirme = new JButton("Confirmar");
		buttonConfirme.setBounds(88, 312, 98, 23);
		panelInterno.add(buttonConfirme);

		buttonCancel = new JButton("Cancelar");
		buttonCancel.setBounds(248, 312, 89, 23);
		panelInterno.add(buttonCancel);

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

		textMensagemConc = new JTextArea();
		textMensagemConc.setBorder(javax.swing.BorderFactory
				.createEmptyBorder());
		textMensagemConc.setEditable(false);
		textMensagemConc.setBackground(new Color(240, 240, 240));
		textMensagemConc.setBounds(47, 243, 307, 41);
		panelInterno.add(textMensagemConc);
		textMensagemConc.setColumns(10);
		textMensagemConc.setLineWrap(true);
		textMensagemConc.setWrapStyleWord(true);

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

		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				disposeFrame();
			}

		});
	}

	@SuppressWarnings("unused")
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
				if (idEvento != -1) {
					boolean cancela = verificaSePodeCancelar(idEvento, lugar);
					if (cancela) {
						try {
							ctrReservaBilhete.confirmarBilhete(idEvento, lugar,
									"Livre");
						} catch (SQLException e) {
							e.printStackTrace();
						}
						editaBilhete(idEvento, lugar, "Livre");

						showJOption(1);
					} else
						showJOption(2);
				}
			}
		} else
			showJOption(4);
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
					try {
						ctrReservaBilhete.confirmarBilhete(idEvento, lugar,
								"Reservado");
					} catch (SQLException e) {
						if (ctrReservaBilhete.getCONCORRENCY_STATE() == ConcorrencyLevel.OPTIMIST) {
							textMensagemConc
									.setText("Pedimos desculpa mas o lugar deixou de estar disponível.");
						}
					}
				editaBilhete(idEvento, lugar, "Reservado");

				showJOption(3);
			}
		} else
			showJOption(4);
	}

	private void showJOption(int escolha) {
		switch (escolha) {
		case 1:
			JOptionPane.showMessageDialog(null,
					"Bilhete cancelado com sucesso!");
			break;
		case 2:
			JOptionPane.showMessageDialog(null,
					"Não pode cancelar. O Bilhete já se encontra livre!");
			break;
		case 3:
			JOptionPane.showMessageDialog(null,
					"Bilhete reservado com sucesso!");
			break;
		case 4:
			JOptionPane.showMessageDialog(null,
					"Tem de escolher um evento e um lugar!");
			break;

		default:
			break;
		}
	}

	private boolean verificaSePodeCancelar(int idEvento, int lugar) {
		for (int i = 0; i < bilhetes.size(); i++) {
			if (bilhetes.get(i).getIDEvento() == idEvento
					&& bilhetes.get(i).getNumeroLugar() == lugar
					&& bilhetes.get(i).getEstado().equals("Reservado")) {
				return true;
			}
		}
		return false;
	}

	private void editaBilhete(int idEvento, int lugar, String estado) {
		for (int i = 0; i < bilhetes.size(); i++) {
			if (bilhetes.get(i).getIDEvento() == idEvento
					&& bilhetes.get(i).getNumeroLugar() == lugar)
				bilhetes.get(i).setEstado(estado);
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
		comboBoxLugar.removeAllItems();
		if (!comboBoxEvento.getSelectedItem().equals("")) {
			String evento = (String) comboBoxEvento.getSelectedItem();
			int id = -1;
			for (int i = 0; i < eventoOferecido.size(); i++) {
				if (eventoOferecido.get(i).getDescricao().equals(evento))
					id = eventoOferecido.get(i).getIdEvento();
			}
			if (id != -1)
				try {
					this.bilhetes = ctrReservaBilhete.getLugares(id);
				} catch (SQLException e) {
					if (ctrReservaBilhete.getCONCORRENCY_STATE() == ConcorrencyLevel.PESSIMIST) {
//						textMensagemConc
//								.setText("Pedimos desculpa mas existe um utilizador a seleccionar lugares para este evento.");
						textMensagemConc
						.setText("Pedimos desculpa mas a sua sessão de reserva expirou");
						e.printStackTrace();
					}
				}

			comboBoxLugar.insertItemAt("", 0);
			if (bilhetes != null) {
				for (ReservaDeBilhetes bilhete : bilhetes) {
					comboBoxLugar.insertItemAt(bilhete.getNumeroLugar(),
							comboBoxLugar.getItemCount());
				}
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

	private void disposeFrame() {
		this.dispose();
	}
}
