package co.edu.uptc.Presentacion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import co.edu.uptc.Control.Control;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GUIMostrarNota extends JFrame {

	private JPanel panelFondo, panelEncabezado, panelAbajo, panelCentro;
	private JTextField titulo;
	private JTextArea contenido;
	private JButton volver, guardarCambios, agregarContraseña;

	public GUIMostrarNota(String nombreNota) {

		// inicializar componentes

		panelFondo = new JPanel();
		panelEncabezado = new JPanel();
		panelCentro = new JPanel();
		panelAbajo = new JPanel();
		titulo = new JTextField();
		contenido = new JTextArea();
		volver = new JButton();
		guardarCambios = new JButton();
		agregarContraseña = new JButton();

		// Configuracion del Frame

		ImageIcon im = new ImageIcon("RecursosGUI/notas1.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(im.getImage());
		setSize(360, 630);
		setTitle(nombreNota);
		getContentPane().setBackground(Color.WHITE);

		// Adicionando componentes

		add(AreaTrabajo(nombreNota));

		setVisible(true);
	}

	private JPanel AreaTrabajo(String nombreNota) {

		panelFondo.setBackground(getForeground());

		// panel del ecabezado

		panelEncabezado.setBackground(getForeground());
//		panelEncabezado.setLayout(new FlowLayout(FlowLayout.CENTER));

		titulo.setText(nombreNota);
		titulo.setFont(new FontUIResource("TimesRoman", Font.PLAIN, 30));
		titulo.setBorder(new LineBorder(new Color(0, 0, 0, 0)));

		volver.setIcon(new ImageIcon("RecursosGUI/flecha.png"));
		volver.setBackground(Color.WHITE);
		volver.setBorder(new LineBorder(new Color(0, 0, 0, 0), 0));
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GUIListadoNotas notas = new GUIListadoNotas();
				notas.setVisible(true);
				notas.setResizable(true);
				notas.setLocationRelativeTo(null);
				setVisible(false);
			}

		});

		JPanel p = new JPanel();
		p.setBackground(getForeground());

		p.add(volver);
		p.add(titulo);

		panelEncabezado.add(p);

//		panelEncabezado.add(volver);
//		panelEncabezado.add(titulo);

		// panel del centro

		panelCentro.setBackground(getForeground());
		panelCentro.setBorder(new LineBorder(new Color(0, 0, 0, 0), 20));

		JPanel notasPanel = new JPanel();
		notasPanel.setPreferredSize(new DimensionUIResource(300, 320));
		notasPanel.setBackground(getForeground());
		notasPanel.setBorder(new LineBorder(Color.BLACK));

		Control c = new Control();

		contenido.setPreferredSize(new DimensionUIResource(285, 305));
		contenido.setLineWrap(true);
		contenido.setWrapStyleWord(true);
		contenido.setText(c.buscarNota(nombreNota).getContenido());
		contenido.setFont(new FontUIResource("Calibri", Font.PLAIN, 20));

		notasPanel.add(contenido);

		panelCentro.add(notasPanel);

		// panel de abajo

		panelAbajo.setBackground(getForeground());
		panelAbajo.setBorder(new LineBorder(new Color(0, 0, 0, 0), 30));

		JPanel iconos = new JPanel();
		iconos.setBackground(getForeground());
		iconos.setLayout(new GridLayout(1, 3, 10, 10));

		guardarCambios.setText("Guardar cambios");
		guardarCambios.setFont(new FontUIResource("Calibri", Font.PLAIN, 20));
		guardarCambios.setForeground(Color.WHITE);
		guardarCambios.setBackground(Color.BLACK);
		guardarCambios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!contenido.getText().equals(c.buscarNota(nombreNota))) {
					
					c.buscarNota(nombreNota).setContenido(nombreNota);
					c.eliminarNota(c.buscarNota(nombreNota));
					
					GUIListadoNotas notas = new GUIListadoNotas();
					notas.setVisible(true);
					notas.setResizable(true);
					notas.setLocationRelativeTo(null);
					setVisible(false);
					
				} else if (contenido.getText().equals(c.buscarNota(nombreNota))) {
					
					JOptionPane.showMessageDialog(null, "No se han hecho cambios en al nota");
				}
			}
		});

		panelAbajo.add(guardarCambios);

		// agrega todos los paneles al del fondo

		panelFondo.setLayout(new BorderLayout());
		panelFondo.add(panelEncabezado, BorderLayout.NORTH);
		panelFondo.add(panelCentro, BorderLayout.CENTER);
		panelFondo.add(panelAbajo, BorderLayout.SOUTH);

		return panelFondo;

	}

}
