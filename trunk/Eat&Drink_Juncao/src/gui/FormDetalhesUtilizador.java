package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlo.GestorDetalhesDeUtilizador;
import dados.Botao;
import dados.Utilizador;

public class FormDetalhesUtilizador {
	
	private JFrame window;;
	private Container container;
	private FormConsultaUtilizadores formConsultaUtilizadores;
	private GestorDetalhesDeUtilizador gestorDetalhesDeUtilizador;

	private JPanel detailsPanel = new JPanel();
	private JLabel photoLabel = new JLabel();
	private JLabel fullNameLabel = new JLabel("Nome Completo");
	private JTextField fullNameText = new JTextField(15);
	private JLabel emailLabel = new JLabel("E-mail");
	private JTextField emailText = new JTextField(15);
	private JLabel cityDetailsLabel = new JLabel("Cidade");
	private JTextField cityDetailsText = new JTextField(10);
	private JLabel schoolDetailsLabel = new JLabel("Escola");
	private JTextField schoolDetailsText = new JTextField(10);
	private JLabel followByLabel = new JLabel("Seguido Por");
	private JTable followUsersTable;
	private DefaultTableModel followUsersTableModel;
	private JScrollPane  followUsersTableScrool;
	private JButton updatePhotoButton = new JButton("Alterar Fotografia");
	private JButton saveDefinitionsButton = new JButton("Gravar Alterações");
	private JButton viewSuggestsButton = new JButton("Ver Sugestões");
	private JButton goBackButton = new JButton("Voltar");
	
	public FormDetalhesUtilizador(JFrame window, Container container, FormConsultaUtilizadores formConsultaUtilizadores) {
		this.window = window;
		this.container = container;
		this.formConsultaUtilizadores = formConsultaUtilizadores;
		gestorDetalhesDeUtilizador = new GestorDetalhesDeUtilizador();
	}

