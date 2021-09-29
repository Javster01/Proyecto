package co.edu.uptc.Presentacion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.FontUIResource;

import java.awt.event.*;

public class GUIPrincipal extends JFrame implements ActionListener {

	
	private JPanel panelEncabezado;
	private JPanel panelAbajo;
	private JPanel panelCentro;
	private JLabel labelTituloEncabezado;
	private JList<String> listaNotas;
	private JButton crearNuevaNota, editarNota, borrarNota;

	public GUIPrincipal() {

		// inicializar componentes

		labelTituloEncabezado = new JLabel();
		listaNotas = new JList<String>();
		crearNuevaNota = new JButton();
		

		// Configuracion del Frame

		ImageIcon im = new ImageIcon("RecursosGUI/icono.png");
//		setLayout(null);
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
		JPanel panelFondo = new JPanel();
		panelFondo.setBackground(getForeground());
		
		panelEncabezado = new JPanel();
		panelEncabezado.setBackground(getForeground());
		
		labelTituloEncabezado.setText("Notas");
		labelTituloEncabezado.setFont(new FontUIResource("TimesRoman",Font.PLAIN, 50));
		panelEncabezado.add(labelTituloEncabezado);
		
		
		panelAbajo = new JPanel();
		panelAbajo.setBackground(getForeground());	
		panelAbajo.setBorder(new LineBorder(new Color(0,0,0,0),30));
		
		crearNuevaNota.setText("Crear una nueva nota");
		crearNuevaNota.setFont(new FontUIResource("Calibri",Font.PLAIN, 20));
		crearNuevaNota.setForeground(Color.WHITE);
		crearNuevaNota.setBackground(Color.BLACK);
		panelAbajo.add(crearNuevaNota);
		
		
		
		//Componentes panel centro
		
		panelCentro = new JPanel();
		panelCentro.setBackground(getForeground());	
		panelCentro.setBorder(new LineBorder(new Color(0,0,0,0),65));
		
		JPanel notasPanel = new JPanel();
		notasPanel.setPreferredSize(new DimensionUIResource(300, 110));
		notasPanel.setBackground(getForeground());	
		notasPanel.setBorder(new LineBorder(Color.BLACK));
		
		JPanel item = new JPanel();
		JLabel nombreNota = new JLabel("Notaprueba");
		item.setBackground(Color.WHITE);
		editarNota = new JButton();
		borrarNota = new JButton();
		editarNota.setIcon( new ImageIcon("RecursosGUI/editar.png"));
		editarNota.setBackground(Color.WHITE);
		borrarNota.setIcon( new ImageIcon("RecursosGUI/delete.png"));
		borrarNota.setBackground(Color.WHITE);

		nombreNota.setFont(new FontUIResource("Calibri",Font.PLAIN, 20));
		item.add(nombreNota);
		item.add(editarNota);
		item.add(borrarNota);
		
		notasPanel.add(item);

		
		JPanel item2 = new JPanel();
		JLabel nombreNota2 = new JLabel("Notaprueba2");
		item2.setBackground(Color.WHITE);
		editarNota = new JButton();
		borrarNota = new JButton();
		editarNota.setIcon( new ImageIcon("RecursosGUI/editar.png"));
		editarNota.setBackground(Color.WHITE);
		borrarNota.setIcon( new ImageIcon("RecursosGUI/delete.png"));
		borrarNota.setBackground(Color.WHITE);

		nombreNota2.setFont(new FontUIResource("Calibri",Font.PLAIN, 20));
		item2.add(nombreNota2);
		item2.add(editarNota);
		item2.add(borrarNota);
		
		notasPanel.add(item2);
		
		panelCentro.add(notasPanel);
		
		//Fin componentes panel centro
		
		
		
		
		
		
		
		panelFondo.setLayout(new BorderLayout());
		panelFondo.add(panelEncabezado, BorderLayout.NORTH);
		panelFondo.add(panelCentro, BorderLayout.CENTER);
		panelFondo.add(panelAbajo, BorderLayout.SOUTH);
		
		
		

//		panelPrincipal.setBounds(20, 80, 350, 470);
//		panelPrincipal.setBackground(getForeground());
//		panelPrincipal.setLayout(null);
//
//		labelTituloEncabezado.setText("Notas");
//		labelTituloEncabezado.setBounds(20, -10, 100, 20);
//
//		listaNotas.setBounds(20, 80, 350, 470);
//
//		editarNota.setText("editar");
//		editarNota.setBounds(125, 20, 100, 20);
//
//		borrarNota.setText("borrar");
//		borrarNota.setBounds(340, 20, 100, 20);
//
//		crearNuevanNota.setText("Crear una nota nueva");
//		crearNuevanNota.setBounds(20, 120, 100, 20);
//
//		panelPrincipal.add(labelTituloEncabezado);
//		panelPrincipal.add(listaNotas);
//		panelPrincipal.add(editarNota);
//		panelPrincipal.add(borrarNota);
//		panelPrincipal.add(crearNuevanNota);
//
////		BtnAceptar.addActionListener(this);
//
		return panelFondo;
//
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//
//		Object fuente = e.getSource();
//		if (fuente == BtnAceptar) {
//			regitroDatos();
//		} else {
//			JOptionPane.showMessageDialog(null, "Tenemos un inconveniente... por favorintente mas tarde");
//		}
//
//	}
//
//	private void regitroDatos() {
//
//		// Validar los campos falta
//		new Registro().infoPersona(TxtID.getText(), TxtNombre.getText(), Integer.parseInt(TxtEdad.getText()));
//
//		TxtEdad.setText("");
//		TxtID.setText("");
//		TxtNombre.setText("");
//
//		JOptionPane.showMessageDialog(null, "Registro almacenado");
//
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
