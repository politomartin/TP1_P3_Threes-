package threes;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class MostrarJuego {
	
	private JuegoTres programa;

	private JFrame frame;

	private JPanel panelTablero;

	private JLabel[][] casillas;
	
	private JLabel Ipuntuacion;

	private ReglasDeJuego reglas;
	
	private JLabel ISiguienteFicha;

	private int puntuacion;

	public MostrarJuego(JuegoTres juegoTres) {

		this.programa = juegoTres;

		reglas = new ReglasDeJuego();

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Tres");
		frame.setBounds(650, 350, 1330, 760);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		crearTablero();
		reglas.obtenerMapa().setValor(0, 0, 1);
		reglas.obtenerMapa().setValor(1, 2, 2);
		reglas.obtenerMapa().setValor(3, 3, 3);
		configurarTeclado();
		actualizarTablero();

	}



	private void crearTablero() {
		panelTablero = new JPanel();
		panelTablero.setBackground(new Color(128, 255, 255));
		panelTablero.setSize(600, 600);
		panelTablero.setLocation(298, 66);
		panelTablero.setLayout(new GridLayout(4, 4, 5, 5));
		casillas = new JLabel[4][4];

		for (int fila = 0; fila < 4; fila++) {
			for (int col = 0; col < 4; col++) {
				JLabel casilla = new JLabel();
				casilla.setHorizontalAlignment(SwingConstants.CENTER);
				casilla.setFont(new Font("Arial", Font.BOLD, 24));
				casilla.setOpaque(true);
				casilla.setBackground(Color.LIGHT_GRAY);
				casilla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				casillas[fila][col] = casilla;
				panelTablero.add(casilla);
			}
			;
		}
		frame.getContentPane().add(panelTablero, BorderLayout.CENTER);
		
		Ipuntuacion = new JLabel("Puntuacion :" + this.puntuacion);
		Ipuntuacion.setBounds(947, 23, 196, 43);
		frame.getContentPane().add(Ipuntuacion);
		
		ISiguienteFicha = new JLabel("Proxima:" + reglas.obtenerSiguienteFicha());
		ISiguienteFicha.setBounds(549, 23, 163, 32);
		frame.getContentPane().add(ISiguienteFicha);
	}

	private void actualizarTablero() {
		Tablero tablero = reglas.obtenerMapa();
		for (int fila= 0; fila < 4; fila ++) {
			for(int col= 0; col <4; col++) {
				int valor = tablero.obtenerFicha(fila,col);
				if(valor == 0) {
					casillas[fila][col].setText("");
				}else {
					casillas[fila][col].setText(String.valueOf(valor));
					
				}
				
			}
		}
		ISiguienteFicha.setText("Proxima: " + reglas.obtenerSiguienteFicha());
		this.puntuacion = reglas.obtenerPuntaje();
		Ipuntuacion.setText("Puntuacion :" + this.puntuacion);
	}

    private void configurarTeclado() {

        JRootPane panel = frame.getRootPane();

        panel.getInputMap(
                javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW
        ).put(KeyStroke.getKeyStroke("UP"), "moverArriba");

        panel.getActionMap().put("moverArriba",
                new javax.swing.AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        realizarMovimiento(-1, 0);

                    }

                });

        panel.getInputMap(
                javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW
        ).put(KeyStroke.getKeyStroke("DOWN"), "moverAbajo");

        panel.getActionMap().put("moverAbajo",
                new javax.swing.AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        realizarMovimiento(1, 0);

                    }

                });

        panel.getInputMap(
                javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW
        ).put(KeyStroke.getKeyStroke("LEFT"), "moverIzquierda");

        panel.getActionMap().put("moverIzquierda",
                new javax.swing.AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        realizarMovimiento(0, -1);

                    }

                });

        panel.getInputMap(
                javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW
        ).put(KeyStroke.getKeyStroke("RIGHT"), "moverDerecha");

        panel.getActionMap().put("moverDerecha",
                new javax.swing.AbstractAction() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        realizarMovimiento(0, 1);

                    }

                });

    }
    
    private void realizarMovimiento(int fila, int columna) {

        boolean seMovio = reglas.mover(fila, columna);

        if (seMovio) {
        	

            actualizarTablero();

        }

        if (reglas.juegoTerminado()) {

        	programa.terminarJuego(reglas.obtenerPuntaje());

        }

    }
    
    
    
    
	public void mostrarVentana(boolean b) {
		frame.setVisible(b);
	}
	
	public void cerrar() {
		frame.dispose();
	}
}
