package co.edu.uptc.Presentacion;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.AttributeSet.ColorAttribute;

import co.edu.uptc.Control.Control;
import co.edu.uptc.Modelo.Nota;

import java.awt.*;

@SuppressWarnings("serial")
public class GUINota extends JFrame {

	private JPanel contentPane;

	public GUINota(String nombreNota) {

		Control c = new Control();
//		Nota n1 = c.buscarNota(1);
		
//		System.out.print(n1.getTitulo());
		ImageIcon im = new ImageIcon("RecursosGUI/icono1.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(im.getImage());
		setSize(360, 630);
		setTitle(nombreNota);
		getContentPane().setBackground(Color.WHITE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout(25, 25));

		setContentPane(contentPane);

		JPanel Encabezado = new JPanel();
		contentPane.add(Encabezado, BorderLayout.NORTH);

		JLabel Label3 = new JLabel();
		Encabezado.add(Label3);
		Encabezado.setBackground(getForeground());

		JPanel Contenedor = new JPanel();
		contentPane.add(Contenedor, BorderLayout.CENTER);
		Contenedor.setLayout(new BorderLayout());

		JTextArea contenido = new JTextArea();
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
