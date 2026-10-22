package interfaz;

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


import controlador.ControladorJuego;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class PantallaBienvenida {
	
	private ControladorJuego controlador;
    private JFrame frame;
    private JTextField ingresoNombreUsuario;

    private String nombre;

  
    private JTable tablaMejoresPuntajes;

    public PantallaBienvenida(ControladorJuego controlador) {

        this.controlador = controlador;

        initialize();

    }


	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Bienvenido a Tres");
		frame.setBounds(650, 350, 1330, 760);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel bienvenido = new JLabel("Bienvenido! Ingrese su nombre para iniciar");
		bienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		bienvenido.setBounds(96, 115, 673, 45);
		bienvenido.setFont(new Font("Consolas", Font.PLAIN, 27));
		frame.getContentPane().add(bienvenido);


		ingresoNombreUsuario = new JTextField();
		ingresoNombreUsuario.setBounds(192, 255, 503, 84);
		frame.getContentPane().add(ingresoNombreUsuario);
		ingresoNombreUsuario.setColumns(10);
		ingresoNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		ingresoNombreUsuario.setFont(new Font("Consolas", Font.BOLD, 24));
		
		JLabel errorNombreVacio = new JLabel();
		errorNombreVacio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 26));
		errorNombreVacio.setHorizontalAlignment(SwingConstants.CENTER);
		errorNombreVacio.setBounds(316, 580, 559, 45);
		frame.getContentPane().add(errorNombreVacio);

		JButton botonContinuar = new JButton("Continuar");
		botonContinuar.setBounds(286, 418, 318, 64);
		botonContinuar.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {

		        String nombreIngresado =
		                ingresoNombreUsuario.getText();

		        if (!nombreIngresado.trim().isEmpty()) {

		            nombre = nombreIngresado;

		            controlador.iniciarJuego(nombre);

		        } else {

		            errorNombreVacio.setText(
		                "El nombre no puede ser Vacio"
		            );

		        }
		    }
		});
		
		botonContinuar.setFont(new Font("Consolas", Font.BOLD, 34));
		frame.getContentPane().add(botonContinuar);
		
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
