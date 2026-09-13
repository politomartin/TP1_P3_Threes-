package threes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPuntuacion {

	private JFrame frame;
	private JuegoTres programa;
	private String nombre;
	private String puntuacion;
	
	public PantallaPuntuacion(JuegoTres juegoTres, String nombreUsuario, String puntuacion) {
		this.programa = juegoTres;
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
		frame.setBounds(650, 380, 1300, 760 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Inombre = new JLabel("Nombre: " + this.nombre);
		Inombre.setFont(new Font("Consolas", Font.PLAIN, 20));
		Inombre.setBounds(469, 142, 259, 60);
		frame.getContentPane().add(Inombre);
		
		JLabel IPuntuacion = new JLabel("Puntaje:" + this.puntuacion);
		IPuntuacion.setFont(new Font("Consolas", Font.PLAIN, 20));
		IPuntuacion.setBounds(469, 205, 228, 60);
		frame.getContentPane().add(IPuntuacion);
		
		JButton IVolverAJugar = new JButton("Volver a Jugar");
		IVolverAJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JuegoTres.nuevoJuego();
				cerrar();
			}
		});
		IVolverAJugar.setFont(new Font("Tahoma", Font.BOLD, 20));
		IVolverAJugar.setBounds(200, 418, 253, 141);
		frame.getContentPane().add(IVolverAJugar);
		
		JButton IFinDelJuego = new JButton("Cerrar");
		IFinDelJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}

		});
		IFinDelJuego.setFont(new Font("Tahoma", Font.BOLD, 20));
		IFinDelJuego.setBounds(760, 418, 267, 141);
		frame.getContentPane().add(IFinDelJuego);
		
		
		
	}

	private void cerrar() {
		frame.dispose();
	}
	
	public void mostrarVentana(boolean b) {
		frame.setVisible(b);
	}
}
