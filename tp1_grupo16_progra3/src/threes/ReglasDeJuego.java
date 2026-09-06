package threes;
import java.util.Random; 

public class ReglasDeJuego {
	
	private Tablero tablero;
	private int puntajeFinal;
	private int siguienteFicha;
	private boolean finDelJuego;
	private Random random;
	
	public ReglasDeJuego() {
		tablero = new Tablero(4);
		random = new Random();
		puntajeFinal = 0;
		siguienteFicha = obtenerSiguienteFicha();
		finDelJuego = false;
	}
	
	
	//
	//Apartado de agregado de fichas post movimiento
	//
	
	public boolean posicionLibre(int fil, int col) {
		if (tablero.obtenerFicha(fil, col) == 0) {
			return true;
		}
		return false;
	}
	
	public boolean puedoAgregarFichaArribaOAbajo(int fila) {
		
		if(fila != 0 || fila != tablero.obtenerTamanio()-1) {
			throw new IllegalArgumentException("La fila debe ser la superior o inferior");
		}
		
		boolean resultado = false;
		for(int i = 0; i<tablero.obtenerTamanio(); i++) {
			if(posicionLibre(fila,i)) {
				resultado = true;
			}
		}
		return resultado;
	}
	
	
	public boolean puedoAgregarFichaIzquierdaODerecha(int col) {
		
		if(col != 0 || col != tablero.obtenerTamanio()-1) {
			throw new IllegalArgumentException("La columna debe ser la que se encuentra mas a la izquierda o mas a la derecha");
		}
		
		boolean resultado = false;
		for(int i = 0; i<tablero.obtenerTamanio(); i++) {
			if(posicionLibre(i,col)) {
				resultado = true;
			}
		}
		return resultado;
	}
	
	
	private void agregarFichaNuevaArribaYAbajo(int fila) {
		//Al momento en desarrollo
	}
	
	private void agregarFichaNuevaIzquierdaODerecha(int referencia) {
		//Al momento en desarrollo		
	}
	//
	//Apartado del moviento de la ficha 
	//
	
	public boolean mover(int fichaFila, int fichaColumna) {
		if(tablero.juegoTerminado()) {
			return false;
		}
		boolean huboMovimiento = false;
		
		if (fichaFila == -1 && fichaColumna == 0) {
            huboMovimiento = moverArriba();
        } else if (fichaFila == 1 && fichaColumna == 0) {
            huboMovimiento = moverAbajo();
        } else if (fichaFila == 0 && fichaColumna == -1) {
            huboMovimiento = moverIzquierda();
        } else if (fichaFila == 0 && fichaColumna == 1) {
            huboMovimiento = moverDerecha();
        }

        if (huboMovimiento) {
            insertarEnBordeOpuesto(fichaFila, fichaColumna);
            obtenerSiguienteFicha();
            calcularPuntajeTotal();
            juegoTerminado();
        }

        return huboMovimiento;
	}

	private boolean moverArriba() {
		boolean movido = false;
        int filas = tablero.obtenerFilas();
        int cols = tablero.obtenerColumnas();

        for (int c = 0; c < cols; c++) {
            for (int f = 0; f < filas - 1; f++) {
                if (intentarDesplazarOFusionar(f, c, f + 1, c)) {
                    movido = true;
                }
            }
        }
        return movido;
	}
	
	
	public boolean moverAbajo() {
        boolean movido = false;
        int filas = tablero.obtenerFilas();
        int cols = tablero.obtenerColumnas();

        for (int c = 0; c < cols; c++) {
            for (int f = filas - 1; f > 0; f--) {
                if (intentarDesplazarOFusionar(f, c, f - 1, c)) {
                    movido = true;
                }
            }
        }
        return movido;
    }
	
