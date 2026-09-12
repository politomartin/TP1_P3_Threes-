package threes;

public class JuegoTres {
	static String nombreUsuario;
	String puntuacion;
	
    public static void main(String[] args) {

        PantallaBienvenida pantallaBienvenida = new PantallaBienvenida();
        MostrarJuego juego = new MostrarJuego();
        juego.mostrarVentana(false);
        PantallaPuntuacion pantallaPuntuacion = new PantallaPuntuacion();
        pantallaPuntuacion.mostrarVentana(false);
        pantallaBienvenida.mostrarVentana(true);

        
        if(pantallaBienvenida.IniciarJuego()) {
        	juego.mostrarVentana(false);
        	nombreUsuario= pantallaBienvenida.nombrePasado();
        	juego.mostrarVentana(true);
        }
        
        
        //finalizaJuego
        //guardo la puntuacion
        //juego.mostrarVentana(false)
        //juego.cerrar()
        //muestroLasPuntuaciones
        pantallaPuntuacion.mostrarVentana(true);
        
    }

}