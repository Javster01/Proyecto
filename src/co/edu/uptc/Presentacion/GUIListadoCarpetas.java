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
 * Ventana para mostrar todas las carpetas que existen
 * 
 * @author Luis Pinto
 * 
 **/

@SuppressWarnings("serial")
public class GUIListadoCarpetas extends JFrame {

	private JPanel panelFondo, panelEncabezado, panelAbajo, panelCentro, carpetasPanel;
	private JLabel labelTituloEncabezado;
	private JList<String> listaCarpetas;
	private JButton crearNuevaCarpeta, verCarpeta, borrarCarpeta;
	private String[] nombresCarpetas;
	private Control c;
	private String ruta = "Carpetas";

	/**
	 * Constructor para crear y configurar el frame principal
	 * 
	 * @param String  ruta
	 * 
	 * @param Control c
	 * 
	 **/

	public GUIListadoCarpetas() {

		// inicializar componentes

		panelEncabezado = new JPanel();
		panelFondo = new JPanel();
		labelTituloEncabezado = new JLabel();
		listaCarpetas = new JList<String>();
		verCarpeta = new JButton();
		crearNuevaCarpeta = new JButton();
		borrarCarpeta = new JButton();
		carpetasPanel = new JPanel();
		c = new Control(ruta);

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

		// panel del encabezado

		panelEncabezado.setBackground(Color.WHITE);
		panelEncabezado.setLayout(new BorderLayout());

		labelTituloEncabezado.setText("Carpetas");
		labelTituloEncabezado.setFont(new FontUIResource("Times New Roman", Font.PLAIN, 50));
		panelEncabezado.add(labelTituloEncabezado);

		JPanel pVT = new JPanel();
		pVT.setLayout(new BorderLayout());
		pVT.setBackground(Color.WHITE);

		JPanel pTituto = new JPanel();
		pTituto.setBackground(Color.WHITE);
		pTituto.setLayout(new FlowLayout(FlowLayout.CENTER));
		pTituto.add(labelTituloEncabezado);

		pVT.add(pTituto, BorderLayout.SOUTH);

		panelEncabezado.add(pVT, BorderLayout.CENTER);

		// panel del centro

		panelCentro = new JPanel();
		panelCentro.setBackground(Color.WHITE);
		panelCentro.setBorder(new LineBorder(new Color(0, 0, 0, 0), 20));

		carpetasPanel.setPreferredSize(new DimensionUIResource(280, 305));
		carpetasPanel.setBackground(Color.WHITE);
		carpetasPanel.setBorder(new LineBorder(Color.BLACK));

		listaCarpetas.setFont(new FontUIResource("Calibri", Font.PLAIN, 20));
		listaCarpetas.setBorder(new LineBorder(new Color(0, 0, 0, 0), 14));

		nombresCarpetas = c.getNombresCarpetas();
		listaCarpetas.setListData(nombresCarpetas);

		DefaultListCellRenderer cellRenderer = (DefaultListCellRenderer) listaCarpetas.getCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		listaCarpetas.setBorder(new LineBorder(Color.WHITE));
		listaCarpetas.setVisibleRowCount(10);

		carpetasPanel.add(listaCarpetas);

		JScrollPane barra = new JScrollPane(listaCarpetas);

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
		carpetasPanel.add(barra);

		panelCentro.add(carpetasPanel);

		// panel de abajo

		panelAbajo = new JPanel();
		panelAbajo.setBackground(Color.WHITE);
		panelAbajo.setBorder(new LineBorder(new Color(0, 0, 0, 0), 30));
		panelAbajo.setLayout(new BorderLayout(10, 10));

		JPanel iconos = new JPanel();
		iconos.setBackground(Color.WHITE);
		iconos.setLayout(new GridLayout(1, 3, 10, 10));

		verCarpeta.setIcon(new ImageIcon("RecursosGUI/carpeta.png"));
		verCarpeta.setBackground(Color.WHITE);
		verCarpeta.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		verCarpeta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (listaCarpetas.getSelectedValue() != null) {
					
					String ruta = "Carpetas/" + listaCarpetas.getSelectedValue();
					
					Control c = new Control(ruta);

					GUIListadoNotas guiNotas = new GUIListadoNotas(ruta, c);
					guiNotas.setVisible(true);
					guiNotas.setResizable(true);
					guiNotas.setLocationRelativeTo(null);

					setVisible(false);

				} else if (listaCarpetas.getSelectedValue() == null) {

					UIManager.put("OptionPane.background", Color.white);
					UIManager.put("Panel.background", Color.white);

					JOptionPane.showMessageDialog(null, "No se ha seleccionado una nota para eliminar", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		borrarCarpeta.setIcon(new ImageIcon("RecursosGUI/carpetaBorrar.png"));
		borrarCarpeta.setBackground(Color.WHITE);
		borrarCarpeta.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		borrarCarpeta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (listaCarpetas.getSelectedValue() == null) {

					UIManager.put("OptionPane.background", Color.white);
					UIManager.put("Panel.background", Color.white);

					JOptionPane.showMessageDialog(null, "No se ha seleccionado una nota para eliminar", "Advertencia",
							JOptionPane.WARNING_MESSAGE);

				} else {

					int indice = listaCarpetas.getSelectedIndex();
					c.eliminarCarpeta(c.getCarpeta(indice), ruta);

					refrescar(ruta, c);
				}

			}
		});

		iconos.add(verCarpeta);
		iconos.add(borrarCarpeta);

		crearNuevaCarpeta.setText("Crear una nueva carpeta");
		crearNuevaCarpeta.setFont(new FontUIResource("Calibri", Font.PLAIN, 20));
		crearNuevaCarpeta.setForeground(Color.WHITE);
		crearNuevaCarpeta.setBackground(Color.BLACK);
		crearNuevaCarpeta.addActionListener(new ActionListener() {

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
		panelAbajo.add(crearNuevaCarpeta, BorderLayout.CENTER);

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
		listaCarpetas = new JList<String>();
		verCarpeta = new JButton();
		crearNuevaCarpeta = new JButton();
		borrarCarpeta = new JButton();
		carpetasPanel = new JPanel();

		actualizarPantalla();
		add(AreaTrabajo(ruta, c));
		setVisible(false);
		setVisible(true);

	}

}
