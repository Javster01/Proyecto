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
	private JPanel notasPanel;
	private JLabel labelTituloEncabezado;
	private JList<String> listaNotas;
	private JButton crearNuevaNota, editarNota, borrarNota;
	private String ruta;
//	private JButton verNota;

	public GUIListadoNotas(String ruta) {

		// inicializar componentes

		panelFondo = new JPanel();
		labelTituloEncabezado = new JLabel();
		listaNotas = new JList<String>();
		editarNota = new JButton();
		crearNuevaNota = new JButton();
		borrarNota = new JButton();
		notasPanel = new JPanel();
		this.ruta = ruta;

		// Configuracion del Frame

		ImageIcon im = new ImageIcon("RecursosGUI/notas1.png");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(im.getImage());
		setSize(360, 630);
		setTitle("Bloc De Notas");
		getContentPane().setBackground(Color.WHITE);

		// Adicionando componentes

		add(AreaTrabajo(ruta));

		setVisible(true);
	}

	private JPanel AreaTrabajo(String ruta) {

		panelFondo.setBackground(Color.WHITE);

		// panel del ecabezado

		panelEncabezado = new JPanel();
		panelEncabezado.setBackground(Color.WHITE);

		labelTituloEncabezado.setText("Notas");
		labelTituloEncabezado.setFont(new FontUIResource("TimesRoman", Font.PLAIN, 50));
		panelEncabezado.add(labelTituloEncabezado);

		// panel del centro

		panelCentro = new JPanel();
		panelCentro.setBackground(Color.WHITE);
		panelCentro.setBorder(new LineBorder(new Color(0, 0, 0, 0), 20));

		notasPanel.setPreferredSize(new DimensionUIResource(300, 320));
		notasPanel.setBackground(Color.WHITE);
		notasPanel.setBorder(new LineBorder(Color.BLACK));

		Control c = new Control(ruta);

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

					GUIMostrarNota frame = new GUIMostrarNota(listaNotas.getSelectedValue(), ruta);
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

				Control c = new Control(ruta);

				if (listaNotas.getSelectedValue() == null) {

					UIManager.put("OptionPane.background", Color.white);
					UIManager.put("Panel.background", Color.white);

					JOptionPane.showMessageDialog(null, "No se ha seleccionado una nota para eliminar", "Advertencia",
							JOptionPane.WARNING_MESSAGE);

				} else {

					c.eliminarNota(c.buscarNota(listaNotas.getSelectedValue()), ruta);

					refrescar();
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

				GUICrearNota frame = new GUICrearNota(ruta);
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

	public void actualizarPantalla() {

		getContentPane().removeAll();
		repaint();
	}

	private void refrescar() {

		panelFondo = new JPanel();
		labelTituloEncabezado = new JLabel();
		listaNotas = new JList<String>();
		editarNota = new JButton();
		crearNuevaNota = new JButton();
		borrarNota = new JButton();
		notasPanel = new JPanel();

		actualizarPantalla();
		add(AreaTrabajo(ruta));
		setVisible(false);
		setVisible(true);

	}

}
