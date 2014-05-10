package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controlo.GestorConsultaDeUtilizadores;
import dados.Botao;
import dados.Utilizador;

public class FormConsultaUtilizadores {
	
	private JFrame window;;
	private Container container;
	private FormDetalhesUtilizador formDetalhesUtilizador;
	private GestorConsultaDeUtilizadores gestorConsultaDeUtilizadores;
	
	private String currentUsername = "Aaron Leffler";
	private String currentEmail = "Aaron_Leffler@felicity.com";
	private Utilizador selectedUser;

	private Vector<String> cities = new Vector<String>();
	private Vector<String> schools = new Vector<String>();
	private Vector<Utilizador> users = new Vector<Utilizador>();
	private Vector<Utilizador> usersTemp = new Vector<Utilizador>();
	private String columnsNames[] = {"Nome","Cidade","Escola","A Seguir"};
	
	private JPanel formPanel = new JPanel();
	private JPanel topFormPanel = new JPanel();
	private JPanel centerFormPanel = new JPanel();
	private JPanel bottomFormPanel = new JPanel();
	private JLabel usernameLabel = new JLabel("Nome");
	private JTextField usernameTextField = new JTextField(35);
	private JCheckBox followUser = new JCheckBox("A Seguir");
	private JLabel cityLabel = new JLabel("Cidade");
	private JComboBox cityComboBox;
	private String cityComboBoxNeutralChoice = "Selecione uma cidade";
	private JLabel schoolLabel = new JLabel("Escola");
	private JComboBox schoolComboBox;
	private String schoolComboBoxNeutralChoice = "Selecione uma escola";
	private JButton applyFilterButton = new JButton("Aplicar Filtro");
	private JButton viewAllButton = new JButton("Ver Todos");
	private JTable usersTable;
	private DefaultTableModel tableModel;
	private JScrollPane tableScrool;
	private RowSorter<TableModel> usersTableSorter;
	private JButton detailsButton = new JButton("Detalhes");
	private JButton yourProfileButton = new JButton("Seu Perfil");
	private JButton exitButton = new JButton("Sair");
	
	public FormConsultaUtilizadores(JFrame window, Container container){
		this.window = window;
		this.container = container;
		formDetalhesUtilizador = new FormDetalhesUtilizador(window, container, this);
		gestorConsultaDeUtilizadores = new GestorConsultaDeUtilizadores();
	}
	
