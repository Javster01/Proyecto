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
	private JButton volver, guardarCambios, agregarContrasena, agregarPrioridad;

	public GUIMostrarNota(int indice, String ruta) {

		// inicializar componentes

		panelFondo = new JPanel();
		panelEncabezado = new JPanel();
		panelCentro = new JPanel();
		panelAbajo = new JPanel();
		titulo = new JTextField();
		contenido = new JTextArea();
		volver = new JButton();
		guardarCambios = new JButton();
		agregarContrasena = new JButton();
		agregarPrioridad = new JButton(new ImageIcon("RecursosGUI/priorizar.png"));

		// Configuracion del Frame

		ImageIcon im = new ImageIcon("RecursosGUI/notas1.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(im.getImage());
		setSize(360, 630);
		Control c = new Control(ruta);
		setTitle(c.getNota(indice).getTitulo());
		getContentPane().setBackground(Color.WHITE);

		// Adicionando componentes

		add(areaTrabajo(indice, ruta));

		setVisible(true);
	}

	private JPanel areaTrabajo(int indice, String ruta) {

		panelFondo.setBackground(getForeground());
		Control c = new Control(ruta);

		// panel del ecabezado

		panelEncabezado.setBackground(getForeground());
		panelEncabezado.setLayout(new BorderLayout());

		titulo.setPreferredSize(new DimensionUIResource(300, 30));
		titulo.setText(c.getNota(indice).getTitulo());
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new FontUIResource("TimesRoman", Font.PLAIN, 30));
		titulo.setBorder(new LineBorder(new Color(0, 0, 0, 0), 0));

		agregarContrasena.setIcon(new ImageIcon("RecursosGUI/bloquear.png"));
		agregarContrasena.setBackground(Color.WHITE);
		agregarContrasena.setBorder(new LineBorder(new Color(0, 0, 0, 0)));

		agregarPrioridad.setBackground(Color.WHITE);
		agregarPrioridad.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		agregarPrioridad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GUIListadoNotas notas = new GUIListadoNotas(ruta);
				notas.setVisible(true);
				notas.setResizable(true);
				notas.setLocationRelativeTo(null);
				setVisible(false);
			}

		});

		volver.setIcon(new ImageIcon("RecursosGUI/flecha.png"));
		volver.setBackground(Color.WHITE);
		volver.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GUIListadoNotas notas = new GUIListadoNotas(ruta);
				notas.setVisible(true);
				notas.setResizable(true);
				notas.setLocationRelativeTo(null);
				setVisible(false);
			}

		});

		JPanel pVT = new JPanel();

		pVT.setLayout(new BorderLayout());
		pVT.setBackground(getForeground());

		JPanel pVolver = new JPanel();
		pVolver.setBackground(getForeground());
		pVolver.setLayout(new FlowLayout(FlowLayout.LEFT));
		pVolver.add(volver);

		JPanel pContrasena = new JPanel();
		pContrasena.setBackground(getForeground());
		pContrasena.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pContrasena.add(agregarPrioridad);
		pContrasena.add(agregarContrasena);

		JPanel pTituto = new JPanel();
		pTituto.setBackground(getForeground());
		pTituto.setLayout(new FlowLayout(FlowLayout.CENTER));
		pTituto.add(titulo);

		pVT.add(pVolver, BorderLayout.WEST);
		pVT.add(pContrasena, BorderLayout.EAST);
		pVT.add(pTituto, BorderLayout.SOUTH);

		panelEncabezado.add(pVT, BorderLayout.CENTER);

		// panel del centro

		panelCentro.setBackground(getForeground());
		panelCentro.setBorder(new LineBorder(new Color(0, 0, 0, 0), 20));

		JPanel notasPanel = new JPanel();
		notasPanel.setPreferredSize(new DimensionUIResource(300, 320));
		notasPanel.setBackground(getForeground());
		notasPanel.setBorder(new LineBorder(Color.BLACK));

		contenido.setPreferredSize(new DimensionUIResource(285, 305));
		contenido.setLineWrap(true);
		contenido.setWrapStyleWord(true);
		contenido.setText(c.getNota(indice).getContenido());

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

				if (!contenido.getText().equals(c.getNota(indice).getContenido())
						|| !titulo.getText().equals(c.getNota(indice).getTitulo())) {

					c.editarArchivo(c.getNota(indice), titulo.getText(), contenido.getText(), ruta,
							c.getNota(indice).getUrgencia());

					GUIListadoNotas notas = new GUIListadoNotas(ruta);
					notas.setVisible(true);
					notas.setResizable(false);
					notas.setLocationRelativeTo(null);
					setVisible(false);

				} else if (contenido.getText().equals(c.getNota(indice).getContenido())
						&& titulo.getText().equals(c.getNota(indice).getTitulo())) {

					UIManager.put("OptionPane.background", Color.white);
					UIManager.put("Panel.background", Color.white);

					JOptionPane.showMessageDialog(null, "No se ha realizado ningun cambio", "Advertencia",
							JOptionPane.WARNING_MESSAGE);

				} else if (titulo.getText().isEmpty() || titulo.getText().isEmpty()) {

					UIManager.put("OptionPane.background", Color.white);
					UIManager.put("Panel.background", Color.white);

					JOptionPane.showMessageDialog(null, "Este titulo no puede estar vacio", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
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
