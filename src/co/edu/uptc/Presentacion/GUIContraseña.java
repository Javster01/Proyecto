package co.edu.uptc.Presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUIContrase�a extends JFrame implements ActionListener {

	private JPanel panelFondo;
	private JPasswordField contrase�a;
	private JButton guardarContrase�a;

	public GUIContrase�a() {

		setSize(350, 200);
		setTitle("Contrase�a");
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
		contrase�a = new JPasswordField();
		contrase�a.setBounds(70, 46, 194, 35);
		panelFondo.add(contrase�a);

	}

	private void colocarBotones() {
		guardarContrase�a = new JButton();
		guardarContrase�a.setText("Guardar");
		guardarContrase�a.setBounds(119, 94, 94, 35);
		guardarContrase�a.addActionListener(this);
		panelFondo.add(guardarContrase�a);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == guardarContrase�a) {
			System.out.println("putos todos");
			guardarContrase�a();
		}
	}

	private void guardarContrase�a() {
		String contrase�aN;
		contrase�aN = contrase�a.getText();

	}

}
