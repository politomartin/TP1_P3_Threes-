package threes;
import java.util.Random; 

public class juego {
	
	private Mapa mapa = new Mapa();
	private int puntuacion;
	private int siguienteFicha;
	Random random = new Random();
	
	private void obtenerSiguienteFicha() {
		int num = random.nextInt(3)+1;
		this.siguienteFicha = num;
	};
	
	private void agregarFichaHorizontal(int fila) {
		int col = random.nextInt(4);
		if(mapa.puedoAgregarHorizontal(fila)) {
			
		};
	}
	
	private void agregarFichaVertical(int referencia) {
		
	}
	
}
