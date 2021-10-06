package co.edu.uptc.Presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIContraseña extends JFrame implements ActionListener {

	private JPanel panelFondo;
	private JPasswordField contraseña;
	private JButton guardarContraseña;
	private JTextField texto;

	public GUIContraseña() {

		setSize(350, 200);
		setTitle("Contraseña");
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
		texto.setText("Ingrese una contraseña");
		texto.setBounds(116, 25, 115, 26);
		panelFondo.add(texto);

	}*/

	private void colocarEtiqueta() {
		contraseña = new JPasswordField();
		contraseña.setHorizontalAlignment(SwingConstants.CENTER);
		contraseña.setBounds(75, 61, 194, 35);
		panelFondo.add(contraseña);

	}

	private void colocarBotones() {
		guardarContraseña = new JButton();
		guardarContraseña.setText("Guardar");
		guardarContraseña.setBounds(123, 106, 94, 35);
		guardarContraseña.addActionListener(this);
		panelFondo.add(guardarContraseña);

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == guardarContraseña) {
			System.out.println("putos todos");
			guardarContraseña();
		}
	}

	private void guardarContraseña() {
		String contraseñaN;
		contraseñaN = contraseña.getText();

	}
}
