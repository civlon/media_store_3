package ui;

import javax.swing.*;

public class GUI {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Datenbank-Praktikum. Testat 3.");		
		frame.setSize(600, 300);
		
		JPanel panel = new JPanel();
		
		JTextField welcomeText = new JTextField("Herzlich Willkommen zur Abnahme des dritten Testats im Modul Datenbank-Praktikum.");
		
		panel.add(welcomeText);
		frame.add(panel);
		frame.setVisible(true);
	}

}
