package threes;

public class Tablero {

	private int[][] tablero;
	private int tamanioTablero;
	
	public Tablero(int tamanioSeleccionado) {
		this.tablero = new int[tamanioSeleccionado][tamanioSeleccionado];
		this.tamanioTablero = tamanioSeleccionado;
	}
	
		
	public int obtenerTamanio() {
		return tamanioTablero;
	}
	
	public int obtenerFicha(int fila, int col) {
		return tablero[fila][col];
	}
	

	

	
	
	
	//Getters
	
}
