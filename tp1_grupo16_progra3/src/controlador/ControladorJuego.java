package controlador;

import interfaz.VentanaJuego;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import interfaz.PantallaBienvenida;
import interfaz.PantallaPuntuacion;

public class ControladorJuego {

	private PantallaBienvenida pantallaBienvenida;
	private VentanaJuego ventanaJuego;
	private PantallaPuntuacion pantallaPuntuacion;
	private String nombreUsuario;
	private String puntuacion;
	
	public void iniciar() {
	    pantallaBienvenida = new PantallaBienvenida(this);
	    pantallaBienvenida.mostrarVentana(true);
	}

	
	public void iniciarJuego(String nombreUsuario) {

        this.nombreUsuario = nombreUsuario;

        pantallaBienvenida.mostrarVentana(false);
        pantallaBienvenida.cerrar();

        ventanaJuego = new VentanaJuego(this);

        ventanaJuego.mostrarVentana(true);

    }
	
	 private void guardarPuntaje(String nombre, int puntaje) {
	        try {
	        	FileOutputStream fos = new FileOutputStream("Puntuacion.txt", true);
	        	OutputStreamWriter out = new OutputStreamWriter(fos);
	        	
	        	out.write(nombre + "," + puntaje + "\r\n");
	        	out.close();
	        	
	        } catch (Exception e) {}
	    }
	    
	    public void terminarJuego(Integer puntuacion) {
	    	
	    	this.puntuacion = String.valueOf(puntuacion);
	    	guardarPuntaje(this.nombreUsuario, puntuacion);
	    	
	    	ventanaJuego.mostrarVentana(false);
	    	ventanaJuego.cerrar();
	    	
	    	pantallaPuntuacion = new PantallaPuntuacion(this, this.nombreUsuario, this.puntuacion);
	    	
	    	pantallaPuntuacion.mostrarVentana(true);
	    }
}
