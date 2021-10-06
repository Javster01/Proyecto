package co.edu.uptc.Presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIContraseņa extends JFrame implements ActionListener {

	private JPanel panelFondo;
	private JPasswordField contraseņa;
	private JButton guardarContraseņa;

	public GUIContraseņa() {

		setSize(350, 200);
		setTitle("Contraseņa");
		setLocationRelativeTo(null);
		iniciarComponentes();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	private void iniciarComponentes() {
		colocarPaneles();
		colocarEtiqueta();
		colocarBotones();

	}

	private void colocarPaneles() {
		panelFondo = new JPanel();
		panelFondo.setLayout(null);
		panelFondo.setBackground(Color.WHITE);
		this.getContentPane().add(panelFondo);
	}

	private void colocarEtiqueta() {
		contraseņa = new JPasswordField();
		contraseņa.setBounds(70, 46, 194, 35);
		panelFondo.add(contraseņa);

	}

	private void colocarBotones() {
		guardarContraseņa = new JButton();
		guardarContraseņa.setText("Guardar");
		guardarContraseņa.setBounds(119, 94, 94, 35);
		guardarContraseņa.addActionListener(this);
		panelFondo.add(guardarContraseņa);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == guardarContraseņa) {
			System.out.println("putos todos");
			guardarContraseņa();
		}
	}

	private void guardarContraseņa() {
		String contraseņaN;
		contraseņaN = contraseņa.getText();

	}

}
