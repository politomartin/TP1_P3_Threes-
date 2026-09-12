package threes;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MostrarJuego {

	private JFrame frame;

	public MostrarJuego(String nombreUsuario) {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void mostrarVentana(boolean b) {
		frame.setVisible(b);
	}

}
