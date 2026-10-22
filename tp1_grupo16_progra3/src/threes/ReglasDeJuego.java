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
		siguienteFicha = calcularSiguienteFicha();
		finDelJuego = false;
		colocarFichaInicial();
	}
	
	
	private void colocarFichaInicial() {
		int fila = random.nextInt(tablero.obtenerTamanio());
	    int col = random.nextInt(tablero.obtenerTamanio());
	    tablero.setValor(fila, col, siguienteFicha);
	
	}
	
	//
	//Apartado de agregado de fichas post movimiento
	//
	
	public Ficha obtenerFicha(int fila, int columna) {
	    return tablero.obtenerFicha(fila, columna);
	}
	
	public boolean posicionLibre(int fil, int col) {
		if (tablero.obtenerFicha(fil, col).getValor() == 0) {
			return true;
		}
		return false;
	}
	
	public boolean puedoAgregarFichaArribaOAbajo(int fila) {
		
		if(fila != 0 && fila != tablero.obtenerTamanio()-1) {
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
		
		if(col != 0 && col != tablero.obtenerTamanio()-1) {
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
		if(puedoAgregarFichaArribaOAbajo(fila)) {
			int col = random.nextInt(tablero.obtenerTamanio());
			boolean iterador = true;
			while(iterador) {
				if(posicionLibre(fila, col)) {
					tablero.setValor(fila, col, siguienteFicha);
					iterador = false;
				} else {
					col = random.nextInt(tablero.obtenerTamanio());					
				}
			}
		}
	}
	
	private void agregarFichaNuevaIzquierdaODerecha(int col) {
		if(puedoAgregarFichaIzquierdaODerecha(col)) {
			int fila = random.nextInt(tablero.obtenerTamanio());
			boolean iterador = true;
			while(iterador) {
				if(posicionLibre(fila, col)) {
					tablero.setValor(fila, col, siguienteFicha);
					iterador = false;
				} else {
					fila = random.nextInt(tablero.obtenerTamanio());					
				}
			}
		}
	}
	//
	//Apartado del moviento de la ficha 
	//
	
	public boolean mover(int fichaFila, int fichaColumna) {
		if(juegoTerminado()) {
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
            this.siguienteFicha = calcularSiguienteFicha();
            calcularPuntajeTotal();  //Modifique esta funcion, la anterior llamaba al getter de puntaje total
            setJuegoTerminado(calcularFinDelJuego());
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
		
		Ficha origen = obtenerFicha(filaOrigen, columnaOrigen);
		Ficha destino = obtenerFicha(filaDestino, columnaDestino);

		
		if(origen.esVacia()) {
            return false;
        }
		
		if(destino.esVacia()) {
			tablero.setFicha(filaDestino, columnaDestino, origen);
		    tablero.setFicha(filaOrigen, columnaOrigen, new Ficha());
		    return true;
		}
		if(origen.puedeCombinarCon(destino)) {
			
			Ficha fichaCombinada = origen.combinarCon(destino);

		    tablero.setFicha(filaDestino, columnaDestino, fichaCombinada);
		    tablero.setFicha(filaOrigen, columnaOrigen, new Ficha());

		    return true;
        }

        return false;
	}
	private void insertarEnBordeOpuesto(int deltaFila, int deltaCol) {
		if (deltaFila == -1 && deltaCol == 0) {   // se mueve para arriba dado que entra ficha nueva por ABAJO
	        int filaBorde = tablero.obtenerTamanio() - 1;
	        if (puedoAgregarFichaArribaOAbajo(filaBorde)) {
	            agregarFichaNuevaArribaYAbajo(filaBorde);
	        }
	    } else if (deltaFila == 1 && deltaCol == 0) {  // se mueve para abajo dado que entra ficha nueva por ARRIBA
	        int filaBorde = 0;
	        if (puedoAgregarFichaArribaOAbajo(filaBorde)) {
	            agregarFichaNuevaArribaYAbajo(filaBorde);
	        }
	    } else if (deltaFila == 0 && deltaCol == -1) { // se mueve para izquierda entra ficha nueva por la DERECHA
	        int colBorde = tablero.obtenerTamanio() - 1;
	        if (puedoAgregarFichaIzquierdaODerecha(colBorde)) {
	            agregarFichaNuevaIzquierdaODerecha(colBorde);
	        }
	    } else if (deltaFila == 0 && deltaCol == 1) { // se mueve para derecha entra ficha nueva por la IZQUIERDA
	        int colBorde = 0;
	        if (puedoAgregarFichaIzquierdaODerecha(colBorde)) {
	            agregarFichaNuevaIzquierdaODerecha(colBorde);
	        }
	    }
	}
	
	private boolean calcularFinDelJuego() {
	    return tableroLleno() && !existeFusionPosible();
	}

	private boolean tableroLleno() {
		for(int fil = 0; fil<tablero.obtenerTamanio();fil++) {
			for(int col = 0; col<tablero.obtenerTamanio(); col++) {
				if(posicionLibre(fil, col))
					return false;
			}
		}
		
		return true;
	}
	
	private boolean existeFusionPosible() {
		int tamanioTablero = tablero.obtenerTamanio();
		
		for(int fil = 0; fil<tamanioTablero;fil++) {
			for(int col = 0; col<tamanioTablero; col++) {
				Ficha actual = obtenerFicha(fil, col);
				
				boolean fusionAbajo =  fil < tamanioTablero - 1 && actual.puedeCombinarCon(obtenerFicha(fil + 1, col));
				boolean fusionDerecha = col < tamanioTablero - 1 && actual.puedeCombinarCon(obtenerFicha(fil, col + 1));
				
				if ( fusionAbajo || fusionDerecha)
						return true;
			}
		}
		return false;
	}
	//
	//Calcular puntaje al final del juego
	//
	
	public void calcularPuntajeTotal() {
		int total = 0;
		for(int f = 0; f<tablero.obtenerTamanio();f++) {
			for(int c = 0; c<tablero.obtenerTamanio(); c++) {
				total += calcularPuntajeFicha(tablero.obtenerFicha(f, c).getValor());
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
	
	public void setJuegoTerminado(boolean juegoTerminado) {
		finDelJuego = juegoTerminado;
	}	
	
	//Getters
	
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