	public void construirFormularioConsulta() {
		container.removeAll();

		if(!users.isEmpty())
			users.removeAllElements();

		if(usersTable != null)
			usersTable.removeAll();

		obterCidades();
		obterEscolas();
		formPanel.setLayout(new BorderLayout());
		// TOP PANEL
		topFormPanel.add(usernameLabel);
		topFormPanel.add(usernameTextField);

		// CENTER PANEL
		centerFormPanel.setLayout(null);	
		centerFormPanel.add(followUser);
		centerFormPanel.add(cityLabel);
		centerFormPanel.add(cityComboBox);
		centerFormPanel.add(schoolLabel);
		centerFormPanel.add(schoolComboBox);
		centerFormPanel.add(applyFilterButton);
		applyFilterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				users.removeAllElements();
				aplicarFiltro();
			}
		});
		centerFormPanel.add(viewAllButton);
		viewAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				users.removeAllElements();
				verTodosUtilizadores();
			}
		});
		construirTabelaUtilizadores();
		centerFormPanel.add(tableScrool);
		centerFormPanelSetComponentsBounds();

		// BOTTOM PANEL
		bottomFormPanel.add(detailsButton);
		detailsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				obterDetalhesUtilizadorSelecionado();
			}
		});
		bottomFormPanel.add(yourProfileButton);
		yourProfileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedUser = formDetalhesUtilizador.mostrarDetalhes(currentUsername);
				formDetalhesUtilizador.construirFormularioDetalhes(selectedUser.getName(), selectedUser.getCity(), selectedUser.getSchool(), selectedUser.getEmail());
			}
		});
		bottomFormPanel.add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}
		});

		formPanel.add(topFormPanel, BorderLayout.NORTH);
		formPanel.add(centerFormPanel, BorderLayout.CENTER);
		formPanel.add(bottomFormPanel, BorderLayout.SOUTH);
		container.add(formPanel);
		tableModel.setRowCount(0);
		container.repaint();
		container.validate();
	}
	
	private void obterDetalhesUtilizadorSelecionado() {
		if(usersTable.getSelectedRow() == -1)
			JOptionPane.showMessageDialog(window, "Para aceder aos detalhes de um utilizador tem de selecionar um utilizador na tabela", null, JOptionPane.WARNING_MESSAGE);
		else{
			selectedUser = formDetalhesUtilizador.mostrarDetalhes(usersTable.getValueAt(usersTable.getSelectedRow(),0).toString());
			formDetalhesUtilizador.construirFormularioDetalhes(selectedUser.getName(), selectedUser.getCity(), selectedUser.getSchool(), selectedUser.getEmail());
		}
	}
	
	private void centerFormPanelSetComponentsBounds() {
		Insets insets = centerFormPanel.getInsets();

		followUser.setBounds(55 + insets.left, 45 + insets.top, followUser.getPreferredSize().width, followUser.getPreferredSize().height);
		cityLabel.setBounds(270 + insets.left, 20 + insets.top, cityLabel.getPreferredSize().width, cityLabel.getPreferredSize().height);
		cityComboBox.setBounds(320 + insets.left, 15 + insets.top, cityComboBox.getPreferredSize().width, cityComboBox.getPreferredSize().height);
		schoolLabel.setBounds(270 + insets.left, 80 + insets.top, schoolLabel.getPreferredSize().width, schoolLabel.getPreferredSize().height);
		schoolComboBox.setBounds(320 + insets.left, 75 + insets.top, schoolComboBox.getPreferredSize().width, schoolComboBox.getPreferredSize().height);
		applyFilterButton.setBounds(55 + insets.left, 135 + insets.top, applyFilterButton.getPreferredSize().width, applyFilterButton.getPreferredSize().height);
		viewAllButton.setBounds(185 + insets.left, 135 + insets.top, viewAllButton.getPreferredSize().width, viewAllButton.getPreferredSize().height);
		tableScrool.setBounds(55 + insets.left, 185 + insets.top, usersTable.getPreferredSize().width, usersTable.getPreferredSize().height-185);
	}
	
	private void obterCidades() {
		cities.removeAllElements();
		cities = gestorConsultaDeUtilizadores.obterCidades();
		cities.add(0, cityComboBoxNeutralChoice);
		cityComboBox = new JComboBox(cities);
	}	

	private void obterEscolas() {
		schools.removeAllElements();
		schools = gestorConsultaDeUtilizadores.obterEscolas();
		schools.add(0, schoolComboBoxNeutralChoice);
		schoolComboBox = new JComboBox(schools);
	}

	private void consulta(String city, String school, String name, boolean isFollower){
		users.removeAllElements();
		users = gestorConsultaDeUtilizadores.consulta(city, school, name, isFollower);
	}

	private void construirTabelaUtilizadores(){
		tableModel = new DefaultTableModel(columnsNames,30);
		usersTable = new JTable(tableModel);
		usersTableSorter = new TableRowSorter<TableModel>(usersTable.getModel()); 
		usersTable.setRowSorter(usersTableSorter);
		usersTable.setShowGrid(false);
		tableScrool = new JScrollPane(usersTable);

		usersTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					if(column == 3 && usersTable.getValueAt(row, column).equals("Não")){
						usersTable.setValueAt("Sim", row, column);
						deixarSeguir();
					}else if(column == 3 && usersTable.getValueAt(row, column).equals("Sim")){
						seguir();
						usersTable.setValueAt("Não", row, column);
					}
					container.repaint();
					container.validate();
				}
			}
		});
	}

	private void seguir() {
		selectedUser = formDetalhesUtilizador.mostrarDetalhes(usersTable.getValueAt(usersTable.getSelectedRow(),0).toString());
		gestorConsultaDeUtilizadores.seguir(currentEmail, selectedUser.getEmail());
		JOptionPane.showMessageDialog(window,"Neste momento passou a seguir o utilizador " + selectedUser.getName());
	}

	private void deixarSeguir() {
		selectedUser = formDetalhesUtilizador.mostrarDetalhes(usersTable.getValueAt(usersTable.getSelectedRow(),0).toString());
		gestorConsultaDeUtilizadores.deixarSeguir(currentEmail, selectedUser.getEmail());
		JOptionPane.showMessageDialog(window,"Neste momento deixou de seguir o utilizador " + selectedUser.getName());
	}

	private void aplicarFiltro(){
		if(usernameTextField.getText().isEmpty() && 
				cityComboBox.getItemAt(cityComboBox.getSelectedIndex()).toString().equals(cityComboBoxNeutralChoice) &&
				schoolComboBox.getItemAt(schoolComboBox.getSelectedIndex()).toString().equals(schoolComboBoxNeutralChoice) && 
				followUser.isSelected() == false)
			verTodosUtilizadores();
		else{
			if(cityComboBox.getItemAt(cityComboBox.getSelectedIndex()).toString().equals(cityComboBoxNeutralChoice) && 
					schoolComboBox.getItemAt(schoolComboBox.getSelectedIndex()).toString().equals(schoolComboBoxNeutralChoice)){
				consulta("", "", usernameTextField.getText(), false);
			}else if(cityComboBox.getItemAt(cityComboBox.getSelectedIndex()).toString().equals(cityComboBoxNeutralChoice))
				consulta("", schoolComboBox.getItemAt(schoolComboBox.getSelectedIndex()).toString(), usernameTextField.getText(), false);
			else if(schoolComboBox.getItemAt(schoolComboBox.getSelectedIndex()).toString().equals(schoolComboBoxNeutralChoice))
				consulta(cityComboBox.getItemAt(cityComboBox.getSelectedIndex()).toString(), "", usernameTextField.getText(), false);
			else
				consulta(cityComboBox.getItemAt(cityComboBox.getSelectedIndex()).toString(), schoolComboBox.getItemAt(schoolComboBox.getSelectedIndex()).toString(), usernameTextField.getText(), false);

			preencherTabelaUtilizadores();
		}
	}

	private void verTodosUtilizadores(){
		consulta("", "", "", false);
		preencherTabelaUtilizadores();
	}

	private void preencherTabelaUtilizadores(){
		Botao botao = new Botao(usersTable,3);
		int count = 0;
		if(followUser.isSelected())
			apagarUtilizadoresNaoSeguidores();

		tableModel.setRowCount(users.size());
		for (Utilizador user : users) {	
			usersTable.setValueAt(user.getName(),count,0);
			usersTable.setValueAt(user.getCity(),count,1);
			usersTable.setValueAt(user.getSchool(),count,2);

			if(!eSeguidor(currentEmail, user.getEmail())){
				usersTable.setValueAt("Sim",count,3);
			}else if(eSeguidor(currentEmail, user.getEmail())){
				usersTable.setValueAt("Não",count,3);
			}
			count++;
		}
	}

	private void apagarUtilizadoresNaoSeguidores() {
		for (Utilizador user : users) {
			if(eSeguidor(currentEmail, user.getEmail())){
				usersTemp.add(user);
			}		
		}
		users = usersTemp;
	}

	private boolean eSeguidor(String currentEmail, String email) {
		return gestorConsultaDeUtilizadores.isFollower(currentEmail, email);
	}
	
	public String getCurrentUsername() {
		return currentUsername;
	}

	public String getCurrentEmail() {
		return currentEmail;
	}

	public Utilizador getSelectedUser(){
		return selectedUser;
	}
	
	public void setSelectedUser(Utilizador selectedUser){
		this.selectedUser = selectedUser;
	}
	
	public void setTableModelRowCount(int num){
		tableModel.setRowCount(num);
	}
}
