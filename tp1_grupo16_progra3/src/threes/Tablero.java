package threes;

public class Tablero {

	private Ficha[][] tablero;
	private int tamanioTablero;
	private int cantidadFilas;
    private int cantidadColumnas;
	
	
	public Tablero(int tamanioSeleccionado) {
		this.tamanioTablero = tamanioSeleccionado;
		this.tablero = new Ficha[tamanioSeleccionado][tamanioSeleccionado];
		
		for (int fila = 0; fila < tamanioSeleccionado; fila++) {
	        for (int columna = 0; columna < tamanioSeleccionado; columna++) {
	            tablero[fila][columna] = new Ficha();
	        }
	    }
		
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
	
	public Ficha obtenerFicha(int fila, int col) {
		if(filaInvalida(fila)) {
			throw new RuntimeException("fila fuera de rango");
		}
		
		if(columnaInvalida(col)) {
			throw new RuntimeException("col fuera de rango");
		}
		
		return tablero[fila][col];
	}
	
	public void setFicha(int fila, int columna, Ficha ficha) {
		if(filaInvalida(fila)) {
			throw new RuntimeException("fila fuera de rango");
		}
		
		if(columnaInvalida(columna)) {
			throw new RuntimeException("col fuera de rango");
		}
		
	    tablero[fila][columna] = ficha;
	}

	public void setValor(int fila, int col, int valor) {
		
		
		tablero[fila][col] = new Ficha(valor);
    }

	private boolean columnaInvalida(int columna) {
		return columna < 0 || columna >= this.tamanioTablero;
	}
	
	
	private boolean filaInvalida(int fila) {
		return fila < 0 || fila >= this.tamanioTablero;
	}
}
