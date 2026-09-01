package threes;

public class Mapa {

	private int mapa [][];
	
	public Mapa() {
		mapa = new int [4][4];
	}
	
	public int [][] getMapa() {
		return mapa;
	}
	
	public boolean posicionLibre(int fil, int col) {
		if (mapa[fil][col] == 0) {
			return true;
		}
		return false;
	};
	
	public boolean puedoAgregarHorizontal(int fil) {
		boolean resultado = false;
		for(int i = 0; i<mapa.length; i++) {
			if(posicionLibre(fil,i)) {
				resultado = true;
			}
		}
		return resultado;
	};
}
