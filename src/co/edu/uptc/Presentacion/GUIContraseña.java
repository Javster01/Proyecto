package co.edu.uptc.Presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIContraseņa extends JFrame implements ActionListener {

	private JPanel panelFondo;
	private JPasswordField contraseņa;
	private JButton guardarContraseņa;
	private JTextField texto;

	public GUIContraseņa() {

		setSize(350, 200);
		setTitle("Contraseņa");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		iniciarComponentes();

	}

	private void iniciarComponentes() {
		colocarPaneles();
		colocarEtiqueta();
		colocarBotones();
		//texto();

	}

	private void colocarPaneles() {
		panelFondo = new JPanel();
		panelFondo.setLayout(null);
		panelFondo.setBackground(Color.WHITE);
		this.getContentPane().add(panelFondo);
	}

	/*private void texto() {

		texto = new JTextField();
		texto.setText("Ingrese una contraseņa");
		texto.setBounds(116, 25, 115, 26);
		panelFondo.add(texto);

	}*/

	private void colocarEtiqueta() {
		contraseņa = new JPasswordField();
		contraseņa.setHorizontalAlignment(SwingConstants.CENTER);
		contraseņa.setBounds(75, 61, 194, 35);
		panelFondo.add(contraseņa);

	}

	private void colocarBotones() {
		guardarContraseņa = new JButton();
		guardarContraseņa.setText("Guardar");
		guardarContraseņa.setBounds(123, 106, 94, 35);
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
