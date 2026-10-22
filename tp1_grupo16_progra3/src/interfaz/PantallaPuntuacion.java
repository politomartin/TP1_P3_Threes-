package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


import controlador.ControladorJuego;

public class PantallaPuntuacion {

	private ControladorJuego controlador;
	private JFrame frame;
	private String nombre;
	private String puntuacion;
	
	public PantallaPuntuacion(ControladorJuego controlador, String nombreUsuario, String puntuacion) {
		this.controlador = controlador;
		this.nombre = nombreUsuario;
		this.puntuacion = puntuacion;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Fin del juego :'(");
		frame.setBounds(650, 380, 1330, 760 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel nombre = new JLabel("Nombre: " + this.nombre);
		nombre.setHorizontalAlignment(SwingConstants.CENTER);
		nombre.setFont(new Font("Consolas", Font.PLAIN, 20));
		nombre.setBounds(452, 144, 396, 60);
		frame.getContentPane().add(nombre);
		
		JLabel puntuacion = new JLabel("Puntaje:" + this.puntuacion);
		puntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		puntuacion.setFont(new Font("Consolas", Font.PLAIN, 20));
		puntuacion.setBounds(452, 217, 396, 60);
		frame.getContentPane().add(puntuacion);
		
		JButton volverAJugar = new JButton("Volver a Jugar");
		volverAJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.iniciar();
				cerrar();
			}
		});
		volverAJugar.setFont(new Font("Tahoma", Font.BOLD, 20));
		volverAJugar.setBounds(200, 418, 267, 80);
		volverAJugar.setFocusPainted(false);
		frame.getContentPane().add(volverAJugar);
		
		JButton finDelJuego = new JButton("Cerrar");
		finDelJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}

		});
		finDelJuego.setFont(new Font("Tahoma", Font.BOLD, 20));
		finDelJuego.setBounds(847, 418, 267, 80);
		frame.getContentPane().add(finDelJuego);

	}

	private void cerrar() {
		frame.dispose();
	}
	
	public void mostrarVentana(boolean b) {
		frame.setVisible(b);
	}
}
