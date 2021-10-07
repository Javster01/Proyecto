package co.edu.uptc.Presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import co.edu.uptc.Control.Control;
import co.edu.uptc.Modelo.Nota;

@SuppressWarnings("serial")
public class GUIContrasena extends JFrame {

	private JPanel panelFondo;
	private JPasswordField contrasena;
	private JButton guardarContrasena;
	// private JTextField texto;

	public GUIContrasena(Control c, Nota n, String ruta) {

		setSize(350, 200);
		setTitle("Contrasena");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		iniciarComponentes(c, n, ruta);

	}

	private void iniciarComponentes(Control c, Nota n, String ruta) {
		colocarPaneles();
		colocarEtiqueta();
		colocarBotones(c, n, ruta);
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

	private void colocarBotones(Control c, Nota n, String ruta) {
		guardarContrasena = new JButton();
		guardarContrasena.setText("Guardar");
		guardarContrasena.setBounds(123, 106, 94, 35);
		guardarContrasena.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (contrasena.getText() != "") {
					c.editarArchivo(n, n.getTitulo(), n.getContenido(), ruta, n.getPrioridad(), contrasena.getText());

					GUIListadoNotas gui = new GUIListadoNotas(ruta, c);
					gui.setVisible(true);
					gui.setResizable(true);
					gui.setLocationRelativeTo(null);
					dispose();

				}

			}

		});

		panelFondo.add(guardarContrasena);

	}

}
