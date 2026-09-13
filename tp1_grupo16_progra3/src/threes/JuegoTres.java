package threes;

public class JuegoTres {

    private PantallaBienvenida pantallaBienvenida;
    private MostrarJuego juego;
    private PantallaPuntuacion pantallaPuntuacion;

    private String nombreUsuario;
    private String puntuacion;

    public static void main(String[] args) {

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

        juego = new MostrarJuego(nombreUsuario);

        juego.mostrarVentana(true);

    }

}