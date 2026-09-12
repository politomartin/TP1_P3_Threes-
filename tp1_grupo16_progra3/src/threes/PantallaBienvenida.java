package threes;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class PantallaBienvenida {

	private JFrame frame;
	private JTextField nombreUsuario;
	private String nombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaBienvenida window = new PantallaBienvenida();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaBienvenida() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel Bienvenido = new JLabel("Bienvenido! Ingrese su nombre para iniciar");
		springLayout.putConstraint(SpringLayout.NORTH, Bienvenido, 24, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, Bienvenido, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(Bienvenido);
		
		nombreUsuario = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, nombreUsuario, 31, SpringLayout.SOUTH, Bienvenido);
		springLayout.putConstraint(SpringLayout.WEST, nombreUsuario, 103, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(nombreUsuario);
		nombreUsuario.setColumns(10);
		
		JButton botonContinuar = new JButton("continuar");
		springLayout.putConstraint(SpringLayout.NORTH, botonContinuar, 34, SpringLayout.SOUTH, nombreUsuario);
		springLayout.putConstraint(SpringLayout.WEST, botonContinuar, 139, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(botonContinuar);
	}

	public void mostrarVentana(boolean b) {
	    frame.setVisible(b);
	}

	public void cerrar() {
		frame.dispose();
	}

	public boolean IniciarJuego() {
		// TODO Auto-generated method stub
		return false;
	}

	public String nombrePasado() {
		return nombre;
	}
}
