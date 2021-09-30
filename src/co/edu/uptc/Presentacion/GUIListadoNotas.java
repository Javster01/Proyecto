package co.edu.uptc.Presentacion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import co.edu.uptc.Control.Control;
import java.awt.event.*;

@SuppressWarnings("serial")
public class GUIListadoNotas extends JFrame {

	private JPanel panelFondo, panelEncabezado, panelAbajo, panelCentro;
	private JLabel labelTituloEncabezado;
	private JList<String> listaNotas;
	private JButton crearNuevaNota, editarNota, borrarNota; 
//	private JButton verNota;

	public GUIListadoNotas() {

		// inicializar componentes

		panelFondo = new JPanel();
		labelTituloEncabezado = new JLabel();
		listaNotas = new JList<String>();
		editarNota = new JButton(); 
		crearNuevaNota = new JButton();
		borrarNota = new JButton();

		// Configuracion del Frame

		ImageIcon im = new ImageIcon("RecursosGUI/notas1.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(im.getImage());
		setSize(360, 630);
		setTitle("Bloc De Notas");
		getContentPane().setBackground(Color.WHITE);

		// Adicionando componentes

		add(AreaTrabajo());

		setVisible(true);
	}

	private JPanel AreaTrabajo() {

		panelFondo.setBackground(getForeground());

		// panel del ecabezado

		panelEncabezado = new JPanel();
		panelEncabezado.setBackground(getForeground());

		labelTituloEncabezado.setText("Notas");
		labelTituloEncabezado.setFont(new FontUIResource("TimesRoman", Font.PLAIN, 50));
		panelEncabezado.add(labelTituloEncabezado);

		// panel del centro

		panelCentro = new JPanel();
		panelCentro.setBackground(getForeground());
		panelCentro.setBorder(new LineBorder(new Color(0, 0, 0, 0), 20));

		JPanel notasPanel = new JPanel();
		notasPanel.setPreferredSize(new DimensionUIResource(300, 320));
		notasPanel.setBackground(getForeground());
		notasPanel.setBorder(new LineBorder(Color.BLACK));

		Control c = new Control();

		listaNotas.setFont(new FontUIResource("Calibri", Font.PLAIN, 20));
		listaNotas.setBorder(new LineBorder(new Color(0, 0, 0, 0), 14));
		listaNotas.setListData(c.getNombresArchivos());
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
		barra.setForeground(getForeground());
		barra.setBackground(getForeground());
		notasPanel.add(barra);

		panelCentro.add(notasPanel);

		// panel de abajo

		panelAbajo = new JPanel();
		panelAbajo.setBackground(getForeground());
		panelAbajo.setBorder(new LineBorder(new Color(0, 0, 0, 0), 30));
		panelAbajo.setLayout(new BorderLayout(10, 10));

		JPanel iconos = new JPanel();
		iconos.setBackground(getForeground());
		iconos.setLayout(new GridLayout(1, 3, 10, 10));

		editarNota.setIcon(new ImageIcon("RecursosGUI/editar.png"));
		editarNota.setBackground(Color.WHITE);
		editarNota.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
		editarNota.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (listaNotas.getSelectedValue() != null) {

					GUIMostrarNota frame = new GUIMostrarNota(listaNotas.getSelectedValue());
					frame.setVisible(true);
					frame.setResizable(true);
					frame.setLocationRelativeTo(null);

					setVisible(false);

				} else if (listaNotas.getSelectedValue() == null) {

					JOptionPane.showMessageDialog(null, "No se ha seleccionado una nota para ver");
				}

			}
		});

		borrarNota.setIcon(new ImageIcon("RecursosGUI/eliminar.png"));
		borrarNota.setBackground(Color.WHITE);
		borrarNota.setBorder(new LineBorder(new Color(0, 0, 0, 0)));

		iconos.add(editarNota);
		iconos.add(borrarNota);

		crearNuevaNota.setText("Crear una nueva nota");
		crearNuevaNota.setFont(new FontUIResource("Calibri", Font.PLAIN, 20));
		crearNuevaNota.setForeground(Color.WHITE);
		crearNuevaNota.setBackground(Color.BLACK);
		crearNuevaNota.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				GUICrearNota frame = new GUICrearNota();
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

}
