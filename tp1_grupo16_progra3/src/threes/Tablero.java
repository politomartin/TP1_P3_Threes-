package threes;

public class Tablero {

	private int[][] tablero;
	private int tamanioTablero;
	private boolean juegoTerminado;
	
	public Tablero(int tamanioSeleccionado) {
		this.tablero = new int[tamanioSeleccionado][tamanioSeleccionado];
		this.tamanioTablero = tamanioSeleccionado;
	}
	
	
	public int obtenerFilas() {
		return tablero.length;
	}
	
	public int obtenerColumnas() {
		return tablero[0].length;
	}
		
	public int obtenerTamanio() {
		return tamanioTablero;
	}
	
	public int obtenerFicha(int fila, int col) {
		return tablero[fila][col];
	}
	public void setValor(int fila, int col, int valor) {
        tablero[fila][col] = valor;
    }
	
	public boolean juegoTerminado() {
		return juegoTerminado;
	}
	
	public void setJuegoTerminado(boolean juegoTerminado) {
        this.juegoTerminado = juegoTerminado;
    }
	
	

	

	
	
	
	//Getters
	
}
