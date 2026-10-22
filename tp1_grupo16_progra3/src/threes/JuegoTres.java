package threes;

import controlador.ControladorJuego;

public class JuegoTres {

    public static void main(String[] args) {
    	
    	 try {
    		 javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
    	 }catch (Exception e) {}
    	
    	 ControladorJuego controlador = new ControladorJuego();
         controlador.iniciar();

    }
 
}