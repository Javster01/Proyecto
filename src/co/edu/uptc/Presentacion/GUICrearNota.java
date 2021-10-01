package co.edu.uptc.Presentacion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import co.edu.uptc.Control.Control;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GUICrearNota extends JFrame {

	private JPanel panelFondo, panelEncabezado, panelAbajo, panelCentro;
	private JTextField titulo;
	private JTextArea contenido;
	private JButton volver, guardar;

	public GUICrearNota(String ruta) {

		// inicializar componentes

		panelFondo = new JPanel();
		panelEncabezado = new JPanel();
		panelCentro = new JPanel();
		panelAbajo = new JPanel();
		titulo = new JTextField();
		contenido = new JTextArea();
		volver = new JButton();
		guardar = new JButton();
		// Configuracion del Frame

		ImageIcon im = new ImageIcon("RecursosGUI/notas1.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(im.getImage());
		setSize(360, 630);
		setTitle("Crear nota");
		getContentPane().setBackground(Color.WHITE);

		// Adicionando componentes

		add(AreaTrabajo(ruta));

		setVisible(true);
	}

	private JPanel AreaTrabajo(String ruta) {

		panelFondo.setBackground(getForeground());

		// panel del ecabezado

		panelEncabezado.setBackground(getForeground());
		panelEncabezado.setLayout(new BorderLayout());
		
		titulo.setFont(new FontUIResource("TimesRoman", Font.PLAIN, 30));
		titulo.setBorder(new LineBorder(Color.BLACK));
		titulo.setPreferredSize(new DimensionUIResource(300, 30));

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

		JPanel pTituto = new JPanel();
		pTituto.setBackground(getForeground());
		pTituto.setLayout(new FlowLayout(FlowLayout.CENTER));
		pTituto.add(titulo);

		pVT.add(pVolver, BorderLayout.WEST);
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

		contenido.setFont(new FontUIResource("Calibri", Font.PLAIN, 20));

		notasPanel.add(contenido);

		panelCentro.add(notasPanel);

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

				Control c = new Control(ruta);
				
				if (!contenido.getText().isEmpty() && !titulo.getText().isEmpty()) {
					
					
					c.agregarNota(titulo.getText(), contenido.getText(), "Notas", 0);

					GUIListadoNotas notas = new GUIListadoNotas(ruta);
					notas.setVisible(true);
					notas.setResizable(false);
					notas.setLocationRelativeTo(null);
					setVisible(false);

				} else if (contenido.getText().isEmpty() || titulo.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "No se han hecho los cambios necesarios en al nota");
				}
			}
		});

		panelAbajo.add(guardar);

		// agrega todos los paneles al del fondo

		panelFondo.setLayout(new BorderLayout());
		panelFondo.add(panelEncabezado, BorderLayout.NORTH);
		panelFondo.add(panelCentro, BorderLayout.CENTER);
		panelFondo.add(panelAbajo, BorderLayout.SOUTH);

		return panelFondo;

	}

}
