package co.edu.uptc.Presentacion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import co.edu.uptc.Control.Control;
import java.awt.event.*;

/**
 * Ventana para mostrar todas las notas que existen
 * 
 * @author Luis Pinto
 * 
 **/

@SuppressWarnings("serial")
public class GUIListadoNotas extends JFrame {

	private JPanel panelFondo, panelEncabezado, panelAbajo, panelCentro, notasPanel;
	private JLabel labelTituloEncabezado;
	private JList<String> listaNotas;
	private JButton crearNuevaNota, editarNota, borrarNota, volverCarpetas, ordenP, ordenA;
	private String[] nombresArchivos;

	/**
	 * Constructor para crear y configurar el frame principal
	 * 
	 * @param String  ruta
	 * 
	 * @param Control c
	 * 
	 **/

	public GUIListadoNotas(String ruta, Control c) {

		// inicializar componentes

		panelEncabezado = new JPanel();
		panelFondo = new JPanel();
		labelTituloEncabezado = new JLabel();
		listaNotas = new JList<String>();
		editarNota = new JButton();
		crearNuevaNota = new JButton();
		borrarNota = new JButton();
		volverCarpetas = new JButton();
		ordenP = new JButton();
		ordenA = new JButton();
		notasPanel = new JPanel();

		// Configuracion del Frame

		ImageIcon im = new ImageIcon("RecursosGUI/notas1.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(im.getImage());
		setSize(360, 630);
		setTitle("Bloc De Notas");
		getContentPane().setBackground(Color.WHITE);

		// Adicionando componentes

		add(AreaTrabajo(ruta, c));

		setVisible(true);
	}

	/**
	 * Metodo para crear todos los paneles que hay dentro del frame principal
	 * 
	 * @param String  ruta
	 * 
	 * @param Control c
	 * 
	 * @return JPanel AreaTrabajo
	 * 
	 **/

	private JPanel AreaTrabajo(String ruta, Control c) {

		panelFondo.setBackground(Color.WHITE);

		// panel del ecabezado

		panelEncabezado.setBackground(Color.WHITE);
		panelEncabezado.setLayout(new BorderLayout());

		labelTituloEncabezado.setText("Notas");
		panelEncabezado.add(labelTituloEncabezado);

		ordenP.setIcon(new ImageIcon("RecursosGUI/orden2.png"));
		ordenP.setBackground(Color.WHITE);
		ordenP.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		ordenP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				c.organizarPrioritariamente();
				nombresArchivos = c.getNombresArchivos();
				refrescar(ruta, c);
				c.setOrden(false);

			}

		});

		ordenA.setIcon(new ImageIcon("RecursosGUI/orden1.png"));
		ordenA.setBackground(Color.WHITE);
		ordenA.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		ordenA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				c.organizarAlfabeticamente();
				nombresArchivos = c.getNombresArchivos();
				refrescar(ruta, c);
				c.setOrden(true);

			}

		});

		volverCarpetas.setIcon(new ImageIcon("RecursosGUI/flecha.png"));
		volverCarpetas.setBackground(Color.WHITE);
		volverCarpetas.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		volverCarpetas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

		JPanel pVT = new JPanel();
		pVT.setLayout(new BorderLayout());
		pVT.setBackground(Color.WHITE);

		JPanel pVolverCarpeta = new JPanel();
		pVolverCarpeta.setBackground(Color.WHITE);
		pVolverCarpeta.setLayout(new FlowLayout(FlowLayout.LEFT));
		pVolverCarpeta.add(volverCarpetas);

		JPanel pOrdenar = new JPanel();
		pOrdenar.setBackground(Color.WHITE);
		pOrdenar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pOrdenar.add(ordenA);
		pOrdenar.add(ordenP);

		JPanel pTituto = new JPanel();
		pTituto.setBackground(Color.WHITE);
		pTituto.setLayout(new FlowLayout(FlowLayout.CENTER));
		pTituto.add(labelTituloEncabezado);

		pVT.add(pVolverCarpeta, BorderLayout.WEST);
		pVT.add(pOrdenar, BorderLayout.EAST);
		pVT.add(pTituto, BorderLayout.SOUTH);

		panelEncabezado.add(pVT, BorderLayout.CENTER);

		// panel del centro

		panelCentro = new JPanel();
		panelCentro.setBackground(Color.WHITE);
		panelCentro.setBorder(new LineBorder(new Color(0, 0, 0, 0), 20));

		notasPanel.setPreferredSize(new DimensionUIResource(280, 305));
		notasPanel.setBackground(Color.WHITE);
		notasPanel.setBorder(new LineBorder(Color.BLACK));

		listaNotas.setFont(new FontUIResource("Calibri", Font.PLAIN, 20));
		listaNotas.setBorder(new LineBorder(new Color(0, 0, 0, 0), 14));

		nombresArchivos = c.getNombresArchivos();
		listaNotas.setListData(nombresArchivos);

		DefaultListCellRenderer cellRenderer = (DefaultListCellRenderer) listaNotas.getCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		listaNotas.setBorder(new LineBorder(Color.WHITE));
		listaNotas.setVisibleRowCount(10);

		notasPanel.add(listaNotas);

		JScrollPane barra = new JScrollPane(listaNotas);

		barra.setBorder(new LineBorder(new Color(0, 0, 0, 0), 5));
		barra.getVerticalScrollBar().setBackground(Color.WHITE);
		barra.setBackground(Color.BLACK);
		barra.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.BLACK;
			}
		});
		barra.setForeground(Color.WHITE);
		barra.setBackground(Color.WHITE);
		notasPanel.add(barra);

		panelCentro.add(notasPanel);

		// panel de abajo

		panelAbajo = new JPanel();
		panelAbajo.setBackground(Color.WHITE);
		panelAbajo.setBorder(new LineBorder(new Color(0, 0, 0, 0), 30));
		panelAbajo.setLayout(new BorderLayout(10, 10));

		JPanel iconos = new JPanel();
		iconos.setBackground(Color.WHITE);
		iconos.setLayout(new GridLayout(1, 3, 10, 10));

		editarNota.setIcon(new ImageIcon("RecursosGUI/editar.png"));
		editarNota.setBackground(Color.WHITE);
		editarNota.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		editarNota.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (listaNotas.getSelectedValue() != null) {

					int indice = listaNotas.getSelectedIndex();

					GUIMostrarNota frame = new GUIMostrarNota(indice, ruta, c);
					frame.setVisible(true);
					frame.setResizable(true);
					frame.setLocationRelativeTo(null);

					setVisible(false);

				} else if (listaNotas.getSelectedValue() == null) {

					UIManager.put("OptionPane.background", Color.white);
					UIManager.put("Panel.background", Color.white);

					JOptionPane.showMessageDialog(null, "No se ha seleccionado una nota para eliminar", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		borrarNota.setIcon(new ImageIcon("RecursosGUI/eliminar.png"));
		borrarNota.setBackground(Color.WHITE);
		borrarNota.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		borrarNota.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (listaNotas.getSelectedValue() == null) {

					UIManager.put("OptionPane.background", Color.white);
					UIManager.put("Panel.background", Color.white);

					JOptionPane.showMessageDialog(null, "No se ha seleccionado una nota para eliminar", "Advertencia",
							JOptionPane.WARNING_MESSAGE);

				} else {

					int indice = listaNotas.getSelectedIndex();
					c.eliminarNota(c.getNota(indice), ruta);

					refrescar(ruta, c);
				}

			}
		});

		iconos.add(editarNota);
		iconos.add(borrarNota);

		crearNuevaNota.setText("Crear una nueva nota");
		crearNuevaNota.setFont(new FontUIResource("Calibri", Font.PLAIN, 20));
		crearNuevaNota.setForeground(Color.WHITE);
		crearNuevaNota.setBackground(Color.BLACK);
		crearNuevaNota.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GUICrearNota frame = new GUICrearNota(ruta, c);
				frame.setVisible(true);
				frame.setResizable(true);
				frame.setLocationRelativeTo(null);

				setVisible(false);

			}
		});

		panelAbajo.add(iconos, BorderLayout.NORTH);
		panelAbajo.add(crearNuevaNota, BorderLayout.CENTER);

		// agrega todos los paneles al del fondo

		panelFondo.setLayout(new BorderLayout());
		panelFondo.add(panelEncabezado, BorderLayout.NORTH);
		panelFondo.add(panelCentro, BorderLayout.CENTER);
		panelFondo.add(panelAbajo, BorderLayout.SOUTH);

		return panelFondo;

	}

	/**
	 * Metodo para remover y volver a pintar los componentes dentro del frame
	 * principal
	 * 
	 **/

	public void actualizarPantalla() {

		getContentPane().removeAll();
		repaint();
	}

	/**
	 * Metodo para refrescar la ventana de listar las notas
	 * 
	 * @param String  ruta
	 * 
	 * @param Control c
	 * 
	 **/

	private void refrescar(String ruta, Control c) {

		panelEncabezado = new JPanel();
		panelFondo = new JPanel();
		labelTituloEncabezado = new JLabel();
		listaNotas = new JList<String>();
		editarNota = new JButton();
		crearNuevaNota = new JButton();
		borrarNota = new JButton();
		volverCarpetas = new JButton();
		ordenP = new JButton();
		ordenA = new JButton();
		notasPanel = new JPanel();

		actualizarPantalla();
		add(AreaTrabajo(ruta, c));
		setVisible(false);
		setVisible(true);

	}

}
