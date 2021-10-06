package co.edu.uptc.Presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUIContrasena extends JFrame implements ActionListener {

	private JPanel panelFondo;
	private JPasswordField contrasena;
	private JButton guardarContrasena;
	// private JTextField texto;

	public GUIContrasena() {

		setSize(350, 200);
		setTitle("Contrasena");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		iniciarComponentes();

	}

	private void iniciarComponentes() {
		colocarPaneles();
		colocarEtiqueta();
		colocarBotones();
		// texto();

	}

	private void colocarPaneles() {
		panelFondo = new JPanel();
		panelFondo.setLayout(null);
		panelFondo.setBackground(Color.WHITE);
		this.getContentPane().add(panelFondo);
	}

	private void colocarEtiqueta() {
		contrasena = new JPasswordField();
		contrasena.setHorizontalAlignment(SwingConstants.CENTER);
		contrasena.setBounds(75, 61, 194, 35);
		panelFondo.add(contrasena);

	}

	private void colocarBotones() {
		guardarContrasena = new JButton();
		guardarContrasena.setText("Guardar");
		guardarContrasena.setBounds(123, 106, 94, 35);
		guardarContrasena.addActionListener(this);
		panelFondo.add(guardarContrasena);

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == guardarContrasena) {
			System.out.println("putos todos");
			guardarContrasena();
		}
	}

	private void guardarContrasena() {
		String ContrasenaN;
		ContrasenaN = contrasena.getText();

	}
}
