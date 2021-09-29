package co.edu.uptc.Presentacion;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.AttributeSet.ColorAttribute;

import co.edu.uptc.Modelo.Nota;

import java.awt.*;

public class GUINota extends JFrame {

	private JPanel contentPane;

	public GUINota(Nota n) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout(25, 25));

		setContentPane(contentPane);

		JPanel Encabezado = new JPanel();
		contentPane.add(Encabezado, BorderLayout.NORTH);

		JLabel Label3 = new JLabel(n.getTitulo());
		Encabezado.add(Label3);
//		Encabezado.setBackground(Color.decode("#"));
		Encabezado.setBackground(getForeground());

		JPanel Contenedor = new JPanel();
		contentPane.add(Contenedor, BorderLayout.CENTER);
		Contenedor.setLayout(new BorderLayout());

		JTextArea contenido = new JTextArea(n.getContenido());
		contenido.setLineWrap(true);
		contenido.setWrapStyleWord(true);

		JButton button1 = new JButton("Salir");
		Encabezado.add(Label3);

		JPanel Cont = new JPanel();
		Cont.setBackground(getForeground());
		contentPane.add(Cont, BorderLayout.SOUTH);
		Cont.add(button1);

//		Border b;
//		contenido.setBorder(b.paintBorder(contenido, null, contenido.getWidth(), contenido.getHeight(), defaultCloseOperation, defaultCloseOperation));
		Contenedor.add(contenido, BorderLayout.CENTER);

	}
}
