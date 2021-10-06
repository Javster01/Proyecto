package co.edu.uptc.Presentacion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import co.edu.uptc.Control.Control;
import java.awt.event.*;

/**
 * Ventana para crear una nueva nota
 * 
 * @author Luis Pinto
 * 
 **/

@SuppressWarnings("serial")
public class GUICrearCarpeta extends JFrame {

	private JPanel panelFondo, panelEncabezado, panelAbajo;
	private JTextField nombre;
	private JButton volver, guardar;

	/**
	 * Constructor para crear y configurar el frame principal
	 * 
	 * @param String  ruta
	 * 
	 * @param Control c
	 * 
	 **/

	public GUICrearCarpeta(String ruta, Control c) {

		// inicializar componentes

		panelFondo = new JPanel();
		panelEncabezado = new JPanel();
		panelAbajo = new JPanel();
		nombre = new JTextField();
		volver = new JButton();
		guardar = new JButton();

		// Configuracion del Frame

		ImageIcon im = new ImageIcon("RecursosGUI/notas1.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(im.getImage());
		setSize(360, 210);
		setTitle("Crear Carpeta");
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

		panelFondo.setBackground(getForeground());

		// panel del ecabezado

		panelEncabezado.setBackground(getForeground());
		panelEncabezado.setLayout(new BorderLayout());

		nombre.setFont(new FontUIResource("Times New Roman", Font.PLAIN, 30));
		nombre.setBorder(new LineBorder(Color.BLACK));
		nombre.setPreferredSize(new DimensionUIResource(300, 30));

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

		JPanel pTituto = new JPanel();
		pTituto.setBackground(getForeground());
		pTituto.setLayout(new FlowLayout(FlowLayout.CENTER));
		pTituto.add(nombre);

		pVT.add(pVolver, BorderLayout.WEST);
		pVT.add(pTituto, BorderLayout.SOUTH);

		panelEncabezado.add(pVT, BorderLayout.CENTER);

		// panel de abajo

		panelAbajo.setBackground(getForeground());
		panelAbajo.setBorder(new LineBorder(new Color(0, 0, 0, 0), 30));

		JPanel iconos = new JPanel();
		iconos.setBackground(getForeground());
		iconos.setLayout(new GridLayout(1, 3, 10, 10));

		guardar.setText("Guardar cambios");
		guardar.setFont(new FontUIResource("Calibri", Font.PLAIN, 20));
		guardar.setForeground(Color.WHITE);
		guardar.setBackground(Color.BLACK);
		guardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!nombre.getText().isEmpty()) {
					
					c.agregarCarpeta(nombre.getText(), ruta);

					GUIListadoCarpetas carpetas = new GUIListadoCarpetas();
					carpetas.setVisible(true);
					carpetas.setResizable(false);
					carpetas.setLocationRelativeTo(null);
					setVisible(false);

				} else if (nombre.getText().isEmpty()) {

					UIManager.put("OptionPane.background", Color.white);
					UIManager.put("Panel.background", Color.white);

					JOptionPane.showMessageDialog(null, "No se han llenado los cambios requeridos", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		panelAbajo.add(guardar);

		// agrega todos los paneles al del fondo

		panelFondo.setLayout(new BorderLayout());
		panelFondo.add(panelEncabezado, BorderLayout.NORTH);
		panelFondo.add(panelAbajo, BorderLayout.SOUTH);

		return panelFondo;

	}

}