	public boolean moverIzquierda() {
        boolean movido = false;
        int filas = tablero.obtenerFilas();
        int cols = tablero.obtenerColumnas();

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < cols - 1; c++) {
                if (intentarDesplazarOFusionar(f, c, f, c + 1)) {
                    movido = true;
                }
            }
        }
        return movido;
    }

    public boolean moverDerecha() {
        boolean movido = false;
        int filas = tablero.obtenerFilas();
        int cols = tablero.obtenerColumnas();

        for (int f = 0; f < filas; f++) {
            for (int c = cols - 1; c > 0; c--) {
                if (intentarDesplazarOFusionar(f, c, f, c - 1)) {
                    movido = true;
                }
            }
        }
        return movido;
    }


	private boolean intentarDesplazarOFusionar(int filaDestino, int columnaDestino, int filaOrigen, int columnaOrigen) {
		int valorOrigen = tablero.obtenerFicha(filaOrigen, columnaOrigen);
		int valorDestino = tablero.obtenerFicha(filaDestino, columnaDestino);
		
		if(valorOrigen == 0) {
            return false;
        }
		
		if(valorDestino == 0) {
			tablero.setValor(filaDestino, columnaDestino, valorOrigen);
			tablero.setValor(filaOrigen, columnaOrigen, 0);
            return true;
		}
		if(puedenFusionarse(valorOrigen, valorDestino)) {
			tablero.setValor(filaDestino, columnaDestino, valorOrigen + valorDestino);
            tablero.setValor(filaOrigen, columnaOrigen, 0);
            return true;
        }

        return false;
	}
	private void insertarEnBordeOpuesto(int deltaFila, int deltaCol) {
	   
	}
	
	
	//
	//Apartado combinar Fichas
	//
	public boolean puedenFusionarse(int ficha1, int ficha2) {
		if(ficha1 == 0 || ficha2 == 0) {
			return false;
		}
		
		if(sonFichas1y2(ficha1, ficha2)) {
			return true;
		}
		
		return sonFichasMayorQue3Iguales(ficha1, ficha2);
	}
	
	//Revisar con test si funciona correctamente
	public int combinarFichas(int ficha1, int ficha2) {
		
		
		if(sonFichas1y2(ficha1, ficha2)) {
			return ficha1 + ficha2;
		}
		
		if((ficha1 >= 3 && ficha2 == ficha1) ||
			(ficha1 == ficha2 && ficha2 >= 3)) {
			return ficha1*2;
		}
		
		return 0;
	}
	
	private boolean sonFichas1y2(int ficha1, int ficha2) {
		return ((ficha1 == 1 && ficha2 == 2) || (ficha1 == 2 && ficha2 == 1));
	}
	
	private boolean sonFichasMayorQue3Iguales(int ficha1, int ficha2) {
		return ficha1 >= 3 && ficha1 == ficha2;
	}
	
	//
	//Calcular puntaje al final del juego
	//
	
	public void calcularPuntajeTotal() {
		int total = 0;
		for(int f = 0; f<tablero.obtenerTamanio();f++) {
			for(int c = 0; c<tablero.obtenerTamanio(); c++) {
				total += calcularPuntajeFicha(tablero.obtenerFicha(f, c));
			}
		}
		puntajeFinal = total;
	}
	
	private int calcularPuntajeFicha(int valor) {
		if(valor < 3) {
			return 0;
		}
		
		int exponente = 0;
		int contador = valor/3;
		while (contador >1) {
			exponente ++;
			contador /= 2;
		}
		
		return (int) Math.pow(3, exponente+1);
	};
	
	//
	//Otros
	//
	
	private int calcularSiguienteFicha() {
		return random.nextInt(3)+1;
	}
	
	//Getters
	
	public Tablero obtenerMapa() {
		return tablero;
	}
	
	public int obtenerPuntaje() {
		return puntajeFinal;
	}
	
	public int obtenerSiguienteFicha() {
		return siguienteFicha;
	}
	
	public boolean juegoTerminado() {
		return finDelJuego;
	}
}
