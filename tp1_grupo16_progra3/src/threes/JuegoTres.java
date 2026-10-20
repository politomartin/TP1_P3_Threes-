package threes;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import interfaz.VentanaJuego;
import interfaz.PantallaBienvenida;
import interfaz.PantallaPuntuacion;

public class JuegoTres {

    private PantallaBienvenida pantallaBienvenida;
    private VentanaJuego juego;
    private PantallaPuntuacion pantallaPuntuacion;

    private String nombreUsuario;
    private String puntuacion;

    public static void main(String[] args) {
    	
    	 try {
    		 javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
    	 }catch (Exception e) {}
    	
    	nuevoJuego();

    }
    
    public static void nuevoJuego() {
        JuegoTres programa = new JuegoTres();

        programa.iniciar();
    	
    }
    

    public void iniciar() {

        pantallaBienvenida = new PantallaBienvenida(this);

        pantallaBienvenida.mostrarVentana(true);

    }

    public void iniciarJuego(String nombreUsuario) {

        this.nombreUsuario = nombreUsuario;

        pantallaBienvenida.mostrarVentana(false);
        pantallaBienvenida.cerrar();

        juego = new VentanaJuego(this);

        juego.mostrarVentana(true);

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
    	
    	juego.mostrarVentana(false);
    	juego.cerrar();
    	
    	pantallaPuntuacion = new PantallaPuntuacion(this, this.nombreUsuario, this.puntuacion);
    	
    	pantallaPuntuacion.mostrarVentana(true);
    }


}