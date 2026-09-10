package threes;

public class Tablero {

	private int[][] tablero;
	private int tamanioTablero;
	
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
		if(filaInvalida(fila)) {
			throw new RuntimeException("fila fuera de rango");
		}
		
		if(columnaInvalida(col)) {
			throw new RuntimeException("col fuera de rango");
		}
		
		return tablero[fila][col];
	}
	


	public void setValor(int fila, int col, int valor) {
		if(filaInvalida(fila)) {
			throw new RuntimeException("fila fuera de rango");
		}
		
		if(columnaInvalida(col)) {
			throw new RuntimeException("col fuera de rango");
		}
		
		tablero[fila][col] = valor;
    }

	private boolean columnaInvalida(int columna) {
		return columna < 0 || columna >= this.tamanioTablero;
	}
	
	
	private boolean filaInvalida(int fila) {
		return fila < 0 || fila >= this.tamanioTablero;
	}
}
