package threes;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


import javax.swing.JTable;
import javax.swing.JScrollPane;

public class PantallaBienvenida {

    private JFrame frame;
    private JTextField IngresoNombreUsuario;

    private String nombre;

    private JuegoTres programa;
    private JTable tablaMejoresPuntajes;

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
		Bienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		Bienvenido.setBounds(96, 115, 673, 45);
		Bienvenido.setFont(new Font("Consolas", Font.PLAIN, 27));
		frame.getContentPane().add(Bienvenido);


		IngresoNombreUsuario = new JTextField();
		IngresoNombreUsuario.setBounds(192, 255, 503, 84);
		frame.getContentPane().add(IngresoNombreUsuario);
		IngresoNombreUsuario.setColumns(10);
		IngresoNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		IngresoNombreUsuario.setFont(new Font("Consolas", Font.BOLD, 24));
		
		JLabel ErrorNombreVacio = new JLabel();
		ErrorNombreVacio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		ErrorNombreVacio.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorNombreVacio.setBounds(316, 580, 559, 45);
		frame.getContentPane().add(ErrorNombreVacio);

		JButton BotonContinuar = new JButton("Continuar");
		BotonContinuar.setBounds(286, 418, 318, 64);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(902, 115, 347, 506);
		frame.getContentPane().add(scrollPane);
		
		tablaMejoresPuntajes = new JTable();
		scrollPane.setViewportView(tablaMejoresPuntajes);	
		DefaultTableModel modelo = new DefaultTableModel();
		tablaMejoresPuntajes.setModel(modelo);
		modelo.addColumn("Nombre");
		modelo.addColumn("Puntaje");
		tablaMejoresPuntajes.setRowHeight(35);
		tablaMejoresPuntajes.setFont(new Font("Consolas", Font.PLAIN, 18));
		tablaMejoresPuntajes.getTableHeader().setFont(new Font("Consolas", Font.BOLD, 18));
		tablaMejoresPuntajes.getTableHeader().setBackground(new java.awt.Color(220, 220, 220));
		tablaMejoresPuntajes.setShowGrid(false);
		cargarPuntajes(modelo);
		

	}
	
	private void cargarPuntajes(DefaultTableModel modelo) {
		List<String[]> listaPuntajes = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream("Puntuacion.txt");
			Scanner scanner = new Scanner(fis);
			
			while(scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				String [] datos = linea.split(",");
				if(datos.length == 2){
					datos[0] = datos[0].trim();
					datos[1] = datos[1].trim();
					listaPuntajes.add(datos);
				}
			}
			
			scanner.close();
			
		} catch(Exception e) {
			System.out.println("Error al leer: " + e.getMessage());
		};
		
		listaPuntajes.sort((a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])));
		
		for (int i = 0; i < Math.min(10, listaPuntajes.size()); i++) {
	        modelo.addRow(listaPuntajes.get(i));
	    }
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