	public void construirFormularioDetalhes(String username, String city, String school, String email){
		container.removeAll();
		detailsPanel.setLayout(null);

		desactivarCamposDeAlteracaoDePerfil();
		
		fullNameText.setText(username);
		emailText.setText(email);
		cityDetailsText.setText(city);
		schoolDetailsText.setText(school);

		ImageIcon imageUser = new ImageIcon("palito.jpg");
		photoLabel.setIcon(imageUser);
		fullNameText.setFont(fullNameText.getFont().deriveFont(Font.PLAIN));
		emailText.setFont(emailText.getFont().deriveFont(Font.PLAIN));
		cityDetailsText.setFont(cityDetailsText.getFont().deriveFont(Font.PLAIN));
		schoolDetailsText.setFont(schoolDetailsText.getFont().deriveFont(Font.PLAIN));

		detailsPanel.add(photoLabel);
		detailsPanel.add(fullNameLabel);
		detailsPanel.add(fullNameText);
		detailsPanel.add(emailLabel);
		detailsPanel.add(emailText);
		detailsPanel.add(cityDetailsLabel);
		detailsPanel.add(cityDetailsText);
		detailsPanel.add(schoolDetailsLabel);
		detailsPanel.add(schoolDetailsText);
		detailsPanel.add(followByLabel);
		contruirTabelaDeSeguidores();
		detailsPanel.add(followUsersTableScrool);
		detailsPanel.add(updatePhotoButton);
		
		//BOTAO ALTERAR FOTO
		updatePhotoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gestorDetalhesDeUtilizador.alterarFoto(formConsultaUtilizadores.getSelectedUser().getName());
			}
		});
		
		//BOTAO ALTERAR CAMPOS
		detailsPanel.add(saveDefinitionsButton);
		saveDefinitionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gravarAlteracoes();
				JOptionPane.showMessageDialog(window,"Gravadas as alterações com sucesso");
			}
		});
		
		//BOTAO VER SUGESTOES
		detailsPanel.add(viewSuggestsButton);
		viewSuggestsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EcraSugestoesEstabelecimentos(formConsultaUtilizadores.getSelectedUser().getName(), formConsultaUtilizadores.getSelectedUser().getEmail());
			}
		});
		
		//BOTAO VOLTAR AO FORMULÁRIO DE CONSULTA
		detailsPanel.add(goBackButton);
		goBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				formConsultaUtilizadores.setTableModelRowCount(0);
				formConsultaUtilizadores.construirFormularioConsulta();
			}
		});

		detailsPanelSetComponentsBounds();
		
		if(username.equals(formConsultaUtilizadores.getCurrentUsername()))
			activarCamposDeAltercaoDePerfil();
		
		container.add(detailsPanel);
		container.repaint();
		container.validate();
	}

	private void gravarAlteracoes() {
		gestorDetalhesDeUtilizador.gravarAlteracoes(fullNameText.getText().trim(), emailText.getText().trim(), cityDetailsText.getText().trim(), schoolDetailsText.getText().trim());
	}	

	public Utilizador mostrarDetalhes(String userSelecionado) {
		return gestorDetalhesDeUtilizador.carregarDados(userSelecionado);
	}

	private void activarCamposDeAltercaoDePerfil(){
		fullNameText.setEditable(true);
		emailText.setEditable(true);
		cityDetailsText.setEditable(true);
		schoolDetailsText.setEditable(true);
		saveDefinitionsButton.setVisible(true);
		updatePhotoButton.setVisible(true);
		
	}
	
	private void desactivarCamposDeAlteracaoDePerfil(){
			fullNameText.setEditable(false);
			emailText.setEditable(false);
			cityDetailsText.setEditable(false);
			schoolDetailsText.setEditable(false);
			saveDefinitionsButton.setVisible(false);
			updatePhotoButton.setVisible(false);
	}
	
	private void detailsPanelSetComponentsBounds() {
		Insets insets = detailsPanel.getInsets();

		photoLabel.setBounds(55 + insets.left, 20 + insets.top, photoLabel.getPreferredSize().width, photoLabel.getPreferredSize().height);
		fullNameLabel.setBounds(280 + insets.left, 20 + insets.top, fullNameLabel.getPreferredSize().width, fullNameLabel.getPreferredSize().height);
		fullNameText.setBounds(280 + insets.left, 40 + insets.top, fullNameText.getPreferredSize().width, fullNameText.getPreferredSize().height);
		emailLabel.setBounds(280 + insets.left, 80 + insets.top, emailLabel.getPreferredSize().width, emailLabel.getPreferredSize().height);
		emailText.setBounds(280 + insets.left, 100 + insets.top, emailText.getPreferredSize().width, emailText.getPreferredSize().height);
		cityDetailsLabel.setBounds(280 + insets.left, 140 + insets.top, cityDetailsLabel.getPreferredSize().width, cityDetailsLabel.getPreferredSize().height);
		cityDetailsText.setBounds(330 + insets.left, 140 + insets.top, cityDetailsText.getPreferredSize().width, cityDetailsText.getPreferredSize().height);
		schoolDetailsLabel.setBounds(280 + insets.left, 180 + insets.top, schoolDetailsLabel.getPreferredSize().width, schoolDetailsLabel.getPreferredSize().height);
		schoolDetailsText.setBounds(330 + insets.left, 180 + insets.top, schoolDetailsText.getPreferredSize().width, schoolDetailsText.getPreferredSize().height);
		followByLabel.setBounds(55 + insets.left, 220 + insets.top, followByLabel.getPreferredSize().width, followByLabel.getPreferredSize().height);
		followUsersTableScrool.setBounds(55 + insets.left, 240 + insets.top, followUsersTableScrool.getPreferredSize().width-275, followUsersTableScrool.getPreferredSize().height-175);
		updatePhotoButton.setBounds(300 + insets.left, 240 + insets.top, updatePhotoButton.getPreferredSize().width, updatePhotoButton.getPreferredSize().height);
		saveDefinitionsButton.setBounds(300 + insets.left, 340 + insets.top, saveDefinitionsButton.getPreferredSize().width, saveDefinitionsButton.getPreferredSize().height);
		viewSuggestsButton.setBounds(300 + insets.left, 440 + insets.top, viewSuggestsButton.getPreferredSize().width, viewSuggestsButton.getPreferredSize().height);
		goBackButton.setBounds(215 + insets.left, 525 + insets.top, goBackButton.getPreferredSize().width, goBackButton.getPreferredSize().height);
	}
	
	private void contruirTabelaDeSeguidores(){	
		String columnsNamesDetails[] = {"",""};
		followUsersTableModel = new DefaultTableModel(columnsNamesDetails , formConsultaUtilizadores.getSelectedUser().getFollowers().size());
		followUsersTable = new JTable(followUsersTableModel);
		followUsersTable.setShowGrid(false);
		followUsersTableScrool = new JScrollPane(followUsersTable);
		followUsersTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					formConsultaUtilizadores.setSelectedUser(gestorDetalhesDeUtilizador.obterDetalhesPorBotao(followUsersTable.getValueAt(row, 0).toString()));
					obterDetalhesUtilizadorDaLista();
				}
			}
		});
		preencherTabelaDeSeguidores();
	}

	private void obterDetalhesUtilizadorDaLista() {
		limparTabelaDeSeguidores();
		fullNameText.setText(formConsultaUtilizadores.getSelectedUser().getName());
		emailText.setText(formConsultaUtilizadores.getSelectedUser().getEmail());
		cityDetailsText.setText(formConsultaUtilizadores.getSelectedUser().getCity());
		schoolDetailsText.setText(formConsultaUtilizadores.getSelectedUser().getSchool());
		preencherTabelaDeSeguidores();
		container.repaint();
		container.validate();
	}

	private void preencherTabelaDeSeguidores(){
		Botao botao = new Botao(followUsersTable,1);
		int count = 0;
		followUsersTableModel.setRowCount(formConsultaUtilizadores.getSelectedUser().getFollowers().size());
		for (String user : formConsultaUtilizadores.getSelectedUser().getFollowers()) {
			followUsersTable.setValueAt(user,count,0);
			followUsersTable.setValueAt("Info",count,1);
			count++;
		}
	}		

	private void limparTabelaDeSeguidores() {
		int count = 0;
		followUsersTableModel.setRowCount(30);
		while (count != followUsersTableModel.getRowCount()) {
			followUsersTable.setValueAt("",count,0);
			followUsersTable.setValueAt("",count,1);
			count++;
		}
	}
}
