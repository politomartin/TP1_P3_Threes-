package threes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class PantallaBienvenida {

    private JFrame frame;
    private JTextField IngresoNombreUsuario;

    private String nombre;

    private JuegoTres programa;

    public PantallaBienvenida(JuegoTres programa) {

        this.programa = programa;

        initialize();

    }


	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Bienvenido a Tres");
		frame.setBounds(650, 350, 1330, 760);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel Bienvenido = new JLabel("Bienvenido! Ingrese su nombre para iniciar");
		Bienvenido.setBounds(424, 160, 406, 26);
		Bienvenido.setFont(new Font("Tahoma", Font.PLAIN, 21));
		frame.getContentPane().add(Bienvenido);


		IngresoNombreUsuario = new JTextField();
		IngresoNombreUsuario.setBounds(316, 270, 625, 84);
		frame.getContentPane().add(IngresoNombreUsuario);
		IngresoNombreUsuario.setColumns(10);
		
		JLabel ErrorNombreVacio = new JLabel();
		ErrorNombreVacio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		ErrorNombreVacio.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorNombreVacio.setBounds(316, 580, 559, 45);
		frame.getContentPane().add(ErrorNombreVacio);

		JButton BotonContinuar = new JButton("continuar");
		BotonContinuar.setBounds(462, 451, 318, 64);
		BotonContinuar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {

		        String nombreIngresado =
		                IngresoNombreUsuario.getText();

		        if (!nombreIngresado.trim().isEmpty()) {

		            nombre = nombreIngresado;

		            programa.iniciarJuego(nombre);

		        } else {

		            ErrorNombreVacio.setText(
		                "El nombre no puede ser Vacio"
		            );

		        }
		    }
		});
		
		BotonContinuar.setFont(new Font("Consolas", Font.BOLD, 34));
		frame.getContentPane().add(BotonContinuar);
		

	}

	public void mostrarVentana(boolean b) {
		frame.setVisible(b);
	}

	public void cerrar() {
		frame.dispose();
	}


	public String nombrePasado() {
		return nombre;
	}
}
