package View;

import java.awt.*;
import javax.swing.*;

public class UI {

	private FormConsultaUtilizadores formConsultaUtilizadores;
	
	private JFrame window = new JFrame("Eat & Drink");
	private Container container = window.getContentPane();
	private Toolkit toolkit = Toolkit.getDefaultToolkit();

	public UI(){
		window.setSize(500, 600);
		window.setLocation(toolkit.getScreenSize().width/2 - window.getWidth()/2, toolkit.getScreenSize().height/2 - window.getHeight()/2);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		formConsultaUtilizadores = new FormConsultaUtilizadores(window, container);
		formConsultaUtilizadores.construirFormularioConsulta();
	}

	private void run() {
		window.setVisible(true);
	}

	public static void main(String[] args) {
		new UI().run();
	}
}
