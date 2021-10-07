package co.edu.uptc.Presentacion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import co.edu.uptc.Control.Control;
import java.awt.event.*;

/**
 * Ventana para mostrar el contenido de una nota
 * 
 * @author Luis Pinto
 * 
 **/

@SuppressWarnings("serial")
public class GUIMostrarNota extends JFrame {

	private JPanel panelFondo, panelEncabezado, panelAbajo, panelCentro;
	private JTextField titulo;
	private JTextArea contenido;
	private JButton volver, guardarCambios, agregarPrioridad;
	private JComboBox<String> prioridad;
	private boolean estado;
	private String[] prioridades;

	/**
	 * Constructor para crear y configurar el frame principal
	 * 
	 * @param int     indice
	 * 
	 * @param String  ruta
	 * 
	 * @param Control c
	 * 
	 **/

	public GUIMostrarNota(int indice, String ruta, Control c) {

		// inicializar componentes

		panelFondo = new JPanel();
		panelEncabezado = new JPanel();
		panelCentro = new JPanel();
		panelAbajo = new JPanel();
		titulo = new JTextField();
		contenido = new JTextArea();
		volver = new JButton();
		guardarCambios = new JButton();
		agregarPrioridad = new JButton(new ImageIcon("RecursosGUI/orden2.png"));
		prioridades = new String[4];
		prioridades[0] = "Ninguna";
		prioridades[1] = "Baja";
		prioridades[2] = "Media";
		prioridades[3] = "Alta";
		prioridad = new JComboBox<>(prioridades);
		estado = false;

		// Configuracion del Frame

		ImageIcon im = new ImageIcon("RecursosGUI/notas1.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(im.getImage());
		setSize(360, 630);
		setTitle(c.getNota(indice).getTitulo());
		getContentPane().setBackground(Color.WHITE);

		// Adicionando componentes

		add(areaTrabajo(indice, ruta, c));

		setVisible(true);
	}

	/**
	 * Metodo para crear todos los paneles que hay dentro del frame principal
	 * 
	 * @param int     indice
	 * 
	 * @param String  ruta
	 * 
	 * @param Control c
	 * 
	 * @return JPanel AreaTrabajo
	 * 
	 **/

	private JPanel areaTrabajo(int indice, String ruta, Control c) {

		panelFondo.setBackground(getForeground());

		// panel del ecabezado

		panelEncabezado.setBackground(getForeground());
		panelEncabezado.setLayout(new BorderLayout());

		titulo.setPreferredSize(new DimensionUIResource(300, 30));
		titulo.setText(c.getNota(indice).getTitulo());
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new FontUIResource("Times New Roman", Font.PLAIN, 30));
		titulo.setBorder(new LineBorder(new Color(0, 0, 0, 0), 0));

		prioridad.setBackground(Color.WHITE);
		prioridad.setFont(new FontUIResource("Calibri", Font.PLAIN, 15));

		JPanel pComboBox = new JPanel();
		pComboBox.setBackground(getForeground());
		pComboBox.add(prioridad);
		pComboBox.setVisible(false);

		agregarPrioridad.setBackground(Color.WHITE);
		agregarPrioridad.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		agregarPrioridad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (estado == false) {

					estado = true;

					pComboBox.setVisible(estado);
					prioridad.setSelectedIndex(c.getNota(indice).getPrioridad());

				} else if (estado == true) {

					estado = false;

					pComboBox.setVisible(estado);
				}
			}
		}

		);

		volver.setIcon(new ImageIcon("RecursosGUI/flecha.png"));
		volver.setBackground(Color.WHITE);
		volver.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GUIListadoNotas notas = new GUIListadoNotas(ruta, c);
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
		pContrasena.add(pComboBox);
		pContrasena.add(agregarPrioridad);

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
						|| !titulo.getText().equals(c.getNota(indice).getTitulo())
						|| prioridad.getSelectedIndex() != c.getNota(indice).getPrioridad()) {

					c.editarArchivo(c.getNota(indice), titulo.getText(), contenido.getText(), ruta,
							prioridad.getSelectedIndex(), c.getNota(indice).getContrasena());

					GUIListadoNotas notas = new GUIListadoNotas(ruta, c);
					notas.setVisible(true);
					notas.setResizable(false);
					notas.setLocationRelativeTo(null);
					setVisible(false);

				} else if (contenido.getText().equals(c.getNota(indice).getContenido())
						&& titulo.getText().equals(c.getNota(indice).getTitulo())
						&& prioridad.getSelectedIndex() == c.getNota(indice).getPrioridad()) {

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
